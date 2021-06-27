package application.model;

import java.awt.Rectangle;

import application.config.Utilities;

public class Paddle extends CommonVariables {
	
	int speed;

	@Override
	public Rectangle getRect() {
		return new Rectangle(x, y, Utilities.DIM_X_PADDLE, Utilities.DIM_Y_PADDLE);
	}
}
