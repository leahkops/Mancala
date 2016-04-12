package mancala;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.google.inject.Inject;

public class InsertNamesPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name1, name2;

	@Inject
	public InsertNamesPanel(JLabel playerLabel1, JLabel playerLabel2, JTextField playerField1, JTextField playerField2,
			JButton okayButton) {

		setLayout(new FlowLayout());
		setOpaque(false);
		playerLabel1.setText("Player 1");
		playerLabel2.setText("Player 2");
		Font font1 = new Font("Calibri", Font.BOLD, 40);
		Font font2 = new Font("Calibri", Font.BOLD, 30);

		format(playerLabel1, playerLabel2, playerField1, playerField2, okayButton, font1, font2);

		addListener(playerField1, playerField2, okayButton);
		add(playerLabel1);
		add(playerField1);
		add(playerLabel2);
		add(playerField2);
		add(okayButton);

	}

	private void format(JLabel playerLabel1, JLabel playerLabel2, JTextField playerField1, JTextField playerField2,
			JButton okayButton, Font font1, Font font2) {
		formatLabel(playerLabel1, font1);
		formatLabel(playerLabel2, font1);
		formatTextField(playerField1, font2);
		formatTextField(playerField2, font2);
		formatButton(okayButton);
	}

	private void formatButton(JButton okayButton) {
		okayButton.setOpaque(false);
		okayButton.setIcon(new ImageIcon(getClass().getResource("/startButton.png")));
		okayButton.setBackground(Color.black);
		okayButton.setPreferredSize(new Dimension(80, 80));

	}

	private void formatTextField(JTextField field, Font font) {
		field.setColumns(10);
		field.setFont(font);
		field.setBackground(Color.darkGray);
		field.setForeground(Color.orange);

	}

	private void formatLabel(JLabel label, Font font) {
		label.setSize(60, 20);
		label.setFont(font);
		label.setForeground(Color.yellow); // TODO Auto-generated method stub

	}

	private void addListener(final JTextField playerField1, final JTextField playerField2, JButton okayButton) {

		okayButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				name1 = playerField1.getText().trim().toUpperCase();
				name2 = playerField2.getText().trim().toUpperCase();
				if ((name1.length() == 0) || (name2.length() == 0)) {
					JOptionPane.showMessageDialog(null, "You must enter a name for both players!!", "WARNING!",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				if ((name1.length() > 10) || (name2.length() > 10)) {
					JOptionPane.showMessageDialog(null, "Name can't be longer than 10 digits", "WARNING!",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				GameGui gui = new GameGui(name1, name2);
				gui.setVisible(true);
			}

		});
	}

}
