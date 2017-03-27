package ca.mcgill.ecse321.TAMAS.view;

import ca.mcgill.ecse321.TAMAS.controller.TamasController;
import ca.mcgill.ecse321.TAMAS.model.Applicant;
import ca.mcgill.ecse321.TAMAS.model.Application;
import ca.mcgill.ecse321.TAMAS.model.Course;
import ca.mcgill.ecse321.TAMAS.model.Instructor;
import ca.mcgill.ecse321.TAMAS.model.JobPosting;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;
import ca.mcgill.ecse321.TAMAS.persistence.PersistenceXStream;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

public class AllApplication extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5797257474418419821L;

	private ManagementSystem ms;
	private static String filename = "output/data.xml";

	private Object user;
	private int selectedApplication;
	private HashMap<String, Application> applicationMap = new HashMap<String, Application>();
    private TamasController tm;
    private String[][] data;
    private JTable table;
    
	public AllApplication(ManagementSystem ms, Object user) {
		this.user = user;
		this.ms = ms;
		tm = new TamasController(ms);
		data = new String[ms.numberOfApplicants() * 3 + 1][5];
		initComponents();
	}

	private void initComponents() {
		// get table data ready;
		String[] columnNames = { "Applicant Name", "U/G", "Job Title", "Course Code", "Application Status" };
		
		int i = 0;
		if (user.getClass().equals(Applicant.class)) {
			Applicant me = (Applicant) user;
			for (Application myApplication : me.getApplications()) {
				data[i][0] = me.getName();
				
				if (me.getIsUnderGraduated()==true){
					data[i][1] = "Und";
				}
				else{
					data[i][1] = "Grad";
				}
				
				data[i][2] = myApplication.getJobPosting().getJobTitle();
				data[i][3] = myApplication.getJobPosting().getCourse().getCourseCode();
				data[i][4] = myApplication.getApplicationStatus();
				i++;
			}
		} else if (user.getClass().equals(Instructor.class)) {
			Instructor anInstructor = (Instructor) user;
			List<Course> myCourses = anInstructor.getCourses();
			List<JobPosting> relatedJobPosting = new ArrayList<>();
			for (Course aCourse : myCourses) {
				relatedJobPosting.addAll(aCourse.getJobPosting());
			}
			for (JobPosting aJobPosting: relatedJobPosting) {
				List<Application> allApplication = aJobPosting.getApplications();
				for (Application aApplication : allApplication) {
					data[i][0] = aApplication.getApplicant().getName();
					
					if (aApplication.getApplicant().getIsUnderGraduated()==true){
						data[i][1] = "Und";
					}
					else{
						data[i][1] = "Grad";
					}
					
					data[i][2] = aApplication.getJobPosting().getJobTitle();
					data[i][3] = aApplication.getJobPosting().getCourse().getCourseCode();
					data[i][4] = aApplication.getApplicationStatus();
					i++;
				}
			}
		} else {
			for (Applicant anApplicant : ms.getApplicants()) {
				for (Application aApplication : anApplicant.getApplications()) {
					data[i][0] = anApplicant.getName();
					
					if (anApplicant.getIsUnderGraduated()==true){
						data[i][1] = "Und";
					}
					else{
						data[i][1] = "Grad";
					}
					
					data[i][2] = aApplication.getJobPosting().getJobTitle();
					data[i][3] = aApplication.getJobPosting().getCourse().getCourseCode();
					data[i][4] = aApplication.getApplicationStatus();
					i++;
				}
			}
		}

		// get table itself ready;
		table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(700, 100));
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		JPanel buttomPane = new JPanel();
		JPanel commandPane = new JPanel();

		// get the rest of frame ready;
		JButton applyJobButton = new JButton("Apply For a Job");
		final JComboBox allApplication = new JComboBox<String>();
		for (Applicant anApplicant : ms.getApplicants()) {
			for (Application aApplication : anApplicant.getApplications()) {
				String appDescription = anApplicant.getName() + " (" + aApplication.getJobPosting().getJobTitle() 
						+ " for " + aApplication.getJobPosting().getCourse().getCourseCode() + ")";
				allApplication.addItem(appDescription);
				applicationMap.put(appDescription,aApplication);
			}
		}
		JButton acceptAppButton = new JButton("Accept");
		JButton rejectAppButton = new JButton("Reject");
		JButton backButton = new JButton("Back");
		        
		// get frame ready;
		setTitle("View All Application");
		
		BorderLayout layout = new BorderLayout();
		Container pane = getContentPane();
		pane.setLayout(layout);
		pane.add(scrollPane, BorderLayout.PAGE_START);
		if (user.getClass().equals(Instructor.class)) {
			buttomPane.add(backButton);
			pane.add(buttomPane, BorderLayout.PAGE_END);
		} 
		else if (user.getClass().equals(Applicant.class)){
		    buttomPane.add(applyJobButton);
		    buttomPane.add(backButton);
			pane.add(buttomPane, BorderLayout.PAGE_END);
		}
		else {
		    buttomPane.add(applyJobButton);
		    buttomPane.add(backButton);
			pane.add(buttomPane, BorderLayout.PAGE_END);
			
			commandPane.add(allApplication);
			commandPane.add(acceptAppButton);
			commandPane.add(rejectAppButton);
			pane.add(commandPane, BorderLayout.CENTER);
		}
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		pack();
		setVisible(true);
		
		// add actions listeners
		applyJobButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(AllApplication.this,"If application deadline passed\n"
						+ "remove the job posting from available positions");
				
				final ManagementSystem ms = PersistenceXStream.initializeModelManager(filename);
				new ApplyForJob(ms, user).setVisible(true);
				dispose();
			}
		});
		
		allApplication.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
		        JComboBox<String> cb = (JComboBox<String>) e.getSource();
		        selectedApplication = cb.getSelectedIndex();
			}
		});
	
		acceptAppButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
		        String appDescription = allApplication.getItemAt(selectedApplication).toString();
				Application selectedApp = applicationMap.get(appDescription);
			    tm.acceptApplication(selectedApp);
			    dispose();
			    initComponents();
			}
		});
		
		rejectAppButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 String appDescription = allApplication.getItemAt(selectedApplication).toString();	
				 Application selectedApp = applicationMap.get(appDescription);
				 tm.rejectApplication(selectedApp);
				 dispose();
				 initComponents();	
			}
		});
		
		rejectAppButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 String appDescription = allApplication.getItemAt(selectedApplication).toString();	
				 Application selectedApp = applicationMap.get(appDescription);
				 tm.rejectApplication(selectedApp);
				 dispose();
				 initComponents();	
			}
		});

		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				backToMain();
				setVisible(false);
			}
		});
		
		
	}

	private void backToMain() {
		new MainPage(user).setVisible(true);
	}
}
