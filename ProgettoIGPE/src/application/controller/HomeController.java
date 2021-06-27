package application.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import application.LevelsPage;
import application.Main;
import application.SettingsPage;
import application.view.HomePagePanel;
import application.view.ImageLoader;
import application.view.ManagerSoundBotton;
import application.view.Sounds;

public class HomeController extends MouseAdapter {

	private HomePagePanel homePage;
	JButton exit;
	JButton start;
	JButton settings;
	Sounds bottonEffect;
	
	public HomeController(HomePagePanel homePage) {
		this.homePage = homePage;
		bottonEffect = new Sounds("botton.wav");
		exit = homePage.getExitButton();
		start = homePage.getStartButton();
		settings = homePage.getSettingsButton();
		start.addMouseListener(this);
		settings.addMouseListener(this);
		exit.addMouseListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == exit) {
			ManagerSoundBotton.getIstance().pressBotton();
			System.exit(0);
		}
		
		if (e.getSource() == start) {
			ManagerSoundBotton.getIstance().pressBotton();
			Main.startPage.setVisible(false);
			LevelsPage lp = new LevelsPage();
		}
		if (e.getSource() == settings) {
			ManagerSoundBotton.getIstance().pressBotton();
			Main.startPage.setEnabled(false);
			SettingsPage sp = new SettingsPage();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == start)
			ImageLoader.getIstance().initStartButton(start, 1);
		else if (e.getSource() == settings)
			ImageLoader.getIstance().initSettingButton(settings, 1);
		else if (e.getSource() == exit) 
			ImageLoader.getIstance().initExitButton(exit, 1);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == start)
			ImageLoader.getIstance().initStartButton(start, 0);
		if (e.getSource() == settings)
			ImageLoader.getIstance().initSettingButton(settings, 0);
		if (e.getSource() == exit)
			ImageLoader.getIstance().initExitButton(exit, 0);
	}


}
