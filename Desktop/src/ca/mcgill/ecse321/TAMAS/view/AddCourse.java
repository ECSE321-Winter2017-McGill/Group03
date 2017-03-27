package ca.mcgill.ecse321.TAMAS.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import ca.mcgill.ecse321.TAMAS.controller.TamasController;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;
import ca.mcgill.ecse321.TAMAS.controller.InvalidInputException;

public class AddCourse extends JFrame {
	
	private static final long serialVersionUID = -2255666817179960802L;
	private ManagementSystem ms;
	private Object user;
	
	private JLabel formTitle;
	private JLabel errorMessage;
	private JLabel semesterLabel;
	private JComboBox<String> semesterToggleList;
	private JLabel nameLabel;
	private JTextField nameTextField;
	private JLabel codeLabel;
	private JTextField codeTextField;
	private JLabel creditLabel;
	private JTextField creditTextField;
	private JLabel maxStudentLabel;
	private JTextField maxStudentTextField;
	private JLabel instructorLabel;
	private JTextField instructorTextField;
	
	private JLabel numLabLabel;
	private JTextField numLabTextField;
	private JLabel numTutorialLabel;
	private JTextField numTutorialTextField;
	private JLabel numTaNeededLabel;
	private JTextField numTaNeededTextField;
	private JLabel numGraderNeededLabel;
	private JTextField numGraderNeededTextField;
	private JLabel hourTaLabel;
	private JTextField hourTaTextField;
	private JLabel hourGraderLabel;
	private JTextField hourGraderTextField;
	
	private String error = null;
	private Integer selectedSemester = -1;

	private JButton submitButton;
	private JButton cancelButton;

	private JSeparator horizontalLineTop;
	private JSeparator horizontalLineMiddle;
	

	public AddCourse(ManagementSystem ms, Object user) {
		this.ms = ms;
		this.user = user;
		
		initComponents();
		refreshData();
	}

	private void initComponents() {
		
		formTitle = new JLabel("Add Course");
		formTitle.setFont(new Font("Georgia", Font.BOLD, 22));
		
		horizontalLineTop = new JSeparator();
		
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		
		semesterLabel = new JLabel("Semester:");
		semesterLabel.setForeground(Color.BLACK);
		semesterToggleList = new JComboBox<String>(new String[0]);
		semesterToggleList.addItem("Winter 2017");
		semesterToggleList.addItem("Summer 2017");
		semesterToggleList.addItem("Fall 2017");
		semesterToggleList.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				@SuppressWarnings("unchecked")
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedSemester = cb.getSelectedIndex();
			}
		});
	
		codeLabel = new JLabel("Course Code:");
		codeLabel.setForeground(Color.BLACK);
		codeTextField = new JTextField();
		
		nameLabel = new JLabel("Course Name:");
		nameLabel.setForeground(Color.BLACK);
		nameTextField = new JTextField();
		
		creditLabel = new JLabel("Credit:");
		creditLabel.setForeground(Color.BLACK);
		creditTextField = new JTextField();
		
		maxStudentLabel = new JLabel("Maximum Student:");
		maxStudentLabel.setForeground(Color.BLACK);
		maxStudentTextField = new JTextField();
		
		instructorLabel = new JLabel("Instructor:");
		instructorLabel.setForeground(Color.BLACK);
		instructorTextField = new JTextField();
		
		horizontalLineMiddle = new JSeparator();
		
		numLabLabel = new JLabel("Number of Lab Sessions:");
		numLabLabel.setForeground(Color.BLACK);
		numLabTextField = new JTextField();
		
		numTutorialLabel = new JLabel("Number of Tutorial Sessions:");
		numTutorialLabel.setForeground(Color.BLACK);
		numTutorialTextField = new JTextField();
		
		numTaNeededLabel = new JLabel("Number of TA needed:");
		numTaNeededLabel.setForeground(Color.BLACK);
		numTaNeededTextField = new JTextField();
		
		numGraderNeededLabel = new JLabel("Number of Grader needed:");
		numGraderNeededLabel.setForeground(Color.BLACK);
		numGraderNeededTextField = new JTextField();
		
		hourTaLabel = new JLabel("Appointment Hour for TA:");
		hourTaLabel.setForeground(Color.BLACK);
		hourTaTextField = new JTextField();
		
		hourGraderLabel = new JLabel("Appointment Hour for Grader:");
		hourGraderLabel.setForeground(Color.BLACK);
		hourGraderTextField = new JTextField();

		submitButton = new JButton("  Submit  ");
		submitButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				submitButtonActionPerformed(evt);
			}
		});
		
		
		cancelButton = new JButton("  Cancel  ");
		cancelButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelButtonActionPerformed(evt);
			}
		});
		
		
		
		setTitle("Add a New Course");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(layout.createParallelGroup()
				.addComponent(formTitle)
				.addComponent(errorMessage)
				.addComponent(horizontalLineTop)
				.addComponent(horizontalLineMiddle)

				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
								.addComponent(semesterLabel,200,250,500)
								.addComponent(codeLabel)
								.addComponent(nameLabel)
								.addComponent(creditLabel)
								.addComponent(maxStudentLabel)
								.addComponent(instructorLabel)
								.addComponent(numLabLabel)
								.addComponent(numTutorialLabel)
								.addComponent(numTaNeededLabel)
								.addComponent(numGraderNeededLabel)
								.addComponent(hourTaLabel)
								.addComponent(hourGraderLabel)
								.addComponent(cancelButton))
						.addGroup(layout.createParallelGroup()
								.addComponent(semesterToggleList)
								.addComponent(codeTextField)
								.addComponent(nameTextField)
								.addComponent(creditTextField)
								.addComponent(maxStudentTextField,200,250,500)
								.addComponent(instructorTextField)
								.addComponent(numLabTextField)
								.addComponent(numTutorialTextField)
								.addComponent(numTaNeededTextField)
								.addComponent(numGraderNeededTextField)
								.addComponent(hourTaTextField)
								.addComponent(hourGraderTextField)
								.addComponent(submitButton))));
										

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(formTitle)
				.addGroup(layout.createParallelGroup()
						.addComponent(horizontalLineTop))
				.addComponent(errorMessage)
				.addGroup(layout.createParallelGroup()
						.addComponent(semesterLabel)
						.addComponent(semesterToggleList))
				.addGroup(layout.createParallelGroup()
						.addComponent(codeLabel)
						.addComponent(codeTextField,23,23,23))
				.addGroup(layout.createParallelGroup()
						.addComponent(nameLabel)
						.addComponent(nameTextField,23,23,23))
				.addGroup(layout.createParallelGroup()
						.addComponent(creditLabel)
						.addComponent(creditTextField,23,23,23))
				.addGroup(layout.createParallelGroup()
						.addComponent(maxStudentLabel)
						.addComponent(maxStudentTextField,23,23,23))
				.addGroup(layout.createParallelGroup()
						.addComponent(instructorLabel)
						.addComponent(instructorTextField,23,23,23))
				.addGroup(layout.createParallelGroup()
						.addComponent(horizontalLineMiddle))
				.addGroup(layout.createParallelGroup()
						.addComponent(numLabLabel)
						.addComponent(numLabTextField,23,23,23))
				.addGroup(layout.createParallelGroup()
						.addComponent(numTutorialLabel)
						.addComponent(numTutorialTextField,23,23,23))
				.addGroup(layout.createParallelGroup()
						.addComponent(numTaNeededLabel)
						.addComponent(numTaNeededTextField,23,23,23))
				.addGroup(layout.createParallelGroup()
						.addComponent(numGraderNeededLabel)
						.addComponent(numGraderNeededTextField,23,23,23))
				.addGroup(layout.createParallelGroup()
						.addComponent(hourTaLabel)
						.addComponent(hourTaTextField,23,23,23))
				.addGroup(layout.createParallelGroup()
						.addComponent(hourGraderLabel)
						.addComponent(hourGraderTextField,23,23,23))
				.addGroup(layout.createParallelGroup()
						.addComponent(cancelButton)
						.addComponent(submitButton))
		);

		pack();
		setResizable(false);
		
		
	}
	
	
	private void refreshData() {
		// error
		errorMessage.setText(error);
		
		// If everything is filled out correctly	
		if (error == null || error.length() == 0) {
			
			// TODO: Remove setText for nameTextField
//			nameTextField.setText("");
			codeTextField.setText("");
			creditTextField.setText("");
			maxStudentTextField.setText("");
			instructorTextField.setText("");
			
			numLabTextField.setText("");
			numTutorialTextField.setText("");
			numTaNeededTextField.setText("");
			numGraderNeededTextField.setText("");
			hourTaTextField.setText("");
			hourGraderTextField.setText("");

			selectedSemester = -1;
			semesterToggleList.setSelectedIndex(selectedSemester);
		}
		
		// If there is error
		

		pack();
	}
	
	private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO: Add a Course name attribute to model.
		TamasController tc = new TamasController(ms);
		error = "";
		

	    if (selectedSemester < 0){
	        error = error + "Please specify a semester.";
	        }
	    
	    if (error.trim().length() == 0) {
	        // call the controller
			String semester = (String) semesterToggleList.getSelectedItem();
			String courseName = nameTextField.getText(); 
			String courseCode = codeTextField.getText();
			
			int creditHour = 0;
			if (creditTextField.getText() == null || creditTextField.getText().trim().length() == 0){
				creditHour = -1;
			}
			else if (!creditTextField.getText().matches("[0-9]+")){
				creditHour = -1;
			}
			else{
				creditHour = Integer.parseInt(creditTextField.getText());
			}
			
			
			int maxStudentNum = 0;
			if (maxStudentTextField.getText() == null || maxStudentTextField.getText().trim().length() == 0){
				maxStudentNum = -1;
			}
			else if (!maxStudentTextField.getText().matches("[0-9]+")){
				maxStudentNum = -1;
			}
			else{
				maxStudentNum = Integer.parseInt(maxStudentTextField.getText());
			}
			
			
			String instructorName = instructorTextField.getText();
			
			
			int numTutorial = 0;
			if (numTutorialTextField.getText() == null || numTutorialTextField.getText().trim().length() == 0){
				numTutorial = -1;
			}
			else if (!numTutorialTextField.getText().matches("[0-9]+")){
				numTutorial = -1;
			}
			else{
				numTutorial = Integer.parseInt(numTutorialTextField.getText());
			}
			
			
			int numLab = 0;
			if (numLabTextField.getText() == null || numLabTextField.getText().trim().length() == 0){
				numLab = -1;
			}
			else if (!numLabTextField.getText().matches("[0-9]+")){
				numLab = -1;
			}
			else{
				numLab = Integer.parseInt(numLabTextField.getText());
			}
			
			int numTa = 0;
			if (numTaNeededTextField.getText() == null || numTaNeededTextField.getText().trim().length() == 0){
				numTa = -1;
			}
			else if (!numTaNeededTextField.getText().matches("[0-9]+")){
				numTa = -1;
			}
			else{
				numTa = Integer.parseInt(numTaNeededTextField.getText());
			}
			
			int numGrader = 0;
			if (numGraderNeededTextField.getText() == null || numGraderNeededTextField.getText().trim().length() == 0){
				numGrader = -1;
			}
			else if (!numGraderNeededTextField.getText().matches("[0-9]+")){
				numGrader = -1;
			}
			else{
				numGrader = Integer.parseInt(numGraderNeededTextField.getText());
			}
			
			
			int hourTa = 0;
			if (hourTaTextField.getText() == null || hourTaTextField.getText().trim().length() == 0){
				hourTa = -1;
			}
			else if (!hourTaTextField.getText().matches("[0-9]+")){
				hourTa = -1;
			}
			else{
				hourTa = Integer.parseInt(hourTaTextField.getText());
			}
			
			
			int hourGrader = 0;
			if (hourGraderTextField.getText() == null || hourGraderTextField.getText().trim().length() == 0){
				hourGrader = -1;
			}
			else if (!hourGraderTextField.getText().matches("[0-9]+")){
				hourGrader = -1;
			}
			else{
				hourGrader = Integer.parseInt(hourGraderTextField.getText());
			}
			
		    try {
		    	tc.createCourse(semester,courseName,courseCode,creditHour,maxStudentNum,instructorName,numLab,numTa,numGrader,numTutorial,hourTa,hourGrader,ms);
		    } catch (InvalidInputException e) {
		        error = e.getMessage();
		    }
	    }
		refreshData();
	}
	
	private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
		backToAllCourses();
		setVisible(false);
	}
	
	private void backToAllCourses() {
		new AllCourses(ms, user).setVisible(true);
	}



}
