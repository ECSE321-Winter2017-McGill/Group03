package ca.mcgill.ecse321.TAMAS.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import ca.mcgill.ecse321.TAMAS.model.Applicant;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;

public class ViewEvaluation  extends JFrame {

	private static final long serialVersionUID = 425342489284063626L;
	
	private ManagementSystem ms;
	private Object user;
	
	private JLabel formTitle;
	private JTextArea allEval;
	private JButton backButton;
	private JSeparator horizontalLineTop;
	
	
	public ViewEvaluation(ManagementSystem ms, Object user) {
		this.ms = ms;
		this.user = user;
		
		initialize();
	}
	
	
	private void initialize() {
		
		formTitle = new JLabel("My Evaluation");
		formTitle.setFont(new Font("Georgia", Font.BOLD, 22));
		horizontalLineTop = new JSeparator();
		
		if (user.getClass().equals(Applicant.class)){
			allEval = new JTextArea();
			allEval.setText(((Applicant)user).getEvaluation());
			allEval.setEditable(false);
			allEval.setForeground(Color.BLACK);
		}

		
		backButton = new JButton("  Back  ");
		backButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				backButtonActionPerformed(evt);
			}
		});
		
		
		setTitle("My Evaluations");
		
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(layout.createParallelGroup()
				.addComponent(formTitle).addComponent(horizontalLineTop).addComponent(allEval));
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(formTitle)
				.addGroup(layout.createParallelGroup()
						.addComponent(horizontalLineTop))
				.addGroup(layout.createParallelGroup()
						.addComponent(allEval)));
		
		
		pack();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
		backToAllApplication();
		dispose();
	}
	
	private void backToAllApplication() {
		new AllApplication(ms, user).setVisible(true);
	}
	
}
