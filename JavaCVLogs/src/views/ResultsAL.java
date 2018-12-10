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
		System.out.println(numClusters);
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
        switch (parametroX) {
            case "Fecha":  x = 0;
                     break;
            case "Hora": x = 1;
            		break;
            case "Id de usuario":  x = 5;
                     break;
            default: x = 0;
                     break;
        }
        switch (parametroY) {
	        case "Contexto":  y = 2;
	                 break;
	        case "Componente":  y = 3;
	                 break;
	        case "Descripción del evento":  y = 6;
            		break;
	        case "Id de módulo":  y = 7;
            		break;
	        default: y = 1;
	                 break;
        }
        switch (parametroC) {
	        case "Contexto":  c = 2;
			        break;
			case "Descripción del evento":  c = 6;
					break;
			case "Id de módulo":  c = 7;
					break;
	        default: c = 1;
	                 break;
        }
        
        String filtro = home.getInputFiltro();
        Controller controlador = Controller.getInstance();
        
        if (home.getInputAlgoritmo().equals("Visualize"))
        	controlador.obtainResultsVisualize(inputF, x, y, c, filtro);
        else if (home.getInputAlgoritmo().equals("Clustering"))
        	controlador.obtainResultsClustering(inputF, x, y, numClusters, filtro);
        
        /*ResultGUI result = new ResultGUI(parametroX, parametroY, parametroC);
		result.start();*/
	}

}
