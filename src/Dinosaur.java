import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Dinosaur extends GameObject {
	int speed;
	static final int maxHeight = 80;
	static boolean reachedTop = false;
	static boolean onGround = true;
	public static BufferedImage jongUn;
	public Dinosaur(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 5;
		try {
			jongUn = ImageIO.read(this.getClass().getResourceAsStream("rocketMan.jpg"));
		} catch(IOException e) {
			JOptionPane.showMessageDialog(null, "YOUR IMAGE IS BAD. -Your dinosaur");
		}
	}
	void draw(Graphics g) {
		g.drawImage(jongUn, x, GamePanel.dinosaurY, width, height, null);
	}
	void update() { 
		if (!onGround()) {
			System.out.println("In not on ground");
			GamePanel.dinosaurY += 3;
		}
		super.update();
	}
	void jump() {
		if (onGround()) {
			GamePanel.dinosaurY -= 180;
			System.out.println("In on ground");
		}
	}
	boolean onGround() {
		return ((GamePanel.dinosaurY + height + 25) >= NoInternet.height);
	}
}
