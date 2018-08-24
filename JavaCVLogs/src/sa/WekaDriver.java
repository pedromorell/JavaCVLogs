package sa;

import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import weka.core.Instances;
import weka.core.converters.CSVLoader;
import weka.gui.visualize.JPEGWriter;
import weka.gui.visualize.Plot2D;
import weka.gui.visualize.PlotData2D;

public class WekaDriver {
	
	public void visualize(int x, int y, int c) {
		CSVLoader loader = new CSVLoader();
		File file = new File("LogsAmpliado.csv");
		try {
			loader.setSource(file);
			Instances data = loader.getDataSet();
			PlotData2D pd1 = new PlotData2D(data);
			pd1.m_displayAllPoints = true;
			Plot2D plot = new Plot2D();
			plot.setMasterPlot(pd1);
			plot.setXindex(x);
			plot.setYindex(y);
			plot.setCindex(c);
			
			// Abrir diagrama en un JFrame
			JFrame jf = new JFrame("Diagrama");
			jf.setSize(1024, 512);
			jf.add(plot);
			jf.setLocationRelativeTo(null);
			jf.setVisible(true);
			
			// Guardar diagrama en fichero jpg
			File file1 = new File("src\\imagen.jpg");
			JPEGWriter writer = new JPEGWriter(plot, file1);
			writer.generateOutput();
			jf.setVisible(false);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}