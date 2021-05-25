package application.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import application.model.Game;
import application.view.GamePanel;

public class GameController implements KeyListener {

	private GamePanel gp;
	Game game = Game.getInstance();
	
	public GameController(GamePanel gp) {
		this.gp = gp;
	}
	
	public void update() {
		gp.update();
		game.updateBall();
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
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void keyReleased(KeyEvent e) {}

}
