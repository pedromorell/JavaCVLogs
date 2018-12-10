package controller;

import sa.LogsCleaner;
import sa.WekaDriver;

public class Controller {
	
	private static Controller INSTANCE = null;
	
	public void obtainResultsVisualize(String fichero, int x, int y, int c, String filtro) {
		LogsCleaner main = new LogsCleaner();
		main.cleanLogs(fichero);
		
		WekaDriver weka = new WekaDriver();
		weka.visualize(x, y, c);
			
	}
	
	public void obtainResultsClustering(String fichero, int x, int y, int numClusters, String filtro) {
		LogsCleaner main = new LogsCleaner();
		main.cleanLogs(fichero);
		
		WekaDriver weka = new WekaDriver();
		weka.clustering(x, y, numClusters);
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
