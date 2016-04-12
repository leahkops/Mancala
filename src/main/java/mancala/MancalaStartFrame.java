
package mancala;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.google.inject.Inject;

public class MancalaStartFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	@Inject
	public MancalaStartFrame(InsertNamesPanel playersPanel, JLabel backgroundLabel) {
		setTitle("Mancala Start");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setResizable(false);

		StartScreenPanel startPanel = null;

		try {
			startPanel = new StartScreenPanel(getClass().getResource("/MancalaStartScreen.png").openStream());
		} catch (IOException e) {
		}

		// StartButtonsPanel buttons = new StartButtonsPanel(new
		// ImageIcon(getClass().getResource("/2PButton.png")),
		// new ImageIcon(getClass().getResource("/VsComputerButton.png")));

		// StartButtonsPanel buttons = new StartButtonsPanel();
		add(startPanel, BorderLayout.CENTER);
		// add(buttons, BorderLayout.WEST);
		add(playersPanel, BorderLayout.SOUTH);
	}
}