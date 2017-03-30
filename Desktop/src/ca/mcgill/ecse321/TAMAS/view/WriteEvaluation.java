package ca.mcgill.ecse321.TAMAS.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import ca.mcgill.ecse321.TAMAS.controller.InvalidInputException;
import ca.mcgill.ecse321.TAMAS.controller.TamasController;
import ca.mcgill.ecse321.TAMAS.model.Applicant;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;

public class WriteEvaluation extends JFrame {

	private static final long serialVersionUID = -3813819647258555349L;

	// attributes for GUI elements
	private JLabel formTitle;

	private JLabel TALabel;
	private JComboBox<String> TAToggleList;

	private JLabel EvalLabel;
	private JTextArea EvalTextArea;

	private JButton createPostingButton;
	private JButton cancelButton;

	private JSeparator horizontalLineTop;

	private String error = null;
	private JLabel errorMessage;

	private int selectedJob = -1;

	private ManagementSystem ms;
	private Object user;

	public WriteEvaluation(ManagementSystem ms, Object user) {
		this.ms = ms;
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

		TALabel = new JLabel("Choose a TA:");
		TALabel.setForeground(Color.BLACK);
		TAToggleList = new JComboBox<String>(new String[0]);
		for (int i=0; i<this.ms.getApplicants().size(); i++) {
			TAToggleList.addItem(this.ms.getApplicants().get(i).getName());
		}

		EvalLabel = new JLabel("Write Evaluation Here:");
		EvalLabel.setForeground(Color.BLACK);
		EvalTextArea = new JTextArea(10, 20);

		createPostingButton = new JButton("Submit");
		createPostingButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				createJobPostingButtonActionPerformed();
			}
		});

		cancelButton = new JButton("Cancel");
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
						.addGroup(layout.createParallelGroup().addComponent(TALabel).addComponent(EvalLabel).addComponent(cancelButton))
						.addGroup(layout.createParallelGroup().addComponent(TAToggleList).addComponent(EvalTextArea)
								.addComponent(createPostingButton))
						)
				);


		layout.setVerticalGroup(layout.createSequentialGroup().addComponent(formTitle)
				.addGroup(layout.createParallelGroup().addComponent(horizontalLineTop)).addComponent(errorMessage)				
				.addGroup(layout.createParallelGroup().addComponent(TALabel).addComponent(TAToggleList))
				.addGroup(layout.createParallelGroup().addComponent(EvalLabel).addComponent(EvalTextArea))
				.addGroup(layout.createParallelGroup().addComponent(cancelButton).addComponent(createPostingButton))
				);
				
		pack();
		

		TAToggleList.addActionListener(new java.awt.event.ActionListener() {
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
		if (error == null || error.length() == 0) {

			selectedJob = -1;
			TAToggleList.setSelectedIndex(selectedJob);

			EvalTextArea.setText("");
		}
		// this is needed because the size of the window changes depending on
		// whether an error message is shown or not
		pack();

	}

	private void createJobPostingButtonActionPerformed() {
		// call the controller

		error = "";
		String eval = EvalTextArea.getText();

		if (selectedJob < 0) {
			error += "Please select a TA to Evaluate. ";
		}

		if (eval.length()==0) {
			error += "Please write an evaluation! ";
		}
		
		TamasController tc = new TamasController(ms);
		if (error.trim().length() == 0) {
			try {
				tc.createTAEval(TAToggleList.getItemAt(selectedJob),eval);
			}
			catch (InvalidInputException e) {
				error += e.getMessage();
			}
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
