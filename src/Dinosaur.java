import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Dinosaur extends GameObject {
	int speed;
	static boolean reachedTop = false;
	static boolean onGround = true;
	public static BufferedImage jongUn;
	static int downSpeed;
	public Dinosaur(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 5;
		try {
			jongUn = ImageIO.read(this.getClass().getResourceAsStream("rocketMan.jpg"));
		} catch(IOException e) {
			JOptionPane.showMessageDialog(null, "YOUR IMAGE IS BAD. -Your dinosaur");
		}
		downSpeed = 3;
	}
	void draw(Graphics g) {
		g.setColor(Color.PINK);
		g.drawRect(x, y, width, height);
	}
	void update() { 
		if (!onGround()) {
			//y += 3;
			y += downSpeed;
		}
		super.update();
	}
	void jump() {
		if (onGround()) {
			y -= 180;
		}
	}
	boolean onGround() {
		return ((y + height + 25) >= NoInternet.height);
	}
}
