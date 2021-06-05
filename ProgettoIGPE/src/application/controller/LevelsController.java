package application.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;

import application.GamePage;
import application.LevelsPage;
import application.config.Utilities;
import application.view.LevelsPagePanel;

public class LevelsController extends MouseAdapter {
	
	private LevelsPagePanel levelsPage;
	HashMap<Boolean, JButton> levels; 
	
	public LevelsController(LevelsPagePanel levelsPage) {
		this.levelsPage = levelsPage;
		levels = levelsPage.getLevels();
		for (Boolean e: levels.keySet()) {
			JButton b = levels.get(e);
			b.addMouseListener(this);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	//************ DA MODIFICARE **************//
		if (e.getSource() == levels.get(true)) {
			JFrame f = LevelsPage.getJFrame();
			f.dispose();
			GamePage gp = new GamePage();
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
	
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
}
