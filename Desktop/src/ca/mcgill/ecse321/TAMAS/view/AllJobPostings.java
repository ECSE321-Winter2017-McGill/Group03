package ca.mcgill.ecse321.TAMAS.view;

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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AllJobPostings extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5884843490851166108L;
	private ManagementSystem ms;
	private static String filename = "output/data.xml";

	public AllJobPostings(ManagementSystem ms) {
		this.ms = ms;
		initComponents();
	}

	private void initComponents() {
		// get table data ready;
		String[] columnNames = { "Job Title", "Course ID", "Hour Required TA", "Hourly Rate", "Experience",
				"Submission Dead Line" };
		String[][] data = new String[ms.numberOfJobPostings()][6];
		int i = 0;
		for (JobPosting jp : ms.getJobPostings()) {
			data[i][0] = jp.getJobTitle();
			data[i][1] = jp.getCourse().getCourseCoude();
			data[i][2] = "" + jp.getCourse().getHourRequiredTa();
			data[i][3] = "" + jp.getHourRate();
			data[i][4] = jp.getPerferredExperience();
			data[i][5] = jp.getSubmissionDeadline().toString();
			i++;
		}

		// get table itself ready;
		final JTable table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(700, 100));
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		JPanel butPane = new JPanel();

		// get the rest of frame ready;
		JButton addJobPosting = new JButton("Add A New Job Posting");
		butPane.add(addJobPosting);

		// get frame ready;
		setTitle("View All Job Postings");
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
				new PublishJobPostingPage(ms).setVisible(true);
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
		new MainPage().setVisible(true);
	}
}
