package application;

import javax.swing.JFrame;

import application.view.LevelsPagePanel;

public class LevelsPage {
	
	JFrame levels = new JFrame();

	public LevelsPage() {
	
		levels.setTitle("Universe breakout");
		levels.setLocation(Main.getJFrame().getLocation());
		
		LevelsPagePanel lv = new LevelsPagePanel();
		
		levels.add(lv);
		levels.setUndecorated(true);
		levels.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		levels.setResizable(false);
		levels.setSize(Settings.WIDTH_SIZE, Settings.HEIGHT_SIZE);
		levels.setVisible(true);
		
	}
}
