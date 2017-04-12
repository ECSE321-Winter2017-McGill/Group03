package ca.mcgill.ecse321.TAMAS.controller;

public class InvalidInputException extends Exception {
	
	private static final long serialVersionUID = -5633915762703837868L;
	
	/**
	 * @param errorMessage Error message
	 */
	public InvalidInputException(String errorMessage) {
		super(errorMessage);
	}

}
