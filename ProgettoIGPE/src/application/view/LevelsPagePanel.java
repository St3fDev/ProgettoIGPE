package application.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class LevelsPagePanel extends JPanel {

	private static final long serialVersionUID = 8374418925237195039L;

	Image background;
	JButton backToHome;
	HashMap<Integer, JButton> levels;
	
	public LevelsPagePanel() {
		Image cursor = new ImageIcon(getClass().getResource("/application/resources/icons/cursor.png")).getImage();
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(cursor, new Point(0,0), ""));
		levels = new HashMap<Integer, JButton>();
		int k = 0;
		for (int i = 0; i < 9; i++) {
			JButton level = new JButton();
			levels.put(i, level);
			if (!ManagerFile.getIstance().readLevel(i)) {
				ImageLoader.getIstance().initLevelsButtonLock(level, 141, k + i * 83 + 130, i + 10);
			}
			else
				ImageLoader.getIstance().initLevelsButtonUnlock(level, 141, k + i * 83 + 130, i);
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
		var g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		try {
			background = ImageIO.read(getClass().getResourceAsStream("/application/resources/backgrounds/levels.png"));
			g2d.drawImage(background, 0, 0, null);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "errore nella gestione dell'immagine levels.png", "ERRORE", JOptionPane.ERROR_MESSAGE);
		}
	}

	public HashMap<Integer, JButton> getLevels() {
		return levels;
	}
	
	public JButton getHomeButton() {
		return backToHome;
	}
}
