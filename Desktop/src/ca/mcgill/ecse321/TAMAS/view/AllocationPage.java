package ca.mcgill.ecse321.TAMAS.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import ca.mcgill.ecse321.TAMAS.model.Course;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;

public class AllocationPage extends JFrame {
	
	private static final long serialVersionUID = -6385168299761243422L;

	
	
	
	
//	public AllocationPage(ManagementSystem ms, Course course, Object user){
//		this.ms = ms;
//		this.course = course;
//		this.user = user;
//		initComponents();
//	}
	
	
//	private void initComponents() {
	
	public static void main(String args[]){
		ManagementSystem ms;
		Course course;
		Object user;
		
		JSplitPane pane;
		JPanel mainPanel;
		JScrollPane consolePane;
		JLabel commandLine;
		
		
		
		
		
		pane = new JSplitPane();
		mainPanel = new JPanel();
		pane.setTopComponent(mainPanel);
		
		consolePane = new JScrollPane();
		pane.setBottomComponent(consolePane);
		
		commandLine = new JLabel("<html>");
		commandLine.setVerticalAlignment(SwingConstants.TOP);
		consolePane.setViewportView(commandLine);
			
		
		
		
		
		
		
//		pack();
//		setVisible(true);
//		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	
	public void refreshData() {
		
		
	}
	

}
