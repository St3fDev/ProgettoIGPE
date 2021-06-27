package application.model;

import java.util.TimerTask;

import application.config.Utilities;

public class Countdown extends TimerTask {
	private int seconds;
	private int idx;

	public Countdown(int seconds, int idx) {
		this.seconds = seconds;
		this.idx = idx;
	}

	@Override
	public void run() {
		if (!Game.getInstance().isPause()) {
			if (seconds > 0) {
				Game.getInstance().getManagerTimePwr().set(idx, true);
				if (idx == Utilities.PWR_LARGE_PADDLE) {
					Game.getInstance().setFirstHalfPaddle(40);
					Game.getInstance().setWidthLargePaddle(50);
				}
				if (idx == Utilities.NERF_VEL_PADDLE) 
					Game.getInstance().getPaddle().speed = 16;
				seconds--;
			} else {
				Game.getInstance().getManagerTimePwr().set(idx, false);
				if (idx == Utilities.PWR_LARGE_PADDLE) {
					Game.getInstance().setFirstHalfPaddle(30);
					Game.getInstance().setWidthLargePaddle(0);
				}
				Game.getInstance().setLevelUp(false);
				this.cancel();
			}
		}
	}

}
