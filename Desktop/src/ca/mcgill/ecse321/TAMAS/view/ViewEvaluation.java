package ca.mcgill.ecse321.TAMAS.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import ca.mcgill.ecse321.TAMAS.model.Applicant;

import ca.mcgill.ecse321.TAMAS.model.Application;
import ca.mcgill.ecse321.TAMAS.model.Application.Status;
import ca.mcgill.ecse321.TAMAS.model.Course;
import ca.mcgill.ecse321.TAMAS.model.Department;
import ca.mcgill.ecse321.TAMAS.model.Instructor;
import ca.mcgill.ecse321.TAMAS.model.JobPosting;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;

public class ViewEvaluation extends JFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = 425342489284063626L;

	private ManagementSystem ms;

	private Object user;
	private int selectedApplicant;
	private ArrayList<String> allAppName;

	private JLabel formTitle;
	private JLabel TaLabel;
	private JLabel ViewLabel;
	private JButton backButton;
	private JButton viewEvaluation;
	private JTextArea evaluation;
	private JComboBox<String> allApplicant;
	private JSeparator horizontalLineTop;

	public ViewEvaluation(ManagementSystem ms, Object user) {
		this.user = user;
		this.ms = ms;
		initComponents();
	}


	private void initComponents() {


		formTitle = new JLabel("View Your Evaluation");
		formTitle.setFont(new Font("Georgia", Font.BOLD, 22));

		horizontalLineTop = new JSeparator();

		allApplicant = new JComboBox<String>();


		if (user.getClass().equals(Applicant.class)) {
			Applicant me = (Applicant) user;
			allAppName = new ArrayList<String>();


			for (Application app : me.getApplications()) {
				if (app.getStatus().equals(Status.OFFER_ACCEPTED)) {
					if (app.getJobPosting().getJobTitle().equals("TA")) {
						allAppName.add(me.getName());
						allApplicant.addItem(me.getName());

					}
				}
			}
		}

		else if (user.getClass().equals(Instructor.class)) {
			Instructor me = (Instructor) user;
			allAppName = new ArrayList<String>();

			for (Course c : me.getCourses()) {
				for (JobPosting jp : c.getJobPosting()) {
					for (Application app : jp.getApplications()) {
						if (app.getStatus().equals(Status.OFFER_ACCEPTED)) {
							if (app.getJobPosting().getJobTitle().equals("TA")) {
								allAppName.add(app.getApplicant().getName());
								allApplicant.addItem(app.getApplicant().getName());
							}
						}
					}				}
			}
		} else {
			allAppName = new ArrayList<String>();
			for (Applicant ap : this.ms.getApplicants()) {
				for (Application app : ap.getApplications())
					if (app.getStatus().equals(Status.OFFER_ACCEPTED)) {
						if (app.getJobPosting().getJobTitle().equals("TA")) {
							allAppName.add(ap.getName());
							allApplicant.addItem(ap.getName());
						}					}
			}
		}

		// get frame ready;
		setTitle("View Your Evaluation");

		TaLabel = new JLabel("Choose an Applicant:");
		TaLabel.setForeground(Color.BLACK);

		ViewLabel = new JLabel("View the evaluation:");		ViewLabel.setForeground(Color.BLACK);


			for(Application app: me.getApplications()){
				if (app.getApplicationStatus().equals("accepted")){
				    if (app.getJobPosting().getJobTitle().equals("TA")){
						allAppName.add(me.getName());
						allApplicant.addItem(me.getName());

				    }
				}	
			}
		}
		
		else if (user.getClass().equals(Instructor.class)) {
			Instructor me = (Instructor) user;
			allAppName = new ArrayList<String>();
		    
		    for (Course c: me.getCourses()){
		    	for (JobPosting jp: c.getJobPosting()){
		    		for (Application app: jp.getApplications()){
		    			if (app.getApplicationStatus().equals("accepted")){
					        if (app.getJobPosting().getJobTitle().equals("TA")){
					        	allAppName.add(app.getApplicant().getName());
		   		   		   		allApplicant.addItem(app.getApplicant().getName());
					       }
		    			}
		    		}
		    	}
		    }
		}
		else{
			allAppName = new ArrayList<String>();
			for (Applicant ap: this.ms.getApplicants()){
				for(Application app: ap.getApplications())
					if (app.getApplicationStatus().equals("accepted")){
			             if (app.getJobPosting().getJobTitle().equals("TA")){
			             	allAppName.add(ap.getName());
   		   		   	    	allApplicant.addItem(ap.getName());
			       }
    			}
    		}
		}
		
		// get frame ready;
	    setTitle("View Your Evaluation");
	    
	    TaLabel = new JLabel("Choose an Applicant:");
		TaLabel.setForeground(Color.BLACK);
		
		ViewLabel = new JLabel("View the evaluation:");
		ViewLabel.setForeground(Color.BLACK);
		

		backButton = new JButton("back");

		viewEvaluation = new JButton("view");
		viewEvaluation.setPreferredSize(new Dimension(60, 20));

		evaluation = new JTextArea(10, 20);
		evaluation.setEditable(false);

		// layout
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(layout.createParallelGroup().addComponent(formTitle).addComponent(horizontalLineTop)


				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup().addComponent(TaLabel).addComponent(ViewLabel))
						.addGroup(layout.createParallelGroup().addComponent(allApplicant).addComponent(evaluation)
								.addComponent(viewEvaluation).addComponent(backButton))));

		layout.setVerticalGroup(layout.createSequentialGroup().addComponent(formTitle)
				.addGroup(layout.createParallelGroup().addComponent(horizontalLineTop))
				.addGroup(layout.createParallelGroup().addComponent(TaLabel).addComponent(allApplicant))
				.addGroup(layout.createParallelGroup().addComponent(viewEvaluation))
				.addGroup(layout.createParallelGroup().addComponent(ViewLabel).addComponent(evaluation))
				.addGroup(layout.createParallelGroup().addComponent(backButton)));

		pack();

		// All Action Listeners
		allApplicant.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unchecked")
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
				selectedApplicant = cb.getSelectedIndex();
			}
		});

		viewEvaluation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.print(selectedApplicant);
				String applicantName = allApplicant.getItemAt(selectedApplicant).toString();
				Applicant selectedApp = ms.getApplicant(0);

				for (Applicant a : ms.getApplicants()) {
					if (a.getName().equals(applicantName)) {

						selectedApp = a;
					}
				}
				evaluation.setEditable(true);
				evaluation.append(selectedApp.getEvaluation());
				evaluation.setEditable(false);
			}
		});

		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				backToMain();
				setVisible(false);
			}
		});
	}


	private void backToMain() {
		new MainPage(user).setVisible(true);
	}
}

