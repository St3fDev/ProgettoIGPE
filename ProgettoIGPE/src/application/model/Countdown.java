package application.model;

import java.util.TimerTask;

public class Countdown extends TimerTask {
	int seconds;
	
	public Countdown(int seconds) {
		this.seconds = seconds;
	}

	@Override
	public void run() {
		if (seconds > 0) {
			System.out.println("PWR ATTIVO");
			seconds--;
		}
	}

}
