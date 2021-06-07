package application;

import javax.swing.JFrame;

public class SettingsPage {
	
	JFrame f;

	public SettingsPage() {
		f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().setFocusable(true);
		f.setResizable(false);
		f.setVisible(true);
		f.setSize(400,400);
		f.setLocationRelativeTo(null);
	}
}
