package ca.mcgill.ecse321.TAMAS.application;

import ca.mcgill.ecse321.TAMAS.ManagementSystem;
import ca.mcgill.ecse321.TAMAS.persistence.PersistenceXStream;
import ca.mcgill.ecse321.TAMAS.view.LoginDialog;
import ca.mcgill.ecse321.TAMAS.view.MainPage;
import ca.mcgill.ecse321.TAMAS.view.PublishJobPostingPage;

public class StartApplication {
	private static String fileName = "output/publishjob.xml";

	public static void main(String[] args) {
		LoginDialog login=new LoginDialog();
		login.setVisible(true);	
	}

}
