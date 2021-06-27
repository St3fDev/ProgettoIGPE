package application.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SettingsPagePanel extends JPanel {

	private static final long serialVersionUID = -8148513089187186980L;
	
	JButton backToHome;
	JButton audioActiveted;
	JButton musicActiveted;
	Image background;
	
	public SettingsPagePanel() {
		Image cursor = new ImageIcon(getClass().getResource("/application/resources/icons/cursor.png")).getImage();
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(cursor, new Point(0,0), ""));
		audioActiveted = new JButton();
		musicActiveted = new JButton();
		backToHome = new JButton();
		ImageLoader.getIstance().backBotton(backToHome);
		ImageLoader.getIstance().initAudioBotton(audioActiveted);
		ImageLoader.getIstance().initSoundBotton(musicActiveted);
		this.add(audioActiveted);
		this.add(musicActiveted);
		this.add(backToHome);
		this.setLayout(null);
	}
	
	public JButton getHomeButton() {
		return backToHome;
	}
	
	public JButton getAudioActiveted() {
		return audioActiveted;
	}
	
	public JButton getMusicActiveted() {
		return musicActiveted;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		var g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		try {
			background = ImageIO.read(getClass().getResourceAsStream("/application/resources/backgrounds/settings.png"));
			g2d.drawImage(background, 0, 0, null);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "errore nella gestione dell'immagine settings.png", "ERRORE", JOptionPane.ERROR_MESSAGE);
		}
	}
}
