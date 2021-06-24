package application.view;

import javax.swing.BorderFactory;
import  javax.swing.ImageIcon;
import javax.swing.JButton;

import application.config.Utilities;

public class ImageLoader {

	private static ImageLoader imgLoad = null;
	
	public static ImageLoader getIstance() {
		if (imgLoad == null)
			imgLoad = new ImageLoader();
		return imgLoad;
	}
	
	public void initStartButton(JButton sb, int pos) {
		ImageIcon start = null;
		if (pos == 0)
			start = new ImageIcon(getClass().getResource("/application/resources/icons/startButton.png").getPath());
		else if (pos == 1)
			start = new ImageIcon(getClass().getResource("/application/resources/icons/startButton2.png").getPath());
		sb.setIcon(start);
		sb.setBounds(270,400,259,85);
		sb.setFocusable(false);
		sb.setOpaque(false);
		sb.setBorder(BorderFactory.createEmptyBorder());
		sb.setContentAreaFilled(false);
	}
	
	public void initSettingButton(JButton sb, int pos) {
		ImageIcon settings = null;
		if (pos == 0)
			settings = new ImageIcon(getClass().getResource("/application/resources/icons/settingsButton.png").getPath());
		else if (pos == 1)
			settings = new ImageIcon(getClass().getResource("/application/resources/icons/settingsButton2.png").getPath());
		sb.setIcon(settings);
		sb.setBounds(250,550,299,85);
		sb.setFocusable(false);
		sb.setOpaque(false);
		sb.setBorder(BorderFactory.createEmptyBorder());
		sb.setContentAreaFilled(false);
	}
	
	public void initExitButton(JButton b, int pos) {
		ImageIcon exit = null;
		if (pos == 0)
			exit = new ImageIcon(getClass().getResource("/application/resources/icons/exitButton.png").getPath());
		else if (pos == 1)
			exit = new ImageIcon(getClass().getResource("/application/resources/icons/exitButton2.png").getPath());
		b.setIcon(exit);
		b.setBounds(307,810,179,82);
		b.setFocusable(false);
		b.setOpaque(false);
		b.setBorder(BorderFactory.createEmptyBorder());
		b.setContentAreaFilled(false);
	}
	
	public void initLevelsButtonUnlock(JButton b, int x, int y, int i) {
		ImageIcon levels;
		levels = new ImageIcon(getClass().getResource("/application/resources/levels/"+ i +".png").getPath());
		b.setIcon(levels);
		b.setBounds(x,y,523,82);
		b.setFocusable(false);
		b.setOpaque(false);
		b.setBorder(BorderFactory.createEmptyBorder());
		b.setContentAreaFilled(false);
	}
	
	public void initLevelsButtonLock(JButton b, int x, int y, int i) {
		ImageIcon levels;
		levels = new ImageIcon(getClass().getResource("/application/resources/levels/"+ i +".png").getPath());
		b.setIcon(levels);
		b.setBounds(x,y,523,82);
		b.setFocusable(false);
		b.setOpaque(false);
		b.setBorder(BorderFactory.createEmptyBorder());
		b.setContentAreaFilled(false);
	}
	
	public void backBotton(JButton b) {
		ImageIcon backBotton;
		backBotton = new ImageIcon(getClass().getResource("/application/resources/icons/back.png").getPath());
		b.setIcon(backBotton);
		b.setBounds(40,50,50,50);
		b.setFocusable(false);
		b.setOpaque(false);
		b.setBorder(BorderFactory.createEmptyBorder());
		b.setContentAreaFilled(false);
	}
	
	public void initAudioBotton(JButton b) {
		ImageIcon audioBotton;
		if (Utilities.audioOn)
			audioBotton = new ImageIcon(getClass().getResource("/application/resources/icons/audioOn.png").getPath());
		else 
			audioBotton = new ImageIcon(getClass().getResource("/application/resources/icons/audioOff.png").getPath());
		b.setIcon(audioBotton);
		b.setBounds(100,170,80,80);
		b.setFocusable(false);
		b.setOpaque(false);
		b.setBorder(BorderFactory.createEmptyBorder());
		b.setContentAreaFilled(false);
	}
	
	public void initSoundBotton(JButton b) {
		ImageIcon musicBotton;
		if (Utilities.musicOn)
			musicBotton = new ImageIcon(getClass().getResource("/application/resources/icons/musicOn.png").getPath());
		else 
			musicBotton = new ImageIcon(getClass().getResource("/application/resources/icons/musicOff.png").getPath());
		b.setIcon(musicBotton);
		b.setBounds(220,170,80,80);
		b.setFocusable(false);
		b.setOpaque(false);
		b.setBorder(BorderFactory.createEmptyBorder());
		b.setContentAreaFilled(false);
	}
}
