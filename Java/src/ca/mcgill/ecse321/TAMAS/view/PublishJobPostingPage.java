package ca.mcgill.ecse321.TAMAS.view;

import java.awt.Color;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import ca.mcgill.ecse321.TAMAS.Allocation;
import ca.mcgill.ecse321.TAMAS.Instructor;
import ca.mcgill.ecse321.TAMAS.ManagementSystem;
import ca.mcgill.ecse321.TAMAS.controller.InvalidInputException;
import ca.mcgill.ecse321.TAMAS.controller.TAMAScontroller;

public class PublishJobPostingPage extends JFrame {

	private static final long serialVersionUID = -3813819647258555349L;
	
	//attributes for GUI elements
	private JTextField participantNameTextField;
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
	
	private ManagementSystem ms;
	
	private int selectedJobList = -1;
	/** Creates new form ParticipantPage */
	public PublishJobPostingPage(ManagementSystem ms) {
	    this.ms = ms;
	    initComponents();
	}

	/** Creates new form ParticipantPage 
	 * @return */
	public void ParticipantRegistration(ManagementSystem ms) {
	    this.ms = ms;
	    initComponents();
	}
	
	// can also disable submit if string is empty
	private void refreshData() {
	    // error
	    errorMessage.setText(error);
	    if (error == null || error.length() == 0) {
	        // participant
	        participantNameTextField.setText("");
	    }
	    // this is needed because the size of the window changes depending on whether an error message is shown or not
	    pack();
	}
	
//	private void addParticipantButtonActionPerformed() {
//	    // call the controller
//	    EventRegistrationController erc = new EventRegistrationController(rm);
//	    error = null;
//	    try {
//	        erc.createParticipant(participantNameTextField.getText());
//	    } catch (InvalidInputException e) {
//	        error = e.getMessage();
//	    }
//	    // update visuals
//	    refreshData();
//	}
	
	private void initComponents() {
	    // elements for participant
//	    participantNameTextField = new JTextField();
//	    participantNameLabel = new JLabel();
//	    addParticipantButton = new JButton();

	    // global settings and listeners
	    

	    
	    
	 // elements for error message
	    errorMessage = new JLabel();
	    errorMessage.setForeground(Color.RED);
	    
		courseLabel = new JLabel();
	    courseList = new JComboBox<String>(new String[0]);
	    courseList.addItem(" ");
	    courseList.addItem("ECSE 321");
	    courseList.addItem("COMP 250");
	    courseList.addItem("COMP 251");
	    jobLabel = new JLabel();
	    jobList = new JComboBox<String>(new String[0]);
	    jobList.addItem(" ");
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
	    preferredExperienceLabel.setText("Select the preferred experience");
	    numberofEmployeesLabel.setText("Select the number of positions:");
	    hourlyRateLabel.setText("Set the hourly rate");
	    createJobPosting.setText("Create new job:");
	    
	    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    setTitle("Publish Job Posting");
	    
	    // layout
	    GroupLayout layout = new GroupLayout(getContentPane());
	    getContentPane().setLayout(layout);
	    layout.setAutoCreateGaps(true);
	    layout.setAutoCreateContainerGaps(true);

	    layout.setHorizontalGroup(
	            // error message is added here
	            layout.createParallelGroup()
	            .addComponent(errorMessage)
	            .addGroup(layout.createSequentialGroup()
	            .addGroup(layout.createParallelGroup()
	                .addComponent(courseLabel, 200, 350, 800)
	                .addComponent(jobLabel)
	                .addComponent(deadline)
	                .addComponent(preferredExperienceLabel)
	                .addComponent(numberofEmployeesLabel)
	                .addComponent(hourlyRateLabel)
	                .addComponent(createJobPosting)
	                )
	            .addGroup(layout.createParallelGroup()
		                .addComponent(courseList, 200, 200, 400)
		                .addComponent(jobList)
		                .addComponent(deadlineDate)
		                .addComponent(preferredExperienceTextField)
		                .addComponent(numberofEmployeesTextField)
		                .addComponent(hourlyRateTextField))
	            ));
	    //layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {createCourse, participantNameTextField});

	    layout.setVerticalGroup(
	            layout.createSequentialGroup()
	            // error message is added here
	            .addComponent(errorMessage)
	            .addGroup(layout.createParallelGroup()
	                .addComponent(courseLabel)
	                .addComponent(courseList))
	            .addGroup(layout.createParallelGroup()
		                .addComponent(jobLabel)
		                .addComponent(jobList))
	            .addGroup(layout.createParallelGroup()
		                .addComponent(deadline)
		                .addComponent(deadlineDate))
	            .addGroup(layout.createParallelGroup()
		                .addComponent(preferredExperienceLabel)
		                .addComponent(preferredExperienceTextField))
	            .addGroup(layout.createParallelGroup()
		                .addComponent(numberofEmployeesLabel)
		                .addComponent(numberofEmployeesTextField))
	            .addGroup(layout.createParallelGroup()
		                .addComponent(hourlyRateLabel)
		                .addComponent(hourlyRateTextField))
	            .addComponent(createJobPosting)
	            );

	    pack();
	    
	    
	    jobList.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
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
	
	private void createJobPostingButtonActionPerformed() {
	    // call the controller
	    TAMAScontroller tc = new TAMAScontroller(ms);
	    Instructor prof = new Instructor("Daniel Varro", ms);
	    
	    
	    String semester = "ECSE321";
	    String courseCoude = "1233";
	    int numTutorial = 2;
	    int numLab = 3;
	    int numStudent = 100;
	    int credit = 3;
	    int hourRequiredTa = 75;
	    int hourRequiredGrader = 20;
	    double budgetCalculated = 300;
	    Allocation allocation = new Allocation(semester, courseCoude, numTutorial, numLab, numStudent, credit, hourRequiredTa, hourRequiredGrader, budgetCalculated, prof, ms);

	    int numEmployees = Integer.parseInt(numberofEmployeesTextField.getText());
	    int hourlyRate = Integer.parseInt(hourlyRateTextField.getText());
	    // JSpinner actually returns a date and time
	    // force the same date for start and end time to ensure that only the times differ
//	    Calendar calendar = Calendar.getInstance();
//	    calendar.setTime((Date) startTimeSpinner.getValue());
//	    calendar.set(2000, 1, 1);
//	    Time startTime = new Time(calendar.getTime().getTime());
//	    calendar.setTime((Date) endTimeSpinner.getValue());
//	    calendar.set(2000, 1, 1);
//	    Time endTime = new Time(calendar.getTime().getTime());
	    
	    error = null;
	    try {
	    	tc.createJob(jobList.getSelectedItem().toString(), (java.sql.Date) deadlineDate.getModel().getValue(), preferredExperienceTextField.getText(), numEmployees, hourlyRate, allocation.getCourse());
	    }
	    catch (InvalidInputException e) {
	    	error = e.getMessage();
	    }
	    
	    // update visuals
	    //refreshData();
	}

}

