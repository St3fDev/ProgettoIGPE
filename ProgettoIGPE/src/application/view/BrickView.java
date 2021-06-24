package application.view;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import application.config.Utilities;

public class BrickView extends Common {
	
	Image img1;
	Image img2;
	Image img3;
	Image img4;
	Image img5;
	ArrayList<Image> img;
	
	public BrickView() {
		dimX = Utilities.DIM_X_BRICK;
		dimY = Utilities.DIM_Y_BRICK;
		img = new ArrayList<Image>();
		try {
			img1 = ImageIO.read(getClass().getResourceAsStream("/application/resources/icons/gray2.png"));
			img2 = ImageIO.read(getClass().getResourceAsStream("/application/resources/icons/gray_dmg2.png"));
			img3 = ImageIO.read(getClass().getResourceAsStream("/application/resources/icons/black3.png"));
			img4 = ImageIO.read(getClass().getResourceAsStream("/application/resources/icons/black2.png"));
			img5 = ImageIO.read(getClass().getResourceAsStream("/application/resources/icons/black_dmg2.png"));
			for (int i = 0; i < 9; i++) {
				img.add(ImageIO.read(getClass().getResourceAsStream("/application/resources/icons/" + i + ".png")));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
