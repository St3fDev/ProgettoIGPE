package application.view;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import application.config.Utilities;

public class PaddleView extends Common {
	
	Image img;
	Image img2;
	
	public PaddleView() {
		dimX = Utilities.DIM_X_PADDLE;
		dimY = Utilities.DIM_Y_PADDLE;
		try {
			img = ImageIO.read(getClass().getResourceAsStream("/application/resources/icons/paddle.png"));
			img2 = ImageIO.read(getClass().getResourceAsStream("/application/resources/icons/paddleLarge.png"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "errore nella gestione delle icone del paddle", "ERRORE", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
