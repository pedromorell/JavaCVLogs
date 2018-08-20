package controller;

import sa.LogsCleaner;
import sa.WekaDriver;

public class Controller {
	
	private static Controller INSTANCE = null;
	
	public void obtainResults() {
		LogsCleaner main = new LogsCleaner();
		main.cleanLogs();
		
		WekaDriver weka = new WekaDriver();
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
