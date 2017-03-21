package ca.mcgill.ecse321.TAMAS.application;

import ca.mcgill.ecse321.TAMAS.view.LoginDialog;
import ca.mcgill.ecse321.TAMAS.view.LoginHint;
public class TamasApplication {

	public static void main(String[] args) {
		// Start Application from log in page.
		LoginDialog login = new LoginDialog();
		login.setVisible(true);

		
		// Show hints for loggin in
		LoginHint lg = new LoginHint();
		lg.setVisible(true);

	}
}
