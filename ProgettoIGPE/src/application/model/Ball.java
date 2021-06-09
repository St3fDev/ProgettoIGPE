package application.model;

import java.awt.Rectangle;

import application.config.Utilities;

public class Ball extends CommonVariable {

	int dirX;
	int dirY;

	public Rectangle getRect() {
		return new Rectangle(x, y, Utilities.DIM_BALL, Utilities.DIM_BALL);
	}
}
