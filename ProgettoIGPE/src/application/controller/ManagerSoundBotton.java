package application.controller;

import application.view.Sounds;

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
		bottonEffect.start();
	}
	
	public void SoundMenu() {
		
	}
	
}
