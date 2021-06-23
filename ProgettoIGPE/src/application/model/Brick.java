package application.model;

import java.awt.Rectangle;

import application.config.Utilities;

public class Brick extends CommonVariable {

	int resistance;
	int resistanceInit;  
	Boolean destroyed;
	
	public Brick(int x, int y) {
		this.x = x;
		this.y = y;
		destroyed = false;
	}

	public Boolean getDestroyed() {
		return destroyed;
	}
	
	public void setDestroyed(Boolean d) {
		destroyed = d;
	}
	
	public int getLivesBrick() {
		return resistance;
	}
	
	public int getResistance() {
		return resistanceInit;
	}
	
	@Override
	public Rectangle getRect() {
		return new Rectangle(x, y, Utilities.DIM_X_BRICK, Utilities.DIM_Y_BRICK);
	}
}
