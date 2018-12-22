package controller;

import sa.LogsCleaner;
import sa.WekaDriver;

public class Controller {
	
	private static Controller INSTANCE = null;
	
	public void obtainResultsVisualize(String fichero, int x, int y, int c, String filtro, String param1, String param2) {
		LogsCleaner main = new LogsCleaner();
		main.cleanLogs(fichero, filtro, param1, param2);
		
		WekaDriver weka = new WekaDriver();
		weka.visualize(x, y, c);
			
	}
	
	public void obtainResultsClustering(String fichero, int numClusters, String filtro, String param1, String param2) {
		LogsCleaner main = new LogsCleaner();
		main.cleanLogs(fichero, filtro, param1, param2);
		
		WekaDriver weka = new WekaDriver();
		weka.clustering(numClusters);
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
