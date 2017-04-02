package ca.mcgill.ecse321.TAMAS.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import ca.mcgill.ecse321.TAMAS.model.Applicant;
import ca.mcgill.ecse321.TAMAS.model.Instructor;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;
import ca.mcgill.ecse321.TAMAS.persistence.PersistenceXStream;

public class MainPage extends JFrame {


	private static final long serialVersionUID = 3144791873613255242L;
	private JButton viewJobPosting;
	private JButton viewApplication;
	private JButton viewCourse;
	private JButton addTAEval;
	private static String fileName = "output/data.xml";

	private String userName;

	private Object user;

	public MainPage(Object user) {
		this.user = user;

		initComponents(user);

	}

	private void initComponents(Object role) {
		viewCourse = new JButton("View all Courses");
		viewCourse.setPreferredSize(new Dimension(300, 150));
		viewJobPosting = new JButton("View all Job Postings");
		viewJobPosting.setPreferredSize(new Dimension(300, 150));
		viewApplication = new JButton();
		viewApplication.setPreferredSize(new Dimension(300, 150));
		
		// Instructor Only
		addTAEval = new JButton("Write a new evaluation");
		addTAEval.setPreferredSize(new Dimension(300, 150));
		
		setTitle("Welcome Page");
		
		BorderLayout layout = new BorderLayout();
		Container pane = getContentPane();
		pane.setLayout(layout);

		if (role.getClass().equals(Instructor.class)) {
			pane.add(viewCourse, BorderLayout.PAGE_START);
			pane.add(viewJobPosting,BorderLayout.CENTER);
			pane.add(addTAEval,BorderLayout.LINE_START);
			viewApplication.setText("View all Applications");
			pane.add(viewApplication, BorderLayout.PAGE_END);
		} else if (role.getClass().equals(Applicant.class)) {
			pane.add(viewCourse, BorderLayout.PAGE_START);
			pane.add(viewJobPosting, BorderLayout.CENTER);
			viewApplication.setText("View my Applications");
			pane.add(viewApplication, BorderLayout.PAGE_END);
		} else {
			pane.add(viewCourse, BorderLayout.PAGE_START);
			pane.add(viewJobPosting,BorderLayout.CENTER);
			pane.add(addTAEval,BorderLayout.LINE_START);
			viewApplication.setText("View all Applications");
			pane.add(viewApplication, BorderLayout.PAGE_END);
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();

		viewJobPosting.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final ManagementSystem ms = PersistenceXStream.initializeModelManager(fileName);
				new AllJobPostings(ms, user).setVisible(true);
				dispose();
			}
		});

		viewApplication.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final ManagementSystem ms = PersistenceXStream.initializeModelManager(fileName);
				new AllApplication(ms, user).setVisible(true);
				dispose();
			}
		});
		viewCourse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				final ManagementSystem ms = PersistenceXStream.initializeModelManager(fileName);
				new AllCourses(ms, user).setVisible(true);
				dispose();
			}

		});
		addTAEval.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				final ManagementSystem ms = PersistenceXStream.initializeModelManager(fileName);
				new WriteEvaluation(ms, user).setVisible(true);
				dispose();
			}

		});
	}

	public void backToMain() {
		new MainPage(userName).setVisible(true);
	}
}
