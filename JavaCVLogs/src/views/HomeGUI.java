package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Enumeration;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class HomeGUI {
	
	private JFrame mainFrame;
	private JPanel logoPanel;
	private JPanel cabecera;
	private JPanel mainInputs;
	private JComboBox<String> inputFichero;
	private JComboBox<String> inputAlgoritmo;
	private JPanel secondaryInputs;
	private JComboBox<String> inputX;
	private JComboBox<String> inputY;
	private JComboBox<String> inputC;
	private JFormattedTextField inputD;
	private JPanel panelSubmit;
	private JButton submit;
		
	private static HomeGUI INSTANCE = null;
	

	public HomeGUI() {
		init();
	}
	
	private void init() {
		setUIFont (new javax.swing.plaf.FontUIResource("Arial",Font.PLAIN,14));

		mainFrame = new JFrame("Home");
		mainFrame.setMinimumSize(new Dimension(900,700));
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		try {
			// Logo
			logoPanel = new JPanel();
			
			BufferedImage myPicture;
			final String dir = System.getProperty("user.dir");
			myPicture = ImageIO.read(new File(dir + "\\src\\logo.png"));
			BufferedImage resize = resize(myPicture, 140, 450);
			JLabel logo = new JLabel(new ImageIcon(resize));			
			logoPanel.add(logo);
			
			c.gridx = 0;
			c.gridy = 0;
			
			mainFrame.add(logoPanel, c);
			
			// Bienvenido
			cabecera = new JPanel(new GridBagLayout());
			
			JLabel bienvenido = new JLabel();
			bienvenido.setText("Bienvenido!");
			bienvenido.setFont(new Font("Georgia", Font.PLAIN, 22));
			cabecera.add(bienvenido);
			
			cabecera.setPreferredSize(new Dimension(800, 70));
			
			c.gridx = 0;
			c.gridy = 1;
			
			mainFrame.add(cabecera, c);
			
			// Main inputs
			mainInputs = new JPanel();
			mainInputs.setPreferredSize(new Dimension(700, 100));
			inputFichero = new JComboBox<String>();
			inputFichero.addItem("---");
			File folder = new File(dir + "\\python\\ficheros");
			File[] listOfFiles = folder.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
			  if (listOfFiles[i].isFile()) {
				  String s = listOfFiles[i].getName();
				  inputFichero.addItem(s.substring(0,s.lastIndexOf(".")));
			  }
			}
			inputAlgoritmo = new JComboBox<String>();
			inputAlgoritmo.addItem("---");
			inputAlgoritmo.addItem("Visualize");
			inputAlgoritmo.addItem("Clustering");
			
			mainInputs.setLayout(new GridLayout(4, 1));
			mainInputs.add(new JLabel("Fichero"));
			mainInputs.add(inputFichero);
			mainInputs.add(new JLabel("Algoritmo"));
			mainInputs.add(inputAlgoritmo);
			
			c.gridx = 0;
			c.gridy = 2;
			
			mainFrame.add(mainInputs, c);
			
			
			// Visualize inputs
			secondaryInputs = new JPanel();
			secondaryInputs.setPreferredSize(new Dimension(700, 180));
						
			inputX = new JComboBox<String>();
			inputX.addItem("---");
			inputX.addItem("Hora");
			inputX.addItem("Id de usuario");
			inputY = new JComboBox<String>();
			inputY.addItem("---");
			inputY.addItem("Contexto");
			inputY.addItem("Componente");
			inputY.addItem("Descripción del evento");
			inputY.addItem("Id de módulo");
			inputC = new JComboBox<String>();
			inputC.addItem("---");
			inputC.addItem("Contexto");
			inputC.addItem("Descripción del evento");
			inputC.addItem("Id de módulo");
			inputD = new JFormattedTextField(NumberFormat.getNumberInstance());
			
			JPanel auxPanel1 = new JPanel();
			auxPanel1.setLayout(new GridLayout(2,1));
			auxPanel1.add(new JLabel("Parámetro X"));
			auxPanel1.add(inputX);
			
			JPanel auxPanel2 = new JPanel();
			auxPanel2.setLayout(new GridLayout(2,1));
			JLabel paramY = new JLabel("Parámetro Y");
			auxPanel2.add(paramY);
			auxPanel2.add(inputY);
			
			JPanel auxPanel3 = new JPanel();
			auxPanel3.setLayout(new GridLayout(2,1));
			JLabel color = new JLabel("Color");
			auxPanel3.add(color);
			auxPanel3.add(inputC);
			
			JPanel auxPanel4 = new JPanel();
			auxPanel4.setLayout(new GridLayout(2,1));
			JLabel numClusters = new JLabel("Número de clusters");
			auxPanel4.add(numClusters);
			auxPanel4.add(inputD);
			
			inputX.setPreferredSize(new Dimension(230,25));
			auxPanel1.setBorder(new EmptyBorder(25, 10, 0, 30));
			secondaryInputs.add(auxPanel1);
			
			inputY.setPreferredSize(new Dimension(230,25));
			auxPanel2.setBorder(new EmptyBorder(25, 10, 0, 30));
			secondaryInputs.add(auxPanel2);
			
			inputC.setPreferredSize(new Dimension(230,25));
			auxPanel3.setBorder(new EmptyBorder(10, 0, 10, 0));
			
			inputD.setPreferredSize(new Dimension(230,25));
			auxPanel4.setBorder(new EmptyBorder(10, 0, 10, 0));
			
			secondaryInputs.setBorder(new LineBorder(Color.black, 1));
			
			inputAlgoritmo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (inputAlgoritmo.getSelectedItem().toString().equals("Visualize")) {
						c.gridx = 0;
						c.gridy = 3;
						mainInputs.setBorder(new EmptyBorder(0,0,25,0));
						mainInputs.setPreferredSize(new Dimension(700, 125));
						secondaryInputs.remove(auxPanel4);
						secondaryInputs.add(auxPanel3);
						mainFrame.add(secondaryInputs, c);
						mainFrame.repaint();
						mainFrame.revalidate();
					} else if (inputAlgoritmo.getSelectedItem().toString().equals("Clustering")) {
						c.gridx = 0;
						c.gridy = 3;
						mainInputs.setBorder(new EmptyBorder(0,0,25,0));
						mainInputs.setPreferredSize(new Dimension(700, 125));
						secondaryInputs.remove(auxPanel3);
						secondaryInputs.add(auxPanel4);
						mainFrame.add(secondaryInputs, c);
						mainFrame.repaint();
						mainFrame.revalidate();
					}
					else {
						mainInputs.setBorder(null);
						mainInputs.setPreferredSize(new Dimension(700, 100));
						mainFrame.remove(secondaryInputs);
						mainFrame.revalidate();
					}
				}
				
			});
			
			
			// Submit btn
			panelSubmit = new JPanel(new GridBagLayout());
			
			submit = new JButton();
			submit.addActionListener(new ResultsAL());
			submit.setText("Obtener resultados");
			
			c.gridx = 0;
			c.gridy = 4;
			
			panelSubmit.add(submit);
			panelSubmit.setPreferredSize(new Dimension(600, 100));
			
			mainFrame.add(panelSubmit, c);
			
			
			mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen
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
	
	public static HomeGUI getInstance() {
		if (INSTANCE == null) {
            synchronized(HomeGUI.class) {
                if (INSTANCE == null) {
                	INSTANCE = new HomeGUI();
                }
            }
        }
        return INSTANCE;
    }

	public JPanel getSecondaryInputs() {
		return secondaryInputs;
	}

	public void setSecondaryInputs(JPanel secondaryInputs) {
		this.secondaryInputs = secondaryInputs;
	}
	
	public static void setUIFont (javax.swing.plaf.FontUIResource f){
	    Enumeration<Object> keys = UIManager.getDefaults().keys();
	    while (keys.hasMoreElements()) {
	      Object key = keys.nextElement();
	      Object value = UIManager.get (key);
	      if (value instanceof javax.swing.plaf.FontUIResource)
	        UIManager.put (key, f);
	      }
	    }
	
	public String getInputFichero() {
		return inputFichero.getSelectedItem().toString();
	}
	
	public String getInputAlgoritmo() {
		return inputAlgoritmo.getSelectedItem().toString();
	}
	
	public String getInputX() {
		return inputX.getSelectedItem().toString();
	}

	public String getInputY() {
		return inputY.getSelectedItem().toString();
	}

	public String getInputC() {
		return inputC.getSelectedItem().toString();
	}
	
	public void toBack() {
		this.mainFrame.toBack();
	}
	
	public int getInputD() {
		if (inputD.getValue() != null)
			return ((Number) inputD.getValue()).intValue();
		else
			return 0;
	}

	public void setInputD(JFormattedTextField inputD) {
		this.inputD = inputD;
	}

}
