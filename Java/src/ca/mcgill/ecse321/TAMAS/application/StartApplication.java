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
//		new MainPage().setVisible(true);
//		final ManagementSystem rm = PersistenceXStream.initializeModelManager(fileName);
//
//		LoginDialog login=new LoginDialog();
//		login.setVisible(true);
//		login.setFocusableWindowState(true);
//        if(login.isSucceeded()){
////        	login.setText("Hi " + login.getUsername() + "!");
//        	System.out.println("sss");
//    		new PublishJobPostingPage(rm).setVisible(true);	
//        }
//		java.awt.EventQueue.invokeLater( new Runnable() {
//			public void run() {
////				new PublishJobPostingPage(rm).setVisible(true);
//					LoginDialog login=new LoginDialog();
//					login.setVisible(true);
////				while(!login.isSucceeded()){
////					
////				}
////				login.dispose();
//				new PublishJobPostingPage(rm).setVisible(true);	
//			}
//		});
	}

}
