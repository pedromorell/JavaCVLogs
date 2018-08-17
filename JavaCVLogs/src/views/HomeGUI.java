package views;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomeGUI {
	
	private JFrame mainFrame;
	private JPanel cabecera;
	private JPanel mainInputs;
	private JPanel secondaryInputs;
	private JButton submit;
	
	public HomeGUI() {
		mainFrame = new JFrame("Home");
		mainFrame.setLayout(new GridBagLayout());
		cabecera = new JPanel();
		JLabel bienvenido = new JLabel();
		bienvenido.setText("Bienvenido!");
		cabecera.add(bienvenido);
		mainFrame.add(cabecera);
		
		JComboBox<String> input1 = new JComboBox<String>();
		input1.addItem("Opción 1");
		input1.setMaximumSize(new Dimension(600, 30));
		JComboBox<String> input2 = new JComboBox<String>();
		input2.addItem("Opción 1");
		input2.setMaximumSize(new Dimension(600, 30));
		mainInputs = new JPanel();
		mainInputs.setLayout(new GridLayout(2,1));
		mainInputs.add(input1);
		mainInputs.add(input2);
		mainInputs.setPreferredSize(new Dimension(700, 100));
		mainFrame.add(mainInputs);
		
		submit = new JButton();
		submit.setText("Obtener resultados");
		JPanel panelSubmit = new JPanel();
		panelSubmit.add(submit);
		mainFrame.add(panelSubmit);
		
		mainFrame.pack();
	}
	
	public void start() {
		mainFrame.setVisible(true);
	}
		
	public static void main(String[] args) {
		HomeGUI home = new HomeGUI();
		home.start();
	}
}
