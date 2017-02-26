package ca.mcgill.ecse321.TAMAS.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import ca.mcgill.ecse321.TAMAS.ManagementSystem;
import ca.mcgill.ecse321.TAMAS.persistence.PersistenceXStream;

public class MainPage extends JFrame {
	private JButton addJobPosting;
	private JButton applyForJob;
	private static String fileName = "output/publishjob.xml";

	public MainPage() {
		initComponents();
	}

	private void initComponents() {
		// TODO Auto-generated method stub
		addJobPosting = new JButton("Add Job Posting");
		applyForJob = new JButton("Apply For Job");
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
				// TODO Auto-generated method stub
				// System.out.println(e.getActionCommand());
				final ManagementSystem rm = PersistenceXStream.initializeModelManager(fileName);
				new PublishJobPostingPage(rm).setVisible(true);
				dispose();
			}
		});
	}

	public void backToMain() {
		new MainPage().setVisible(true);
	}
}
