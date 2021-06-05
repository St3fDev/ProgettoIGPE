package application.model;

import java.awt.Rectangle;

import application.config.Utilities;

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
		return new Rectangle(x, y, Utilities.DIM_BALL, Utilities.DIM_BALL);
	}
}
