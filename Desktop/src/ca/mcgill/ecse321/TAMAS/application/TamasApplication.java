package ca.mcgill.ecse321.TAMAS.application;

import javax.swing.UIManager;

import ca.mcgill.ecse321.TAMAS.view.LoginDialog;
import ca.mcgill.ecse321.TAMAS.view.LoginHint;
import ca.mcgill.ecse321.TAMAS.view.WriteEvaluation;

public class TamasApplication {

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		}
		// Start Application from log in page.
		LoginDialog login = new LoginDialog();
		login.setVisible(true);

		// Show hints for loggin in
		LoginHint lg = new LoginHint();
		lg.setVisible(true);

	}
}
