package ca.mcgill.ecse321.TAMAS.view;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import ca.mcgill.ecse321.TAMAS.model.Applicant;
import ca.mcgill.ecse321.TAMAS.model.Course;
import ca.mcgill.ecse321.TAMAS.model.Department;
import ca.mcgill.ecse321.TAMAS.model.Instructor;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;
import ca.mcgill.ecse321.TAMAS.persistence.PersistenceXStream;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class NewMainPage extends JPanel {
	private static String fileName = "output/data.xml";
	private ManagementSystem ms;
	private Object user;

	public NewMainPage(ManagementSystem ms, Object user) {
		super(new GridLayout(1, 1));

		this.user = user;
		this.ms = ms;

		JTabbedPane tabbedPane = new JTabbedPane();
		JComponent panel1 = allCourse(ms, user);

		tabbedPane.addTab("Tab 1", panel1);
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		JComponent panel2 = makeTextPanel("Panel #2");

		tabbedPane.addTab("Tab 2", panel2);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

		JComponent panel3 = makeTextPanel("Panel #3");
		tabbedPane.addTab("Tab 3", panel3);
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

		JComponent panel4 = makeTextPanel("Panel #4 (has a preferred size of 410 x 50).");
		panel4.setPreferredSize(new Dimension(410, 50));
		tabbedPane.addTab("Tab 4", panel4);
		tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);

		// Add the tabbed pane to this panel.
		add(tabbedPane);

		// The following line enables to use scrolling tabs.
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

		JFrame frame = new JFrame("TabbedPaneDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add content to the window.
		frame.add(this, BorderLayout.CENTER);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	private JComponent allCourse(ManagementSystem ms, final Object user) {
		// get table data ready;

		String[] columnNames = { "Semester", "Course Code", "Course Name", "Credit", "Instructor", "Number of TA",
				"Number of Grader", "Num of Tutorials", "Num of Labs" };
		String[][] data = new String[ms.numberOfCourses() + 1][9];

		int i = 0;
		for (Instructor anInstructor : ms.getInstructors()) {
			for (Course aCourse : anInstructor.getCourses()) {
				data[i][0] = aCourse.getSemester();
				data[i][1] = aCourse.getCourseCode();
				data[i][2] = aCourse.getCourseName();
				data[i][3] = "" + aCourse.getCredit() + " credits";
				data[i][4] = anInstructor.getName();
				data[i][5] = "" + aCourse.getNumTaNeeded();
				data[i][6] = "" + aCourse.getNumGraderNeeded();
				data[i][7] = "" + aCourse.getNumTutorial() + " / week";
				data[i][8] = "" + aCourse.getNumLab() + " / week";

				i++;
			}
		}

		// get table itself ready;
		final JTable table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(1200, 100));
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		JPanel buttomPane = new JPanel();

		// get the rest of frame ready;
		JButton addCourse = new JButton("Add a new Course");
		JButton backButton = new JButton("Back");

		// get frame ready;
		// setTitle("View All Course");
		BorderLayout layout = new BorderLayout();
		JPanel pane = new JPanel();
		pane.setLayout(layout);
		pane.add(scrollPane, BorderLayout.PAGE_START);
		if (user.getClass().equals(Instructor.class)) {
			// buttomPane.add(backButton);
			pane.add(buttomPane, BorderLayout.CENTER);
		} else if (user.getClass().equals(Applicant.class)) {
			// buttomPane.add(backButton);
			pane.add(buttomPane, BorderLayout.CENTER);
		} else {
			buttomPane.add(addCourse);
			// buttomPane.add(backButton);
			pane.add(buttomPane, BorderLayout.CENTER);
		}
		// pack();
		setVisible(true);
		// add actions listeners
		addCourse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final ManagementSystem ms = PersistenceXStream.initializeModelManager("output/data.xml");
				new AddCourse(ms, user).setVisible(true);
			}
		});

		// backButton.addActionListener(new ActionListener() {
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// //backToMain();
		// setVisible(false);
		// }
		// });
		return pane;
	}

	protected JComponent makeTextPanel(String text) {
		JPanel panel = new JPanel(false);
		JLabel filler = new JLabel(text);
		filler.setHorizontalAlignment(JLabel.CENTER);
		panel.setLayout(new GridLayout(1, 1));
		panel.add(filler);
		return panel;
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = NewMainPage.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event dispatch thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.

	}

	public static void main(String[] args) {
		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// Turn off metal's use of bold fonts
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				createAndShowGUI();
			}
		});
	}
}