package application;

import javax.swing.JFrame;

import application.controller.HomeController;
import application.view.HomePagePanel;

public class Main {

	static JFrame startPage;
	
	public static void main(String[] args) {
		startPage = new JFrame();

		startPage.setTitle("Franco scoreggione");
		startPage.setSize(Settings.WIDTH_SIZE, Settings.HEIGHT_SIZE);
		HomePagePanel start = new HomePagePanel();
		HomeController home = new HomeController(start);
		
		startPage.setLocationRelativeTo(null);
		startPage.setResizable(false);
		startPage.setUndecorated(true);
		start.setFocusable(true);
		startPage.add(start);
		startPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startPage.setVisible(true);
	}

	public static JFrame getJFrame() {
		return startPage;
	}
	
}
