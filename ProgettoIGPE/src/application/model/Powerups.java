package application.model;

import java.awt.Rectangle;

import application.config.Utilities;

public class Powerups {
	int x;
	int y;
	int speed;
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Rectangle getRect() {
		return new Rectangle(x, y, Utilities.DIM_PWR, Utilities.DIM_PWR);
	}
}
