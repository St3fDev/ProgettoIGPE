package application.view;

import application.config.Utilities;

public class ManagerSoundBotton {
	
	private static ManagerSoundBotton mSound = null;
	public Sounds bottonEffect;
	
	public static ManagerSoundBotton getIstance() {
		if (mSound == null)
			mSound = new ManagerSoundBotton();
		return mSound;
	}
	
	public void pressBotton() {
		bottonEffect = new Sounds("botton.wav");
		bottonEffect.reduceVolume();
		if (Utilities.audioOn)
			bottonEffect.start();
	}
	
}
