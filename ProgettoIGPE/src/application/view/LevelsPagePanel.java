package application.view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class LevelsPagePanel extends JPanel {
	
	private static final long serialVersionUID = 8374418925237195039L;

	Image background;
	HashMap<Boolean, JButton> levels;
	
	
	public LevelsPagePanel() {
		levels = new HashMap<Boolean, JButton>();
		int k = 0;
		for (int i = 0; i < 9; i++) {
				JButton level = new JButton();
				ImageLoader.getIstance().initLevelsButton(level, 141,k + i * 82 + 130, i);
				this.add(level);
				if (i == 0) 
					levels.put(true, level);
				else
					levels.put(false, level);
				k += 10;
		}
		this.setLayout(null);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			background = ImageIO.read(getClass().getResourceAsStream("/application/resources/backgrounds/levels.png"));
			g.drawImage(background, 0, 0, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public HashMap<Boolean,JButton> getLevels() {
		return levels;
	}
	
}
