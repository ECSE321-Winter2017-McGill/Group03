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
	private Instructor prof = new Instructor("Daniel Varro", ms);

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


	public void createApplicant(String name, int id, String major, boolean isUndergrad, String year, String exp,
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

		Applicant this_applicant = new Applicant(0, "", "", true, "", "", "", "", "", 0, ms);
		boolean found = false;
		List<Applicant> allApplicants = ms.getApplicants();
		if (allApplicants.size() > 1) {

			for (int i = 0; i < allApplicants.size(); i++) {
				Applicant anApplicant = allApplicants.get(i);

				if ((anApplicant.getName().equals(name)) && (anApplicant.getStudentID() == id)) {

					this_applicant = anApplicant;
					found = true;
					break;
				}
			}

			if (found == false) {

				this_applicant = ms.addApplicant(id, name, exp, isUndergrad, major, year, firstChoice, secondChoice,
						thirdChoice, totalAppointmentHour);
			}

		}

		else {
			this_applicant = ms.addApplicant(id, name, exp, isUndergrad, major, year, firstChoice, secondChoice,
					thirdChoice, totalAppointmentHour);
		}

		Date date = new Date(0);
		Course course = new Course("", "", 0, 0, 0, 0, 0, 0, 0.0, prof, ms);

		JobPosting jobPosting = new JobPosting("", date, "", 0, 0.0, ms, course);

		if (this_applicant.getApplications().size() < 3) {
			Application application = new Application("submitted", jobPosting);
			this_applicant.addApplication(application);
		}

		else {

			error += "You cannot submit more applications! ";
		}

		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}

		PersistenceXStream.saveToXMLwithXStream(ms);

	}

}
