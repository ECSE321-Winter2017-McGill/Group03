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

	public static void createAllocation(ManagementSystem mm, Course course, ArrayList<Applicant> listApplicant)
			throws InvalidInputException {

		if (course == null) {
			throw new InvalidInputException("Can not change allocation due to null course");
		}
		if (listApplicant == null || listApplicant.size() == 0) {
			throw new InvalidInputException("Can not change allocation due to Empty list of Applicant");
		}
		try {
			Allocation allocation = new Allocation(course);
			for (Applicant app : listApplicant) {
				allocation.addApplicant(app);
			}
			course.setCourseJobAllocation(allocation);
		} catch (Exception e) {
			throw new InvalidInputException(e.getMessage());
		}
		PersistenceXStream.saveToXMLwithXStream(mm);

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

	public void createTAEval(Applicant ta, String eval) throws InvalidInputException {
		String error = "";

		// Applicant newApplicant;

		if (eval.trim().length() == 0) {
			error += "Can not submit an empty Evaluation ";
		}

		try {
			ta.setEvaluation(eval);
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
			Application application = new Application(jp, ap);
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

	private Applicant createApplicant(String name, int id, String major, boolean isUndergrad, String year, String exp,
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

		// TODO: consider modifying this
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

		// TODO: HOW TO CALCULATE BUDGET
		double budgetCalculated = (18 * hourRequiredTa) + (15 * totalGraderHour);

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

<<<<<<< HEAD
	public void acceptApplication(Application application){
		if (application.getStatus().equals(Status.PENDING)){ 
			application.setStatus(Status.SELECTED);
=======
	public void acceptApplication(Application application) throws InvalidInputException {
		if (application.getStatus().equals(Status.PENDING)) {
			application.setStatus(Status.OFFER_ACCEPTED);
>>>>>>> 261938e8dcfcecadd45b006e3ae2321f56c10599
			PersistenceXStream.saveToXMLwithXStream(ms);
		}
	}

	public void rejectApplication(Application application) {
		if (application.getStatus().equals(Status.PENDING)) {
			application.setStatus(Status.OFFER_DECLINED);
			PersistenceXStream.saveToXMLwithXStream(ms);
		}
		
	}

	public void changeHours(Course course, int hour) throws InvalidInputException {
		if (course == null) {
			throw new InvalidInputException("Select a course! ");
		}

		if (hour <= 0) {
			throw new InvalidInputException("Select a course! ");
		}
	}

}
