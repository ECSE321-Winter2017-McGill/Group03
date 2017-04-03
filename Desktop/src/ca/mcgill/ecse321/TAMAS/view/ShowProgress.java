package ca.mcgill.ecse321.TAMAS.view;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;

public class ShowProgress extends JPanel implements Runnable {

	JProgressBar pbar;

	static final int MY_MINIMUM = 0;

	static final int MY_MAXIMUM = 100;

	public ShowProgress() {
		BorderLayout layout = new BorderLayout();
		setLayout(layout);
		// initialize Progress Bar
		pbar = new JProgressBar();
		pbar.setBounds(100, 100, 400, 100);
		pbar.setMinimum(MY_MINIMUM);
		pbar.setMaximum(MY_MAXIMUM);
		// add to JPanel
		add(pbar, BorderLayout.PAGE_END);

		JLabel lblNewLabel = new JLabel("   Loading Database");
		lblNewLabel.setBounds(145, 26, 135, 35);
		lblNewLabel.setFont(new Font("Georgia", Font.BOLD,26));
		add(lblNewLabel, BorderLayout.PAGE_START);
	}

	public void updateBar(int newValue) {
		pbar.setValue(newValue);
	}

//	public static void main(String args[]) {
//
//		final ShowProgress it = new ShowProgress();
//
//		JFrame frame = new JFrame("Progress");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setContentPane(it);
//		frame.pack();
//		frame.setVisible(true);
//
//		// run a loop to demonstrate raising
//		for (int i = MY_MINIMUM; i <= MY_MAXIMUM; i++) {
//			final int percent = i;
//			try {
//				SwingUtilities.invokeLater(new Runnable() {
//					public void run() {
//						it.updateBar(percent);
//					}
//				});
//				java.lang.Thread.sleep(10);
//			} catch (InterruptedException e) {
//				;
//			}
//		}
//		frame.dispose();
//	}

	@Override
	public void run() {

		final ShowProgress it = new ShowProgress();

		JFrame frame = new JFrame("Progress");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(it);
		frame.pack();
		frame.setVisible(true);

		// run a loop to demonstrate raising
		for (int i = MY_MINIMUM; i <= MY_MAXIMUM; i++) {
			final int percent = i;
			try {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						it.updateBar(percent);
					}
				});
				java.lang.Thread.sleep(10);
			} catch (InterruptedException e) {
				;
			}
		}
		frame.dispose();
		
	}
}