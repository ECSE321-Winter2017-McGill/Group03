package ca.mcgill.ecse321.TAMAS.controller;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ca.mcgill.ecse321.TAMAS.controller.InvalidInputException;
import ca.mcgill.ecse321.TAMAS.model.Allocation;
import ca.mcgill.ecse321.TAMAS.model.Allocation.AllocationStatus;
import ca.mcgill.ecse321.TAMAS.model.Applicant;
import ca.mcgill.ecse321.TAMAS.model.Application;
import ca.mcgill.ecse321.TAMAS.model.Application.Status;
import ca.mcgill.ecse321.TAMAS.model.Course;
import ca.mcgill.ecse321.TAMAS.model.Department;
import ca.mcgill.ecse321.TAMAS.model.Instructor;
import ca.mcgill.ecse321.TAMAS.model.JobPosting;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;
import ca.mcgill.ecse321.TAMAS.persistence.PersistenceXStream;

public class TamasController {

	private ManagementSystem ms = new ManagementSystem();

	public static void changeAllocationStatus(ManagementSystem mm, Object user, Course course)
			throws InvalidInputException {

		String userRole = user.getClass().toString();

		if (course.hasCourseJobAllocation()) {
			Allocation allo = course.getCourseJobAllocation();
			if (allo == null || allo.getApplicants().size() == 0) {
				throw new InvalidInputException("Course has no assigned Allocaiton");
			} else {
				if (userRole.equals(Instructor.class) && allo.getAllocationStatus().equals(AllocationStatus.INITIAL)) {
					allo.setAllocationStatus(AllocationStatus.INSTRUCTOR_APPROVED);
				} else if (userRole.equals(Department.class)
						&& allo.getAllocationStatus().equals(AllocationStatus.INSTRUCTOR_APPROVED)) {
					allo.setAllocationStatus(AllocationStatus.FINAL_APPROVAL);
				}
			}
		}
		PersistenceXStream.saveToXMLwithXStream(mm);

	}

	public void createAllocation(ManagementSystem mm, Course course, ArrayList<Applicant> tas,
			ArrayList<Integer> taHours, ArrayList<Applicant> graders, ArrayList<Integer> graderHours)
			throws InvalidInputException {

		if (course == null) {
			throw new InvalidInputException("Can not change allocation due to null course. ");
		}
		if (tas == null) {
			throw new InvalidInputException("Can not change allocation due to Empty list of potential TAs. ");
		}
		if (taHours == null || taHours.size() != tas.size()) {
			throw new InvalidInputException("Please assign working hours to all selected applicants. ");
		}
		if (graders == null) {
			throw new InvalidInputException("Can not change allocation due to Empty list of potential Graders. ");
		}
		if (graderHours == null || graderHours.size() != graders.size()) {
			throw new InvalidInputException("Please assign working hours to all selected applicants. ");
		}
		try {
			Allocation allocation = new Allocation(course);

			double taBudget = 0;
			double graderBudget = 0;

			for (int i = 0; i < tas.size(); i++) {
				if ((180 - tas.get(i).getTotalAppointmentHours()) < taHours.get(i).intValue()) {
					allocation.delete();
					throw new InvalidInputException("Applicant " + tas.get(i).getName() + " can only have "
							+ (180 - tas.get(i).getTotalAppointmentHours()) + " appointment hours");
				}
				allocation.addApplicant(tas.get(i));
				int newTotalHours = tas.get(i).getTotalAppointmentHours() + taHours.get(i);
				tas.get(i).setTotalAppointmentHours(newTotalHours);

				List<Application> allApplications = tas.get(i).getApplications();
				for (Application anApp : allApplications) {
					Course thisCourse = anApp.getJobPosting().getCourse();
					String jobTitle = anApp.getJobPosting().getJobTitle();
					if (thisCourse.getSemester().equals(course.getSemester())
							&& thisCourse.getCourseCode().equals(course.getCourseCode()) && jobTitle.equals("TA")) {
						anApp.setStatus(Status.SELECTED);
						taBudget = taBudget + anApp.getJobPosting().getHourRate();
					}
				}
			}

			for (int j = 0; j < graders.size(); j++) {
				allocation.addApplicant(graders.get(j));

				List<Application> allApplications = graders.get(j).getApplications();
				for (Application anApp : allApplications) {
					Course thisCourse = anApp.getJobPosting().getCourse();
					String jobTitle = anApp.getJobPosting().getJobTitle();
					if (thisCourse.getSemester().equals(course.getSemester())
							&& thisCourse.getCourseCode().equals(course.getCourseCode()) && jobTitle.equals("Grader")) {
						anApp.setStatus(Status.SELECTED);
						graderBudget = graderBudget + anApp.getJobPosting().getHourRate();
					}
				}
			}

			if ((taBudget + graderBudget) > course.getBudgetCalculated()) {
				allocation.delete();
				throw new InvalidInputException("This allocation exceeds the budget!");
			}

			for (int i = 0; i < tas.size(); i++) {
				List<Application> allApplications = tas.get(i).getApplications();
				for (Application anApp : allApplications) {
					Course thisCourse = anApp.getJobPosting().getCourse();
					String jobTitle = anApp.getJobPosting().getJobTitle();
					if (thisCourse.getSemester().equals(allocation.getCourse().getSemester())
							&& thisCourse.getCourseCode().equals(allocation.getCourse().getCourseCode())
							&& jobTitle.equals("TA")) {
						anApp.setHour(taHours.get(i));
					}
				}
			}

			for (int j = 0; j < graders.size(); j++) {
				List<Application> allApplications = graders.get(j).getApplications();
				for (Application anApp : allApplications) {
					Course thisCourse = anApp.getJobPosting().getCourse();
					String jobTitle = anApp.getJobPosting().getJobTitle();
					if (thisCourse.getSemester().equals(allocation.getCourse().getSemester())
							&& thisCourse.getCourseCode().equals(allocation.getCourse().getCourseCode())
							&& jobTitle.equals("Grader")) {
						anApp.setHour(graderHours.get(j));
					}
				}
			}

			allocation.setAllocationStatus(AllocationStatus.INITIAL);
		} catch (Exception e) {
			e.printStackTrace();
			throw new InvalidInputException(e.getMessage());
		}
		PersistenceXStream.saveToXMLwithXStream(mm);
	}

	public double getRemainingBudget(Course course) {
		double reBud = 0;

		for (JobPosting jp : course.getJobPosting()) {
			for (Application apn : jp.getApplications()) {
				boolean ispartBudget = apn.getStatus().equals(Status.SELECTED)
						|| apn.getStatus().equals(Status.OFFER_ACCEPTED);
				if (ispartBudget) {
					reBud *= jp.getHourRate();
				}
			}
		}

		return course.getBudgetCalculated() - reBud;

	}

	public static Date getToday() {

		Calendar c = Calendar.getInstance();

		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		return c.getTime();
	}

	public static Date getDefaultDeadLine() {
		Calendar c = Calendar.getInstance();
		// default deadline
		c.set(Calendar.YEAR, 2017);
		c.set(Calendar.MONTH, 8);
		c.set(Calendar.DAY_OF_MONTH, 6);

		return c.getTime();
	}

	public static boolean isDeadlinePassed() {

		return isDeadlinePassed(getDefaultDeadLine());
	}

	public static boolean isDeadlinePassed(Date deadline) {

		if (deadline.before(getToday())) {
			return true;
		} else {
			return false;
		}

	}

	public TamasController(ManagementSystem ms) {
		this.ms = ms;
	}

	public void createTAEval(Applicant ta, String header, String eval) throws InvalidInputException {
		String error = "";

		if (eval.trim().length() == 0) {
			error += "Can not submit an empty Evaluation ";
		}

		try {
			String formatedEval = header + ":\n" + eval;
			ta.setEvaluation(formatedEval);
		} catch (Exception e) {
			error += e.getMessage();
		}

		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}

		PersistenceXStream.saveToXMLwithXStream(ms);
	}

	public void addMoreTAEval(Applicant ta, String header, String eval) throws InvalidInputException {
		String error = "";

		if (eval.trim().length() == 0) {
			error += "Can not submit an empty Evaluation ";
		}

		try {
			String previousEval = ta.getEvaluation().trim();
			String newEval = "\n" + "\n" + header + ":\n" + eval;
			String allEval = previousEval + newEval;

			ta.setEvaluation(allEval);
		} catch (Exception e) {
			error += e.getMessage();
		}

		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}

		PersistenceXStream.saveToXMLwithXStream(ms);

	}

	public void createJobPosting(String jobPosition, Date deadlineDate, String exp, double hourlyRate, Course aCourse)
			throws InvalidInputException {
		String error = "";

		if (deadlineDate == null) {
			error += "Please specify a date! ";
		}
		if (exp == null || exp.trim().length() == 0) {
			error += "Please specify preferred experience! ";
		}
		if (hourlyRate < 0) {
			error += "Please specify hourly wage! ";
		}

		error = error.trim();

		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}

		ms.addJobPosting(jobPosition, (java.sql.Date) deadlineDate, exp, hourlyRate, aCourse);
		PersistenceXStream.saveToXMLwithXStream(ms);

	}

	public void createApplication(JobPosting jp, String name, int id, String major, boolean isUndergrad, String year,
			String exp, String firstChoice, String secondChoice, String thirdChoice, int totalAppointmentHour)
			throws InvalidInputException {

		String error = "";
		Applicant ap = createApplicant(name, id, major, isUndergrad, year, exp, firstChoice, secondChoice, thirdChoice,
				totalAppointmentHour);

		System.out.println("create");
		System.out.println(ap.numberOfApplications());
		if (ap.getApplications().size() < 3) {
			Application application = new Application(0, jp, ap);
			application.setStatus(Status.PENDING);
			ap.addApplication(application);
		}

		else {

			error += "You cannot submit more applications! ";
		}

		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}

		PersistenceXStream.saveToXMLwithXStream(ms);
	}

	public Applicant createApplicant(String name, int id, String major, boolean isUndergrad, String year, String exp,
			String firstChoice, String secondChoice, String thirdChoice, int totalAppointmentHour)
			throws InvalidInputException {
		String error = "";

		if (name == null || name.trim().length() == 0) {
			error += "Name cannot be empty!";
		}
		if (id < 0) {
			error += "You must input a valid id!";
		}
		if (major == null || major.trim().length() == 0) {
			error += "Major cannot be empty!";
		}
		if (exp == null || exp.trim().length() == 0) {
			error += "Past experience cannot be empty!";
		}

		error = error.trim();
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}

		Applicant this_applicant = null;

		for (Applicant anApplicant : ms.getApplicants()) {
			if ((anApplicant.getName().equals(name))) {
				this_applicant = anApplicant;
				PersistenceXStream.saveToXMLwithXStream(ms);
				return anApplicant;
			}
		}

		this_applicant = new Applicant(id, name, exp, isUndergrad, major, year, firstChoice, secondChoice, thirdChoice,
				"", totalAppointmentHour, ms);

		PersistenceXStream.saveToXMLwithXStream(ms);
		return this_applicant;

	}

	public void registerApplicant(String name) throws InvalidInputException {

		String error = "";

		if (name == null || name.trim().length() == 0) {
			error += "Name cannot be empty! ";
		}

		boolean found = false;
		List<Applicant> allApplicants = ms.getApplicants();
		List<Instructor> allInstructors = ms.getInstructors();

		for (Applicant anApplicant : allApplicants) {
			if (anApplicant.getName().equals(name.trim())) {
				error += "This username already exists! ";
				found = true;
				break;
			}
		}

		if (found == false) {
			for (Instructor anInstructor : allInstructors) {
				if (anInstructor.getName().equals(name.trim())) {
					error += "This username already exists! ";
					found = true;
					break;
				}
			}
		}

		error = error.trim();
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}

		try {
			// TODO: Create a real applicant to test
			ms.addApplicant(0, name, null, true, null, null, null, null, null, null, 0);
		} catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}

		PersistenceXStream.saveToXMLwithXStream(ms);
	}

	public void registerInstructor(String name) throws InvalidInputException {

		String error = "";

		if (name == null || name.trim().length() == 0) {
			error += "Name cannot be empty! ";
		}

		boolean found = false;
		List<Applicant> allApplicants = ms.getApplicants();
		List<Instructor> allInstructors = ms.getInstructors();

		for (Applicant anApplicant : allApplicants) {
			if (anApplicant.getName().equals(name.trim())) {
				error += "This username already exists! ";
				found = true;
				break;
			}
		}

		if (found == false) {
			for (Instructor anInstructor : allInstructors) {
				if (anInstructor.getName().equals(name.trim())) {
					error += "This username already exists! ";
					found = true;
					break;
				}
			}
		}

		error = error.trim();
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}

		try {
			ms.addInstructor(name);
		} catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}

		PersistenceXStream.saveToXMLwithXStream(ms);

	}

	public void createCourse(String semester, String courseName, String courseCode, int credit, int maxStudent,
			String instructorName, int numGraderNeeded, int numLab, int numTutorial, int labHour, int tutorialHour,
			int totalGraderHour) throws InvalidInputException {

		String error = "";
		courseCode = courseCode.replaceAll("\\s+", "");
		if (courseName == null || courseName.trim().length() == 0) {
			error += "Please specify the course name! ";
		}
		if (courseCode == null || courseCode.trim().length() == 0) {
			error += "Please specify the course code! ";
		}
		if (credit < 0) {
			error += "Please specify the number of credits in the correct format! ";
		}
		if (maxStudent < 0) {
			error += "Please specify the maximum number of students in the correct format! ";
		}
		if (instructorName == null || instructorName.trim().length() == 0) {
			error += "Please specify the instructor! ";
		}
		if (labHour < 0) {
			error += "Please specify a correct lab hour! ";
		}
		if (tutorialHour < 0) {
			error += "Please specify a correct tutorial hour! ";
		}
		if (numLab < 0) {
			error += "Please specify the numebr of lab sessions in the correct format! ";
		}
		if (numTutorial < 0) {
			error += "Please specify the number of tutorials in the correct format! ";
		}

		if (numGraderNeeded < 0) {
			error += "Please specify the number of Grader needed in the correct format! ";
		}

		if (totalGraderHour < 0) {
			error += "Please specify the Grader appointment hour in the correct format! ";
		}

		Instructor instructor = null;

		for (Instructor i : ms.getInstructors()) {
			if (i.getName().equals(instructorName)) {
				instructor = i;
				break;
			}
		}

		if (instructor == null) {
			instructor = new Instructor(instructorName, ms);
		}

		error = error.trim();

		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}

		// Required total TA working hours of a course
		// labHour = total hour for one lab session/semester
		// tutorialHour = total hour for one tutorial session/semester
		int hourRequiredTa = numLab * labHour + numTutorial * tutorialHour;

		// The total number of TA needed for a course = ceil(total hours/max
		// working hours of a TA)
		// This gives the minimum number of TA needed
		double temp = (numLab * labHour + numTutorial * tutorialHour) / 180;
		int numTaNeeded = (int) Math.ceil(temp);

		// HOW TO CALCULATE BUDGET
		double budgetCalculated = (18 * hourRequiredTa) + (15 * totalGraderHour);

		@SuppressWarnings("unused")
		Course newCourse = new Course(semester, courseName, courseCode.trim(), numTutorial, numLab, maxStudent, credit,
				numTaNeeded, numGraderNeeded, labHour, tutorialHour, totalGraderHour, budgetCalculated, instructor, ms);
		PersistenceXStream.saveToXMLwithXStream(ms);
	}

	public boolean applicationAccepted(Application application) {
		if (application.getStatus().equals(Status.SELECTED)) {
			return true;
		}
		return false;
	}

	public boolean applicationRejected(Application application) {
		if (application.getStatus().equals(Status.REJECTED)) {
			return true;
		}
		return false;
	}

	public void rejectApplication(Application application) {
		if (application.getStatus().equals(Status.PENDING)) {
			application.setStatus(Status.REJECTED);
			PersistenceXStream.saveToXMLwithXStream(ms);
		}

	}

	// Can take a course instead of an allocation
	public void changeHours(Allocation allocation, ManagementSystem mm, ArrayList<Applicant> tas,
			ArrayList<Integer> taHours, ArrayList<Applicant> graders, ArrayList<Integer> graderHours)
			throws InvalidInputException {

		if (allocation == null) {
			throw new InvalidInputException("Select a course! ");
		}

		if (!allocation.getAllocationStatus().equals(AllocationStatus.INITIAL)) {
			throw new InvalidInputException("You cannot modify this allocation! ");
		}

		if (tas == null) {
			throw new InvalidInputException("Can not change allocation due to Empty list of potential TAs. ");
		}
		if (taHours == null || taHours.size() != tas.size()) {
			throw new InvalidInputException("Please assign working hours to all selected applicants. ");
		}
		if (graders == null) {
			throw new InvalidInputException("Can not change allocation due to Empty list of potential Graders. ");
		}
		if (graderHours == null || graderHours.size() != graders.size()) {
			throw new InvalidInputException("Please assign working hours to all selected applicants. ");
		}
		try {
			double taBudget = 0;
			double graderBudget = 0;

			for (int i = 0; i < tas.size(); i++) {
				if ((180 - tas.get(i).getTotalAppointmentHours()) < taHours.get(i).intValue()) {
					throw new InvalidInputException("Applicant " + tas.get(i).getName() + " can only have "
							+ (180 - tas.get(i).getTotalAppointmentHours()) + " appointment hours");
				}
				try {

					allocation.addApplicant(tas.get(i));
					int newTotalHours = tas.get(i).getTotalAppointmentHours() + taHours.get(i);
					tas.get(i).setTotalAppointmentHours(newTotalHours);

					List<Application> allApplications = tas.get(i).getApplications();
					for (Application anApp : allApplications) {

						Course thisCourse = anApp.getJobPosting().getCourse();
						String jobTitle = anApp.getJobPosting().getJobTitle();
						if (thisCourse.getSemester().equals(allocation.getCourse().getSemester())
								&& thisCourse.getCourseCode().equals(allocation.getCourse().getCourseCode())
								&& jobTitle.equals("TA")) {
							anApp.setStatus(Status.SELECTED);
							taBudget = taBudget + anApp.getJobPosting().getHourRate();
						}
					}
				} catch (Exception e) {
				}
			}
			for (int j = 0; j < graders.size(); j++) {
				allocation.addApplicant(graders.get(j));

				List<Application> allApplications = graders.get(j).getApplications();
				for (Application anApp : allApplications) {
					Course thisCourse = anApp.getJobPosting().getCourse();
					String jobTitle = anApp.getJobPosting().getJobTitle();
					if (thisCourse.getSemester().equals(allocation.getCourse().getSemester())
							&& thisCourse.getCourseCode().equals(allocation.getCourse().getCourseCode())
							&& jobTitle.equals("Grader")) {
						anApp.setStatus(Status.SELECTED);
						graderBudget = graderBudget + anApp.getJobPosting().getHourRate();
					}
				}
			}

			if ((taBudget + graderBudget) > allocation.getCourse().getBudgetCalculated()) {
				// allocation.delete();
				System.out.println("budgert error");
				throw new InvalidInputException("This allocation exceeds the budget!");
			}

			for (int i = 0; i < tas.size(); i++) {
				List<Application> allApplications = tas.get(i).getApplications();
				for (Application anApp : allApplications) {
					Course thisCourse = anApp.getJobPosting().getCourse();
					String jobTitle = anApp.getJobPosting().getJobTitle();
					if (thisCourse.getSemester().equals(allocation.getCourse().getSemester())
							&& thisCourse.getCourseCode().equals(allocation.getCourse().getCourseCode())
							&& jobTitle.equals("TA")) {

						if (anApp.setHour(taHours.get(i))) {
						}
					}
				}
			}

			for (int j = 0; j < graders.size(); j++) {
				List<Application> allApplications = graders.get(j).getApplications();
				for (Application anApp : allApplications) {
					Course thisCourse = anApp.getJobPosting().getCourse();
					String jobTitle = anApp.getJobPosting().getJobTitle();
					if (thisCourse.getSemester().equals(allocation.getCourse().getSemester())
							&& thisCourse.getCourseCode().equals(allocation.getCourse().getCourseCode())
							&& jobTitle.equals("Grader")) {
						if (anApp.setHour(taHours.get(j))) {
						}
					}
				}
			}

			System.out.println("set status" + allocation.setAllocationStatus(AllocationStatus.INSTRUCTOR_APPROVED));
			try {
				PersistenceXStream.saveToXMLwithXStream(mm);
			} catch (Exception e) {
				System.out.println("per error" + e.getMessage());

			}
			System.out.println(PersistenceXStream.saveToXMLwithXStream(mm));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new InvalidInputException(e.getMessage());
		}

	}

	public void finalizeAllocation(Allocation allocation) throws InvalidInputException {
		if (!allocation.getAllocationStatusFullName().equals("INSTRUCTOR_APPROVED")) {
			throw new InvalidInputException(
					"The allocation cannot be finalized. It could because of Instructor haven't approved it yet, or it has already be finalized. ");
		}
		allocation.setAllocationStatus(AllocationStatus.FINAL_APPROVAL);
		PersistenceXStream.saveToXMLwithXStream(ms);
	}

	public boolean offerAccepted(Application application) {
		if (application.getStatus().equals(Status.OFFER_ACCEPTED)) {
			return true;
		}
		return false;
	}

	public boolean offerRejected(Application application) {
		if (application.getStatus().equals(Status.OFFER_DECLINED)) {
			return true;
		}
		return false;
	}

	public void acceptOffer(ManagementSystem mm, Application application) {
		if (application.getStatus().equals(Status.SELECTED)) {
			application.setStatus(Status.OFFER_ACCEPTED);
			PersistenceXStream.saveToXMLwithXStream(ms);
		}
	}

	public void rejectOffer(Application application) {
		if (application.getStatus().equals(Status.SELECTED)) {
			application.setStatus(Status.OFFER_DECLINED);
			PersistenceXStream.saveToXMLwithXStream(ms);
		}
	}

}
