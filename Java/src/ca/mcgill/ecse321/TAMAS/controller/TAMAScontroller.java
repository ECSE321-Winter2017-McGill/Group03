package ca.mcgill.ecse321.TAMAS.controller;

import java.sql.Date;

import ca.mcgill.ecse321.TAMAS.Course;
import ca.mcgill.ecse321.TAMAS.JobPosting;
import ca.mcgill.ecse321.TAMAS.ManagementSystem;
import ca.mcgill.ecse321.TAMAS.persistence.PersistenceXStream;

public class TAMAScontroller {
	
	private ManagementSystem ms;
	
	public TAMAScontroller(ManagementSystem ms) {
		this.ms = ms;
	}
	
	public void createJob(String jobPosition, Date deadlineDate, String exp, int numberEmployees, int hourlyRate, Course aCourse) throws InvalidInputException {
		String error = "";
		if (jobPosition.equals("")) {
			error += "Must specify a job position! ";
		}
		if (deadlineDate == null) {
			error += "Must specify a date! ";
		}
		if (exp == null || exp.trim().length() == 0) {
			error += "Must specify preferred experience! ";
		}
		if (numberEmployees == -1) {
			error += "Must specify how many workers! ";
		}
		if (hourlyRate == -1) {
			error += "Must specify hourly wage! ";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		
		JobPosting jp = new JobPosting(jobPosition, deadlineDate, exp, numberEmployees, hourlyRate, ms, aCourse);
		ms.addJobPosting(jp);
		PersistenceXStream.saveToXMLwithXStream(ms);
	}

}
