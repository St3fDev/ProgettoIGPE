package application.view;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import application.config.Utilities;

public class BallView extends Common {
	
	Image img;
	Image fireball_img;
	
	public BallView() {
		dimX = Utilities.DIM_BALL;
		dimY = Utilities.DIM_BALL;
		try {
			img = ImageIO.read(getClass().getResourceAsStream("/application/resources/icons/ball.png"));
			fireball_img = ImageIO.read(getClass().getResourceAsStream("/application/resources/icons/fireball.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


