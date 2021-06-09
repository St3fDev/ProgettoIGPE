package application.model;

import java.awt.Rectangle;

import application.config.Utilities;

public class Powerups extends CommonVariable {
	int speed;
	int power;
	
	public void setPower(int pwr) {
		power = pwr;
	}
	
	public int getPower() {
		return power;
	}
	
	public Rectangle getRect() {
		return new Rectangle(x, y, Utilities.DIM_PWR, Utilities.DIM_PWR);
	}
}
