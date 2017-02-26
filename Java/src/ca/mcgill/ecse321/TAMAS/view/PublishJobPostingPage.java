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
import javax.swing.WindowConstants;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import ca.mcgill.ecse321.TAMAS.ManagementSystem;

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
	
	private JButton createCourse;
	
	private String error = null;
	private JLabel errorMessage;
	private ManagementSystem rm;
	/** Creates new form ParticipantPage */
	public PublishJobPostingPage(ManagementSystem rm) {
	    this.rm = rm;

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
	    
	    createCourse = new JButton();
	    
	    courseLabel.setText("Select a course:");
	    jobLabel.setText("Job Title");
	    deadline.setText("Specifiy a deadline:");
	    preferredExperienceLabel.setText("Select the preferred experience");
	    numberofEmployeesLabel.setText("Select the number of positions:");
	    hourlyRateLabel.setText("Set the hourly rate");
	    createCourse.setText("Create new job:");
	    
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
	                .addComponent(courseLabel, 200, 350, 500)
	                .addComponent(jobLabel)
	                .addComponent(deadline)
	                .addComponent(preferredExperienceLabel)
	                .addComponent(numberofEmployeesLabel)
	                .addComponent(hourlyRateLabel)
	                .addComponent(createCourse)
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
	            .addComponent(createCourse)
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
//	    addParticipantButton.addActionListener(new java.awt.event.ActionListener() {
//	        public void actionPerformed(java.awt.event.ActionEvent evt) {
//	            addParticipantButtonActionPerformed();
//	        }
//	    });
	}
	private void backToMain(){
    	new MainPage().setVisible(true);
	}

}

