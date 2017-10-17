import java.awt.Color;
import java.awt.Graphics;

public class Dinosaur extends GameObject {
	int speed;
	static final int maxHeight = 80;
	static boolean reachedTop = false;
	static boolean onGround = true;
	public Dinosaur(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 5;
	}
	void draw(Graphics g) {
		g.setColor(Color.RED);
		g.drawRect(x, GamePanel.dinosaurY, width, height);
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
