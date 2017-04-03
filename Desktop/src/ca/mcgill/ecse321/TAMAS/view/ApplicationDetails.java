package ca.mcgill.ecse321.TAMAS.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;

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
import ca.mcgill.ecse321.TAMAS.model.Applicant;
import ca.mcgill.ecse321.TAMAS.model.JobPosting;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;

public class ApplicationDetails extends JFrame {

	private static final long serialVersionUID = -3622744248951509231L;

	// Basic information
	private JLabel formTitle;
	private JLabel nameLabel;
	private JTextField nameTextField;
	private JLabel idLabel;
	private JTextField idTextField;
	private JLabel majorLabel;
	private JTextField majorTextField;
	private JLabel isUndergradLabel;
	private JComboBox<String> isUndergradToggleList;
	private JLabel yearLabel;
	private JComboBox<String> yearToggleList;

	// Past experience
	private JLabel pastExperienceLabel;
	private JTextArea pastExperienceTextArea;
	private JLabel firstChoiceLabel;
	private JComboBox<String> firstChoiceToggleList;
	private JLabel secondChoiceLabel;
	private JComboBox<String> secondChoiceToggleList;
	private JLabel thirdChoiceLabel;
	private JComboBox<String> thirdChoiceToggleList;

	private JSeparator horizontalLineTop;
	private JSeparator horizontalLineMiddle1;
	private JSeparator horizontalLineMiddle2;
	
	private JButton closeButton;

	private ManagementSystem ms;
	private Applicant app;
	private Object user;

	public ApplicationDetails(ManagementSystem ms,Applicant app,Object user) {
		this.ms = ms;
		this.app = app;
		this.user= user;
		initComponents();
	}

	public void initComponents() {
		
		formTitle = new JLabel("Application");
		formTitle.setFont(new Font("Georgia", Font.BOLD, 22));


		horizontalLineTop = new JSeparator();
		horizontalLineMiddle1 = new JSeparator();
		horizontalLineMiddle2 = new JSeparator();
		
		
		nameLabel = new JLabel("Name:");
		nameLabel.setForeground(Color.BLACK);
		nameTextField = new JTextField();
		nameTextField.setText(app.getName());
		nameTextField.setForeground(Color.BLACK);
		nameTextField.setEditable(false);
	
		idLabel = new JLabel("McGill ID:");
		idLabel.setForeground(Color.BLACK);
		idTextField = new JTextField();
		idTextField.setText(Integer.toString(app.getStudentID()));
		idTextField.setForeground(Color.BLACK);
		idTextField.setEditable(false);
		
		majorLabel = new JLabel("Major:");
		majorLabel.setForeground(Color.BLACK);
		majorTextField = new JTextField();
		majorTextField.setText(app.getMajor());
		majorTextField.setForeground(Color.BLACK);
		majorTextField.setEditable(false);
		
		isUndergradLabel = new JLabel("Undergrad/Grad");
		isUndergradLabel.setForeground(Color.BLACK);
		isUndergradToggleList = new JComboBox<String>(new String[0]);
		if (app.getIsUnderGraduated()==true){
			isUndergradToggleList.addItem("Undergraduate");
		}
		else{
			isUndergradToggleList.addItem("Graduate");
		}		
		isUndergradToggleList.setForeground(Color.BLACK);
		
		
		yearLabel = new JLabel("Year:");
		yearLabel.setForeground(Color.BLACK);
		yearToggleList = new JComboBox<String>(new String[0]);
		yearToggleList.addItem(app.getYear());
		yearToggleList.setForeground(Color.BLACK);


		pastExperienceLabel = new JLabel("Past Experience");
		pastExperienceLabel.setForeground(Color.BLACK);
		pastExperienceTextArea = new JTextArea(5, 20);
		pastExperienceTextArea.setText(app.getPreviousExperience());
		pastExperienceTextArea.setForeground(Color.BLACK);
		pastExperienceTextArea.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		pastExperienceTextArea.setEditable(false);

		

		firstChoiceLabel = new JLabel("First Choice");
		firstChoiceToggleList = new JComboBox<String>(new String[0]);
		firstChoiceToggleList.addItem(app.getFirstChoice());
		firstChoiceToggleList.setForeground(Color.BLACK);


		secondChoiceLabel = new JLabel("Second Choice");
		secondChoiceToggleList = new JComboBox<String>(new String[0]);
		secondChoiceToggleList.addItem(app.getSecondChoice());
		secondChoiceToggleList.setForeground(Color.BLACK);

		
		thirdChoiceLabel = new JLabel("Third Choice");
		thirdChoiceToggleList = new JComboBox<String>(new String[0]);
		thirdChoiceToggleList.addItem(app.getThirdChoice());
		thirdChoiceToggleList.setForeground(Color.BLACK);
		
		
		
		closeButton = new JButton("  Close  ");
		closeButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				closeButtonActionPerformed(evt);
			}
		});
		
		

		setTitle("Application Details");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(layout.createParallelGroup()
				.addComponent(formTitle).addComponent(horizontalLineTop)
				.addComponent(horizontalLineMiddle1).addComponent(horizontalLineMiddle2)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup() 
								.addComponent(nameLabel)
								.addComponent(idLabel)
								.addComponent(majorLabel)
								.addComponent(isUndergradLabel)
								.addComponent(yearLabel)
								.addComponent(pastExperienceLabel)
								.addComponent(firstChoiceLabel)
								.addComponent(secondChoiceLabel)
								.addComponent(thirdChoiceLabel))
						.addGroup(layout.createParallelGroup()
								.addComponent(nameLabel)
								.addComponent(nameTextField)
								.addComponent(idTextField)
								.addComponent(majorTextField)
								.addComponent(isUndergradToggleList)
								.addComponent(yearToggleList)
								.addComponent(pastExperienceTextArea)
								.addComponent(firstChoiceToggleList)
								.addComponent(secondChoiceToggleList)
								.addComponent(thirdChoiceToggleList)
								.addComponent(closeButton))));

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(formTitle)
				.addGroup(layout.createParallelGroup()
						.addComponent(horizontalLineTop))
				.addGroup(layout.createParallelGroup()
						.addComponent(nameLabel)
						.addComponent(nameTextField))
				.addGroup(layout.createParallelGroup()
						.addComponent(idLabel)
						.addComponent(idTextField))
				.addGroup(layout.createParallelGroup()
						.addComponent(majorLabel)
						.addComponent(majorTextField))
				.addGroup(layout.createParallelGroup()
						.addComponent(horizontalLineMiddle1))
				.addGroup(layout.createParallelGroup()
						.addComponent(isUndergradLabel).addComponent(isUndergradToggleList))
				.addGroup(layout.createParallelGroup().addComponent(yearLabel).addComponent(yearToggleList))
				.addGroup(layout.createParallelGroup().addComponent(pastExperienceLabel)
						.addComponent(pastExperienceTextArea))
				.addGroup(layout.createParallelGroup().addComponent(horizontalLineMiddle2))
				.addGroup(layout.createParallelGroup()
						.addComponent(firstChoiceLabel).addComponent(firstChoiceToggleList))
				.addGroup(layout.createParallelGroup().addComponent(secondChoiceLabel)
						.addComponent(secondChoiceToggleList))
				.addGroup(layout.createParallelGroup()
						.addComponent(thirdChoiceLabel).addComponent(thirdChoiceToggleList))
				.addGroup(layout.createParallelGroup()
						.addComponent(closeButton)));
				
		

		pack();
		setResizable(false);
	}
	
	private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {
		setVisible(false);
	}
	
	private void backToAllApp() {
		new AllApplication(ms, user).setVisible(true);
	}
}
