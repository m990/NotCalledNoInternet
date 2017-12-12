import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Mountian extends GameObject {
	public static BufferedImage mountian;
	public Mountian(int x, int y, int width, int height){
		super(x, y, width, height);
		try {
			mountian = ImageIO.read(this.getClass().getResourceAsStream("mountian.png"));
		}
		catch (IOException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Your mountian is being mean");
		}
			
	}
	void update() {
		if (x <= 0 - width) {
			x += 850 + width;
		}
		x -= 1;
	}
	void draw(Graphics g) {
		g.drawImage(mountian, x, y, width, height, null);
	}
}
