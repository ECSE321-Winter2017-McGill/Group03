package ca.mcgill.ecse321.TAMAS.application;

import ca.mcgill.ecse321.TAMAS.ManagementSystem;
import ca.mcgill.ecse321.TAMAS.persistence.PersistenceXStream;
import ca.mcgill.ecse321.TAMAS.view.PublishJobPostingPage;

public class PublishJobPosting {
	private static String fileName = "output/publishjob.xml";

	public static void main(String[] args) {
		
		final ManagementSystem rm = PersistenceXStream.initializeModelManager(fileName);
		java.awt.EventQueue.invokeLater( new Runnable() {
			public void run() {
				new PublishJobPostingPage(rm).setVisible(true);
			}
		});
	}

}
