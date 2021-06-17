package application.view;

import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SettingsPagePanel extends JPanel {

	private static final long serialVersionUID = -8148513089187186980L;
	
	JButton backToHome;
	
	public SettingsPagePanel() {
		backToHome = new JButton();
		ImageLoader.getIstance().backBotton(backToHome);
		this.add(backToHome);
		this.setLayout(null);
	}
	
	public JButton getHomeButton() {
		return backToHome;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
	}
}
