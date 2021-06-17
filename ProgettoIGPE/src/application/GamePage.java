package application;

import javax.swing.JFrame;
import javax.swing.JPanel;

import application.config.Sounds;
import application.config.Utilities;
import application.controller.GameController;
import application.model.Game;
import application.view.GamePanel;

public class GamePage {
	
	JFrame gamePage = new JFrame();
	
	public GamePage(int lvl) {
		GamePanel gp = new GamePanel();
		GameController controller = new GameController(gp);
		Game.getInstance().setLevel(lvl);
		Game.getInstance().showLevel();
		gp.addKeyListener(controller);
		gp.setFocusable(true);
		gamePage.add(gp);
		gamePage.setUndecorated(true);
		gamePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gamePage.setResizable(false);
		gamePage.setSize(Utilities.WIDTH_SIZE, Utilities.HEIGHT_SIZE);
		gamePage.setLocation(Main.startPage.getLocation());
		gamePage.setVisible(true);
		GameLoop gameLoop = new GameLoop(controller);
		Thread t = new Thread(gameLoop);
		t.start();
	}
	
}
