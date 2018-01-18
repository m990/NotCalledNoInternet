import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Cactus extends GameObject {
	public static BufferedImage nuke;
	long lastSpawnTime;
	Random random;
	boolean moving;
	int speed = 6;
	public Cactus(int x, int y, int width, int height) {
		super(x, y, width, height);
		lastSpawnTime = System.currentTimeMillis();
		random = new Random();
		moving = true;
	}
	void draw(Graphics g) {
		g.drawImage(GamePanel.cactusImage, x, y, width, height, null);
	}
	void update() {
		if (GamePanel.score.playerScore %400 == 0) {
			speed++;
		}
		if (moving) {
			x -= speed;
		}
		if (x <= 0) {
			if (System.currentTimeMillis() > lastSpawnTime + random.nextInt(6)) {
				x += 1600 + width;
				lastSpawnTime = System.currentTimeMillis();
			}
		}
		super.update();
	}
}
