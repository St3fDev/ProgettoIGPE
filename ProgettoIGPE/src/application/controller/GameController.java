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
	
	public GameController(GamePanel gp) {
		this.gp = gp;
	}
	
	public void update() {
		gp.update();
		game.updateBall();
		game.pwrCollision();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			Game.getInstance().movePaddle(1);
			Game.getInstance().setPause(false);
		}
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			Game.getInstance().movePaddle(0);
			Game.getInstance().setPause(false);
		}
		else if (e.getKeyCode() == KeyEvent.VK_P) {
			if (Game.getInstance().getPause())
				Game.getInstance().setPause(false);
			else 
				Game.getInstance().setPause(true);
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void keyReleased(KeyEvent e) {}

}
