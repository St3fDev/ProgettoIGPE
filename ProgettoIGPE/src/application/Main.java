package application;

import javax.swing.JFrame;

import application.config.Sounds;
import application.config.Utilities;
import application.controller.HomeController;
import application.view.HomePagePanel;

public class Main {

	public static JFrame startPage;
	public static Sounds soundMenu;
	public static Sounds soundTrack;

	public static void main(String[] args) {
		startPage = new JFrame();

		startPage.setSize(Utilities.WIDTH_SIZE, Utilities.HEIGHT_SIZE);
		HomePagePanel start = new HomePagePanel();
		HomeController home = new HomeController(start);
		soundMenu = new Sounds("soundTrack.wav");
		soundMenu.reduceVolume();
		soundTrack = new Sounds("soundTrack.wav");
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
