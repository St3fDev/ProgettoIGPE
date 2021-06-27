package application.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import application.Main;
import application.SettingsPage;
import application.config.Utilities;
import application.view.ImageLoader;
import application.view.ManagerSoundBotton;
import application.view.SettingsPagePanel;

public class SettingsController extends MouseAdapter {

	private SettingsPagePanel settingsPage;
	JButton backToHome;
	JButton audioActiveted;
	JButton musicActiveted;
	
	public SettingsController(SettingsPagePanel settingsPage) {
		this.settingsPage = settingsPage;
		backToHome = settingsPage.getHomeButton();
		audioActiveted = settingsPage.getAudioActiveted();
		musicActiveted = settingsPage.getMusicActiveted();
		backToHome.addMouseListener(this);
		audioActiveted.addMouseListener(this);
		musicActiveted.addMouseListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == backToHome) {
			ManagerSoundBotton.getIstance().pressBotton();
			Main.startPage.setEnabled(true);
			SettingsPage.settingsPage.dispose();
		}
		if (e.getSource() == audioActiveted) {
			ManagerSoundBotton.getIstance().pressBotton();
			if (Utilities.audioOn)
				Utilities.audioOn = false;
			else 
				Utilities.audioOn = true;
			ImageLoader.getIstance().initAudioBotton(settingsPage.getAudioActiveted());
		}
		if (e.getSource() == musicActiveted) {
			ManagerSoundBotton.getIstance().pressBotton();
			if (Utilities.musicOn) {
				Main.soundMenu.stop();
				Utilities.musicOn = false;
			} 
			else { 
				Main.soundMenu.restart();
				Utilities.musicOn = true;
			}
			ImageLoader.getIstance().initSoundBotton(settingsPage.getMusicActiveted());
		}
	}
}
