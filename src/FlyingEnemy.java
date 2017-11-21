import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.sql.Date;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class FlyingEnemy extends GameObject {
	boolean moving;
	Random randomChooser = new Random();
	long lastSpawnTime;
	flyingManager fm;
	CactusManager cm;
	ArrayList<BufferedImage> imageList;
	public FlyingEnemy(int x, int y, int width, int height) {
		super(x, y, width, height);
		moving = true;
		lastSpawnTime = System.currentTimeMillis();
		fm = new flyingManager();
		cm = new CactusManager();
	}
	void draw(Graphics g) {
		g.drawImage(GamePanel.pterodactylImage, x, y, width, height, null);
	}
	void update(FlyingEnemy flyingEnemy) {
		if (moving) {
			x -= 5;
		}
		if (x<=0) {
			if (System.currentTimeMillis() > lastSpawnTime + randomChooser.nextInt(4)) {
					x += 1600 + width;
					if (cm.proximityDetection(flyingEnemy)) {
						x += 30;
					}
					lastSpawnTime = System.currentTimeMillis();
				}
			}
		super.update();
		}
	}
