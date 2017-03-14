package ca.mcgill.ecse321.TAMAS.view;

import ca.mcgill.ecse321.TAMAS.model.Applicant;
import ca.mcgill.ecse321.TAMAS.model.Application;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;
import ca.mcgill.ecse321.TAMAS.persistence.PersistenceXStream;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AllCourses extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4515187663890954534L;

	private ManagementSystem ms;
	private static String filename = "output/data.xml";

	private String userName;

	public AllCourses(ManagementSystem ms, String userName) {
		this.userName = userName;
		this.ms = ms;
		initComponents();
	}

	private void initComponents() {
		// get table data ready;
		String[] columnNames = { "Job Title", "Course ID", "Application Status"};
		String[][] data = new String[ms.numberOfJobPostings()][3];
		int i = 0;
		Applicant me = null;
		for (Applicant at : ms.getApplicants()) {
			if (at.getName().equals(userName))
				me = at;
		}
		if (me != null) {
			for (Application an:me.getApplications()) {
				data[i][0] = an.getJobPosting().getJobTitle();
				data[i][1] = an.getJobPosting().getCourse().getCourseCoude();
				data[i][2] = an.getApplicationStatus();
				i++;
			}
		}else{
		}

		// get table itself ready;
		final JTable table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(700, 100));
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		JPanel butPane = new JPanel();

		// get the rest of frame ready;
		JButton addJobPosting = new JButton("App For a Job");
		butPane.add(addJobPosting);

		// get frame ready;
		setTitle("View All Application");
		BorderLayout layout = new BorderLayout();
		Container pane = getContentPane();
		pane.setLayout(layout);
		pane.add(scrollPane, BorderLayout.PAGE_START);
		pane.add(butPane, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		pack();
		setVisible(true);
		// add actions listeners
		addJobPosting.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final ManagementSystem ms = PersistenceXStream.initializeModelManager(filename);
				new ApplyForJob(ms,userName).setVisible(true);
				dispose();
			}
		});
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				backToMain();
			}
		});
	}

	private void backToMain() {
		new MainPage(userName).setVisible(true);
	}
}
