package application;

import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class SettingsPage {
	
	public SettingsPage() {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().setFocusable(true);
		f.setResizable(false);
		f.setVisible(true);
		f.setSize(400,400);
		f.setLocationRelativeTo(null);
	}
}
