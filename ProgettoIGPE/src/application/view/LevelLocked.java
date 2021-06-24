package application.view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

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
	
	public void setAllUnlocked() {
		String[] level = new String[9];
		for (int i = 0; i < 9; i++) {
			level[i] = i + ";true";
		}
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(getClass().getResource("/application/resources/file/levelLock.txt").getFile(), false));
			for (int i = 0; i < 9; i++) {			
				writer.write(level[i]);
				writer.newLine();
			}
			writer.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "can't find the object levelLock.txt", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public boolean readAbout() {
		boolean about = false;
		try {
			BufferedReader br = new BufferedReader(new FileReader(getClass().getResource("/application/resources/file/firstGame.txt").getPath()));
			while (br.ready()) {
				String line = br.readLine();
				if (line.equals("true"))
					about = true;
			}
		} catch (IOException e) {
			System.err.println("Errore nella lettura del file levelLock.txt");
		}
		return about;
	}
	
	public void setFirstGame() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(getClass().getResource("/application/resources/file/firstGame.txt").getFile(), false));
			writer.write("false");
			writer.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "can't find the object firstGame.txt", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
}
