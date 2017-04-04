package ca.mcgill.ecse321.TAMAS.view;

import java.awt.BorderLayout;
import java.awt.Color;
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
		pbar.setForeground(Color.PINK);
		pbar.setBounds(100, 100, 400, 120);
		pbar.setMinimum(MY_MINIMUM);
		pbar.setMaximum(MY_MAXIMUM);
		// add to JPanel
		add(pbar, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel("     Loading Database     ");
		lblNewLabel.setBounds(145, 26, 135, 35);
		lblNewLabel.setFont(new Font("Georgia", Font.BOLD, 20));
		add(lblNewLabel, BorderLayout.PAGE_START);

		JLabel spaceLabel = new JLabel("                          ");
		add(spaceLabel, BorderLayout.PAGE_END);
	}

	public void updateBar(int newValue) {
		pbar.setValue(newValue);
	}

	@Override
	public void run() {

		final ShowProgress progress = new ShowProgress();

		JFrame frame = new JFrame("  Loading  ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(progress);
		frame.setBackground(Color.WHITE);
		// frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);

		// run a loop to demonstrate raising
		for (int i = MY_MINIMUM; i <= MY_MAXIMUM; i++) {
			final int percent = i;
			try {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						progress.updateBar(percent);
					}
				});

				java.lang.Thread.sleep(getNum());
			} catch (InterruptedException e) {
				;
			}
		}
		frame.dispose();

	}

	private long getNum() {
		int[] a = { 5, 85, 10, 0 ,1};
		return a[(int) (Math.random() * 5)];
	}
}