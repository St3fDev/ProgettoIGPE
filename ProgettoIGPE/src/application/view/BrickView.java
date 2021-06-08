package application.view;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import application.config.Utilities;

public class BrickView {
	
	Image img;
	Image img2;
	Image img3;
	int dimX;
	int dimY;
	
	public BrickView() {
		dimX = Utilities.DIM_X_BRICK;
		dimY = Utilities.DIM_Y_BRICK;
		try {
			img = ImageIO.read(getClass().getResourceAsStream("/application/resources/icons/brick_gray2.png"));
			img2 = ImageIO.read(getClass().getResourceAsStream("/application/resources/icons/brick_gray_dmg2.png"));
			img3 = ImageIO.read(getClass().getResourceAsStream("/application/resources/icons/brick_blue1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
