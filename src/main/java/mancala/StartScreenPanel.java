package mancala;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import com.google.inject.Inject;

public class StartScreenPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private BufferedImage image;

	@Inject
	public StartScreenPanel(InputStream background) {

		try {
			image = ImageIO.read(background);
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
