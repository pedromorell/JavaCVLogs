package sa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LogsCleaner {
	public void cleanLogs(String fichero, String filtro, String param1, String param2) {
		final String dir = System.getProperty("user.dir");
		try {
			Process proc = Runtime.getRuntime().exec("python " + dir + "\\python\\limpiar.py " + fichero + " " + filtro + " " + param1 + " " + param2);
			BufferedReader stdInput = new BufferedReader(new 
				     InputStreamReader(proc.getInputStream()));

			BufferedReader stdError = new BufferedReader(new 
			     InputStreamReader(proc.getErrorStream()));

			// read the output from the command
			System.out.println("Here is the standard output of the command:\n");
			String s = null;
			while ((s = stdInput.readLine()) != null) {
			    System.out.println(s);
			}

			// read any errors from the attempted command
			System.out.println("Here is the standard error of the command (if any):\n");
			while ((s = stdError.readLine()) != null) {
			    System.out.println(s);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
