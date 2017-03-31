package ca.mcgill.ecse321.TAMAS.controller;

import java.util.Date;
import java.util.Calendar;
import java.util.List;

import com.thoughtworks.xstream.XStream;

import ca.mcgill.ecse321.TAMAS.controller.InvalidInputException;
import ca.mcgill.ecse321.TAMAS.model.Applicant;
import ca.mcgill.ecse321.TAMAS.model.Application;
import ca.mcgill.ecse321.TAMAS.model.Course;
import ca.mcgill.ecse321.TAMAS.model.Instructor;
import ca.mcgill.ecse321.TAMAS.model.JobPosting;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;
import ca.mcgill.ecse321.TAMAS.persistence.DBmanager;
import ca.mcgill.ecse321.TAMAS.persistence.PersistenceXStream;

public class TController {
	private static XStream xstream = new XStream();
	private ManagementSystem ms = new ManagementSystem();

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
		c.set(Calendar.MONTH, 4);
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

	public TController(ManagementSystem ms) {
		this.ms = ms;
	}

	public void createTAEval(String name, String eval) throws InvalidInputException {
		String error = "";

		Applicant newApplicant;
		if(eval==null||eval.length()==0){
			error+="No Input";
		}
		if (eval.trim().length()==0) {
			error += "A space cannot be an evaluation! ";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		for (int i=0; i<this.ms.getApplicants().size(); i++) {
			if (this.ms.getApplicants().get(i).getName().equals(name)) {
				newApplicant = this.ms.getApplicants().get(i);
				newApplicant.setEvaluation(eval);
				break;
			}
			
		}
		DBmanager.updateDB(this.objToXML(ms));
	}
	
	public void createJobPosting(String jobPosition, Date deadlineDate, String exp, double hourlyRate, Course aCourse)
			throws InvalidInputException {
		String error = "";
		System.out.println("ccJP");
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
		DBmanager.updateDB(this.objToXML(ms));

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
			Application application = new Application("Submitted", jp, ap);
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
		}		if (id < 0) {
			error += "You must input a valid id!";
		}
		if (major == null || major.trim().length() == 0) {
			error += "Major cannot be empty!";
		}
		if (exp == null || exp.trim().length() == 0) {
			error += "Past experience cannot be empty!";
		}

		error = error.trim();		if (error.length() > 0) {
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

		this_applicant = new Applicant(id, name, exp, isUndergrad, major, year, firstChoice, secondChoice, thirdChoice
				,"",totalAppointmentHour, ms);

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
			String instructorName, int numLab, int numTutorial, int numTaNeeded, int numGraderNeeded,
			int hourRequiredTa, int hourRequiredGrader, ManagementSystem aManagementSystem)
			throws InvalidInputException {
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
		if (numLab < 0) {
			error += "Please specify the numebr of lab sessions in the correct format! ";
		}
		if (numTutorial < 0) {
			error += "Please specify the number of tutorial in the correct format! ";
		}
		if (numTaNeeded < 0) {
			error += "Please specify the number of TA needed in the correct format! ";
		}
		if (numGraderNeeded < 0) {
			error += "Please specify the number of Grader needed in the correct format! ";
		}
		if (hourRequiredTa < 0) {
			error += "Please specify the TA appointment hour in the correct format! ";
		}
		if (hourRequiredGrader < 0) {
			error += "Please specify the Grader appointment hour in the correct format! ";
		}

		Instructor instructor = null;
		for (Instructor i : ms.getInstructors()) {
			if (i.getName().equals(instructorName))
				instructor = i;
		}
		if (instructor == null) {
			instructor = new Instructor(instructorName, ms);
		}
		error = error.trim();

		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}

		// TODO: HOW TO CALCULATE BUDGET
		double budgetCalculated = (18 * hourRequiredTa * numTaNeeded) + (15 * hourRequiredGrader * numGraderNeeded);

		ms.addCourse(new Course(semester, courseName, courseCode, numTutorial, numLab, maxStudent, credit, numTaNeeded,
				numGraderNeeded, hourRequiredTa, hourRequiredGrader, budgetCalculated, instructor, ms));
		PersistenceXStream.saveToXMLwithXStream(ms);
	}

	public boolean acceptApplication(Application application) {
		if (application.getApplicationStatus().equals("Submitted")) {
			application.setApplicationStatus("Accpeted");
			PersistenceXStream.saveToXMLwithXStream(ms);
			return true;
		}
		return false;
	}

	public boolean rejectApplication(Application application) {
		if (application.getApplicationStatus().equals("Submitted")) {
			
			application.setApplicationStatus("Rejected");
			PersistenceXStream.saveToXMLwithXStream(ms);
			return true;
		}
		return false;

	}
	public String objToXML(Object obj) {
		xstream.setMode(XStream.ID_REFERENCES);
		String xml = xstream.toXML(obj); // save our xml file
		return xml;

	}

}