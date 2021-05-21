package application.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import application.LevelsPage;
import application.Main;
import application.SettingsPage;
import application.view.HomePagePanel;
import application.view.ImageLoader;

public class HomeController extends MouseAdapter {

	private HomePagePanel homePage;
	JButton exit;
	JButton start;
	JButton settings;
	
	public HomeController(HomePagePanel homePage) {
		this.homePage = homePage;
		exit = homePage.getExitButton();
		start = homePage.getStartButton();
		settings = homePage.getSettingsButton();
		start.addMouseListener(this);
		settings.addMouseListener(this);
		exit.addMouseListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == exit)
			System.exit(0);
		
		if (e.getSource() == start) {
			JFrame f = Main.getJFrame();
			f.dispose();
			LevelsPage lp = new LevelsPage();
		}
		/******aggiungere il click sulla finestra settings******/
		if (e.getSource() == settings) {
			SettingsPage sp = new SettingsPage();
			
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == start)
			ImageLoader.initStartButton(start, 1);
		else if (e.getSource() == settings)
			ImageLoader.initSettingButton(settings, 1);
		else if (e.getSource() == exit) 
			ImageLoader.initExitButton(exit, 1);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == start)
			ImageLoader.initStartButton(start, 0);
		if (e.getSource() == settings)
			ImageLoader.initSettingButton(settings, 0);
		if (e.getSource() == exit)
			ImageLoader.initExitButton(exit, 0);
	}

}
