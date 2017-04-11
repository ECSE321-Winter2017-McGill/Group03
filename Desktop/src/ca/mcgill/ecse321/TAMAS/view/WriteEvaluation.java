package ca.mcgill.ecse321.TAMAS.view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import ca.mcgill.ecse321.TAMAS.controller.TamasController;
import ca.mcgill.ecse321.TAMAS.model.Applicant;
import ca.mcgill.ecse321.TAMAS.model.Application;
import ca.mcgill.ecse321.TAMAS.model.Course;
import ca.mcgill.ecse321.TAMAS.model.Instructor;
import ca.mcgill.ecse321.TAMAS.model.JobPosting;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.awt.event.ActionEvent;

import javax.swing.JSplitPane;

import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.JTextPane;

public class WriteEvaluation extends JFrame {

	private static final long serialVersionUID = -1858104722034773170L;
	private ManagementSystem ms;
	private Object user;

	private JFrame frame;

	private JComboBox<String> TAlist;
	private HashMap<String, Applicant> Appmap = new HashMap<>();
	JLabel lblEvaluation = new JLabel(" ");
	int taSelected = -1;
	private JLabel errorMessage = new JLabel("");
	private JTextPane textPane = new JTextPane();
	private String error = "";

	public WriteEvaluation(ManagementSystem ms, Object user) {
		this.ms = ms;
		this.user = user;
		
		initialize(user);
		taSelected = -1;
		TAlist.setSelectedIndex(taSelected);
	}

	private void initialize(final Object user) {
		
		textPane.setBackground(Color.GRAY.brighter());
		textPane.setForeground(Color.BLACK);
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 719, 534);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));

		JSplitPane wholePane = new JSplitPane();

		JPanel panel = new JPanel();
		wholePane.setLeftComponent(panel);

		TAlist = new JComboBox<String>(new String[0]);

		// A instructor can only write evaluation for TA of his/her course
		if (user.getClass().equals(Instructor.class)) {
			Instructor anInstructor = (Instructor) user;
			List<Course> myCourses = anInstructor.getCourses();
			List<JobPosting> relatedJobPosting = new ArrayList<>();
			for (Course aCourse : myCourses) {
				relatedJobPosting.addAll(aCourse.getJobPosting());
			}
			for (JobPosting aJobPosting : relatedJobPosting) {
				List<Application> allApplication = aJobPosting.getApplications();
				for (Application aApplication : allApplication) {
					if (aApplication.getStatusFullName().equals("OFFER_ACCEPTED")){
						String key = aApplication.getApplicant().getName() + " - " + aApplication.getJobPosting().getJobTitle() + " for " + aApplication.getJobPosting().getCourse().getCourseCode();
						Appmap.put(key, aApplication.getApplicant());
					}
							
				}
			}
		}
		
		// A department can write evaluation for all the TA
		else {
			for (Applicant applicant : ms.getApplicants()) {
				for (Application aApplication: applicant.getApplications()){
					String key = applicant.getName() + " - " + aApplication.getJobPosting().getJobTitle() + " for " + aApplication.getJobPosting().getCourse().getCourseCode();
					TAlist.addItem(key);
					Appmap.put(key, applicant);
				}

			}
		}

		TAlist.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				@SuppressWarnings("unchecked")
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				taSelected = cb.getSelectedIndex();
				updataList();       ////////////////////////////////////////////////////////////////////
			}
		});

		JButton btnSubmit = new JButton("Submit");

		JLabel lblSelectATa = new JLabel("Select a TA:");

		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				backToMain();
			}
		});


		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING,
										gl_panel.createSequentialGroup().addComponent(lblSelectATa).addContainerGap(178,
												Short.MAX_VALUE))
								.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup().addGroup(gl_panel
										.createParallelGroup(Alignment.TRAILING)
										.addComponent(textPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 316,
												Short.MAX_VALUE)
										.addComponent(TAlist, Alignment.LEADING, 0, 316, Short.MAX_VALUE)
										.addGroup(gl_panel.createSequentialGroup()
												.addComponent(backButton, GroupLayout.PREFERRED_SIZE, 146,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
												.addComponent(btnSubmit, GroupLayout.PREFERRED_SIZE, 146,
														GroupLayout.PREFERRED_SIZE)))
										.addGap(42))
								.addGroup(gl_panel.createSequentialGroup().addComponent(errorMessage).addContainerGap(223,
										Short.MAX_VALUE)))));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addGap(14).addComponent(errorMessage).addGap(3).addComponent(lblSelectATa)
				.addGap(18)
				.addComponent(TAlist, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(18).addComponent(textPane, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)
				.addGap(18)
				.addGroup(
						gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(btnSubmit).addComponent(backButton))
				.addContainerGap()));
		panel.setLayout(gl_panel);
		getContentPane().add(wholePane);

		JScrollPane scrollPane = new JScrollPane();
		wholePane.setRightComponent(scrollPane);
		setPreferredSize(new Dimension(800, 515));
		scrollPane.setColumnHeaderView(lblEvaluation);
		
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				backToMain();
				dispose();
			}
		});
		
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				error = "";
				
				if (taSelected < 0){
					error = error + "Please select a TA to evaluate.";
				}
				
				if (error.trim().length() == 0){
				String eval = textPane.getText();

				TamasController tc = new TamasController(ms);
				String nameKey = (String) TAlist.getSelectedItem();
				Applicant thisTA = Appmap.get(nameKey);
				
				String header = "";
				if (user.getClass().equals(Instructor.class)){
					header = nameKey;
				}
				else{
					header = "Department's Note";
				}
				
				
				try {
					if (thisTA.getEvaluation().trim().length() != 0){
						tc.addMoreTAEval(thisTA, header, eval);
					}
					else{
						tc.createTAEval(thisTA,header, eval);
					}
				} catch (Exception e) {
					errorMessage.setForeground(Color.RED);
					errorMessage.setText(e.getMessage());
					e.printStackTrace();
				}

				
				}
				
				refreshData();
			}
		});
		pack();

	}
	
	
	public void refreshData(){
		// error
		errorMessage.setText(error);
		errorMessage.setForeground(Color.RED);
		
		taSelected = -1;
		TAlist.setSelectedIndex(taSelected);
		textPane.setText("");
		
	}
	

	protected void updataList() {
		String TA = (String) TAlist.getSelectedItem();
		Applicant ta = Appmap.get(TA);
		if (ta == null || ta.getEvaluation() == null || ta.getEvaluation().length() <= 2){
			lblEvaluation.setText("<html>" + "<br>" + "<p>" + "No Evaluation Yet." + "</p>" + "</html>");
			
		}

		else {
			lblEvaluation.setText("<html>" + "<br>" + "<p>" + ta.getEvaluation() + "</p>" + "</html>");
		}
	}

	public void backToMain() {
		new MainPage(user).setVisible(true);
	}
}
