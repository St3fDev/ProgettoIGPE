package application.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import application.GamePage;
import application.LevelsPage;
import application.model.Game;
import application.view.GamePanel;
import application.view.Maps;

public class GameController extends KeyAdapter {

	private GamePanel gp;
	Game game = Game.getInstance();

	public GameController(GamePanel gp) {
		this.gp = gp;
	}

	public void update() {
		if (!game.isLose() && !game.isWon() && !game.isLevelUp()) {
			game.updateBall();
			game.pwrCollision();
		}
		gp.update();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (!game.isLose() && !game.isWon()) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
				game.movePaddle(1);
				game.setPause(false);
				gp.setFirstTime(false);
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
				game.movePaddle(0);
				game.setPause(false);
				gp.setFirstTime(false);
			} else if (e.getKeyCode() == KeyEvent.VK_P) {
				if (game.isPause())
					game.setPause(false);
				else
					game.setPause(true);
			}
		} else {
			if (e.getKeyCode() == KeyEvent.VK_R) {
				game.restartAll();
				gp.setGame(true);
				game.setLose(false);
				game.setWon(false);
			}
			if (e.getKeyCode() == KeyEvent.VK_Q) {
				System.exit(0);
			}
		}
		if (game.isPause())
			if (e.getKeyCode() == KeyEvent.VK_Q) {
				int a = JOptionPane.showConfirmDialog(gp,
						"Are you sure you want to quit the game? All progress will be lost","Confirm",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (a == JOptionPane.YES_OPTION)
					System.exit(0);
			}
	}
}
