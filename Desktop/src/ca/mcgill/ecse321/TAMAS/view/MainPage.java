package ca.mcgill.ecse321.TAMAS.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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

	private JPanel topPanel;
	private JPanel optionsPanel;

	private static String fileName = "output/data.xml";

	private Object user;

	public MainPage(Object user) {
		this.user = user;
		initComponents(user);
	}

	private void initComponents(Object role) {

		viewCourse = new JButton("   View All Courses   ");

		viewJobPosting = new JButton("View All Job Postings ");

		viewApplication = new JButton();

		// Instructor Only
		addTAEval = new JButton("Write A New Evaluation");

		setTitle("Welcome Page");

		topPanel = new JPanel(new GridLayout(0, 1));
		topPanel.setBackground(Color.BLACK);
		topPanel.add(new JLabel(new ImageIcon("MainPageTitle.JPG")));

		optionsPanel = new JPanel(new GridBagLayout());
		GridBagConstraints layout = new GridBagConstraints();
		optionsPanel.setBackground(Color.black);

		layout.insets = new Insets(10, 0, 0, 0);

		if (role.getClass().equals(Instructor.class)) {
			layout.gridx = 0;
			layout.gridy = 0;
			optionsPanel.add(viewCourse, layout);

			layout.gridx = 0;
			layout.gridy = 1;
			optionsPanel.add(viewJobPosting, layout);

			layout.gridx = 0;
			layout.gridy = 2;
			optionsPanel.add(addTAEval, layout);

			layout.insets = new Insets(10, 0, 20, 0);

			layout.gridx = 0;
			layout.gridy = 3;
			viewApplication.setText("View All Applications");
			optionsPanel.add(viewApplication, layout);

		} else if (role.getClass().equals(Applicant.class)) {
			layout.gridx = 0;
			layout.gridy = 0;
			optionsPanel.add(viewCourse, layout);

			layout.gridx = 0;
			layout.gridy = 1;
			optionsPanel.add(viewJobPosting, layout);

			layout.insets = new Insets(10, 0, 20, 0);

			layout.gridx = 0;
			layout.gridy = 2;
			optionsPanel.add(viewApplication, layout);
			viewApplication.setText("View My Applications");

		} else {
			layout.gridx = 0;
			layout.gridy = 0;
			optionsPanel.add(viewCourse, layout);

			layout.gridx = 0;
			layout.gridy = 1;
			optionsPanel.add(viewJobPosting, layout);

			layout.gridx = 0;
			layout.gridy = 2;
			optionsPanel.add(addTAEval, layout);

			layout.insets = new Insets(10, 0, 20, 0);

			layout.gridx = 0;
			layout.gridy = 3;
			optionsPanel.add(viewApplication, layout);
			viewApplication.setText("View All Applications");
		}

		add(topPanel, BorderLayout.NORTH); // Adds Panels into Frame
		add(optionsPanel, BorderLayout.CENTER);
		setBackground(Color.BLACK);

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

				if (ms.getApplicants().size() > 0) {
					new WriteEvaluation(ms, user).setVisible(true);
					dispose();
				}

				else {
					JOptionPane.showMessageDialog(MainPage.this,
							"You cannot write evaluation because\n" + "there is currently no applicant in the system");
				}
			}

		});

	}

}
