package application.view;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import application.config.Utility;

public class BallView {
	
	Image img;
	int dimX;
	int dimY;
	
	public BallView() {
		dimX = Utility.DIM_X_BALL;
		dimY = Utility.DIM_Y_BALL;
		try {
			img = ImageIO.read(getClass().getResourceAsStream("/application/resources/ball.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


