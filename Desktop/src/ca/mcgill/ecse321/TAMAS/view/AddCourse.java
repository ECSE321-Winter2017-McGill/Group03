package ca.mcgill.ecse321.TAMAS.view;

import javax.swing.JFrame;

import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;

public class AddCourse extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2255666817179960802L;
	private ManagementSystem ms;
	private Object user;

	public AddCourse(ManagementSystem ms, Object user) {
		this.ms = ms;
		this.user = user;
		initComponents();
	}

	private void initComponents() {

	}

}
