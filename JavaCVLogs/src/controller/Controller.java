package controller;

import sa.LogsCleaner;
import sa.WekaDriver;

public class Controller {
	
	private static Controller INSTANCE = null;
	
	public void obtainResults(String fichero, int x, int y, int c, String algoritmo) {
		LogsCleaner main = new LogsCleaner();
		main.cleanLogs(fichero);
		
		WekaDriver weka = new WekaDriver();
		if (algoritmo.equals("Visualize"))
			weka.visualize(x, y, c);
		else if (algoritmo.equals("Clustering")) {
			weka.clustering(x, y, c);
		}
			
	}
	
	public static Controller getInstance() {
		if (INSTANCE == null) {
            synchronized(Controller.class) {
                if (INSTANCE == null) {
                	INSTANCE = new Controller();
                }
            }
        }
        return INSTANCE;
    }
	
}
