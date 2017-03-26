package ca.mcgill.ecse321.TAMAS.view;

import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class LoginHint extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1637505664795335145L;

	public LoginHint() {
		JLabel hint0 = new JLabel("To log in as a department use: ");
		JLabel hint1 = new JLabel("        Username: d or department Password: anykey");
		JLabel hint2 = new JLabel("To log in as a/an instructor or student use: ");
		JLabel hint3 = new JLabel("        Username: the instructor/student's name Password: anykey");
		setTitle("log in hints");
		Container pane = getContentPane();
		BoxLayout layout = new BoxLayout(pane, JFrame.EXIT_ON_CLOSE);
		pane.setLayout(layout);
		pane.add(hint0);
		pane.add(hint1);
		pane.add(hint2);
		pane.add(hint3);
		pack();
	}

}
