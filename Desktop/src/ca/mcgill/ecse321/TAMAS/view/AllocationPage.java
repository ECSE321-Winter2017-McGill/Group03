package ca.mcgill.ecse321.TAMAS.view;

import ca.mcgill.ecse321.TAMAS.controller.TamasController;
import ca.mcgill.ecse321.TAMAS.model.Applicant;
import ca.mcgill.ecse321.TAMAS.model.Application;
import ca.mcgill.ecse321.TAMAS.model.Instructor;
import ca.mcgill.ecse321.TAMAS.model.JobPosting;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;
import ca.mcgill.ecse321.TAMAS.persistence.PersistenceXStream;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AllocationPage extends JFrame {
	private static String filename = "output/data.xml";
	
	private ManagementSystem ms;	
	private Object user;
	private List<Application> applications;
	
	
	public AllocationPage(ManagementSystem ms, Object user,List<Application> applications){
		this.ms = ms;
		this.user = user;
		this.applications = applications;
		initComponents();
		refreshData();
	}
	
	
	private void initComponents() {
		// get table data ready;
		String[] columnNames = { "Job Title", "Course Code", "Course Name", "Hour Required", "Hourly Rate",
				"Preferred Experience", "Submission Deadline" };
		List<Application> acceptedApplication = new ArrayList<>();
		
		for (JobPosting aJobPosting: ms.getJobPostings()){
			if (aJobPosting.getApplications().status)
				acceptedApplication.add();
		}
		String[][] data = new String[fds+ 1][7];
		int i = 0;

		
		
		
	}
	
	private void refreshData(){
		
	}
	
}
