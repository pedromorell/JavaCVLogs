package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import controller.Controller;

public class ResultsAL implements ActionListener {
		
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		HomeGUI home = HomeGUI.getInstance();
		
		String inputF = home.getInputFichero();
		String inputA = home.getInputAlgoritmo();
		
		if (inputF.equals("---") || inputA.equals("---")) {
			JOptionPane.showMessageDialog(null, "Debes seleccionar un fichero y un algoritmo.");
			return;
		}
		
		String parametroX = home.getInputX();
		String parametroY = home.getInputY();
		String parametroC = "";
		if (home.getInputC() != null)
			parametroC = home.getInputC();
		int numClusters = 0;
		numClusters = home.getInputD();
		if (home.getInputAlgoritmo().equals("Visualize") && (parametroX.equals("---") || parametroY.equals("---") 
				|| parametroC.equals("---"))) {
			JOptionPane.showMessageDialog(null, "Debes seleccionar un valor para cada parámetro.");
			return;
		}
		if (home.getInputAlgoritmo().equals("Clustering") && numClusters <= 0) {
			JOptionPane.showMessageDialog(null, "El número de clusters debe ser mayor que cero.");
			return;
		}
		int x, y, c;
		
		x = generarIndice(parametroX);
		y = generarIndice(parametroY);
		c = generarIndice(parametroC);
        
        String filtroInput = home.getInputFiltro();
        String filtro;
        
        switch (filtroInput) {
        	case "Filtrar por fecha": filtro = "fecha";
        			break;
        	case "Filtrar por hora": filtro = "hora";
        			break;
        	default: filtro = "";
        			break;
        }
        
        Controller controlador = Controller.getInstance();
        
        String param1 = home.getParametroFiltro1();
        String param2 = home.getParametroFiltro2();
        
        if (home.getInputAlgoritmo().equals("Visualize"))
        	controlador.obtainResultsVisualize(inputF, x, y, c, filtro, param1, param2);
        else if (home.getInputAlgoritmo().equals("Clustering"))
        	controlador.obtainResultsClustering(inputF, x, y, numClusters, filtro, param1, param2);
        
        /*ResultGUI result = new ResultGUI(parametroX, parametroY, parametroC);
		result.start();*/
	}
	
	private int generarIndice(String parametro) {
		int i;
		switch (parametro) {
	        case "Fecha":  i = 0;
	                 break;
	        case "Hora": i = 1;
	        		break;
	        case "Contexto":  i = 2;
	        		break;
	        case "Componente":  i = 3;
            		break;
	        case "Id de usuario":  i = 5;
	                 break;
	        case "Descripción del evento":  i = 6;
					break;
	        case "Id de módulo":  i = 7;
					break;
	        default: i = 0;
	                 break;
	    }
		return i;
	}

}
