package application.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.JButton;

import application.Settings;
import application.view.LevelsPagePanel;

public class LevelsController extends MouseAdapter {
	
	private LevelsPagePanel levelsPage;
	HashMap<JButton, Boolean> levels; 
	
	public LevelsController(LevelsPagePanel levelsPage) {
		this.levelsPage = levelsPage;
		levels = levelsPage.getLevels();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
	
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
}
