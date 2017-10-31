import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Cactus extends GameObject {
	public static BufferedImage nuke;
	public Cactus(int x, int y, int width, int height) {
		super(x, y, width, height);
		
		try {
			nuke = ImageIO.read(this.getClass().getResourceAsStream("nucularImage.png"));
		} catch(IOException e) {
			JOptionPane.showMessageDialog(null, "Your image hates you -Your image");
		}
	}
	void draw(Graphics g) {
		g.drawImage(nuke, x, y, width, height, null);
		//g.setColor(Color.CYAN);
		//g.drawRect(x, y, width, height);
	}
	void update() {
		x -= 5;
		if (x  < 0) {
			x += 800 + width;
		}
		super.update();
	}
}
