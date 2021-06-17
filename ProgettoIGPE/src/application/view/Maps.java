package application.view;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Maps {
	
	private static Maps maps = null;
	
	public static Maps getIstance() {
		if (maps == null) 
			maps = new Maps();
		return maps;
	}
	
	public int[][] ReadMap(int level) {
		int [][] livello = new int[15][8];
		try {
			BufferedReader br = new BufferedReader(new FileReader(getClass().getResource("/application/resources/maps/"+ level + ".txt").getPath()));
			int i = 0;
			while (br.ready()) {
				String line = br.readLine();
				String[] row = line.split(";");
				for (int j = 0; j < 8; j++) { 
					livello[i][j] = Integer.parseInt(row[j].trim());
				}
				i++;
			}	 
		} catch (IOException e) {
			System.out.println("Errore nella lettura del file " + level + ".txt");
		}
		
		return livello;
	}
}
