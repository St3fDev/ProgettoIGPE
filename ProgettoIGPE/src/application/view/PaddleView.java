package application.view;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import application.config.Utilities;
import application.model.Game;

public class PaddleView extends Common {
	
	Image img;
	
	public PaddleView() {
		dimX = Utilities.DIM_X_PADDLE;
		dimY = Utilities.DIM_Y_PADDLE;
		try {
			img = ImageIO.read(getClass().getResourceAsStream("/application/resources/icons/paddle.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
