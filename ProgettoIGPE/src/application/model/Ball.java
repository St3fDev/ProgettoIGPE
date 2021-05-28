package application.model;

import java.awt.Rectangle;

import application.config.Utility;

public class Ball {
	int x;
	int y;
	int dirX;
	int dirY;
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Rectangle getRect() {
		return new Rectangle(x, y, Utility.DIM_BALL, Utility.DIM_BALL);
	}
}
