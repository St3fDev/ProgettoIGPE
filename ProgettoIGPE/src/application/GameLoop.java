package application;

import application.controller.GameController;

public class GameLoop implements Runnable {

	private GameController controller;
	private int frequency = 30;
	
	public GameLoop(GameController controller) {
		this.controller = controller;
	}
	
	@Override
	public void run() {		
		while(true) {
			controller.update(); 
			
			try {
				Thread.sleep(frequency);
			} catch (InterruptedException e) {
				return;
			}
		}
	}

}
