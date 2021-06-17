package application;

import application.controller.GameController;

// utilizzando questa classe non mettiamo ad ogni movimento il repaint
// ma utilizziamo i thread per fare il repaint ogni tot tempo (60 millisec)
public class GameLoop implements Runnable {

	private GameController controller;
	private int frequency = 30;
	
	public GameLoop(GameController controller) {
		this.controller = controller;
	}
	
	@Override
	public void run() {		
		while(true) {
			controller.update(); // che ha il metodo repaint 
			
			try {
				Thread.sleep(frequency);
			} catch (InterruptedException e) {
				return;
			}
		}
	}

}
