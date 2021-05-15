package application;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame startPage = new JFrame();
		startPage.setTitle("Universe Breakout");
		startPage.setSize(Settings.WIDTH_SIZE, Settings.HEIGHT_SIZE);
		startPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startPage.setVisible(true);
	}

}
