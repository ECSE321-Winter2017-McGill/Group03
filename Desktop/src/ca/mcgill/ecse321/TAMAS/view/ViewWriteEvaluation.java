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
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;

import javax.swing.JSplitPane;

import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.JTextPane;

public class ViewWriteEvaluation extends JFrame {

	private static final long serialVersionUID = -1858104722034773170L;
	private ManagementSystem ms;
	private Object user;

	private JFrame frame;

	private JComboBox<String> TAlist;
	private HashMap<String, Applicant> Appmap = new HashMap<>();
	JLabel lblEvaluation = new JLabel(" ");

	public ViewWriteEvaluation(ManagementSystem ms, Object user) {
		this.ms = ms;
		this.user = user;
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 719, 534);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));

		JSplitPane wholePane = new JSplitPane();

		JPanel panel = new JPanel();
		wholePane.setLeftComponent(panel);

		TAlist = new JComboBox<String>();

		for (Applicant app : ms.getApplicants()) {
			String key = app.getName();
			TAlist.addItem(key);
			Appmap.put(key, app);
		}
		TAlist.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				@SuppressWarnings("unchecked")
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				cb.getSelectedIndex();
				updataList();
			}
		});

		JButton btnSubmit = new JButton("Submit");

		JLabel lblSelectATa = new JLabel("Select A TA:");

		final JTextPane textPane = new JTextPane();

		JButton backBTN = new JButton("Back");
		backBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		final JLabel errorlbl = new JLabel("");
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
												.addComponent(backBTN, GroupLayout.PREFERRED_SIZE, 146,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
												.addComponent(btnSubmit, GroupLayout.PREFERRED_SIZE, 146,
														GroupLayout.PREFERRED_SIZE)))
										.addGap(42))
								.addGroup(gl_panel.createSequentialGroup().addComponent(errorlbl).addContainerGap(223,
										Short.MAX_VALUE)))));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addGap(14).addComponent(errorlbl).addGap(3).addComponent(lblSelectATa)
				.addGap(18)
				.addComponent(TAlist, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(18).addComponent(textPane, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)
				.addGap(18)
				.addGroup(
						gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(btnSubmit).addComponent(backBTN))
				.addContainerGap()));
		panel.setLayout(gl_panel);
		getContentPane().add(wholePane);

		JScrollPane scrollPane = new JScrollPane();
		wholePane.setRightComponent(scrollPane);
		setPreferredSize(new Dimension(800, 515));
		scrollPane.setColumnHeaderView(lblEvaluation);
		backBTN.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				backToMain();
				dispose();
			}
		});
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String eval = textPane.getText();

				TamasController tc = new TamasController(ms);
				String TA = (String) TAlist.getSelectedItem();
				Applicant ta = Appmap.get(TA);
				try {
					tc.createTAEval(ta, eval);
				} catch (Exception e) {
					errorlbl.setForeground(Color.RED);
					errorlbl.setText(e.getMessage());
					e.printStackTrace();
				}

				textPane.setText("");
			}
		});
		pack();

	}

	protected void updataList() {
		String TA = (String) TAlist.getSelectedItem();
		Applicant ta = Appmap.get(TA);
		if (ta.getEvaluation().length() <= 3) {
			lblEvaluation.setText("<html>" + "<br>" + "<p>" + "No Evaluation Yet." + "</p>" + "</html>");
		} else {
			lblEvaluation.setText("<html>" + "<br>" + "<p>" + ta.getEvaluation() + "</p>" + "</html>");
		}
	}

	public void backToMain() {
		new MainPage(user).setVisible(true);
	}
}
