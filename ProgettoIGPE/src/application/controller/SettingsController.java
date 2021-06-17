package application.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import application.Main;
import application.SettingsPage;
import application.view.SettingsPagePanel;

public class SettingsController extends MouseAdapter {

	private SettingsPagePanel settingsPage;
	JButton backToHome;
	
	public SettingsController(SettingsPagePanel settingsPage) {
		this.settingsPage = settingsPage;
		backToHome = settingsPage.getHomeButton();
		backToHome.addMouseListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == backToHome) {
			Main.startPage.setEnabled(true);
			SettingsPage.settingsPage.dispose();
		}
	}
}
