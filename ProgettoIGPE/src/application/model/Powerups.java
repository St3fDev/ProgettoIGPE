package application.model;

import java.awt.Rectangle;

import application.config.Utilities;

public class Powerups extends CommonVariables {
	int speed;
	int power;
	
	public void setPower(int pwr) {
		power = pwr;
	}
	
	public int getPower() {
		return power;
	}
	
	@Override
	public Rectangle getRect() {
		return new Rectangle(x, y, Utilities.DIM_X_PWR, Utilities.DIM_Y_PWR);
	}
}
