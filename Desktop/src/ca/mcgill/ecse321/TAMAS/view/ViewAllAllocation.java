package ca.mcgill.ecse321.TAMAS.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.JList;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JToggleButton;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JTabbedPane;
import javax.swing.JProgressBar;
import javax.swing.JTree;

public class ViewAllAllocation extends JFrame{
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	
	/**
	 * Class constructor
	 */
	public ViewAllAllocation() {
		getContentPane().setLayout(null);
		
		Component verticalGlue = Box.createVerticalGlue();
		verticalGlue.setBounds(168, 205, 0, -178);
		getContentPane().add(verticalGlue);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setBounds(0, 0, 418, 212);
		getContentPane().add(splitPane);
		
		JToolBar toolBar = new JToolBar();
		splitPane.setRightComponent(toolBar);
		toolBar.add(tabbedPane);
		
		JProgressBar progressBar = new JProgressBar();
		tabbedPane.addTab("New tab", null, progressBar, null);
	}
}
