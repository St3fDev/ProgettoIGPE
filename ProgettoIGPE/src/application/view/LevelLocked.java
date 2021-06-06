package application.view;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LevelLocked {

	private static LevelLocked level = null;
	
	public static LevelLocked getIstance() {
		if (level == null) 
			level = new LevelLocked();
		return level;
	}
	
	public boolean readLevel(int i) {
		boolean unlock = false;
		try {
			BufferedReader br = new BufferedReader(new FileReader(getClass().getResource("/application/resources/file/levelLock.txt").getPath()));
			while (br.ready()) {
				String line = br.readLine();
				String[] livello = line.split(";");
				if (Integer.parseInt(livello[0]) == i && livello[1].equals("true")) {
					unlock = true;
				}
			}
		} catch (IOException e) {
			System.err.println("Errore nella lettura del file levelLock.txt");
		}
		return unlock;
	}
	
	//******************da fare quando si finisce il lvl 8*****************//
	public void setAllUnlocked() {
		
	}
}
