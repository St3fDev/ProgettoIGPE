package application.view;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import application.config.Utilities;

public class PowerupsView extends Common {
	
	Image fireball_img;
	Image largePaddle_img;
	Image life_img;
	Image fastBall_img;
	Image slowPaddle_img;
	
	public PowerupsView() {
		dimX = Utilities.DIM_X_PWR;
		dimY = Utilities.DIM_Y_PWR;
		try {
			fastBall_img = ImageIO.read(getClass().getResourceAsStream("/application/resources/icons/fastBall.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
