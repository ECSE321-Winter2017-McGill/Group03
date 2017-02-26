package ca.mcgill.ecse321.TAMAS.view;

import java.awt.Color;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

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
//<<<<<<< HEAD
	
	private ManagementSystem ms;
	
	private int selectedCourseList = -1;
	private int selectedJobList = -1;
	
	public PublishJobPostingPage(ManagementSystem ms) {
	    this.ms = ms;
	    initComponents();
	}

//	public void ParticipantRegistration(ManagementSystem ms) {
//	    this.ms = ms;
//
//	    initComponents();
//	}

	
	
	
	
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
	    preferredExperienceLabel.setText("Select the preferred experience");
	    numberofEmployeesLabel.setText("Select the number of positions:");
	    hourlyRateLabel.setText("Set the hourly rate");
	    createJobPosting.setText("Create new job:");
	    
	    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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
	                .addComponent(courseLabel, 200, 350, 1000)
	                .addComponent(jobLabel)
	                .addComponent(deadline)
	                .addComponent(preferredExperienceLabel)
	                .addComponent(numberofEmployeesLabel)
	                .addComponent(hourlyRateLabel)
	                .addComponent(createJobPosting)
	                )
	            .addGroup(layout.createParallelGroup()
		                .addComponent(courseList, 200, 350, 1000)
		                .addComponent(jobList)
		                .addComponent(deadlineDate)
		                .addComponent(preferredExperienceTextField)
		                .addComponent(numberofEmployeesTextField)
		                .addComponent(hourlyRateTextField))
	            ));
	    layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {createJobPosting});

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
	    addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
            	backToMain();
            }
        });
//<<<<<<< HEAD
	    
	    
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
		    // this is needed because the size of the window changes depending on whether an error message is shown or not
		    pack();
		    
		    
		}
	
	private void createJobPostingButtonActionPerformed() {
	    // call the controller
	    TAMAScontroller tc = new TAMAScontroller(ms);
	    Instructor prof = new Instructor("Daniel Varro", ms);
	    
	    
	    String semester = "Fall";
	    String courseCoude = courseList.getSelectedItem().toString();
	    int numTutorial = 2;
	    int numLab = 3;
	    int numStudent = 100;
	    int credit = 3;
	    int hourRequiredTa = 75;
	    int hourRequiredGrader = 20;
	    double budgetCalculated = 300;
	    int numEmployees;
		int hourlyRate;
	    Allocation allocation = new Allocation(semester, courseCoude, numTutorial, numLab, numStudent, credit, hourRequiredTa, hourRequiredGrader, budgetCalculated, prof, ms);
	    if (numberofEmployeesTextField.getText().equals("")) {
	    	numEmployees = -1;
	    }
	    else {
	    	numEmployees = Integer.parseInt(numberofEmployeesTextField.getText());
	    }
	    
	    if (hourlyRateTextField.getText().equals("")) {
	    	hourlyRate = -1;
	    }
	    else {
	    	hourlyRate = Integer.parseInt(hourlyRateTextField.getText());
	    }

	    error = null;
	    try {
	    	tc.createJob(jobList.getSelectedItem().toString(), (java.sql.Date) deadlineDate.getModel().getValue(), preferredExperienceTextField.getText(), numEmployees, hourlyRate, allocation.getCourse());
	    }
	    catch (InvalidInputException e) {
	    	error = e.getMessage();
	    }

	    refreshData();
	
	    }
	
	private void backToMain(){
    	new MainPage().setVisible(true);
	}

}

