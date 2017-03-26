package ca.mcgill.ecse321.TAMAS.view;

import java.awt.Container;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;

public class LoginHint extends JFrame {

	private static final long serialVersionUID = -1637505664795335145L;

	public LoginHint() {
		JLabel hint0 = new JLabel("To log in as a department use: ");
		hint0.setFont(new Font("Georgia", Font.BOLD, 13));
		
		JLabel hint1 = new JLabel("        Username: d or department");
		JLabel hint4 = new JLabel("        Password: anykey");
		
		JSeparator separator = new JSeparator();
		
		JLabel hint2 = new JLabel("To log in as an instructor or a student use: ");
		hint2.setFont(new Font("Georgia", Font.BOLD, 13));
		
		JLabel hint3 = new JLabel("        Username: the instructor/student's name");
		JLabel hint5 = new JLabel("        Password: anykey");
		setTitle("Log In Hints");
		
		Container pane = getContentPane();
		BoxLayout layout = new BoxLayout(pane, JFrame.EXIT_ON_CLOSE);
		pane.setLayout(layout);
		pane.add(hint0);
		pane.add(hint1);
		pane.add(hint4);
		pane.add(separator);
		pane.add(hint2);
		pane.add(hint3);
		pane.add(hint5);
		
		pack();
		setResizable(false);
	}

}
