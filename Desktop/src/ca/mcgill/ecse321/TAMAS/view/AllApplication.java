package ca.mcgill.ecse321.TAMAS.view;

import ca.mcgill.ecse321.TAMAS.controller.TamasController;
import ca.mcgill.ecse321.TAMAS.model.Applicant;
import ca.mcgill.ecse321.TAMAS.model.Application;
import ca.mcgill.ecse321.TAMAS.model.Course;
import ca.mcgill.ecse321.TAMAS.model.Instructor;
import ca.mcgill.ecse321.TAMAS.model.JobPosting;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;
import ca.mcgill.ecse321.TAMAS.persistence.PersistenceXStream;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

public class AllApplication extends JFrame {


	private static final long serialVersionUID = 5797257474418419821L;

	private ManagementSystem ms;
	private static String filename = "output/data.xml";

	private Object user;
	private int selectedCourse;
	private HashMap<String, Course> courseMap = new HashMap<String, Course>();
	private TamasController tc;
	private String[][] data;
	private JTable table;

	public AllApplication(ManagementSystem ms, Object user) {
		this.user = user;
		this.ms = ms;
		tc = new TamasController(ms);
		data = new String[ms.numberOfApplicants() * 3 + 1][5];
		initComponents();
	}

	private void initComponents() {
		// get table data ready;
		String[] columnNames = { "Applicant Name", "U/G", "Job Title", "Course Code", "Application Status" };

		int i = 0;
		if (user.getClass().equals(Applicant.class)) {
			Applicant me = (Applicant) user;
			for (Application myApplication : me.getApplications()) {
				data[i][0] = me.getName();

				if (me.getIsUnderGraduated() == true) {
					data[i][1] = "Und";
				} else {
					data[i][1] = "Grad";
				}

				data[i][2] = myApplication.getJobPosting().getJobTitle();
				data[i][3] = myApplication.getJobPosting().getCourse().getCourseCode();
				data[i][4] = myApplication.getStatus().toString();
				i++;
			}
		} else if (user.getClass().equals(Instructor.class)) {
			Instructor anInstructor = (Instructor) user;
			List<Course> myCourses = anInstructor.getCourses();
			List<JobPosting> relatedJobPosting = new ArrayList<>();
			for (Course aCourse : myCourses) {
				relatedJobPosting.addAll(aCourse.getJobPosting());
			}
			for (JobPosting aJobPosting : relatedJobPosting) {
				List<Application> allApplication = aJobPosting.getApplications();
				for (Application aApplication : allApplication) {
					data[i][0] = aApplication.getApplicant().getName();

					if (aApplication.getApplicant().getIsUnderGraduated() == true) {
						data[i][1] = "Und";
					} else {
						data[i][1] = "Grad";
					}

					data[i][2] = aApplication.getJobPosting().getJobTitle();
					data[i][3] = aApplication.getJobPosting().getCourse().getCourseCode();
					data[i][4] = aApplication.getStatus().toString();
					i++;
				}
			}
		} else {
			for (Applicant anApplicant : ms.getApplicants()) {
				for (Application aApplication : anApplicant.getApplications()) {
					data[i][0] = anApplicant.getName();

					if (anApplicant.getIsUnderGraduated() == true) {
						data[i][1] = "Und";
					} else {
						data[i][1] = "Grad";
					}

					data[i][2] = aApplication.getJobPosting().getJobTitle();
					data[i][3] = aApplication.getJobPosting().getCourse().getCourseCode();
					data[i][4] = aApplication.getStatus().toString();
					i++;
				}
			}
		}

		// get table itself ready;
		table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(700, 100));
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		JPanel buttomPane = new JPanel();
		JPanel commandPane = new JPanel();

		// get the rest of frame ready;
		JButton applyJobButton = new JButton("Apply for a Position");
		final JComboBox<String> allJobPostingCourse = new JComboBox<String>(new String[0]);
		JButton backButton = new JButton("Back");
		JButton viewAllocation = new JButton("View Allocation");

		// get frame ready;
		

		BorderLayout layout = new BorderLayout();
		Container pane = getContentPane();
		pane.setLayout(layout);
		pane.add(scrollPane, BorderLayout.PAGE_START);
		if (user.getClass().equals(Instructor.class)) {
			setTitle("View All Applications");
			
			buttomPane.add(backButton);
			pane.add(buttomPane, BorderLayout.PAGE_END);
						

			Instructor anInstructor = (Instructor) user;
			List<Course> myCourses = anInstructor.getCourses();
			for (Course aCourse : myCourses) {
				String courseDescription = aCourse.getCourseName() + " (" + aCourse.getCourseCode() + ")";
				allJobPostingCourse.addItem(courseDescription);
				courseMap.put(courseDescription, aCourse);
			}
			
			commandPane.add(allJobPostingCourse);
			commandPane.add(viewAllocation);
			pane.add(commandPane, BorderLayout.CENTER);
		} else if (user.getClass().equals(Applicant.class)) {
			setTitle("View My Applications");
			
			buttomPane.add(applyJobButton);
			buttomPane.add(backButton);
			pane.add(buttomPane, BorderLayout.PAGE_END);
		} else {
			setTitle("View All Applications");
			
			buttomPane.add(applyJobButton);
			buttomPane.add(backButton);
			pane.add(buttomPane, BorderLayout.PAGE_END);

			for (Course aCourse: ms.getCourses()){
				String courseDescription = aCourse.getCourseName() + " (" + aCourse.getCourseCode() + ")";
				allJobPostingCourse.addItem(courseDescription);
				courseMap.put(courseDescription, aCourse);
			}
						
			commandPane.add(allJobPostingCourse);
			commandPane.add(viewAllocation);
			pane.add(commandPane, BorderLayout.CENTER);
		}
		pack();
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		

		// add actions listeners
		applyJobButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (ms.getCourses().size()==0){
					JOptionPane.showMessageDialog(AllApplication.this,
							"No Job Posting has been published.\n"
							+ "Please try again later.");
				}else{
					final ManagementSystem ms = PersistenceXStream.initializeModelManager(filename);
					new ApplyForJob(ms, user).setVisible(true);
					dispose();
				}
			}
		});

		allJobPostingCourse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unchecked")
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
				selectedCourse = cb.getSelectedIndex();
			}
		});
		
		
		
		viewAllocation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (allJobPostingCourse.getItemCount()==0){
					JOptionPane.showMessageDialog(AllApplication.this,
							"No course information has been submitted.");
				}else{
					String courseDescription = allJobPostingCourse.getItemAt(selectedCourse).toString();
					Course selectedCourse = courseMap.get(courseDescription);
					
					new AllocationPage1(ms,selectedCourse,user).setVisible(true);
					setVisible(false);
					}
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