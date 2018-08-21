package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class HomeGUI {
	
	private JFrame mainFrame;
	private JPanel logoPanel;
	private JPanel cabecera;
	private JPanel mainInputs;
	private JPanel secondaryInputs;
	private JPanel panelSubmit;
	private JButton submit;
	
	private static HomeGUI INSTANCE = null;
	
	public HomeGUI() {
		setUIFont (new javax.swing.plaf.FontUIResource("Arial",Font.PLAIN,14));

		mainFrame = new JFrame("Home");
		mainFrame.setLayout(new GridBagLayout());
		mainFrame.setBackground(Color.WHITE);
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
			mainInputs.setPreferredSize(new Dimension(600, 100));
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
			
			mainInputs.setLayout(new GridLayout(4, 1));
			mainInputs.add(new JLabel("Fichero"));
			mainInputs.add(input1);
			mainInputs.add(new JLabel("Algoritmo"));
			mainInputs.add(input2);
			
			c.gridx = 0;
			c.gridy = 2;
			
			mainFrame.add(mainInputs, c);
			
			
			// Secondary inputs
			secondaryInputs = new JPanel();
			secondaryInputs.setPreferredSize(new Dimension(600, 180));
			
			JComboBox<String> input3 = new JComboBox<String>();
			input3.addItem("---");
			input3.addItem("Hora");
			input3.addItem("Id de usuario");
			JComboBox<String> input4 = new JComboBox<String>();
			input4.addItem("---");
			input4.addItem("Contexto");
			input4.addItem("Componente");
			input4.addItem("Descripción del evento");
			input4.addItem("Id de módulo");
			JComboBox<String> input5 = new JComboBox<String>();
			input5.addItem("---");
			input5.addItem("Contexto");
			input5.addItem("Descripción del evento");
			input5.addItem("Id de módulo");
			
			JPanel auxPanel1 = new JPanel();
			auxPanel1.setLayout(new GridLayout(2,1));
			auxPanel1.add(new JLabel("Parámetro X"));
			auxPanel1.add(input3);
			
			JPanel auxPanel2 = new JPanel();
			auxPanel2.setLayout(new GridLayout(2,1));
			JLabel paramY = new JLabel("Parámetro Y");
			//paramY.setBorder(new EmptyBorder(0, 10, 0, 0));
			auxPanel2.add(paramY);
			auxPanel2.add(input4);
			
			JPanel auxPanel3 = new JPanel();
			auxPanel3.setLayout(new GridLayout(2,1));
			JLabel color = new JLabel("Color");
			auxPanel3.add(color);
			auxPanel3.add(input5);
			
			input3.setPreferredSize(new Dimension(230,25));
			auxPanel1.setBorder(new EmptyBorder(25, 10, 0, 30));
			secondaryInputs.add(auxPanel1);
			
			input4.setPreferredSize(new Dimension(230,25));
			auxPanel2.setBorder(new EmptyBorder(25, 10, 0, 30));
			secondaryInputs.add(auxPanel2);
			
			input5.setPreferredSize(new Dimension(230,25));
			auxPanel3.setBorder(new EmptyBorder(10, 0, 10, 0));
			secondaryInputs.add(auxPanel3);
			
			//secondaryInputs.setBorder(new EmptyBorder(25, 0, 0, 0));
			secondaryInputs.setBorder(new LineBorder(Color.black, 1));
			input2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (input2.getSelectedItem().toString().equals("Clustering")) {
						c.gridx = 0;
						c.gridy = 3;
						mainInputs.setBorder(new EmptyBorder(0,0,25,0));
						mainInputs.setPreferredSize(new Dimension(600, 125));
						mainFrame.add(secondaryInputs, c);
						mainFrame.revalidate();
					} else {
						mainInputs.setBorder(null);
						mainInputs.setPreferredSize(new Dimension(600, 100));
						mainFrame.remove(secondaryInputs);
						mainFrame.revalidate();
					}
				}
				
			});
			
			
			// Submit btn
			panelSubmit = new JPanel(new GridBagLayout());
			
			submit = new JButton();
			submit.setBackground(new Color(26,117,255));
			submit.setForeground(Color.WHITE);
			submit.setFocusPainted(false);
			submit.setFont(new Font("Arial", Font.PLAIN, 18));
			submit.setPreferredSize(new Dimension(240,40));
			Border emptyBorder = BorderFactory.createEmptyBorder();
			submit.setBorder(emptyBorder);
			//submit.setEnabled(false);
			submit.addActionListener(new ResultsAL());
			submit.setText("Obtener resultados");
			
			c.gridx = 0;
			c.gridy = 4;
			
			panelSubmit.add(submit);
			panelSubmit.setPreferredSize(new Dimension(600, 100));
			
			mainFrame.add(panelSubmit, c);
			
			// Full screen
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
	    java.util.Enumeration keys = UIManager.getDefaults().keys();
	    while (keys.hasMoreElements()) {
	      Object key = keys.nextElement();
	      Object value = UIManager.get (key);
	      if (value instanceof javax.swing.plaf.FontUIResource)
	        UIManager.put (key, f);
	      }
	    } 

}
