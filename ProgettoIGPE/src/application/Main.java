package application;

import javax.swing.JFrame;

import application.config.Utility;
import application.controller.HomeController;
import application.view.HomePagePanel;

public class Main {

	public static JFrame startPage;
	
	public static void main(String[] args) {
		startPage = new JFrame();

		startPage.setSize(Utility.WIDTH_SIZE, Utility.HEIGHT_SIZE);
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
