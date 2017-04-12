package ca.mcgill.ecse321.TAMAS.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.LineBorder;

import ca.mcgill.ecse321.TAMAS.model.Applicant;
import ca.mcgill.ecse321.TAMAS.model.Department;
import ca.mcgill.ecse321.TAMAS.model.Instructor;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;
import ca.mcgill.ecse321.TAMAS.persistence.PersistenceXStream;

public class LoginDialog extends JFrame {

	private static String fileName = "output/data.xml";
	private HashMap<String, String> loginInfo = new HashMap<>();
	private static final long serialVersionUID = -4375840286920173785L;
	private JTextField tfUsername;
	private JPasswordField pfPassword;
	private JLabel lbUsername;
	private JLabel lbPassword;
	private JButton btnLogin;
	private JButton btnRegister;
	private boolean succeeded;

	/**
	 * Class constructor
	 */
	public LoginDialog() {

		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();
		cs.fill = GridBagConstraints.HORIZONTAL;

		lbUsername = new JLabel("Username: ");
		cs.gridx = 0;
		cs.gridy = 0;
		cs.gridwidth = 1;
		panel.add(lbUsername, cs);
		cs = new GridBagConstraints();
		tfUsername = new JTextField(20);
		cs.gridx = 1;
		cs.gridy = 0;
		cs.gridwidth = 2;
		panel.add(tfUsername, cs);
		cs = new GridBagConstraints();
		lbPassword = new JLabel("Password: ");
		cs.gridx = 0;
		cs.gridy = 1;
		cs.gridwidth = 1;
		panel.add(lbPassword, cs);
		cs = new GridBagConstraints();
		pfPassword = new JPasswordField(20);
		cs.gridx = 1;
		cs.gridy = 1;
		cs.gridwidth = 2;
		panel.add(pfPassword, cs);
		panel.setBorder(new LineBorder(Color.GRAY));

		btnLogin = new JButton("Login");

		btnLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Object user = getUserRole();
				if (user != null) {
					String type = "";
					if (user.getClass().equals(Instructor.class))
						type += " Instructor ";
					else if (user.getClass().equals(Department.class))
						type += " Department ";
					else if (user.getClass().equals(Applicant.class))
						type += " Applicant ";

					JOptionPane.showMessageDialog(LoginDialog.this,
							"Hi" + type + getUsername() + "! You have successfully logged in.", "Login",
							JOptionPane.INFORMATION_MESSAGE);
					succeeded = true;
					new MainPage(getUserRole()).setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(LoginDialog.this, "Invalid username or password", "Login",
							JOptionPane.ERROR_MESSAGE);
					// reset username and password
					tfUsername.setText("");
					pfPassword.setText("");
					succeeded = false;
				}
			}
		});
		btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				tfUsername.setText("");
				pfPassword.setText("");
				
				setVisible(false);
				toRegisterPage();
			}
		});
		JPanel bp = new JPanel();
		bp.add(btnLogin);
		bp.add(btnRegister);

		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(bp, BorderLayout.PAGE_END);

		pack();
		setResizable(false);
	}

	/**
	 * @param username Username
	 * @param password Password
	 * @return boolean that indicates if user is authenticated
	 */
	protected boolean authenticate(String username, String password) {
		// TODO Auto-generated method stub
		loginInfo.put("i", "i");
		loginInfo.put("d", "d");
		loginInfo.put("s", "s");
		if (loginInfo.containsKey(username)) {
			if (loginInfo.get(username).equals(password))
				return true;
		}
		return false;
	}

	/**
	 * @return username Username
	 */
	public String getUsername() {
		return tfUsername.getText().trim();
	}

	/**
	 * @return password Password
	 */
	public String getPassword() {
		return new String(pfPassword.getPassword());
	}

	/**
	 * @return boolean that indicates if succeeded
	 */
	public boolean isSucceeded() {
		return succeeded;
	}

	/**
	 * @return role of the user
	 */
	private Object getUserRole() {
		final ManagementSystem ms = PersistenceXStream.initializeModelManager(fileName);

		for (Instructor i : ms.getInstructors()) {
			if (i.getName() != null)
				if (this.getUsername().equals(i.getName()))
					return i;
		}
		for (Applicant i : ms.getApplicants()) {
			if (i.getName() != null)
				if (this.getUsername().equals(i.getName()))
					return i;
		}
		if (this.getUsername().equals("d") || this.getUsername().equals("department")) {
			return new Department();
		}
		return null;

	}

	private void toRegisterPage() {
		final ManagementSystem ms = PersistenceXStream.initializeModelManager(fileName);
		new RegisterPage(ms).setVisible(true);
	}
}
