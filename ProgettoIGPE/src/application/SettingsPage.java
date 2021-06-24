package application;

import javax.swing.JFrame;

import application.config.Utilities;
import application.controller.SettingsController;
import application.view.SettingsPagePanel;

public class SettingsPage {
	
	public static JFrame settingsPage = new JFrame();

	public SettingsPage() {
		

		SettingsPagePanel sp = new SettingsPagePanel();
		SettingsController controller = new SettingsController(sp);
		
		settingsPage.setSize(400, 400);
		settingsPage.setLocationRelativeTo(null);
		settingsPage.add(sp);
		settingsPage.setUndecorated(true);
		settingsPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		settingsPage.setResizable(false);
		settingsPage.setVisible(true);
	}
}
