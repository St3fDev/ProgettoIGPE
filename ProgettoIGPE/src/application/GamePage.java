package application;

import javax.swing.JFrame;

import application.config.Utility;
import application.controller.GameController;
import application.view.GamePanel;

public class GamePage {
	
	JFrame gamePage = new JFrame();
	
	public GamePage() {
				
		GamePanel gp = new GamePanel();
		GameController controller = new GameController(gp);
		
		gp.addKeyListener(controller);
		gp.setFocusable(true);
		gamePage.add(gp);
		gamePage.setUndecorated(true);
		gamePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gamePage.setResizable(false);
		gamePage.setSize(Utility.WIDTH_SIZE, Utility.HEIGHT_SIZE);
		gamePage.setLocation(Main.getJFrame().getLocation());
		gamePage.setVisible(true);
		GameLoop gameLoop = new GameLoop(controller);
		Thread t = new Thread(gameLoop);
		t.start();
	}
	
}
