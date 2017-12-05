import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Clouds extends GameObject {
	public static BufferedImage cloud1;
	public Clouds(int x, int y, int width, int height) {
		super(x, y, width, height);
		try {
			 cloud1 = ImageIO.read(this.getClass().getResourceAsStream("cloud.png"));
		}
		catch (IOException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Your cloud is being mean");
		}
	}
	void draw(Graphics g) {
		g.drawImage(cloud1, x, y, width, height, null);
	}
	void update() {
		if (x <= 0 - width) {
			x += 825 + width;
		}
		x -= 2;
		super.update();
	}
}
