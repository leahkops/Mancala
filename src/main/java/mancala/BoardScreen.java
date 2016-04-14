package mancala;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardScreen extends JFrame {

	private Container container;
	private TopPanel top;
	private BottomPanel bottom; 
	private CenterPanel center;

	
	public BoardScreen() {
		setTitle("Mancala");
		setSize(1100, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		//setLayout(new BorderLayout());
		
		setContentPane(new JLabel(new ImageIcon(getClass().getResource(
				"/mancala board.jpg"))));
		container = getContentPane();
		container.setLayout(new BorderLayout());
		
		setIconImage(new ImageIcon(getClass().getResource("/mancala.jpg"))
		.getImage());

		addPanels();
	}

	private void addPanels() {
		top = new TopPanel();
		bottom = new BottomPanel();
		center = new CenterPanel(top, bottom);
		container.add(top, BorderLayout.PAGE_START);
		container.add(bottom, BorderLayout.PAGE_END);
		container.add(center, BorderLayout.CENTER);
	}

}
