package application.model;

import java.awt.Rectangle;

public abstract class CommonVariables {
	int x;
	int y;
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public abstract Rectangle getRect();
}
