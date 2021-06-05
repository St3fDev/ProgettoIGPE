package application.view;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import application.config.Utilities;

public class BallView {
	
	Image img;
	int dimX;
	int dimY;
	
	public BallView() {
		dimX = Utilities.DIM_BALL;
		dimY = Utilities.DIM_BALL;
		try {
			img = ImageIO.read(getClass().getResourceAsStream("/application/resources/icons/ball.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


