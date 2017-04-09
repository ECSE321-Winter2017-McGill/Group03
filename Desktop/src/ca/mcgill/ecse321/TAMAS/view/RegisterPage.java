package ca.mcgill.ecse321.TAMAS.view;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

import ca.mcgill.ecse321.TAMAS.controller.InvalidInputException;
import ca.mcgill.ecse321.TAMAS.controller.TamasController;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;

public class RegisterPage extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -765105219779249925L;
	//UI elements
	private JLabel errorMessage;
	private String error = null;
	
	private JLabel reminder;
	
	//Choose registration type
	private JLabel typeLabel;
	private JComboBox<String> typeToggleList;
	private Integer selectedType = -1;
	
	//Username
	private JLabel usernameLabel;
	private JTextField usernameTextField;
	
	//Register button
	private JButton registerButton;
	private JButton cancelButton;
	
	private ManagementSystem ms;
	public RegisterPage(ManagementSystem ms){
		this.ms = ms;
		initComponents();
		refreshData();
	}
	
	private void initComponents(){
		
		errorMessage = new JLabel ("");
		errorMessage.setForeground(Color.RED);
		
		
		reminder = new JLabel("Please select \"Applicant\" if you are a student");
		
		typeLabel = new JLabel ("Instructor/Applicant: ");
		typeLabel.setForeground(Color.BLACK);
		
		typeToggleList = new JComboBox<String>(new String[0]);
		typeToggleList.setForeground(Color.BLACK); 
		typeToggleList.addItem("Instructor");
		typeToggleList.addItem("Applicant");
		typeToggleList.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
		        @SuppressWarnings("unchecked")
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
		        selectedType = cb.getSelectedIndex();
			}
		});		
		
		usernameLabel = new JLabel("Username: ");
		usernameLabel.setForeground(Color.BLACK);
		usernameTextField = new JTextField();
		usernameTextField.setForeground(Color.BLACK);
		usernameTextField.setBorder(new LineBorder(Color.BLACK));
		
		
		registerButton = new JButton("Register");
		registerButton.addActionListener(new java.awt.event.ActionListener(){		
			public void actionPerformed(java.awt.event.ActionEvent evt){
				registerButtonActionPerformed(evt);
			}	
		});
		
		cancelButton= new JButton("Cancel");
		cancelButton.addActionListener(new java.awt.event.ActionListener(){		
			public void actionPerformed(java.awt.event.ActionEvent evt){
				cancelButtonActionPerformed(evt);
			}	
		});

		setTitle("Register");
		
		JSeparator horizontalLineTop = new JSeparator();


		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createParallelGroup()
				.addComponent(errorMessage)
				.addComponent(horizontalLineTop)

				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
								.addComponent(reminder)
								.addComponent(typeLabel)
								.addComponent(usernameLabel)
								.addComponent(cancelButton)
								)
						.addGroup(layout.createParallelGroup()
								.addComponent(typeToggleList)
								.addComponent(usernameTextField,100,100,100)
								.addComponent(registerButton)
								)
						)
				);
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createParallelGroup()
						.addComponent(reminder))
				.addGroup(layout.createParallelGroup()
						.addComponent(typeLabel)
						.addComponent(typeToggleList)
						)
				.addGroup(layout.createParallelGroup()
						.addComponent(horizontalLineTop)
						)
				.addGroup(layout.createParallelGroup()
						.addComponent(usernameLabel)
						.addComponent(usernameTextField,23,23,23)
						)
				.addGroup(layout.createParallelGroup()
						.addComponent(cancelButton)
						.addComponent(registerButton)
						)
				
				);
		pack();
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	private void refreshData(){
		errorMessage.setText(error);
		
		if (error==null|| error.trim().length()==0){
			selectedType = -1;
			typeToggleList.setSelectedIndex(selectedType);
			
			usernameTextField.setText("");
		}
		
		pack();
	}
	
	
	private void registerButtonActionPerformed(java.awt.event.ActionEvent evt){
		
		String username = usernameTextField.getText();
		error = "";
		
		if (selectedType < 0){
			error += "Please select a type! ";
		}
		
		
		if (error.length()==0){
			TamasController tc = new TamasController(ms);
			// Instructor 
			try{
				if (selectedType==0){
					tc.registerInstructor(username);
				} else{
					tc.registerApplicant(username);
				}
			}
			catch (InvalidInputException e){
				error += e.getMessage();
			}
			
			if (error.trim().length()==0){
				int selectedOption = JOptionPane.showConfirmDialog(null, 
		                "Registration Completed.\n"
		                + "Registration Page will be closed", 
		                "", 
		                JOptionPane.OK_CANCEL_OPTION); 
				if (selectedOption == JOptionPane.OK_OPTION) {
					new LoginDialog().setVisible(true);
					new LoginHint().setVisible(true);
					setVisible(false);
				}
			}


		}
		
		
		refreshData();
		

	}
	
	private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt){
		new LoginDialog().setVisible(true);
		setVisible(false);
	}
	
}
