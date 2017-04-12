
//This is the same controller used in our desktop Application

package ca.mcgill.ecse321.TAMAS.controller;

import java.util.Date;
import java.util.Calendar;
import java.util.List;

import com.thoughtworks.xstream.XStream;

import ca.mcgill.ecse321.TAMAS.controller.InvalidInputException;
import ca.mcgill.ecse321.TAMAS.model.Applicant;
import ca.mcgill.ecse321.TAMAS.model.Application;
import ca.mcgill.ecse321.TAMAS.model.Application.Status;
import ca.mcgill.ecse321.TAMAS.model.Course;
import ca.mcgill.ecse321.TAMAS.model.Instructor;
import ca.mcgill.ecse321.TAMAS.model.JobPosting;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;
import ca.mcgill.ecse321.TAMAS.persistence.DBmanager;

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

	public TController(ManagementSystem ms) {
		this.ms = ms;
	}

	public void createTAEval(String name, String eval) throws InvalidInputException {
		String error = "";

		Applicant newApplicant;

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
			Application application = new Application(0, jp, ap);
			ap.addApplication(application);
		}

		else {

			error += "You cannot submit more applications! ";
		}

		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}

		DBmanager.updateDB(this.objToXML(ms));
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
				DBmanager.updateDB(this.objToXML(ms));
				return anApplicant;
			}
		}

		this_applicant = new Applicant(id, name, exp, isUndergrad, major, year, firstChoice, secondChoice, thirdChoice
				,"",totalAppointmentHour, ms);

		DBmanager.updateDB(this.objToXML(ms));
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

		DBmanager.updateDB(this.objToXML(ms));
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

		DBmanager.updateDB(this.objToXML(ms));

	}

	public void createCourse(String semester, String courseName, String courseCode, int credit, int maxStudent,
			String instructorName,int numGraderNeeded,int numLab, int numTutorial, int labHour, int tutorialHour,
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
			if (i.getName().equals(instructorName)){
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
		
		//Required total TA working hours of a course
		int hourRequiredTa = numLab*labHour + numTutorial*tutorialHour;
		
		//The total number of TA needed for a course = ceil(total hours/max working hours of a TA)
		//This gives the minimum number of TA needed
		double temp = (numLab*labHour + numTutorial*tutorialHour)/180;
		int numTaNeeded = (int) Math.ceil(temp);

		// TODO: HOW TO CALCULATE BUDGET
		double budgetCalculated = (18 * hourRequiredTa) + (15 * totalGraderHour);

		@SuppressWarnings("unused")
		Course newCourse = new Course(semester, courseName, courseCode.trim(), numTutorial, numLab, maxStudent, credit, numTaNeeded,
				numGraderNeeded, labHour, tutorialHour,totalGraderHour,budgetCalculated, instructor, ms);
		DBmanager.updateDB(this.objToXML(ms));
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

	public void acceptApplication(Application application) throws InvalidInputException {
		if (application.getStatus().equals(Status.PENDING)) {
			application.setStatus(Status.OFFER_ACCEPTED);
			DBmanager.updateDB(this.objToXML(ms));
		} else {
			throw new InvalidInputException("This applicant has already been processed");
		}

	}

	public void rejectApplication(Application application) throws InvalidInputException {
		if (application.getStatus().equals(Status.PENDING)) {
			application.setStatus(Status.REJECTED);
			DBmanager.updateDB(this.objToXML(ms));
		} else {
			throw new InvalidInputException("This applicant has already been processed");
		}
	}
	public void changeHours(Course course, int hour) throws InvalidInputException{
		if (course==null){
			throw new InvalidInputException("Select a course! ");
		}
		
		if (hour<=0){
			throw new InvalidInputException("Select a course! ");
		}
	}
	public String objToXML(Object obj) {
		xstream.setMode(XStream.ID_REFERENCES);
		xstream.alias("jobInfo", JobPosting.class);
		String xml = xstream.toXML(obj); // save our xml file
		return xml;
	}

}
