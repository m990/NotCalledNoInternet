import java.awt.Color;
import java.awt.Graphics;
import java.sql.Date;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Random;

import javax.swing.JOptionPane;

public class FlyingEnemy extends GameObject {
	boolean moving;
	Random randomChooser = new Random();
	long lastSpawnTime;
	public FlyingEnemy(int x, int y, int width, int height) {
		super(x, y, width, height);
		moving = true;
		lastSpawnTime = System.currentTimeMillis();
	}
	void draw(Graphics g) {
		g.setColor(Color.RED);
		g.drawRect(x, y, width, height);
		System.out.println(x + " " + y);
	}
	void update() {
		System.out.println(System.currentTimeMillis());
		if (moving) {
			x -= 5;
		}
		if (x<=0) {
			if (System.currentTimeMillis() > lastSpawnTime + randomChooser.nextInt(4)) {
				x += 1600 + width;
			}
		}
	}
}
