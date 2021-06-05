package application.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import application.model.Game;
import application.view.GamePanel;

public class GameController implements KeyListener {

	private GamePanel gp;
	Game game = Game.getInstance();
	private Boolean pause;
	
	public GameController(GamePanel gp) {
		this.gp = gp;
		pause = false;
	}
	
	public void update() {
		if (!pause) {
		gp.update();
		game.updateBall();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			Game game = Game.getInstance();
			game.movePaddle(1);
		}
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			Game game = Game.getInstance();
			game.movePaddle(0);
		}
		else if (e.getKeyCode() == KeyEvent.VK_P) {
			if (!pause) 
				pause = true;
			else 
				pause = false;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void keyReleased(KeyEvent e) {}

}
