package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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
		mainFrame.setBackground(Color.WHITE);
		GridBagConstraints c = new GridBagConstraints();
		
		try {
			// Logo
			BufferedImage myPicture;
			final String dir = System.getProperty("user.dir");
			myPicture = ImageIO.read(new File(dir + "\\src\\logo.png"));
			BufferedImage resize = resize(myPicture, 140, 450);
			JLabel logo = new JLabel(new ImageIcon(resize));
			JPanel logoPanel = new JPanel();
			logoPanel.add(logo);
			c.gridx = 0;
			c.gridy = 0;
			c.fill = GridBagConstraints.HORIZONTAL;
			mainFrame.add(logoPanel, c);
			
			// Bienvenido
			cabecera = new JPanel(new GridBagLayout());
			JLabel bienvenido = new JLabel();
			bienvenido.setText("Bienvenido!");
			cabecera.add(bienvenido);
			cabecera.setPreferredSize(new Dimension(800, 70));
			c.gridx = 0;
			c.gridy = 1;
			c.fill = GridBagConstraints.HORIZONTAL;
			mainFrame.add(cabecera, c);
			
			// Main inputs
			JComboBox<String> input1 = new JComboBox<String>();
			input1.addItem("---");
			File folder = new File(dir + "\\python\\ficheros");
			File[] listOfFiles = folder.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
			  if (listOfFiles[i].isFile()) {
				  String s = listOfFiles[i].getName();
				  input1.addItem(s.substring(0,s.lastIndexOf(".")));
			  }
			}
			JComboBox<String> input2 = new JComboBox<String>();
			input2.addItem("---");
			input2.addItem("Clustering");
			mainInputs = new JPanel();
			mainInputs.setLayout(new GridLayout(4, 1));
			mainInputs.add(new JLabel("Fichero"));
			mainInputs.add(input1);
			mainInputs.add(new JLabel("Algoritmo"));
			mainInputs.add(input2);
			c.gridx = 0;
			c.gridy = 2;
			c.fill = GridBagConstraints.HORIZONTAL;
			mainFrame.add(mainInputs, c);
			
			// Secondary inputs
			
			// Submit btn
			submit = new JButton();
			//submit.setEnabled(false);
			submit.addActionListener(new ResultsAL());
			submit.setText("Obtener resultados");
			JPanel panelSubmit = new JPanel(new GridBagLayout());
			c.gridx = 0;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridy = 3;
			panelSubmit.add(submit);
			panelSubmit.setPreferredSize(new Dimension(600, 100));
			mainFrame.add(panelSubmit, c);
			mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void start() {
		mainFrame.setVisible(true);
	}
	
	private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

}
