package application.model;

import java.awt.Rectangle;

import application.config.Utility;

public class Paddle {
	
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
		return new Rectangle(x, y, Utility.DIM_X_PADDLE, Utility.DIM_Y_PADDLE);
	}
}
