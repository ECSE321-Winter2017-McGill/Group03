package ca.mcgill.ecse321.TAMAS.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import ca.mcgill.ecse321.TAMAS.model.Applicant;
import ca.mcgill.ecse321.TAMAS.model.Department;
import ca.mcgill.ecse321.TAMAS.model.Instructor;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;
import ca.mcgill.ecse321.TAMAS.persistence.PersistenceXStream;

public class MainPage extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3144791873613255242L;
	private JButton addJobPosting;
	private JButton applyForJob;
	private JButton addCourse;
	private static String fileName = "output/data.xml";

	private String userName;

	private Object user;

	public MainPage(Object user) {
		this.user = user;

		initComponents(user);

	}

	private void initComponents(Object role) {
		addCourse = new JButton("Courses");
		addCourse.setPreferredSize(new Dimension(300, 150));
		addJobPosting = new JButton("Job Postings");
		addJobPosting.setPreferredSize(new Dimension(300, 150));
		applyForJob = new JButton("Applications");
		applyForJob.setPreferredSize(new Dimension(300, 150));
		setTitle("Welcome Page");
		BorderLayout layout = new BorderLayout();
		Container pane = getContentPane();
		pane.setLayout(layout);

		if (role.getClass().equals(Instructor.class)) {
			pane.add(addCourse, BorderLayout.PAGE_START);
			pane.add(addJobPosting);
			pane.add(applyForJob, BorderLayout.PAGE_END);
		} else if (role.getClass().equals(Applicant.class)) {
			pane.add(addJobPosting, BorderLayout.PAGE_START);
			pane.add(applyForJob, BorderLayout.PAGE_END);
		} else {
			pane.add(addCourse, BorderLayout.PAGE_START);
			pane.add(addJobPosting);
			pane.add(applyForJob, BorderLayout.PAGE_END);
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();

		addJobPosting.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final ManagementSystem ms = PersistenceXStream.initializeModelManager(fileName);
				new AllJobPostings(ms, user).setVisible(true);
				dispose();
			}
		});

		applyForJob.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final ManagementSystem ms = PersistenceXStream.initializeModelManager(fileName);
				new AllApplication(ms, user).setVisible(true);
				dispose();
			}
		});
		addCourse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				final ManagementSystem ms = PersistenceXStream.initializeModelManager(fileName);
				new AllCourses(ms, user).setVisible(true);
				dispose();
			}

		});
	}

	public void backToMain() {
		new MainPage(userName).setVisible(true);
	}
}
