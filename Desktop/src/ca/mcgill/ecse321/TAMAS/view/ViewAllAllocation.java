package ca.mcgill.ecse321.TAMAS.view;

import javax.swing.JFrame;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JTabbedPane;
import javax.swing.JProgressBar;

public class ViewAllAllocation extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6085118368680695878L;
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
