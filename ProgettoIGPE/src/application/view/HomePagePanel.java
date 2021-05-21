package application.view;


import java.awt.Graphics;
import java.awt.Image;
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
	
	public HomePagePanel() {
		startButton = new JButton();
		settingsButton = new JButton();
		exitButton = new JButton();
		ImageLoader.initStartButton(startButton, 0);
		ImageLoader.initSettingButton(settingsButton, 0);
		ImageLoader.initExitButton(exitButton, 0);
		
		this.add(exitButton);
		this.add(settingsButton);
		this.add(startButton);
		this.setLayout(null);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			background = ImageIO.read(getClass().getResourceAsStream("/application/resources/start.jpeg"));
			g.drawImage(background, 0, 0, null);
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
