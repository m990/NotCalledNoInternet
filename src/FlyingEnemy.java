import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class FlyingEnemy extends GameObject {
	boolean moving;
	Random randomChooser = new Random();
	long lastSpawnTime;
	flyingManager fm;
	CactusManager cm;
	ArrayList<BufferedImage> imageList;
	int imageIndex = 0;

	public FlyingEnemy(int x, int y, int width, int height) {
		super(x, y, width, height);
		moving = true;
		lastSpawnTime = System.currentTimeMillis();
		fm = new flyingManager();
		cm = new CactusManager();
	}

	void draw(Graphics g) {
		imageIndex = fm.getNextIndex(imageIndex);
		g.drawImage(flyingManager.imageList.get(imageIndex), x, y, width, height, null);
	}

	void update(FlyingEnemy flyingEnemy) {
		if (moving) {
			x -= 6;
		}
		if (x <= 0) {
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
