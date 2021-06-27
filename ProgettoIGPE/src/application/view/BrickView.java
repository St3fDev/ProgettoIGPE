package application.view;

import java.awt.Image;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import application.config.Utilities;

public class BrickView extends Common {
	
	Image img1;
	Image img2;
	Image img3;
	Image img4;
	Image img5;
	Image imgLightOn;
	Image imgLightOff;
	Vector<Image> img;
	
	public BrickView() {
		dimX = Utilities.DIM_X_BRICK;
		dimY = Utilities.DIM_Y_BRICK;
		img = new Vector<Image>();
		try {
			img1 = ImageIO.read(getClass().getResourceAsStream("/application/resources/icons/gray2.png"));
			img2 = ImageIO.read(getClass().getResourceAsStream("/application/resources/icons/gray_dmg2.png"));
			img3 = ImageIO.read(getClass().getResourceAsStream("/application/resources/icons/black3.png"));
			img4 = ImageIO.read(getClass().getResourceAsStream("/application/resources/icons/black2.png"));
			img5 = ImageIO.read(getClass().getResourceAsStream("/application/resources/icons/black_dmg2.png"));
			imgLightOn = ImageIO.read(getClass().getResourceAsStream("/application/resources/icons/lightOn.png"));
			imgLightOff = ImageIO.read(getClass().getResourceAsStream("/application/resources/icons/lightOff.png"));
			for (int i = 0; i < 9; i++) {
				img.add(ImageIO.read(getClass().getResourceAsStream("/application/resources/icons/" + i + ".png")));
			}
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "errore nella gestione delle icone dei brick", "ERRORE", JOptionPane.ERROR_MESSAGE);
		}
	}
}
