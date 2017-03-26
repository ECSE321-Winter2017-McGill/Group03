package ca.mcgill.ecse321.TAMAS.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import ca.mcgill.ecse321.TAMAS.controller.InvalidInputException;
import ca.mcgill.ecse321.TAMAS.controller.TamasController;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;

public class RegisterPage extends JFrame{
	
	//UI elements
	private JLabel errorMessage;
	private String error = null;
	
	//Choose registration type
	private JLabel typeLabel;
	private JComboBox<String> typeToggleList;
	private Integer selectedType = -1;
	
	//Username
	private JLabel usernameLabel;
	private JTextField usernameTextField;
	
	//Register button
	private JButton registerButton;
	
	private ManagementSystem ms;
	private static String fileName = "output/data.xml";
	
	public RegisterPage(ManagementSystem ms){
		this.ms = ms;
		initComponents();
		refreshData();
	}
	
	private void initComponents(){
		
		errorMessage = new JLabel ("");
		errorMessage.setForeground(Color.RED);
		
		typeLabel = new JLabel ("Instructor/Applicant: ");
		typeLabel.setForeground(Color.BLACK);
		
		typeToggleList = new JComboBox<String>(new String[0]);
		typeToggleList.setForeground(Color.BLACK); 
		typeToggleList.addItem("Instructor");
		typeToggleList.addItem("Applicant");
		typeToggleList.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
		        JComboBox<String> cb = (JComboBox<String>) evt.getSource();
		        selectedType = cb.getSelectedIndex();
			}
		});		
		
		usernameLabel = new JLabel("username: ");
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

		setTitle("Register");
		
		JSeparator horizontalLineTop = new JSeparator();
		JSeparator horizontalLineMiddle1 = new JSeparator();


		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createParallelGroup()
				.addComponent(errorMessage)
				.addComponent(horizontalLineTop)
				.addComponent(horizontalLineMiddle1)
				

				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
								.addComponent(typeLabel)
								.addComponent(usernameLabel)
								)
						.addGroup(layout.createParallelGroup()
								.addComponent(typeToggleList)
								.addComponent(usernameTextField)
								.addComponent(registerButton)
								)
						)
				);
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createParallelGroup()
						.addComponent(typeLabel)
						.addComponent(typeToggleList)
						)
				.addGroup(layout.createParallelGroup()
						.addComponent(horizontalLineTop)
						)
				.addGroup(layout.createParallelGroup()
						.addComponent(usernameLabel)
						.addComponent(usernameTextField)
						)
				.addGroup(layout.createParallelGroup()
						.addComponent(horizontalLineMiddle1)
						)
				.addGroup(layout.createParallelGroup()
						.addComponent(registerButton)
						)
				
				);
		pack();
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
		
		if (username == null || username.trim().length()==0){
			error += "Please enter a username! ";
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

		}
		
		refreshData();
		
	}
	

}
