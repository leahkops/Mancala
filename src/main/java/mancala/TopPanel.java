package mancala;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TopPanel extends JPanel {

	private JPanel pieceInfo;
	private JLabel[] labels;

	public TopPanel() {
		super(new BorderLayout());
		setBackground(new Color(0, 0, 0, 0));
		setOpaque(false);
		JLabel black = new JLabel(" ");
		black.setPreferredSize(new Dimension(40, 45));
		addPieceInfo();
		add(black, BorderLayout.PAGE_START);
	}

	private void addPieceInfo() {
		JPanel numPanel = new JPanel(new BorderLayout());
		numPanel.setBackground(new Color(0, 0, 0, 0));
		pieceInfo = new JPanel(new GridLayout(1, 7, 0, 6));
		pieceInfo.setPreferredSize(new Dimension(110, 100));
		pieceInfo.setBackground(new Color(0, 0, 0, 0));
		JLabel space2 = new JLabel(" ");
		space2.setPreferredSize(new Dimension(90, 10));
		JLabel space3 = new JLabel(" ");
		space3.setPreferredSize(new Dimension(125, 4));
		labels = new JLabel[7];
		for (int i = 0; i < 7; i++) {
			labels[i] = new JLabel("4");
			labels[i].setForeground(Color.MAGENTA);
			labels[i].setFont(new Font("Arial", Font.BOLD, 20));
			pieceInfo.add(labels[i]);
		}
		labels[0].setText("0");
		add(numPanel, BorderLayout.CENTER);
		numPanel.add(space2, BorderLayout.WEST);
		numPanel.add(pieceInfo, BorderLayout.CENTER);
		numPanel.add(space3, BorderLayout.EAST);
	}

	public void addValue(int position) {
		int currVal = Integer.parseInt(labels[position].getText());
		labels[position].setText(String.valueOf(++currVal));
		repaint();
	}
	
	public int reset(int position){
		int currVal = Integer.parseInt(labels[position].getText());
		labels[position].setText("0");
		repaint();
		return currVal;
	}

	public static void main(String[] args) {
		new BoardScreen().setVisible(true);
	}
}
