package ca.mcgill.ecse321.TAMAS.view;

import java.awt.Color;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.Properties;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import ca.mcgill.ecse321.TAMAS.controller.InvalidInputException;
import ca.mcgill.ecse321.TAMAS.controller.TamasController;
import ca.mcgill.ecse321.TAMAS.model.Allocation;
import ca.mcgill.ecse321.TAMAS.model.Course;
import ca.mcgill.ecse321.TAMAS.model.Instructor;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;

public class PublishJobPostingPage extends JFrame {

	private static final long serialVersionUID = -3813819647258555349L;
	private String userName;
	// attributes for GUI elements
	private JLabel courseLabel;
	private JComboBox<String> courseList;
	private JLabel jobLabel;
	private JComboBox<String> jobList;
	private JLabel deadline;
	private JDatePickerImpl deadlineDate;
	private JLabel preferredExperienceLabel;
	private JTextField preferredExperienceTextField;
	private JLabel numberofEmployeesLabel;
	private JTextField numberofEmployeesTextField;
	private JLabel hourlyRateLabel;
	private JTextField hourlyRateTextField;
	private JButton createJobPosting;

	private String error = null;
	private JLabel errorMessage;
	// <<<<<<< HEAD

	private ManagementSystem ms;

	private int selectedCourseList = -1;
	private int selectedJobList = -1;

	private Object user;
	public PublishJobPostingPage(ManagementSystem ms,Object user) {
		this.ms = ms;
		this.user=user;
		initComponents();
		refreshData();
	}

	private void initComponents() {
		// global settings and listeners

		// elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);

		courseLabel = new JLabel();
		courseList = new JComboBox<String>(new String[0]);
		courseList.addItem("");
		courseList.addItem("ECSE 321");
		courseList.addItem("COMP 250");
		courseList.addItem("COMP 251");
		jobLabel = new JLabel();
		jobList = new JComboBox<String>(new String[0]);
		jobList.addItem("");
		jobList.addItem("TA");
		jobList.addItem("Grader");

		deadline = new JLabel();

		SqlDateModel model = new SqlDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		deadlineDate = new JDatePickerImpl(datePanel, new DateLabelFormatter());

		preferredExperienceLabel = new JLabel();
		preferredExperienceTextField = new JTextField();

		numberofEmployeesLabel = new JLabel();
		numberofEmployeesTextField = new JTextField();

		hourlyRateLabel = new JLabel();
		hourlyRateTextField = new JTextField();

		createJobPosting = new JButton();

		courseLabel.setText("Select a course:");
		jobLabel.setText("Job Title");
		deadline.setText("Specifiy a deadline:");
		preferredExperienceLabel.setText("Enter the preferred experience");
		numberofEmployeesLabel.setText("Enter the number of positions:");
		hourlyRateLabel.setText("Set the hourly rate");
		createJobPosting.setText("Publish Job Posting");

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Publish Job Posting");

		// layout
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(
				// error message is added here
				layout.createParallelGroup().addComponent(errorMessage)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup().addComponent(courseLabel, 200, 350, 1000)
										.addComponent(jobLabel).addComponent(deadline)
										.addComponent(preferredExperienceLabel).addComponent(numberofEmployeesLabel)
										.addComponent(hourlyRateLabel).addComponent(createJobPosting))
								.addGroup(layout.createParallelGroup().addComponent(courseList, 200, 350, 1000)
										.addComponent(jobList).addComponent(deadlineDate)
										.addComponent(preferredExperienceTextField)
										.addComponent(numberofEmployeesTextField).addComponent(hourlyRateTextField))));
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] { createJobPosting });

		layout.setVerticalGroup(layout.createSequentialGroup()
				// error message is added here
				.addComponent(errorMessage)
				.addGroup(layout.createParallelGroup().addComponent(courseLabel).addComponent(courseList))
				.addGroup(layout.createParallelGroup().addComponent(jobLabel).addComponent(jobList))
				.addGroup(layout.createParallelGroup().addComponent(deadline).addComponent(deadlineDate))
				.addGroup(layout.createParallelGroup().addComponent(preferredExperienceLabel)
						.addComponent(preferredExperienceTextField))
				.addGroup(layout.createParallelGroup().addComponent(numberofEmployeesLabel)
						.addComponent(numberofEmployeesTextField))
				.addGroup(layout.createParallelGroup().addComponent(hourlyRateLabel).addComponent(hourlyRateTextField))
				.addComponent(createJobPosting));

		pack();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				backToAllJobs();
			}
		});
		// <<<<<<< HEAD

		courseList.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				@SuppressWarnings("unchecked")
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedCourseList = cb.getSelectedIndex();
			}
		});

		jobList.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				@SuppressWarnings("unchecked")
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedJobList = cb.getSelectedIndex();
			}
		});

		createJobPosting.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				createJobPostingButtonActionPerformed();
			}
		});
	}

	// can also disable submit if string is empty
	private void refreshData() {
		// error
		errorMessage.setText(error);
		if (error == null || error.length() == 0) {
			selectedCourseList = -1;
			courseList.setSelectedIndex(selectedCourseList);
			selectedJobList = -1;
			jobList.setSelectedIndex(selectedJobList);
			deadlineDate.getModel().setValue(null);
			preferredExperienceTextField.setText("");
			numberofEmployeesTextField.setText("");
			hourlyRateTextField.setText("");
		}
		// this is needed because the size of the window changes depending on
		// whether an error message is shown or not
		pack();

	}

	private void createJobPostingButtonActionPerformed() {
		// call the controller
		Instructor prof = new Instructor("Daniel Varro", ms);

		error = "";

		int numEmployees;
		double hourlyRate;
//		String aSemester, String aCourseCoude, int aNumTutorial, int aNumLab, int aNumStudent, int aCredit, int aHourRequiredTa, int aHourRequiredGrader, double aBudgetCalculated, Instructor aInstructor, ManagementSystem aManagementSystem)
		  
		Course course = new Course("Winter 2017", courseList.getSelectedItem().toString(), 1, 1, 100, 3,60, 60, 10000.0, prof, ms);
		Allocation allocation = new Allocation(course);

		if (selectedCourseList < 0) {
			error += "Please select a course. ";
		}

		if (selectedJobList < 0) {
			error += "Please select a Job Title.";
		}
		if ((java.sql.Date) deadlineDate.getModel().getValue() == null) {
			error += "Please specify a date.";
		}

		if (numberofEmployeesTextField.getText().equals("")) {
			numEmployees = -1;
			error += "Please specify the number of positions!";
		}

		else if (!numberofEmployeesTextField.getText().matches("[0-9]+")) {
			numEmployees = -1;
			error += "Please enter only string for number of position.";
		}

		else {
			numEmployees = Integer.parseInt(numberofEmployeesTextField.getText());
		}

		if (hourlyRateTextField.getText().equals("")) {
			hourlyRate = -1.0;
			error += "Please specify the hour rate!";
		} else {
			try {
				hourlyRate = Double.parseDouble(hourlyRateTextField.getText());
			} catch (NumberFormatException e) {
				hourlyRate = -1.0;
			}

		}

		if (error.length() == 0) {
			// call the controller
			TamasController tc = new TamasController(ms);
			try {
				tc.createJob(jobList.getSelectedItem().toString(), (java.sql.Date) deadlineDate.getModel().getValue(),
						preferredExperienceTextField.getText(), numEmployees, hourlyRate, allocation.getCourse());
				backToAllJobs();
				dispose();
			} catch (InvalidInputException e) {
				error += e.getMessage();
			}
		}
		refreshData();
	}

	private void backToAllJobs() {
		new AllJobPostings(ms,user).setVisible(true);
	}

}
