package application.view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class SettingsPagePanel extends JPanel {

	private static final long serialVersionUID = -8148513089187186980L;
	
	JButton backToHome;
	JButton audioActiveted;
	JButton musicActiveted;
	Image background;
	
	public SettingsPagePanel() {
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
		try {
			background = ImageIO.read(getClass().getResourceAsStream("/application/resources/backgrounds/settings.png"));
			g.drawImage(background, 0, 0, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
