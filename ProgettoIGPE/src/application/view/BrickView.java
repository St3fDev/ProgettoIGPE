package application.view;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import application.config.Utilities;

public class BrickView {
	
	Image img;
	int dimX;
	int dimY;
	
	public BrickView() {
		dimX = Utilities.DIM_X_BRICK;
		dimY = Utilities.DIM_Y_BRICK;
		try {
			img = ImageIO.read(getClass().getResourceAsStream("/application/resources/icons/muro (1).png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
