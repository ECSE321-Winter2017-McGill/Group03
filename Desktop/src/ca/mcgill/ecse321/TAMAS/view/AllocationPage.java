package ca.mcgill.ecse321.TAMAS.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import ca.mcgill.ecse321.TAMAS.controller.InvalidInputException;
import ca.mcgill.ecse321.TAMAS.controller.TamasController;
import ca.mcgill.ecse321.TAMAS.model.Applicant;
import ca.mcgill.ecse321.TAMAS.model.Application;
import ca.mcgill.ecse321.TAMAS.model.Course;
import ca.mcgill.ecse321.TAMAS.model.Instructor;
import ca.mcgill.ecse321.TAMAS.model.JobPosting;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;

public class AllocationPage extends JFrame {

	private static final long serialVersionUID = -6385168299761243422L;

	private ManagementSystem ms;
	private Course course;
	private Object user;
	private TamasController tc;

	private JPanel mainPane;
	private JScrollPane consolePane;
	private JLabel commandLine;
	private JScrollPane scrollPane;

	private JPanel commandPane;
	private JPanel buttomPane;

	private JLabel budgetRemainingLabel;
	private JTextField budgetRemainingTextField;
	private JButton createButton;
	private JButton modifyButton;
	private JButton finalizeButton;
	private JButton backButton;
	// private JButton deleteButton;

	private HashMap<String, Application> applicationMap = new HashMap<String, Application>();
	private JComboBox<String> allApplication;
	private JButton viewDetailButton;
	private JButton acceptAppButton;
	private JButton rejectAppButton;
	private JButton checkGradButton;
	private int selectedApplication;

	private JSplitPane splitPane;

	private JTable table;
	private String[] columnNames = { "Name", "U/G", "Job Title", "Appointment Hour" };
	private String[][] data;

	private int valueSet = 0;
	private int rowToBeChangedIndex;
	private HashMap<String, Applicant> nameApplicantMap = new HashMap<String, Applicant>();
	private ArrayList<Applicant> tas;
	private ArrayList<Integer> taHours;

	public AllocationPage(ManagementSystem ms, Course course, Object user) {
		this.ms = ms;
		this.course = course;
		this.user = user;

		tc = new TamasController(ms);
		initComponents();
	}

	private void initComponents() {

		String[] columnNames = { "Name", "U/G", "Job Title", "Appointment Hour" };
		int numApplications = 0;

		if (course.getCourseJobAllocation() == null) {
			for (Applicant anApplicant : ms.getApplicants()) {
				for (Application aApplication : anApplicant.getApplications()) {
					if ((aApplication.getStatusFullName().equals("PENDING")
							|| aApplication.getStatusFullName().equals("SELECTED"))
							&& aApplication.getJobPosting().getCourse().getCourseName()
									.equals(course.getCourseName())) {
						numApplications++;
					}
				}
			}
		} else {
			for (Applicant anApplicant : ms.getApplicants()) {
				for (Application aApplication : anApplicant.getApplications()) {
					if (aApplication.getStatusFullName().equals("SELECTED") && aApplication.getJobPosting().getCourse()
							.getCourseName().equals(course.getCourseName())) {
						numApplications++;
					}
				}
			}
		}

		data = new String[numApplications + 1][4];

		int i = 0;

		if (course.getCourseJobAllocation() == null) {
			for (Applicant anApplicant : ms.getApplicants()) {
				for (Application aApplication : anApplicant.getApplications()) {
					if ((aApplication.getStatusFullName().equals("PENDING")
							|| aApplication.getStatusFullName().equals("SELECTED"))
							&& aApplication.getJobPosting().getCourse().getCourseName()
									.equals(course.getCourseName())) {
						data[i][0] = anApplicant.getName();
						nameApplicantMap.put(anApplicant.getName(), anApplicant);
						if (anApplicant.getIsUnderGraduated() == true) {
							data[i][1] = "Und";
						} else {
							data[i][1] = "Grad";
						}
						data[i][2] = aApplication.getJobPosting().getJobTitle();
						data[i][3] = "45";
						i++;
					}
				}
			}
		} else {
			for (Applicant anApplicant : ms.getApplicants()) {
				for (Application aApplication : anApplicant.getApplications()) {
					if (aApplication.getStatusFullName().equals("SELECTED") && aApplication.getJobPosting().getCourse()
							.getCourseName().equals(course.getCourseName())) {
						data[i][0] = anApplicant.getName();
						if (anApplicant.getIsUnderGraduated() == true) {
							data[i][1] = "Und";
						} else {
							data[i][1] = "Grad";
						}
						data[i][2] = aApplication.getJobPosting().getJobTitle();
						data[i][3] = "45";
						i++;
					}
				}
			}
		}

		// get table itself ready;
		DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
		table = new JTable(tableModel);

		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		table.setPreferredScrollableViewportSize(new Dimension(1200, 100));
		table.setFillsViewportHeight(true);
		scrollPane = new JScrollPane(table);
		commandPane = new JPanel();
		buttomPane = new JPanel();

		if (valueSet != 0) {
			tableModel.setValueAt(valueSet, selectedApplication, 3);
		}

		int budget = (int) Math.floor(course.getBudgetCalculated());

		budgetRemainingLabel = new JLabel("Budget Remaining:");
		budgetRemainingTextField = new JTextField();
		budgetRemainingTextField.setText("     $" + Integer.toString(budget) + "      ");
		budgetRemainingTextField.setForeground(Color.BLACK);
		budgetRemainingTextField.setEditable(false);

		// deleteButton = new JButton("Remove Applicant");
		// deleteButton.addActionListener(new ActionListener() {
		// @Override
		// public void actionPerformed(ActionEvent arg0) {
		// // check for selected row first
		// if (table.getSelectedRow() != -1) {
		// // remove selected row from the model
		// tableModel.removeRow(table.getSelectedRow());
		//// table.convertRowIndexToModel(int index)
		// }
		// }
		// });
		final JComboBox<String> allApplication = new JComboBox<String>(new String[0]);
		viewDetailButton = new JButton("View Details");
		acceptAppButton = new JButton("Accept");
		rejectAppButton = new JButton("Reject");
		checkGradButton = new JButton("Check Grad");

		checkGradButton = new JButton("Check Grad");
		checkGradButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				checkGradButtonActionPerformed(evt);
			}
		});

		backButton = new JButton(" Back ");
		backButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				backButtonActionPerformed(evt);
			}
		});

		createButton = new JButton("  Create  ");
		createButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				createButtonActionPerformed(evt);
			}
		});

		modifyButton = new JButton("  Modify  ");
		modifyButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				modifyButtonActionPerformed(evt);
			}
		});

		finalizeButton = new JButton(" Finalize ");
		finalizeButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				finalizeButtonActionPerformed(evt);
			}
		});

		// int j = 0;
		// for (Applicant anApplicant: ms.getApplicants()) {
		// for (Application aApplication: anApplicant.getApplications()){
		// if
		// (aApplication.getJobPosting().getCourse().getCourseName().equals(course.getCourseName())){
		// if (aApplication.getJobPosting().getJobTitle().equals("TA")){
		//// allTAHourAppointed.add((int)hourSpinner[j].getValue());
		// j++;
		// }
		// else if(aApplication.getJobPosting().getJobTitle().equals("Grader")){
		//// allGraderHourAppointed.add((int)hourSpinner[j].getValue());
		// j++;
		// }
		// }
		// }
		// }

		setTitle("View Allocation");

		BorderLayout layout = new BorderLayout();
		mainPane = new JPanel();
		mainPane.setLayout(layout);

		consolePane = new JScrollPane();

		commandLine = new JLabel("<html>");
		commandLine.setVerticalAlignment(SwingConstants.TOP);
		consolePane.setViewportView(commandLine);

		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, mainPane, consolePane);
		add(splitPane);
		setSize(new Dimension(100, 255));

		//
		// Container pane = getContentPane();
		// pane.setLayout(layout);
		mainPane.add(scrollPane, BorderLayout.PAGE_START);
		if (user.getClass().equals(Instructor.class)) {

			// TODO: Change needed (what if no allocation, what should
			// instructor see on ToggleList)
			for (Applicant anApplicant : ms.getApplicants()) {
				for (Application aApplication : anApplicant.getApplications()) {
					if (aApplication.getStatusFullName().equals("SELECTED") && aApplication.getJobPosting().getCourse()
							.getCourseName().equals(course.getCourseName())) {
						String appDescription = aApplication.getApplicant().getName() + " (as "
								+ aApplication.getJobPosting().getJobTitle() + " for "
								+ aApplication.getJobPosting().getCourse().getCourseCode() + ")";
						allApplication.addItem(appDescription);
						applicationMap.put(appDescription, aApplication);
					}
				}
			}

			commandPane.add(allApplication);
			commandPane.add(viewDetailButton);
			commandPane.add(budgetRemainingLabel);
			commandPane.add(budgetRemainingTextField);
			mainPane.add(commandPane, BorderLayout.CENTER);
			buttomPane.add(backButton);

			if (course.getCourseJobAllocation().getAllocationStatusFullName().equals("INITIAL")) {
				buttomPane.add(modifyButton);
			}

			mainPane.add(buttomPane, BorderLayout.PAGE_END);
		} else {
			if (course.getCourseJobAllocation() == null) {
				for (Applicant anApplicant : ms.getApplicants()) {
					for (Application aApplication : anApplicant.getApplications()) {
						if ((aApplication.getStatusFullName().equals("PENDING")
								|| aApplication.getStatusFullName().equals("SELECTED"))
								&& aApplication.getJobPosting().getCourse().getCourseName()
										.equals(course.getCourseName())) {
							String appDescription = aApplication.getApplicant().getName() + " (as "
									+ aApplication.getJobPosting().getJobTitle() + " for "
									+ aApplication.getJobPosting().getCourse().getCourseCode() + ")";
							allApplication.addItem(appDescription);
							applicationMap.put(appDescription, aApplication);
						}
					}
				}
			} else {
				for (Applicant anApplicant : ms.getApplicants()) {
					for (Application aApplication : anApplicant.getApplications()) {
						if (aApplication.getStatusFullName().equals("SELECTED") && aApplication.getJobPosting()
								.getCourse().getCourseName().equals(course.getCourseName())) {
							String appDescription = aApplication.getApplicant().getName() + " (as "
									+ aApplication.getJobPosting().getJobTitle() + " for "
									+ aApplication.getJobPosting().getCourse().getCourseCode() + ")";
							allApplication.addItem(appDescription);
							applicationMap.put(appDescription, aApplication);
						}
					}
				}
			}

			commandPane.add(allApplication);
			commandPane.add(viewDetailButton);
			commandPane.add(acceptAppButton);
			commandPane.add(rejectAppButton);

			commandPane.add(budgetRemainingLabel);
			commandPane.add(budgetRemainingTextField);
			// commandPane.add(deleteButton);
			mainPane.add(commandPane, BorderLayout.CENTER);

			buttomPane.add(backButton);
			if (course.getCourseJobAllocation() == null) {
				buttomPane.add(checkGradButton);
				buttomPane.add(createButton);
			} else if (course.getCourseJobAllocation().getAllocationStatusFullName().equals("INSTRUCTOR_APPROVED")) {
				buttomPane.add(finalizeButton);
			}
			mainPane.add(buttomPane, BorderLayout.PAGE_END);
		}

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
					JOptionPane.showMessageDialog(AllocationPage.this, "No application has been submitted.");
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
					JOptionPane.showMessageDialog(AllocationPage.this, "No application has been submitted.");
				} else {
					int reply = JOptionPane.showConfirmDialog(AllocationPage.this,
							"Hire for all tutorial/lab sessions?", null, JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						valueSet = course.getLabHour() * course.getNumLab()
								+ course.getTutorialHour() * course.getNumTutorial();

					} else if (reply == JOptionPane.NO_OPTION) {
						valueSet = 0;
					}

					String appDescription = allApplication.getItemAt(selectedApplication).toString();
					Application selectedApp = applicationMap.get(appDescription);
					if (tc.applicationAccepted(selectedApp)) {
						JOptionPane.showMessageDialog(AllocationPage.this, "Application already accepted.");
					} else {
//							tc.acceptApplication(selectedApp);
					}
					dispose();
					initComponents();
				}
			}
		});

		rejectAppButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (allApplication.getItemCount() == 0) {
					JOptionPane.showMessageDialog(AllocationPage.this, "No application has been submitted.");
				} else {
					String appDescription = allApplication.getItemAt(selectedApplication).toString();
					Application selectedApp = applicationMap.get(appDescription);
					if (tc.applicationRejected(selectedApp)) {
						JOptionPane.showMessageDialog(AllocationPage.this, "Application already rejected.");
					} else {
						tc.rejectApplication(selectedApp);
					}
					dispose();
					initComponents();
				}
			}
		});

		pack();
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public void refreshData() {
		//
		// int numApplications = 0;
		// for (Applicant anApplicant: ms.getApplicants()) {
		// for (Application aApplication: anApplicant.getApplications()){
		// if ( (aApplication.getStatusFullName().equals("PENDING") ||
		// aApplication.getStatusFullName().equals("SELECTED")) &&
		// aApplication.getJobPosting().getCourse().getCourseName().equals(course.getCourseName())
		// ){
		// numApplications++;
		// }
		// }
		// }
		//
		//
		// String[][] data = new String[numApplications+1][4];
		//
		// int i = 0;
		// for (Applicant anApplicant: ms.getApplicants()) {
		// for (Application aApplication: anApplicant.getApplications()){
		// if ( (aApplication.getStatusFullName().equals("PENDING") ||
		// aApplication.getStatusFullName().equals("SELECTED")) &&
		// aApplication.getJobPosting().getCourse().getCourseName().equals(course.getCourseName())
		// ){
		// data[i][0] = anApplicant.getName();
		// if (anApplicant.getIsUnderGraduated() == true) {
		// data[i][1] = "Und";
		// } else {
		// data[i][1] = "Grad";
		// }
		// data[i][2] = aApplication.getJobPosting().getJobTitle();
		// data[i][3] = "45";
		// i++;
		// }
		// }
		// }
		//
		// DefaultTableModel tableModel = new DefaultTableModel(data,
		// columnNames);
		// table = new JTable(tableModel);
		// table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		// table.setPreferredScrollableViewportSize(new Dimension(1200, 100));
		// table.setFillsViewportHeight(true);
		//
		//
		// if (valueSet != 0){
		// tableModel.setValueAt(valueSet, selectedApplication, 3);
		// }
		//

		// budgetRemainingTextField.setText("$" + Integer.toString());

		String consoleText = "<html>";

	}

	private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private void checkGradButtonActionPerformed(java.awt.event.ActionEvent evt) {

		int numGradStudent = 0;
		String gradNames = "";

		if (course.getCourseJobAllocation() == null) {
			for (Applicant anApplicant : ms.getApplicants()) {
				for (Application aApplication : anApplicant.getApplications()) {
					if ((aApplication.getStatusFullName().equals("PENDING")
							|| aApplication.getStatusFullName().equals("SELECTED"))
							&& aApplication.getJobPosting().getCourse().getCourseName()
									.equals(course.getCourseName())) {
						if (aApplication.getApplicant().isIsUnderGraduated() == false) {
							numGradStudent++;
							gradNames += "," + aApplication.getApplicant().getName();
						}
					}
				}
			}
		} else {
			for (Applicant anApplicant : ms.getApplicants()) {
				for (Application aApplication : anApplicant.getApplications()) {
					if (aApplication.getStatusFullName().equals("SELECTED") && aApplication.getJobPosting().getCourse()
							.getCourseName().equals(course.getCourseName())) {
						if (aApplication.getApplicant().isIsUnderGraduated() == false) {
							numGradStudent++;
							gradNames += "," + aApplication.getApplicant().getName();
						}
					}
				}
			}
		}

		JOptionPane.showMessageDialog(AllocationPage.this,
				"There are " + numGradStudent + " grad applicants:\n" + gradNames);

	}

	private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
		backToAllApp();
		setVisible(false);
	}

	private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {
		String error = "";
		ArrayList<Integer> allTAHourAppointed = new ArrayList<Integer>();
		ArrayList<Integer> allGraderHourAppointed = new ArrayList<Integer>();
		ArrayList<Applicant> taAppointed = new ArrayList<Applicant>();
		ArrayList<Applicant> graderAppointed = new ArrayList<Applicant>();

		for (int i = 0; i < data.length; i++) {

			if (table.getModel().getValueAt(i, 2).equals("TA")) {
				taAppointed.add(nameApplicantMap.get(table.getModel().getValueAt(i, 0)));
				allTAHourAppointed.add((Integer) table.getModel().getValueAt(i, 3));
			} else {
				graderAppointed.add(nameApplicantMap.get(table.getModel().getValueAt(i, 0)));
				allGraderHourAppointed.add((Integer) table.getModel().getValueAt(i, 3));
			}

		}

		try {
			tc.createAllocation(ms, course, taAppointed, allTAHourAppointed, graderAppointed, allGraderHourAppointed);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
	}

	private void modifyButtonActionPerformed(java.awt.event.ActionEvent evt) {
		String error = "";
		ArrayList<Integer> allTAHourAppointed = new ArrayList<Integer>();
		ArrayList<Integer> allGraderHourAppointed = new ArrayList<Integer>();
		ArrayList<Applicant> taAppointed = new ArrayList<Applicant>();
		ArrayList<Applicant> graderAppointed = new ArrayList<Applicant>();
		try {
			tc.changeHours(course.getCourseJobAllocation(), ms, taAppointed, allTAHourAppointed, graderAppointed,
					allGraderHourAppointed);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
	}

	private void finalizeButtonActionPerformed(java.awt.event.ActionEvent evt) {
		String error = "";
		try {
			tc.finalizeAllocation(course.getCourseJobAllocation());
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
	}

	private void backToAllApp() {
		new AllApplication(ms, user).setVisible(true);
	}

}
