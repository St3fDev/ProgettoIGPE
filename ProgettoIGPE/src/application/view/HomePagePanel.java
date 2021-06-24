package application.view;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;


public class HomePagePanel extends JPanel {
	
	private static final long serialVersionUID = -5770724919346341798L;
	
	Image background;
	JButton startButton;
	JButton settingsButton;
	JButton exitButton;
	boolean settingsOn;
	
	public HomePagePanel() {
		startButton = new JButton();
		settingsButton = new JButton();
		exitButton = new JButton();
		ImageLoader.getIstance().initStartButton(startButton, 0);
		ImageLoader.getIstance().initSettingButton(settingsButton, 0);
		ImageLoader.getIstance().initExitButton(exitButton, 0);
		
		this.add(exitButton);
		this.add(settingsButton);
		this.add(startButton);
		this.setLayout(null);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		var g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		try {
			background = ImageIO.read(getClass().getResourceAsStream("/application/resources/backgrounds/start.jpeg"));
			g2d.drawImage(background, 0, 0, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	public JButton getExitButton() {
		return exitButton;
	}

	public JButton getStartButton() {
		return startButton;
	}

	public JButton getSettingsButton() {
		return settingsButton;
	}
}
