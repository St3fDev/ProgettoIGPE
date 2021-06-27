package application;

import javax.swing.JFrame;

import application.config.Utilities;
import application.controller.HomeController;
import application.view.HomePagePanel;
import application.view.Sounds;

public class Main {

	public static JFrame startPage;
	public static Sounds soundMenu;
	
	public static void main(String[] args) {
		startPage = new JFrame();
		startPage.setSize(Utilities.WIDTH_SIZE, Utilities.HEIGHT_SIZE);
		HomePagePanel start = new HomePagePanel();
		HomeController home = new HomeController(start);
		soundMenu = new Sounds("music.wav");
		soundMenu.reduceVolume();
		soundMenu.start();
		soundMenu.loop();
		startPage.setLocationRelativeTo(null);
		startPage.setResizable(false);
		startPage.setUndecorated(true);
		start.setFocusable(true);
		startPage.add(start);
		startPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startPage.setVisible(true);
	}
}
