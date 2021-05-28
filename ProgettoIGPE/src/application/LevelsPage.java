package application;

import javax.swing.JFrame;

import application.config.Utility;
import application.controller.LevelsController;
import application.view.LevelsPagePanel;

public class LevelsPage {

	public static JFrame levels = new JFrame();

	public LevelsPage() {

		levels.setLocation(Main.getJFrame().getLocation());

		LevelsPagePanel lv = new LevelsPagePanel();
		LevelsController controller = new LevelsController(lv);

		levels.add(lv);
		levels.setUndecorated(true);
		levels.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		levels.setResizable(false);
		levels.setSize(Utility.WIDTH_SIZE, Utility.HEIGHT_SIZE);
		levels.setVisible(true);

	}

	public static JFrame getJFrame() {
		return levels;
	}
}
