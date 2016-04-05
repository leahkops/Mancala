package mancala;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.google.inject.Inject;

public class NameFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	@Inject
	public NameFrame(InsertNamesPanel playersPanel, JLabel backgroundLabel) {
		setTitle("Players");
		setSize(900, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setResizable(false);

		backgroundLabel = new JLabel(new ImageIcon(getClass().getResource("/startBackground.png")));

		backgroundLabel.setLayout(new BorderLayout());

		backgroundLabel.add(playersPanel, BorderLayout.PAGE_END);
		add(backgroundLabel);

	}

}
