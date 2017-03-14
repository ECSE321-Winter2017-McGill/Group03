package ca.mcgill.ecse321.TAMAS.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import ca.mcgill.ecse321.TAMAS.controller.InvalidInputException;
import ca.mcgill.ecse321.TAMAS.controller.TamasController;
import ca.mcgill.ecse321.TAMAS.model.JobPosting;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;

public class ApplyForJob extends JFrame {

	private static final long serialVersionUID = 5816868540239806192L;
	private String userName;
	// Basic information
	private JLabel formTitle;
	private JLabel errorMessage;
	private JLabel jobPostingLabel;
	private JLabel nameLabel;
	private JLabel nameTextField;
	private JLabel idLabel;
	private JTextField idTextField;
	private JLabel majorLabel;
	private JTextField majorTextField;
	private JLabel isUndergradLabel;
	private JComboBox<String> isUndergradToggleList;
	private JComboBox<String> jobPostingList;
	private JLabel yearLabel;
	private JComboBox<String> yearToggleList;

	// Past experience
	private JLabel pastExperienceLabel;
	private JTextArea pastExperienceTextArea;

	private JLabel choiceMessage1;
	private JLabel choiceMessage2;
	private JLabel firstChoiceLabel;
	private JComboBox<String> firstChoiceToggleList;
	private JLabel secondChoiceLabel;
	private JComboBox<String> secondChoiceToggleList;
	private JLabel thirdChoiceLabel;
	private JComboBox<String> thirdChoiceToggleList;

	private String error = null;
	private Integer selectedDegree = -1;
	private Integer selectedYear = -1;
	private Integer selectedFirstChoice = -1;
	private Integer selectedSecondChoice = -1;
	private Integer selectedThirdChoice = -1;

	private JButton submitButton;

	private JSeparator horizontalLineTop;
	private JSeparator horizontalLineMiddle1;
	private JSeparator horizontalLineMiddle2;

	private ManagementSystem ms;

	public ApplyForJob(ManagementSystem ms, String userName) {
		this.ms = ms;
		this.userName = userName;
		initComponents();
		refreshData();
	}

	public void initComponents() {

		formTitle = new JLabel("Apply for Job");
		formTitle.setFont(new Font("Georgia", Font.BOLD, 22));

		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		jobPostingLabel = new JLabel("Availabe Job Postings:");
		jobPostingList = new JComboBox<String>();
		for (JobPosting jp : ms.getJobPostings()) {
			jobPostingList.addItem(jp.getJobTitle() + " " + jp.getCourse().getCourseCoude());
		}
		nameLabel = new JLabel("Name:");
		nameTextField = new JLabel(userName);
		idLabel = new JLabel("McGill ID:");
		idTextField = new JTextField();
		majorLabel = new JLabel("Major:");
		majorTextField = new JTextField();

		isUndergradLabel = new JLabel("Undergrad/Grad");
		isUndergradToggleList = new JComboBox<String>(new String[0]);

		isUndergradToggleList.addItem("Undergraduate");
		isUndergradToggleList.addItem("Graduate");
		isUndergradToggleList.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				@SuppressWarnings("unchecked")
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedDegree = cb.getSelectedIndex();
			}
		});
		;

		yearLabel = new JLabel("Year:");
		yearToggleList = new JComboBox<String>(new String[0]);

		yearToggleList.addItem("U0");
		yearToggleList.addItem("U1");
		yearToggleList.addItem("U2");
		yearToggleList.addItem("U3 or more");
		yearToggleList.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				@SuppressWarnings("unchecked")
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedYear = cb.getSelectedIndex();
			}
		});
		;

		pastExperienceLabel = new JLabel("Past Experience");
		pastExperienceTextArea = new JTextArea(5, 20);
		pastExperienceTextArea.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

		choiceMessage1 = new JLabel("Please select a second choice if already applied for 1 position");
		choiceMessage1.setForeground(Color.BLACK);

		choiceMessage2 = new JLabel("Please select a third choice if already applied for 2 positions");
		choiceMessage2.setForeground(Color.BLACK);
		
		// TODO: choices are hard coded.
		firstChoiceLabel = new JLabel("First Choice");

		firstChoiceToggleList = new JComboBox<String>(new String[0]);
		firstChoiceToggleList.addItem("ECSE 321");
		firstChoiceToggleList.addItem("ECSE 250");
		firstChoiceToggleList.addItem("ECSE 251");

		firstChoiceToggleList.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				@SuppressWarnings("unchecked")
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedFirstChoice = cb.getSelectedIndex();
			}
		});
		;

		secondChoiceLabel = new JLabel("Second Choice");

		secondChoiceToggleList = new JComboBox<String>(new String[0]);
		secondChoiceToggleList.addItem("ECSE 321");
		secondChoiceToggleList.addItem("ECSE 250");
		secondChoiceToggleList.addItem("ECSE 251");

		secondChoiceToggleList.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				@SuppressWarnings("unchecked")
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedSecondChoice = cb.getSelectedIndex();
			}
		});
		;

		thirdChoiceLabel = new JLabel("Third Choice");

		thirdChoiceToggleList = new JComboBox<String>(new String[0]);
		thirdChoiceToggleList.addItem("ECSE 321");
		thirdChoiceToggleList.addItem("ECSE 250");
		thirdChoiceToggleList.addItem("ECSE 251");

		thirdChoiceToggleList.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				@SuppressWarnings("unchecked")
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedThirdChoice = cb.getSelectedIndex();
			}
		});
		;

		submitButton = new JButton("Submit");
		submitButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				submitButtonActionPerformed(evt);
			}
		});

		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		setTitle("Job Application");

		horizontalLineTop = new JSeparator();
		horizontalLineMiddle1 = new JSeparator();
		horizontalLineMiddle2 = new JSeparator();

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createParallelGroup().addComponent(formTitle).addComponent(errorMessage)
				.addComponent(horizontalLineTop).addComponent(horizontalLineMiddle1).addComponent(horizontalLineMiddle2)
				.addComponent(choiceMessage1).addComponent(choiceMessage2)

				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup().addComponent(jobPostingLabel).addComponent(nameLabel)
								.addComponent(idLabel).addComponent(majorLabel).addComponent(isUndergradLabel)
								.addComponent(yearLabel).addComponent(pastExperienceLabel)
								.addComponent(firstChoiceLabel).addComponent(secondChoiceLabel)
								.addComponent(thirdChoiceLabel))
						.addGroup(
								layout.createParallelGroup().addComponent(jobPostingLabel).addComponent(jobPostingList)
										.addComponent(nameLabel).addComponent(nameTextField).addComponent(idTextField)
										.addComponent(majorTextField).addComponent(isUndergradToggleList)
										.addComponent(yearToggleList).addComponent(pastExperienceTextArea)
										.addComponent(firstChoiceToggleList).addComponent(secondChoiceToggleList)
										.addComponent(thirdChoiceToggleList).addComponent(submitButton))));

		layout.setVerticalGroup(layout.createSequentialGroup().addComponent(formTitle)
				.addGroup(layout.createParallelGroup().addComponent(horizontalLineTop)).addComponent(errorMessage)
				.addGroup(layout.createParallelGroup().addComponent(jobPostingLabel).addComponent(jobPostingList))
				.addGroup(layout.createParallelGroup().addComponent(nameLabel).addComponent(nameTextField))
				.addGroup(layout.createParallelGroup().addComponent(idLabel).addComponent(idTextField))
				.addGroup(layout.createParallelGroup().addComponent(majorLabel).addComponent(majorTextField))
				.addGroup(layout.createParallelGroup().addComponent(horizontalLineMiddle1))
				.addGroup(
						layout.createParallelGroup().addComponent(isUndergradLabel).addComponent(isUndergradToggleList))
				.addGroup(layout.createParallelGroup().addComponent(yearLabel).addComponent(yearToggleList))
				.addGroup(layout.createParallelGroup().addComponent(pastExperienceLabel)
						.addComponent(pastExperienceTextArea))
				.addGroup(layout.createParallelGroup().addComponent(horizontalLineMiddle2)).addComponent(choiceMessage1)
				.addComponent(choiceMessage2)
				.addGroup(
						layout.createParallelGroup().addComponent(firstChoiceLabel).addComponent(firstChoiceToggleList))
				.addGroup(layout.createParallelGroup().addComponent(secondChoiceLabel)
						.addComponent(secondChoiceToggleList))
				.addGroup(
						layout.createParallelGroup().addComponent(thirdChoiceLabel).addComponent(thirdChoiceToggleList))
				.addGroup(layout.createParallelGroup().addComponent(submitButton))

		);

		pack();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				backToMain();
			}
		});
	}

	private void refreshData() {
		// error
		errorMessage.setText(error);
		if (error == null || error.length() == 0) {
			idTextField.setText("");
			majorTextField.setText("");
			pastExperienceTextArea.setText("");

			selectedDegree = -1;
			isUndergradToggleList.setSelectedIndex(selectedDegree);
			selectedYear = -1;
			yearToggleList.setSelectedIndex(selectedYear);
			selectedFirstChoice = -1;
			firstChoiceToggleList.setSelectedIndex(selectedFirstChoice);
			selectedSecondChoice = -1;
			secondChoiceToggleList.setSelectedIndex(selectedSecondChoice);
			selectedThirdChoice = -1;
			thirdChoiceToggleList.setSelectedIndex(selectedThirdChoice);

		}

		pack();
	}

	private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {

		int totalAppointmentHours = 45;
		TamasController tc = new TamasController(ms);

		error = "";
		String name = nameTextField.getText();
		int id;
		String major = majorTextField.getText();
		boolean degree = true;
		String exp = pastExperienceTextArea.getText();

		if (name.equals("")) {
			error += "Name cannot be empty!";
		}
		if (idTextField.getText().equals("")) {
			id = -1;
		} else if (!idTextField.getText().matches("[0-9]+")) {
			id = -1;
		} else {
			id = Integer.parseInt(idTextField.getText());
		}

		if (id < 0) {
			error += "ID is invalid!";
		}

		if (major.equals("")) {
			error += "Major cannot be empty!";
		}

		if (selectedDegree < 0) {
			error += "Please select either Undergraduate or Graduate.";
		} else if (selectedDegree == 2) {
			degree = false;
		}

		if (selectedYear < 0) {
			error += "Please select your year.";
		}

		if (exp.equals("")) {
			error += "Past experience cannot be empty!";
		}

		if (selectedFirstChoice < 0) {
			error += "Please select your first choice.";
		}

		if (error.length() == 0) {
			String year = (String) yearToggleList.getSelectedItem();
			String firstChoice = null;
			String secondChoice = null;
			String thirdChoice = null;

			firstChoice = (String) firstChoiceToggleList.getSelectedItem();

			if (selectedSecondChoice > 0) {
				secondChoice = (String) secondChoiceToggleList.getSelectedItem();
			}

			if (selectedThirdChoice > 0) {
				thirdChoice = (String) thirdChoiceToggleList.getSelectedItem();
			}

			try {
				JobPosting appliedjob = null;
				String app = jobPostingList.getSelectedItem().toString();
				for (JobPosting jp : ms.getJobPostings()) {
					if (app.indexOf(jp.getJobTitle()) != -1 && app.indexOf(jp.getCourse().getCourseCoude()) != -1)
						appliedjob = jp;
				}
				if (appliedjob != null) {
					tc.createApplication(appliedjob, name, id, major, degree, year, exp, firstChoice, secondChoice,
							thirdChoice, totalAppointmentHours);
				}
			} catch (InvalidInputException e) {
				error += e.getMessage();
			}
		}

		// update visuals
		refreshData();
	}

	private void backToMain() {
		new AllApplication(ms, userName).setVisible(true);
	}

}
