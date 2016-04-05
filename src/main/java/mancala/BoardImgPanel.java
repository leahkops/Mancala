package mancala;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BoardImgPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private BufferedImage image;

	public BoardImgPanel() {

		try {
			image = ImageIO.read(getClass().getResourceAsStream("/MancalaBoardFinal.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
	}

	public BufferedImage getImage() {
		return this.image;
	}
}