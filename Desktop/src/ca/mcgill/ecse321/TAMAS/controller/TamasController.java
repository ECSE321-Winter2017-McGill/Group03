package ca.mcgill.ecse321.TAMAS.controller;

import java.sql.Date;
import java.util.List;

import ca.mcgill.ecse321.TAMAS.controller.InvalidInputException;
import ca.mcgill.ecse321.TAMAS.model.Applicant;
import ca.mcgill.ecse321.TAMAS.model.Application;
import ca.mcgill.ecse321.TAMAS.model.Course;
import ca.mcgill.ecse321.TAMAS.model.Instructor;
import ca.mcgill.ecse321.TAMAS.model.JobPosting;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;
import ca.mcgill.ecse321.TAMAS.persistence.PersistenceXStream;

public class TamasController {

	private ManagementSystem ms = new ManagementSystem();

	public TamasController(ManagementSystem ms) {
		this.ms = ms;
	}

	public void createJob(String jobPosition, Date deadlineDate, String exp, int numberEmployees, double hourlyRate,
			Course aCourse) throws InvalidInputException {
		String error = "";
		if (aCourse == null) {
			error += "Please select a course.";
		}
		if (jobPosition.equals("")) {
			error += "Must specify a job position! ";
		}
		if (deadlineDate == null) {
			error += "Must specify a date! ";
		}
		if (exp == null || exp.trim().length() == 0) {
			error += "Must specify preferred experience! ";
		}
		if (numberEmployees < 0) {
			error += "Must specify number of positions hiring! ";
		}
		if (hourlyRate < 0) {
			error += "Must specify hourly wage! ";
		}
		error = error.trim();

		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}

		ms.addJobPosting(jobPosition, deadlineDate, exp, numberEmployees, hourlyRate, aCourse);
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
			Application application = new Application("submitted", jp, ap);
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
		if (firstChoice == null || firstChoice.trim().length() == 0) {
			error += "First Choice cannnot be empty.";
		}

		error = error.trim();
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		Applicant this_applicant = null;
		boolean found = false;
		List<Applicant> allApplicants = ms.getApplicants();
		if (allApplicants.size() != 0) {

			for (int i = 0; i < allApplicants.size(); i++) {
				Applicant anApplicant = allApplicants.get(i);
				if ((anApplicant.getName().equals(name))) {
					this_applicant = anApplicant;
					found = true;
					break;
				}
			}
			if (found == false) {
				this_applicant = new Applicant(id, name, exp, isUndergrad, major, year, firstChoice, secondChoice,
						thirdChoice, totalAppointmentHour, ms);
			}

		} else {
			this_applicant = new Applicant(id, name, exp, isUndergrad, major, year, firstChoice, secondChoice,
					thirdChoice, totalAppointmentHour, ms);
		}
		PersistenceXStream.saveToXMLwithXStream(ms);
		return this_applicant;

	}
	
	public void createCourse(String aSemester, String aCourseCode, int aNumTutorial, int aNumLab, int aNumStudent, int aCredit, int aHourRequiredTa, 
			int aHourRequiredGrader, double aBudgetCalculated, Instructor aInstructor, ManagementSystem aManagementSystem) throws InvalidInputException {
		String error = "";
		if (aSemester == null) {
			error += "Please specify a semester.";
		}
		if (aCourseCode.equals("")) {
			error += "Please specify the course code! ";
		}
		if (aInstructor.equals("")) {
			error += "Please specify the instructor! ";
		}
		if (aNumTutorial < 0) {
			error += "Must specify the number of tutorial! ";
		}
		if (aNumLab < 0) {
			error += "Must specify the numebr of lab! ";
		}
		if (aNumStudent < 0) {
			error += "Must specify the maximum number of students! ";
		}
		if (aCredit < 0) {
			error += "Must specify the number of credits! ";
		}
		if (aHourRequiredTa < 0) {
			error += "Must specify the hourly wage for TA! ";
		}
		if (aHourRequiredGrader < 0) {
			error += "Must specify the hourly wage for Grader! ";
		}
	/*	if (aBudgetCalculated < 0) {
			error += "Must specify the calculated budget! ";
		}*/
		error = error.trim();

		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		ms.addCourse(new Course(aSemester, aCourseCode, aNumTutorial, aNumLab, aNumStudent,
				aCredit, aHourRequiredTa, 
				aHourRequiredGrader, aBudgetCalculated, 
				aInstructor,ms));
		PersistenceXStream.saveToXMLwithXStream(ms);
	}
	
	public void acceptApplication(Application application){
		if  (application.getApplicationStatus().equals("submitted")) {
		application.setApplicationStatus("accpeted");
		PersistenceXStream.saveToXMLwithXStream(ms);
		}
	}
	
	public void rejectApplication(Application application){
		if  (application.getApplicationStatus().equals("submitted")) {
			application.setApplicationStatus("rejected");
			PersistenceXStream.saveToXMLwithXStream(ms);
		}
	}

}
