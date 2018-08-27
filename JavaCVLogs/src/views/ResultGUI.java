package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class ResultGUI {
	
	private JFrame mainFrame;
	private String paramX;
	private String paramY;
	private String paramC;
	
	public ResultGUI(String paramx, String paramy, String paramc) {
		paramX = paramx;
		paramY = paramy;
		paramC = paramc;
		init();
	}
	
	private void init() {
		mainFrame = new JFrame();
		mainFrame.setLayout(new GridBagLayout());
		mainFrame.setMinimumSize(new Dimension(900,800));
		mainFrame.setLocationRelativeTo(null);
		GridBagConstraints c = new GridBagConstraints();
		
		BufferedImage myPicture;
		final String dir = System.getProperty("user.dir");
		try {
			myPicture = ImageIO.read(new File(dir + "\\src\\imagen.jpg"));
			JLabel d = new JLabel(new ImageIcon(myPicture));			
			JPanel diagramPanel = new JPanel();
			diagramPanel.add(d);
			diagramPanel.setBorder(new LineBorder(Color.black, 1));
			
			JPanel panel1 = new JPanel();
			panel1.add(new JLabel("Parámetro X:"));
			panel1.add(new JLabel(paramX));
	
			JPanel panel2 = new JPanel();
			panel2.add(new JLabel("Parámetro Y:"));
			panel2.add(new JLabel(paramY));
			
			JPanel panel3 = new JPanel();
			panel3.add(new JLabel("Color:"));
			panel3.add(new JLabel(paramC));
			
			c.gridx = 0;
			c.gridy = 0;
			c.weightx = 1;
			mainFrame.add(diagramPanel, c);
			c.gridx = 0;
			c.gridy = 1;
			c.weightx = 0.33;
			mainFrame.add(panel1, c);
			c.gridx = 0;
			c.gridy = 2;
			mainFrame.add(panel2, c);
			c.gridx = 0;
			c.gridy = 3;
			mainFrame.add(panel3, c);
			mainFrame.toFront();
			mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void start() {
		mainFrame.setVisible(true);
	}
}
