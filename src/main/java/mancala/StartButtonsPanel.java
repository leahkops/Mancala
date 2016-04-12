package mancala;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.google.inject.Inject;

public class StartButtonsPanel extends JPanel{

	private JButton twoPlayers;
	private JButton vsComputer;
	@Inject
	public StartButtonsPanel(ImageIcon icon, ImageIcon icon2){
		
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(100, 500));
		this.twoPlayers = new JButton(icon);
		this.vsComputer = new JButton(icon2);
		
		twoPlayers.setPreferredSize(new Dimension(100, 100));
		vsComputer.setPreferredSize(new Dimension(100, 100));
		
		add(twoPlayers);
		add(vsComputer);
		
	}
}
