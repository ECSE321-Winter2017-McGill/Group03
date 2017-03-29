package ca.mcgill.ecse321.TAMAS.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;

public class WriteEvaluation extends JFrame {

	private static final long serialVersionUID = -3813819647258555349L;

	// attributes for GUI elements
	private JLabel formTitle;

	private JLabel jobLabel;
	private JComboBox<String> jobToggleList;

	private JLabel preferredExperienceLabel;
	private JTextArea preferredExperienceTextField;

	private JButton createPostingButton;
	private JButton cancelButton;

	private JSeparator horizontalLineTop;

	private String error = null;
	private JLabel errorMessage;

	private int selectedJob = -1;

	private Object user;
//	private HashMap<String, Course> cMap = new HashMap<>();

	public WriteEvaluation(Object user) {
//		this.ms = ms;
		this.user = user;
		initComponents();
		refreshData();
	}

	private void initComponents() {

		formTitle = new JLabel("TA Evaluation");
		formTitle.setFont(new Font("Georgia", Font.BOLD, 22));

		horizontalLineTop = new JSeparator();

		// elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);

		jobLabel = new JLabel("Choose a TA:");
		jobLabel.setForeground(Color.BLACK);
		jobToggleList = new JComboBox<String>(new String[0]);
		jobToggleList.addItem("TA-COMP-251-TA1");
		jobToggleList.addItem("TA-COMP-251-TA2");
		jobToggleList.addItem("TA-ECSE-221-TA1");
		jobToggleList.addItem("TA-ECSE-221-TA2");

		preferredExperienceLabel = new JLabel("Write Evaluation Here:");
		preferredExperienceLabel.setForeground(Color.BLACK);
		preferredExperienceTextField = new JTextArea(10, 20);

		createPostingButton = new JButton("Submit");
		createPostingButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				createJobPostingButtonActionPerformed();
			}
		});

		cancelButton = new JButton("  Cancel  ");
		cancelButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelButtonActionPerformed(evt);
			}
		});
		setTitle("TA Evaluation");

		// layout
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(layout.createParallelGroup().addComponent(formTitle).addComponent(errorMessage)
				.addComponent(horizontalLineTop)

				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup().addComponent(jobLabel).addComponent(preferredExperienceLabel).addComponent(cancelButton))
						.addGroup(layout.createParallelGroup().addComponent(jobToggleList).addComponent(preferredExperienceTextField)
								.addComponent(createPostingButton))));


		layout.setVerticalGroup(layout.createSequentialGroup().addComponent(formTitle)
				.addGroup(layout.createParallelGroup().addComponent(horizontalLineTop)).addComponent(errorMessage)
				.addGroup(layout.createParallelGroup().addComponent(jobLabel).addComponent(jobToggleList))
				.addGroup(layout.createParallelGroup().addComponent(preferredExperienceLabel).addComponent(preferredExperienceTextField))
				.addGroup(layout.createParallelGroup().addComponent(cancelButton).addComponent(createPostingButton)));

		pack();

		jobToggleList.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				@SuppressWarnings("unchecked")
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedJob = cb.getSelectedIndex();
			}
		});

	}

	// can also disable submit if string is empty
	private void refreshData() {
		// error
		errorMessage.setText(error);
		System.out.println(error);
		if (error == null || error.length() == 0) {

			selectedJob = -1;
			jobToggleList.setSelectedIndex(selectedJob);

			preferredExperienceTextField.setText("");
		}
		// this is needed because the size of the window changes depending on
		// whether an error message is shown or not
		pack();

	}

	private void createJobPostingButtonActionPerformed() {
		// call the controller

		// Instructor prof = new Instructor("Daniel Varro", ms);
		// Course course = new Course("Winter 2017", "Intro to
		// SE",courseToggleList.getSelectedItem().toString(), 1, 1, 100, 3, 1,
		// 1, 60, 60, 10000.0, prof, ms);
		// Allocation allocation = new Allocation(course);

		error = "";
		String blob = preferredExperienceTextField.getText();
//		double hourlyRate;


		if (selectedJob < 0) {
			error += "Please select a TA to Evaluate. ";
		}

		if (blob==null) {
			error += "Please write an evaluation! ";
		}
		refreshData();
	}

	private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
		backToMain(user);
		setVisible(false);
	}

	private void backToMain(Object user) {
		new MainPage(user).setVisible(true);
	}

}
