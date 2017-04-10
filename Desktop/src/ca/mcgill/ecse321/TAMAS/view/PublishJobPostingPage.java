package ca.mcgill.ecse321.TAMAS.view;

import java.awt.Color;
import java.awt.Font;
import java.sql.Date;
import java.util.HashMap;
import java.util.Properties;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import ca.mcgill.ecse321.TAMAS.controller.InvalidInputException;
import ca.mcgill.ecse321.TAMAS.controller.TamasController;
import ca.mcgill.ecse321.TAMAS.model.Course;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;

public class PublishJobPostingPage extends JFrame {

	private static final long serialVersionUID = -3813819647258555349L;

	// attributes for GUI elements
	private JLabel formTitle;
	private JLabel courseLabel;
	private JComboBox<String> courseToggleList;
	private JLabel jobLabel;
	private JComboBox<String> jobToggleList;
	private JLabel deadline;
	private JDatePickerImpl deadlineDate;
	private JLabel preferredExperienceLabel;
	private JTextField preferredExperienceTextField;
	private JLabel hourlyRateLabel;
	private JTextField hourlyRateTextField;
	private JButton createPostingButton;
	private JButton cancelButton;

	private JSeparator horizontalLineTop;

	private String error = null;
	private JLabel errorMessage;

	private ManagementSystem ms;

	private int selectedCourse = -1;
	private int selectedJob = -1;

	private Object user;
	private HashMap<String, Course> cMap = new HashMap<>();

	/**
	 * Class constructor
	 * @param ms Management system
	 * @param user User
	 */
	public PublishJobPostingPage(ManagementSystem ms, Object user) {
		this.ms = ms;
		this.user = user;
		initComponents();
		refreshData();
	}

	/**
	 * Initialize all components
	 */
	private void initComponents() {

		formTitle = new JLabel("Publish Job Posting");
		formTitle.setFont(new Font("Georgia", Font.BOLD, 22));

		horizontalLineTop = new JSeparator();

		// elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);

		courseLabel = new JLabel("Select a course:");
		courseLabel.setForeground(Color.BLACK);
		courseToggleList = new JComboBox<String>();
		for (Course c : ms.getCourses()) {
			String display = c.getCourseCode();
			cMap.put(display, c);
			courseToggleList.addItem(display);
		}

		jobLabel = new JLabel("Job Title");
		jobLabel.setForeground(Color.BLACK);
		jobToggleList = new JComboBox<String>();
		jobToggleList.addItem("TA");
		jobToggleList.addItem("Grader");

		deadline = new JLabel("Specify a deadline:");
		deadline.setForeground(Color.BLACK);

		SqlDateModel model = new SqlDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		deadlineDate = new JDatePickerImpl(datePanel, new DateLabelFormatter());

		preferredExperienceLabel = new JLabel("Enter the preferred experience:");
		preferredExperienceLabel.setForeground(Color.BLACK);
		preferredExperienceTextField = new JTextField();

		hourlyRateLabel = new JLabel("Set Hourly Rate:");
		hourlyRateLabel.setForeground(Color.BLACK);
		hourlyRateTextField = new JTextField();

		createPostingButton = new JButton("Publish Job Posting");
		createPostingButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				createJobPostingButtonActionPerformed();
			}
		});

		cancelButton = new JButton("  Cancel  ");
		cancelButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelButtonActionPerformed(evt);
			}
		});
		setTitle("Publish Job Posting");

		// layout
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(layout.createParallelGroup().addComponent(formTitle).addComponent(errorMessage)
				.addComponent(horizontalLineTop)

				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup().addComponent(courseLabel, 200, 250, 500)
								.addComponent(jobLabel).addComponent(deadline).addComponent(preferredExperienceLabel)
								.addComponent(hourlyRateLabel).addComponent(cancelButton))
						.addGroup(layout.createParallelGroup().addComponent(courseToggleList, 200, 250, 500)
								.addComponent(jobToggleList).addComponent(deadlineDate)
								.addComponent(preferredExperienceTextField).addComponent(hourlyRateTextField)
								.addComponent(createPostingButton))));

		layout.setVerticalGroup(layout.createSequentialGroup().addComponent(formTitle)
				.addGroup(layout.createParallelGroup().addComponent(horizontalLineTop)).addComponent(errorMessage)
				.addGroup(layout.createParallelGroup().addComponent(courseLabel).addComponent(courseToggleList))
				.addGroup(layout.createParallelGroup().addComponent(jobLabel).addComponent(jobToggleList))
				.addGroup(layout.createParallelGroup().addComponent(deadline).addComponent(deadlineDate))
				.addGroup(layout.createParallelGroup().addComponent(preferredExperienceLabel)
						.addComponent(preferredExperienceTextField, 23, 23, 23))
				.addGroup(layout.createParallelGroup().addComponent(hourlyRateLabel).addComponent(hourlyRateTextField,
						23, 23, 23))
				.addGroup(layout.createParallelGroup().addComponent(createPostingButton).addComponent(cancelButton)));

		pack();

		courseToggleList.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				@SuppressWarnings("unchecked")
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedCourse = cb.getSelectedIndex();

			}
		});
		jobToggleList.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				@SuppressWarnings("unchecked")
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedJob = cb.getSelectedIndex();
			}
		});
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);
		

	}

	// can also disable submit if string is empty
	/**
	 * Display error, or reset page if there are no errors
	 */
	private void refreshData() {
		// error
		errorMessage.setText(error);
		if (error == null || error.length() == 0) {
			selectedCourse = -1;
			courseToggleList.setSelectedIndex(selectedCourse);

			selectedJob = -1;
			jobToggleList.setSelectedIndex(selectedJob);

			deadlineDate.getModel().setValue(null);

			preferredExperienceTextField.setText("");
			hourlyRateTextField.setText("");
		}
		// this is needed because the size of the window changes depending on
		// whether an error message is shown or not
		pack();

	}

	/**
	 * Action triggered when creating a new job posting
	 */
	private void createJobPostingButtonActionPerformed() {
		// call the controller

		// Instructor prof = new Instructor("Daniel Varro", ms);
		// Course course = new Course("Winter 2017", "Intro to
		// SE",courseToggleList.getSelectedItem().toString(), 1, 1, 100, 3, 1,
		// 1, 60, 60, 10000.0, prof, ms);
		// Allocation allocation = new Allocation(course);

		error = "";

		double hourlyRate;

		if (selectedCourse < 0) {
			error += "Please select a course.";
		}

		if (selectedJob < 0) {
			error += "Please select a Job Title.";
		}

		if (error.trim().length() == 0) {
			// call the controller
			TamasController tc = new TamasController(ms);

			Date deadline = (java.sql.Date) deadlineDate.getModel().getValue();

			if (hourlyRateTextField.getText().equals("")) {
				hourlyRate = -1.0;
			} else {
				try {
					hourlyRate = Double.parseDouble(hourlyRateTextField.getText());
				} catch (NumberFormatException e) {
					System.out.print(e.getMessage());
					hourlyRate = -1.0;
				}
			}
			try {
				tc.createJobPosting(jobToggleList.getSelectedItem().toString(), deadline,
						preferredExperienceTextField.getText(), hourlyRate,
						cMap.get((String) courseToggleList.getSelectedItem()));
				JOptionPane.showMessageDialog(PublishJobPostingPage.this,"Job Posting Published");
				setVisible(false);
				backToAllJobs();
			} catch (InvalidInputException e) {
				error += e.getMessage();
			}
		}
		refreshData();
	}

	/**
	 * Action triggered when cancelling
	 * @param evt Action event
	 */
	private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
		backToAllJobs();
		setVisible(false);
	}

	/**
	 * Return to all job postings page
	 */
	private void backToAllJobs() {
		new AllJobPostings(ms, user).setVisible(true);
	}

}
