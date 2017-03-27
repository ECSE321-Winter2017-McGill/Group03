package ca.mcgill.ecse321.TAMAS.view;

import ca.mcgill.ecse321.TAMAS.model.Applicant;
import ca.mcgill.ecse321.TAMAS.model.Course;
import ca.mcgill.ecse321.TAMAS.model.Instructor;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;
import ca.mcgill.ecse321.TAMAS.persistence.PersistenceXStream;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

		String[] columnNames = { "Semester", "Course Code", "Course Name", "Credit", "Instructor",
				"Number of TA", "Number of Grader", "Num of Tutorials", "Num of Labs"};
		String[][] data = new String[ms.numberOfCourses() + 1][9];

		int i = 0;
		for (Instructor anInstructor : ms.getInstructors()) {
			for (Course aCourse : anInstructor.getCourses()) {
				data[i][0] = aCourse.getSemester();
				data[i][1] = aCourse.getCourseCode();
				data[i][2] = aCourse.getCourseName();
				data[i][3] = "" + aCourse.getCredit() + " credits";
				data[i][4] = anInstructor.getName();
				data[i][5] = "" + aCourse.getNumTaNeeded();
				data[i][6] = "" + aCourse.getNumGraderNeeded();
				data[i][7] = "" + aCourse.getNumTutorial() + " / week";
				data[i][8] = "" + aCourse.getNumLab() + " / week";

				i++;
			}
		}
		

		// get table itself ready;
		final JTable table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(1200, 100));
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		JPanel buttomPane = new JPanel();

		// get the rest of frame ready;
		JButton addCourse = new JButton("Add a new Course");
		JButton backButton = new JButton("Back");


		// get frame ready;
		setTitle("View All Course");
		BorderLayout layout = new BorderLayout();
		Container pane = getContentPane();
		pane.setLayout(layout);
		pane.add(scrollPane, BorderLayout.PAGE_START);
		if (user.getClass().equals(Instructor.class)) {
			buttomPane.add(backButton);
			pane.add(buttomPane, BorderLayout.CENTER);
		} 
		else if (user.getClass().equals(Applicant.class)){
			buttomPane.add(backButton);
			pane.add(buttomPane, BorderLayout.CENTER);
		}
		else {
			buttomPane.add(addCourse);
			buttomPane.add(backButton);
			pane.add(buttomPane, BorderLayout.CENTER);
		}
		pack();
		setVisible(true);
		// add actions listeners
		addCourse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final ManagementSystem ms = PersistenceXStream.initializeModelManager(filename);
				new AddCourse(ms, user).setVisible(true);
				dispose();
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
