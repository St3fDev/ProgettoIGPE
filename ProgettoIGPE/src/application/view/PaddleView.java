package application.view;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import application.config.Utility;
import application.model.Game;

public class PaddleView {
	
	Image img;
	int dimX;
	int dimY;
	
	public PaddleView() {
		dimX = Utility.DIM_X_PADDLE;
		dimY = Utility.DIM_Y_PADDLE;
		try {
			img = ImageIO.read(getClass().getResourceAsStream("/application/resources/paddle.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
