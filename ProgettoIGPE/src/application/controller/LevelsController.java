package application.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;

import application.GamePage;
import application.LevelsPage;
import application.Main;
import application.view.LevelLocked;
import application.view.LevelsPagePanel;

public class LevelsController extends MouseAdapter {

	private LevelsPagePanel levelsPage;
	HashMap<Integer, JButton> levels;
	JButton backToHome;
	
	public LevelsController(LevelsPagePanel levelsPage) {
		this.levelsPage = levelsPage;
		backToHome = levelsPage.getHomeButton();
		levels = levelsPage.getLevels();
		for(int i: levels.keySet()) {
			levels.get(i).addMouseListener(this);
		}
		backToHome.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == backToHome) {
			Main.startPage.setVisible(true);
			LevelsPage.levels.dispose();
		}
		for (int i: levels.keySet()) {
			if (e.getSource() == levels.get(i)) {
				boolean unlock = LevelLocked.getIstance().readLevel(i);
				if (unlock) {
					//Main.soundMenu.stop();
					LevelsPage.levels.dispose();
					GamePage gp = new GamePage(i);	
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

}
