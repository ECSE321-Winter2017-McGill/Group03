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

public class AddCourse extends JFrame {
	
	private static final long serialVersionUID = -2255666817179960802L;
	private ManagementSystem ms;
	private Object user;
	
	private JLabel formTitle;
	private JLabel errorMessage;
	private JLabel semesterLabel;
	private JComboBox<String> semesterToggleList;
	private JLabel nameLabel;
	private JLabel nameTextField;
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

	private boolean nameCorrect = true;
	private boolean codeCorrect = true;
	private boolean creditCorrect = true;
	private boolean maxStudentCorrect = true;
	private boolean instructorCorrect = true;
	private boolean numLabCorrect = true;
	private boolean numTutorialCorrect = true;
	private boolean hourTaCorrect = true;
	private boolean hourGraderCorrect = true;
	

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
		semesterToggleList = new JComboBox<String>(new String[0]);
		semesterToggleList.addItem("Winter 2017");
		semesterToggleList.addItem("Summer 2017");
		semesterToggleList.addItem("Fall 2017");
		
		nameLabel = new JLabel("Course Name:");
		nameTextField = new JLabel("Hahahahahahaha");
		
		codeLabel = new JLabel("Course Code:");
		codeTextField = new JTextField();
		
		creditLabel = new JLabel("Credit:");
		creditTextField = new JTextField();
		
		maxStudentLabel = new JLabel("Maximum Student:");
		maxStudentTextField = new JTextField();
		
		instructorLabel = new JLabel("Instructor:");
		instructorTextField = new JTextField();
		
		horizontalLineMiddle = new JSeparator();
		
		numLabLabel = new JLabel("Number of Lab Sessions:");
		numLabTextField = new JTextField();
		
		numTutorialLabel = new JLabel("Number of Tutorial Sessions:");
		numTutorialTextField = new JTextField();
		
		hourTaLabel = new JLabel("Hour required for TA:");
		hourTaTextField = new JTextField();
		
		hourGraderLabel = new JLabel("Hour required for Grader:");
		hourGraderTextField = new JTextField();

		submitButton = new JButton("Submit");
		submitButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				submitButtonActionPerformed(evt);
			}
		});
		
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelButtonActionPerformed(evt);
			}
		});
		
		
		
		setTitle("Add a New Course");
		
		
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
								.addComponent(semesterLabel)
								.addComponent(nameLabel)
								.addComponent(codeLabel)
								.addComponent(creditLabel)
								.addComponent(maxStudentLabel)
								.addComponent(instructorLabel)
								.addComponent(numLabLabel)
								.addComponent(numTutorialLabel)
								.addComponent(hourTaLabel)
								.addComponent(hourGraderLabel)
								.addComponent(cancelButton))
						.addGroup(
								layout.createParallelGroup()
								.addComponent(semesterToggleList)
								.addComponent(nameTextField)
								.addComponent(codeTextField)
								.addComponent(creditTextField)
								.addComponent(maxStudentTextField,160,160,160)
								.addComponent(instructorTextField)
								.addComponent(numLabTextField)
								.addComponent(numTutorialTextField)
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
						.addComponent(nameLabel)
						.addComponent(nameTextField,23,23,23))
				.addGroup(layout.createParallelGroup()
						.addComponent(codeLabel)
						.addComponent(codeTextField,23,23,23))
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
		
		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				backToMain();
			}
		});
		
		
	}
	
	
	private void refreshData() {
		// error
		errorMessage.setText(error);
		
		// If everything is filled out correctly	
		if (error == null || error.length() == 0) {
			nameTextField.setText("");
			codeTextField.setText("");
			creditTextField.setText("");
			maxStudentTextField.setText("");
			instructorTextField.setText("");
			
			numLabTextField.setText("");
			numTutorialTextField.setText("");
			hourTaTextField.setText("");
			hourGraderTextField.setText("");

			selectedSemester = -1;
		}
		
		// If there is error
		

		pack();
	}
	
	private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {
		
		TamasController tc = new TamasController(ms);
		error = "";
		
		// TODO: Add a Course name attribute to model.
		
		String semester = (String) semesterToggleList.getSelectedItem();
		String courseName = nameTextField.getText(); 
		String courseCode;
		String credit;
		
		
		
		refreshData();
	}
	
	private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
		backToMain();
		setVisible(false);
	}
	
	private void backToMain() {
		new AllApplication(ms, user).setVisible(true);
	}



}
