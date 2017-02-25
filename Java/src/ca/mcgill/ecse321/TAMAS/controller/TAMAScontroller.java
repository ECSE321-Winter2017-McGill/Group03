package ca.mcgill.ecse321.TAMAS.controller;

import java.sql.Date;

import ca.mcgill.ecse321.TAMAS.ManagementSystem;

public class TAMAScontroller {
	
	private ManagementSystem ms;
	
	public TAMAScontroller(ManagementSystem ms) {
		this.ms = ms;
	}
	
	public void createJob(String course, String jobPosition, Date deadlineDate, String exp, String numberEmployees, String hourlyRate) throws InvalidInputException {
		String error = "";
		if (course == null) {
			error += "Must specify a course!";
		}
		if (jobPosition == null) {
			error += "Must specify a position!";
		}
		if (deadlineDate == null) {
			error += "Must specify a date!";
		}
		if (exp == null || exp.trim().length() == 0) {
			error += "Must specify preferred experience!";
		}
		if (numberEmployees == null || numberEmployees.trim().length() == 0) {
			error += "Must specify how many workers!";
		}
		if (error.length() > 0) {
			throw new InvalidInputException (error);
		}
	}

}
