package ca.mcgill.ecse321.TAMAS.view;

import ca.mcgill.ecse321.TAMAS.controller.TamasController;
import ca.mcgill.ecse321.TAMAS.model.Applicant;
import ca.mcgill.ecse321.TAMAS.model.Application;
import ca.mcgill.ecse321.TAMAS.model.Application.Status;
import ca.mcgill.ecse321.TAMAS.model.Course;
import ca.mcgill.ecse321.TAMAS.model.Department;
import ca.mcgill.ecse321.TAMAS.model.Instructor;
import ca.mcgill.ecse321.TAMAS.model.JobPosting;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;
import ca.mcgill.ecse321.TAMAS.persistence.PersistenceXStream;

import java.awt.BorderLayout;
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

public class AllApplication extends JFrame {

	private static final long serialVersionUID = 5797257474418419821L;

	private ManagementSystem ms;
	private static String filename = "output/data.xml";

	private Object user;
	private int selectedApplication;
	private HashMap<String, Application> applicationMap = new HashMap<String, Application>();
	private TamasController tc;
	private String[][] data;
	private JTable table;

	public AllApplication(ManagementSystem ms, Object user) {
		this.user = user;
		this.ms = ms;
		tc = new TamasController(ms);
		data = new String[ms.numberOfApplicants() * 3 + 1][5];
		System.out.println(data);
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
				data[i][4] = myApplication.getStatusFullName();
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
		final JComboBox<String> allApplication = new JComboBox<String>(new String[0]);
		JButton viewDetailButton = new JButton("View Details");
		JButton acceptAppButton = new JButton("Accept");
		JButton rejectAppButton = new JButton("Reject");
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
			List<JobPosting> relatedJobPosting = new ArrayList<>();
			for (Course aCourse : myCourses) {
				relatedJobPosting.addAll(aCourse.getJobPosting());
			}
			for (JobPosting aJobPosting : relatedJobPosting) {
				List<Application> relatedApplication = aJobPosting.getApplications();
				for (Application aApplication : relatedApplication) {
					String appDescription = aApplication.getApplicant().getName() + " (as "
							+ aApplication.getJobPosting().getJobTitle() + " for "
							+ aApplication.getJobPosting().getCourse().getCourseCode() + ")";
					allApplication.addItem(appDescription);
					applicationMap.put(appDescription, aApplication);
				}
			}

			commandPane.add(allApplication);
			commandPane.add(viewDetailButton);
			commandPane.add(viewAllocation);
			pane.add(commandPane, BorderLayout.CENTER);
		} else if (user.getClass().equals(Applicant.class)) {
			setTitle("View My Applications");
			Applicant using = (Applicant) user;
			for (Applicant anApplicant : ms.getApplicants()) {

				if (anApplicant.getName().equals(using.getName())) {
					for (Application aApplication : anApplicant.getApplications()) {
						String appDescription = anApplicant.getName() + " (as "
								+ aApplication.getJobPosting().getJobTitle() + " for "
								+ aApplication.getJobPosting().getCourse().getCourseCode() + ")";
						allApplication.addItem(appDescription);
						applicationMap.put(appDescription, aApplication);
					}
				}
			}
			commandPane.add(allApplication);
			commandPane.add(viewDetailButton);
			commandPane.add(acceptAppButton);
			commandPane.add(rejectAppButton);
			buttomPane.add(applyJobButton);
			buttomPane.add(backButton);
			pane.add(commandPane, BorderLayout.CENTER);
			pane.add(buttomPane, BorderLayout.PAGE_END);
		} else {
			setTitle("View All Applications");

			buttomPane.add(applyJobButton);
			buttomPane.add(backButton);
			pane.add(buttomPane, BorderLayout.PAGE_END);

			for (Applicant anApplicant : ms.getApplicants()) {
				for (Application aApplication : anApplicant.getApplications()) {
					String appDescription = anApplicant.getName() + " (as " + aApplication.getJobPosting().getJobTitle()
							+ " for " + aApplication.getJobPosting().getCourse().getCourseCode() + ")";
					allApplication.addItem(appDescription);
					applicationMap.put(appDescription, aApplication);
				}
			}

			commandPane.add(allApplication);
			commandPane.add(viewDetailButton);
			commandPane.add(acceptAppButton);
			commandPane.add(rejectAppButton);
			commandPane.add(viewAllocation);
			pane.add(commandPane, BorderLayout.CENTER);
		}
		pack();
		setVisible(true);

		// add actions listeners
		applyJobButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (ms.getCourses().size() == 0) {
					JOptionPane.showMessageDialog(AllApplication.this,
							"No Job Posting has been published.\n" + "Please try again later.");
				} else {
					final ManagementSystem ms = PersistenceXStream.initializeModelManager(filename);
					new ApplyForJob(ms, user).setVisible(true);
					dispose();
				}
			}
		});

		allApplication.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unchecked")
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
				selectedApplication = cb.getSelectedIndex();
			}
		});

		viewDetailButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (allApplication.getItemCount() == 0) {
					JOptionPane.showMessageDialog(AllApplication.this, "No application has been submitted.");
				} else {
					String appDescription = allApplication.getItemAt(selectedApplication).toString();
					Application selectedApp = applicationMap.get(appDescription);

					new ApplicationDetails(ms, selectedApp.getApplicant(), user).setVisible(true);
				}
			}
		});

		acceptAppButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (allApplication.getItemCount() == 0) {
					JOptionPane.showMessageDialog(AllApplication.this, "No application has been submitted.");
				} else {
					String appDescription = allApplication.getItemAt(selectedApplication).toString();
					Application selectedApp = applicationMap.get(appDescription);

					if (user.getClass().equals(Department.class) && selectedApp.getStatus().equals(Status.PENDING)) {
						selectedApp.setStatus(Status.SELECTED);
					} else if (user.getClass().equals(Applicant.class)
							&& selectedApp.getStatus().equals(Status.SELECTED)) {
						selectedApp.setStatus(Status.OFFER_ACCEPTED);
					} else {
						JOptionPane.showMessageDialog(AllApplication.this, "You cannot change the status now");
					}

					PersistenceXStream.saveToXMLwithXStream(ms);

					dispose();
					initComponents();
				}
			}
		});

		rejectAppButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (allApplication.getItemCount() == 0) {
					JOptionPane.showMessageDialog(AllApplication.this, "No application has been submitted.");
				} else {
					String appDescription = allApplication.getItemAt(selectedApplication).toString();
					Application selectedApp = applicationMap.get(appDescription);
					if (user.getClass().equals(Department.class) && selectedApp.getStatus().equals(Status.PENDING)) {
						selectedApp.setStatus(Status.SELECTED);
					} else if (user.getClass().equals(Applicant.class)
							&& selectedApp.getStatus().equals(Status.SELECTED)) {
						selectedApp.setStatus(Status.OFFER_DECLINED);
					} else {
						JOptionPane.showMessageDialog(AllApplication.this, "You cannot change the status now");
					}

					PersistenceXStream.saveToXMLwithXStream(ms);
					dispose();
					initComponents();
				}
			}
		});

		viewAllocation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Course course = 
				//new AllocationPage1(ms,)

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
