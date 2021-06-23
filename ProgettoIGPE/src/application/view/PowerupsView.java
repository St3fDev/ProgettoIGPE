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
			life_img = ImageIO.read(getClass().getResourceAsStream("/application/resources/icons/pwr_life.png"));
			fireball_img = ImageIO.read(getClass().getResourceAsStream("/application/resources/icons/pwr_fireball.png"));
			largePaddle_img = ImageIO.read(getClass().getResourceAsStream("/application/resources/icons/pwr_paddleLarge.png"));
			slowPaddle_img = ImageIO.read(getClass().getResourceAsStream("/application/resources/icons/nerf_slowPaddle.png"));
			fastBall_img = ImageIO.read(getClass().getResourceAsStream("/application/resources/icons/nerf_fastBall.png"));
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
