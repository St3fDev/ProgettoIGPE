package application.view;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ImageLoader {
	
	public static void initStartButton(JButton sb, int pos) {
		ImageIcon start = null;
		if (pos == 0)
			start = new ImageIcon("images/home/startButton.png");
		else if (pos == 1)
			start = new ImageIcon("images/home/startButton2.png");
		sb.setIcon(start);
		sb.setBounds(270,400,259,85);
		sb.setFocusable(false);
		sb.setOpaque(false);
		sb.setBorder(BorderFactory.createEmptyBorder());
		sb.setContentAreaFilled(false);
	}
	
	public static void initSettingButton(JButton sb, int pos) {
		ImageIcon settings = null;
		if (pos == 0)
			settings = new ImageIcon("images/home/settingsButton.png");
		else if (pos == 1)
			settings = new ImageIcon("images/home/settingsButton2.png");
		sb.setIcon(settings);
		sb.setBounds(250,550,299,85);
		sb.setFocusable(false);
		sb.setOpaque(false);
		sb.setBorder(BorderFactory.createEmptyBorder());
		sb.setContentAreaFilled(false);
	}
	
	public static void initExitButton(JButton e, int pos) {
		ImageIcon exit = null;
		if (pos == 0)
			exit = new ImageIcon("images/home/exitButton.png");
		else if (pos == 1)
			exit = new ImageIcon("images/home/exitButton2.png");
		e.setIcon(exit);
		e.setBounds(307,870,179,82);
		e.setFocusable(false);
		e.setOpaque(false);
		e.setBorder(BorderFactory.createEmptyBorder());
		e.setContentAreaFilled(false);
	}
	
	public static void initLevelsButton(JButton b, int x, int y, int i) {
		ImageIcon exit;
		exit = new ImageIcon("images/levels/"+ i +".png");
		b.setIcon(exit);
		b.setBounds(x,y,523,82);
		b.setFocusable(false);
		b.setOpaque(false);
		b.setBorder(BorderFactory.createEmptyBorder());
		b.setContentAreaFilled(false);
	}
	
	
}
