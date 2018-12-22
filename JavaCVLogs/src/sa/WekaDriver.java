package sa;

import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import weka.clusterers.ClusterEvaluation;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
import weka.core.converters.CSVLoader;
import weka.gui.explorer.ClustererAssignmentsPlotInstances;
import weka.gui.visualize.JPEGWriter;
import weka.gui.visualize.PlotData2D;
import weka.gui.visualize.VisualizePanel;

public class WekaDriver {
	
	public void visualize(int x, int y, int c) {
		CSVLoader loader = new CSVLoader();
		File file = new File("LogsAmpliado.csv");
		try {
			loader.setSource(file);
			Instances data = loader.getDataSet();
			PlotData2D pd1 = new PlotData2D(data);
			pd1.m_displayAllPoints = true;
			
			VisualizePanel vp = new VisualizePanel();
	        vp.addPlot(pd1);
	        
	        vp.setXIndex(x);
	        vp.setYIndex(y);
	        vp.setColourIndex(c);
	        
			// Abrir diagrama en un JFrame
			JFrame jf = new JFrame("Diagrama");
			jf.setSize(1024, 512);
			jf.add(vp);
			jf.setLocationRelativeTo(null);
			jf.setVisible(true);
			
			// Guardar diagrama en fichero jpg
			File file1 = new File("src\\imagen.jpg");
			JPEGWriter writer = new JPEGWriter(vp, file1);
			writer.generateOutput();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void clustering(int numClusters) {
		CSVLoader loader = new CSVLoader();
		File file = new File("LogsAmpliado.csv");
		try {
			loader.setSource(file);
			Instances data = loader.getDataSet();
			
			SimpleKMeans kmeans = new SimpleKMeans();
	        kmeans.setNumClusters(numClusters);
	        kmeans.setMaxIterations(100);
	        kmeans.setPreserveInstancesOrder(true);
	        
	        kmeans.buildClusterer(data);
	        	        			
	        ClusterEvaluation eval = new ClusterEvaluation();
	        eval.setClusterer(kmeans);
	        eval.evaluateClusterer(data);
	        
	        ClustererAssignmentsPlotInstances plotInstances = new ClustererAssignmentsPlotInstances();
	        plotInstances.setClusterer(kmeans);
	        plotInstances.setInstances(data);
	        plotInstances.setClusterEvaluation(eval);
	        plotInstances.setUp();
	        
	        VisualizePanel vp = new VisualizePanel();
	        vp.addPlot(plotInstances.getPlotData(kmeans.getClass().getName()));
			
	        JFrame jf = new JFrame("Diagrama");
			jf.setSize(1024, 512);
			jf.add(vp);
			jf.setLocationRelativeTo(null);
			jf.setVisible(true);
			
			// Guardar diagrama en fichero jpg
			File file1 = new File("src\\imagen.jpg");
			JPEGWriter writer = new JPEGWriter(vp, file1);
			writer.generateOutput();
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}