package controller;

import sa.LogsCleaner;
import sa.WekaDriver;

public class Controller {
	
	private static Controller INSTANCE = null;
	
	public void obtainResults(int x, int y, int c) {
		LogsCleaner main = new LogsCleaner();
		main.cleanLogs();
		
		WekaDriver weka = new WekaDriver();
		weka.visualize(x, y, c);
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
