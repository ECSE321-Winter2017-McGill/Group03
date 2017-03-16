package ca.mcgill.ecse321.TAMAS.view;

import ca.mcgill.ecse321.TAMAS.model.Applicant;
import ca.mcgill.ecse321.TAMAS.model.Application;
import ca.mcgill.ecse321.TAMAS.model.Course;
import ca.mcgill.ecse321.TAMAS.model.Instructor;
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
	private static final long serialVersionUID = 5797257474418419821L;

	private ManagementSystem ms;
	private static String filename = "output/data.xml";

	private Object user;

	public AllCourses(ManagementSystem ms, Object user) {
		this.user = user;
		this.ms = ms;
		initComponents();
	}

	private void initComponents() {
		// get table data ready;
		
//		  private String semester;
//		  private String courseCoude;
//		  private int numTutorial;
//		  private int numLab;
//		  private int numStudent;
//		  private int credit;
//		  private int hourRequiredTa;
//		  private int hourRequiredGrader;
//		  private double budgetCalculated;
		String[] columnNames = { "Instructor Name", "Semester", "Course ID", "Hour Required Ta" };
		String[][] data = new String[ms.numberOfApplicants()*3][4];

		int i = 0;
		if (user.getClass().equals(Instructor.class)) {
			Instructor me = (Instructor) user;
			for (Course an : me.getCourses()) {
				data[i][0] = me.getName();
				data[i][1] = an.getSemester();
				data[i][2] = an.getCourseCoude();
				data[i][3] = ""+an.getHourRequiredTa();
				i++;
			}
		} else {
			for (Instructor an : ms.getInstructors()) {
				for (Course ap : an.getCourses()) {
					data[i][0] = an.getName();
					data[i][1] = ap.getSemester();
					data[i][2] = ap.getCourseCoude();
					data[i][3] = ""+ap.getHourRequiredTa();
					i++;
				}
			}
		}

		// get table itself ready;
		final JTable table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(700, 100));
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		JPanel butPane = new JPanel();

		// get the rest of frame ready;
		JButton addJobPosting = new JButton("Add a new Course");
		butPane.add(addJobPosting);

		// get frame ready;
		setTitle("View All Course");
		BorderLayout layout = new BorderLayout();
		Container pane = getContentPane();
		pane.setLayout(layout);
		pane.add(scrollPane, BorderLayout.PAGE_START);
		if (user.getClass().equals(Instructor.class)) {

		} else {
			pane.add(butPane, BorderLayout.CENTER);
		}
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		pack();
		setVisible(true);
		// add actions listeners
		addJobPosting.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final ManagementSystem ms = PersistenceXStream.initializeModelManager(filename);
				new ApplyForJob(ms, user).setVisible(true);
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
		new MainPage(user).setVisible(true);
	}
}
