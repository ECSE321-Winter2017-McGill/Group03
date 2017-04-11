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
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

public class AllCourses extends JFrame {


	private static final long serialVersionUID = 5797257474418419821L;

	private ManagementSystem ms;
	private static String filename = "output/data.xml";

	private Object user;
	private int selectedCourse;
	private HashMap<String, Course> courseMap = new HashMap<String, Course>();
	
	

	public AllCourses(ManagementSystem ms, Object user) {
		this.user = user;
		this.ms = ms;
		initComponents();
	}

	private void initComponents() {
		
		// get table data ready;

		String[] columnNames = { "Semester", "Course Code", "Course Name", "Credit", "Instructor",
				 "Num of Tutorials", "Num of Labs"};
		String[][] data = new String[ms.numberOfCourses() + 1][7];

		int i = 0;
		for (Instructor anInstructor : ms.getInstructors()) {
			for (Course aCourse : anInstructor.getCourses()) {
				data[i][0] = aCourse.getSemester();
				data[i][1] = aCourse.getCourseCode();
				data[i][2] = aCourse.getCourseName();
				data[i][3] = "" + aCourse.getCredit() + " credits";
				data[i][4] = anInstructor.getName();
				data[i][5] = "" + aCourse.getNumTutorial() + " / week";
				data[i][6] = "" + aCourse.getNumLab() + " / week";

				i++;
			}
		}
		

		// get table itself ready;
		final JTable table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(1200, 100));
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		JPanel commandPane = new JPanel();
		JPanel buttomPane = new JPanel();

		// get the rest of frame ready;
		final JComboBox<String> allCourse = new JComboBox<String>(new String[0]);
		JButton viewDetailButton = new JButton("View Details");
		JButton addCourse = new JButton("Add a Course");
		JButton backButton = new JButton("Back");
		
		// Combobox action listenser
		allCourse.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            JComboBox<String> cb = (JComboBox<String>) evt.getSource();
	            selectedCourse = cb.getSelectedIndex();
	        }
	    });

		for (Course aCourse: ms.getCourses()){
			String courseDescription = aCourse.getCourseName() + " (" + aCourse.getCourseCode() + ")";
			allCourse.addItem(courseDescription);
			courseMap.put(courseDescription, aCourse);
		}
		
		// get frame ready;
		setTitle("View All Course");
		BorderLayout layout = new BorderLayout();
		Container pane = getContentPane();
		pane.setLayout(layout);
		pane.add(scrollPane, BorderLayout.PAGE_START);
		if (user.getClass().equals(Instructor.class)) {
			commandPane.add(allCourse);
			commandPane.add(viewDetailButton);
			pane.add(commandPane, BorderLayout.CENTER);
			buttomPane.add(backButton);
			pane.add(buttomPane, BorderLayout.PAGE_END);
		} 
		else if (user.getClass().equals(Applicant.class)){
			buttomPane.add(backButton);
			pane.add(buttomPane, BorderLayout.PAGE_END);
		}
		else {
			commandPane.add(allCourse);
			commandPane.add(viewDetailButton);
			pane.add(commandPane, BorderLayout.CENTER);
			buttomPane.add(addCourse);
			buttomPane.add(backButton);
			pane.add(buttomPane, BorderLayout.PAGE_END);
		}
		pack();
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		
		// add actions listeners
		viewDetailButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (allCourse.getItemCount()==0){
					JOptionPane.showMessageDialog(AllCourses.this,
							"No course information has been submitted.");
				}else{
					String courseDescription = allCourse.getItemAt(selectedCourse).toString();
					Course selectedCourse = courseMap.get(courseDescription);
					System.out.print(courseDescription);
					new CourseDetails(ms,selectedCourse,user).setVisible(true);
				}
			}
		});
		
		
		
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
