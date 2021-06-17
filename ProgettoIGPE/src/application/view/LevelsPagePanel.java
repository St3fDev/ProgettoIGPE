package application.view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class LevelsPagePanel extends JPanel {

	private static final long serialVersionUID = 8374418925237195039L;

	Image background;
	JButton backToHome;
	HashMap<Integer, JButton> levels;
	
	public LevelsPagePanel() {
		levels = new HashMap<Integer, JButton>();
		int k = 0;
		for (int i = 0; i < 9; i++) {
			JButton level = new JButton();
			levels.put(i, level);
			ImageLoader.getIstance().initLevelsButton(level, 141, k + i * 83 + 130, i);
			this.add(level);
			k+=2;
		}
		backToHome = new JButton();
		ImageLoader.getIstance().backBotton(backToHome);
		this.add(backToHome);
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

	public HashMap<Integer, JButton> getLevels() {
		return levels;
	}
	
	public JButton getHomeButton() {
		return backToHome;
	}
}
