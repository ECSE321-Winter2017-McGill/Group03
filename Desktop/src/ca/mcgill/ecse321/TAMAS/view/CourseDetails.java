package ca.mcgill.ecse321.TAMAS.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import ca.mcgill.ecse321.TAMAS.controller.TamasController;
import ca.mcgill.ecse321.TAMAS.model.Course;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;
import ca.mcgill.ecse321.TAMAS.controller.InvalidInputException;

public class CourseDetails extends JFrame {
	

	
	private static final long serialVersionUID = 7420465125526630160L;
	
	private JLabel formTitle;
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
	
	private JLabel numGraderNeededLabel;
	private JTextField numGraderNeededTextField;
	private JLabel numLabLabel;
	private JTextField numLabTextField;
	private JLabel numTutorialLabel;
	private JTextField numTutorialTextField;

	private JLabel labHourLabel;
	private JTextField labHourTextField;
	private JLabel tutorialHourLabel;
	private JTextField tutorialHourTextField;
	private JLabel totalGraderHourLabel;
	private JTextField totalGraderHourTextField;

	private JSeparator horizontalLineTop;
	private JSeparator horizontalLineMiddle;
	
	private JButton closeButton;
	

	private ManagementSystem ms;
	private Course course;
	private Object user;
	
	public CourseDetails(ManagementSystem ms, Course course, Object user) {
		this.ms = ms;
		this.course = course;
		this.user = user;
		initComponents();
	}

	private void initComponents() {
		
		formTitle = new JLabel("Course");
		formTitle.setFont(new Font("Georgia", Font.BOLD, 22));
		
		horizontalLineTop = new JSeparator();
		
		semesterLabel = new JLabel("Semester:");
		semesterLabel.setForeground(Color.BLACK);
		semesterToggleList = new JComboBox<String>(new String[0]);
		semesterToggleList.addItem(course.getSemester());
		semesterToggleList.setForeground(Color.BLACK);
		
		
		nameLabel = new JLabel("Course Name:");
		nameLabel.setForeground(Color.BLACK);
		nameTextField = new JTextField();
		nameTextField.setText(course.getCourseName());
		nameTextField.setForeground(Color.BLACK);
		nameTextField.setEditable(false);
		
		codeLabel = new JLabel("Course Code:");
		codeLabel.setForeground(Color.BLACK);
		codeTextField = new JTextField();
		codeTextField.setText(course.getCourseCode());
		codeTextField.setForeground(Color.BLACK);
		codeTextField.setEditable(false);
		
		creditLabel = new JLabel("Credit:");
		creditLabel.setForeground(Color.BLACK);
		creditTextField = new JTextField();
		creditTextField.setText(Integer.toString(course.getCredit()));
		creditTextField.setForeground(Color.BLACK);
		creditTextField.setEditable(false);
		
		maxStudentLabel = new JLabel("Maximum Student:");
		maxStudentLabel.setForeground(Color.BLACK);
		maxStudentTextField = new JTextField();
		maxStudentTextField.setText(Integer.toString(course.getNumStudent()));
		maxStudentTextField.setForeground(Color.BLACK);
		maxStudentTextField.setEditable(false);
		
		instructorLabel = new JLabel("Instructor:");
		instructorLabel.setForeground(Color.BLACK);
		instructorTextField = new JTextField();
		instructorTextField.setText(course.getInstructor().getName());
		instructorTextField.setForeground(Color.BLACK);
		instructorTextField.setEditable(false);
		
		horizontalLineMiddle = new JSeparator();
		
		numLabLabel = new JLabel("Number of Lab Sessions:");
		numLabLabel.setForeground(Color.BLACK);
		numLabTextField = new JTextField();
		numLabTextField.setText(Integer.toString(course.getNumLab()));  
		numLabTextField.setForeground(Color.BLACK);
		numLabTextField.setEditable(false);
		
		numTutorialLabel = new JLabel("Number of Tutorial Sessions:");
		numTutorialLabel.setForeground(Color.BLACK);
		numTutorialTextField = new JTextField();
		numTutorialTextField.setText(Integer.toString(course.getNumTutorial()));
		numTutorialTextField.setForeground(Color.BLACK);
		numTutorialTextField.setEditable(false);
		
		labHourLabel = new JLabel("Length of Lab Session:");
		labHourLabel.setForeground(Color.BLACK);
		labHourTextField = new JTextField();
		labHourTextField.setText(Integer.toString(course.getLabHour()));
		labHourTextField.setForeground(Color.BLACK);
		labHourTextField.setEditable(false);
		
		tutorialHourLabel = new JLabel("Length of Tutorial Sessions:");
		tutorialHourLabel.setForeground(Color.BLACK);
		tutorialHourTextField = new JTextField();
		tutorialHourTextField.setText(Integer.toString(course.getTutorialHour()));
		tutorialHourTextField.setForeground(Color.BLACK);
		tutorialHourTextField.setEditable(false);
		
		numGraderNeededLabel = new JLabel("Number of Grader needed:");
		numGraderNeededLabel.setForeground(Color.BLACK);
		numGraderNeededTextField = new JTextField();
		numGraderNeededTextField.setText(Integer.toString(course.getNumGraderNeeded()));
		numGraderNeededTextField.setForeground(Color.BLACK);
		numGraderNeededTextField.setEditable(false);
		
		totalGraderHourLabel = new JLabel("Appointment Hour for Grader:");
		totalGraderHourLabel.setForeground(Color.BLACK);
		totalGraderHourTextField = new JTextField();
		totalGraderHourTextField.setText(Integer.toString(course.getTotalGraderHour()));
		totalGraderHourTextField.setForeground(Color.BLACK);
		totalGraderHourTextField.setEditable(false);
		
		
		closeButton = new JButton("  Close  ");
		closeButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				closeButtonActionPerformed(evt);
			}
		});
		
		
		
		setTitle("Course Details");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(layout.createParallelGroup()
				.addComponent(formTitle)
				.addComponent(horizontalLineTop)
				.addComponent(horizontalLineMiddle)

				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
								.addComponent(semesterLabel,200,250,500)
								.addComponent(nameLabel)
								.addComponent(codeLabel)
								.addComponent(creditLabel)
								.addComponent(maxStudentLabel)
								.addComponent(instructorLabel)
								.addComponent(numLabLabel)
								.addComponent(numTutorialLabel)
								.addComponent(labHourLabel)
								.addComponent(tutorialHourLabel)
								.addComponent(numGraderNeededLabel)
								.addComponent(totalGraderHourLabel)
								.addComponent(closeButton))
						.addGroup(layout.createParallelGroup()
								.addComponent(semesterToggleList)
								.addComponent(nameTextField)
								.addComponent(codeTextField)
								.addComponent(creditTextField)
								.addComponent(maxStudentTextField,200,250,500)
								.addComponent(instructorTextField)
								.addComponent(numLabTextField)
								.addComponent(numTutorialTextField)
								.addComponent(labHourTextField)
								.addComponent(tutorialHourTextField)
								.addComponent(numGraderNeededTextField)
								.addComponent(totalGraderHourTextField))));
										

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(formTitle)
				.addGroup(layout.createParallelGroup()
						.addComponent(horizontalLineTop))
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
						.addComponent(labHourLabel)
						.addComponent(labHourTextField,23,23,23))
				.addGroup(layout.createParallelGroup()
						.addComponent(tutorialHourLabel)
						.addComponent(tutorialHourTextField,23,23,23))
				.addGroup(layout.createParallelGroup()
						.addComponent(numGraderNeededLabel)
						.addComponent(numGraderNeededTextField,23,23,23))
				.addGroup(layout.createParallelGroup()
						.addComponent(totalGraderHourLabel)
						.addComponent(totalGraderHourTextField,23,23,23))
				.addGroup(layout.createParallelGroup()
						.addComponent(closeButton))
				);

		pack();
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
	}
	
	private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {
		setVisible(false);
	}
	
	private void backToAllCourses() {
		new AllCourses(ms, user).setVisible(true);
	}
}
