package ca.mcgill.ecse321.TAMAS.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;
import ca.mcgill.ecse321.TAMAS.persistence.PersistenceXStream;

public class MainPage extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3144791873613255242L;
	private JButton addJobPosting;
	private JButton applyForJob;
	private static String fileName = "output/data.xml";

	public MainPage() {
		initComponents();
	}

	private void initComponents() {
		
		// TODO Auto-generated method stub
		addJobPosting = new JButton("Add Job Posting");
		addJobPosting.setPreferredSize(new Dimension(300,150));
		applyForJob = new JButton("Apply For Job");
		applyForJob.setPreferredSize(new Dimension(300,150));
		setTitle("Welcome Page");
		BorderLayout layout = new BorderLayout();
		Container pane = getContentPane();
		pane.setLayout(layout);
		pane.add(addJobPosting, BorderLayout.PAGE_START);
		pane.add(applyForJob, BorderLayout.PAGE_END);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		
		
		addJobPosting.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final ManagementSystem ms = PersistenceXStream.initializeModelManager(fileName);
//				new PublishJobPostingPage(ms).setVisible(true);
				new AllJobPostings(ms).setVisible(true);
				dispose();
			}
		});
		
		applyForJob.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				final ManagementSystem ms = PersistenceXStream.initializeModelManager(fileName);
				new JobApplicationPage(ms).setVisible(true);
				dispose();
			}
		});
	}

	public void backToMain() {
		new MainPage().setVisible(true);
	}
}
