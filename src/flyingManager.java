import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class flyingManager {
	ArrayList<FlyingEnemy> flyingList;
	static ArrayList<BufferedImage> imageList;
	static FlyingEnemy flyingEnemy = new FlyingEnemy(1700, 100, 30, 30);

	// static FlyingEnemy flyingEnemy2 = new FlyingEnemy(2400, 100, 10, 10);
	public flyingManager() {
		try {
			flyingList = new ArrayList<FlyingEnemy>();
			imageList = new ArrayList<BufferedImage>();
			imageList.add(ImageIO.read(this.getClass().getResourceAsStream("flyng thing.png")));
			imageList.add(ImageIO.read(this.getClass().getResourceAsStream("pteropdactly2.png")));
			imageList.add(ImageIO.read(this.getClass().getResourceAsStream("pterodactly3.png")));
			imageList.add(ImageIO.read(this.getClass().getResourceAsStream("pterodactly4.png")));
			imageList.add(ImageIO.read(this.getClass().getResourceAsStream("pterodactly5.png")));
			imageList.add(ImageIO.read(this.getClass().getResourceAsStream("pterodactly6.png")));
			imageList.add(ImageIO.read(this.getClass().getResourceAsStream("pterodactly7.png")));
			imageList.add(ImageIO.read(this.getClass().getResourceAsStream("pterodactly8.png")));
		} catch (Exception e) {

		}
	}

	void draw(Graphics g) {
		for (FlyingEnemy fe : flyingList) {
			g.setColor(Color.red);
			fe.draw(g);
		}
	}

	void update() {
		for (FlyingEnemy fe : flyingList) {
			fe.update(flyingEnemy);

		}
	}

	void reset() {
		flyingList.clear();
	}

	void add() {
		flyingList.add(flyingEnemy);
		// flyingList.add(flyingEnemy2);
	}

	void collisionDection(Dinosaur d) {
		for (FlyingEnemy fe : flyingList) {
			System.out.println("collision dection for loop");
			if (d.getCollisionBox().intersects(fe.getCollisionBox())) {
				d.setAlive(false);
			}
		}
	}

	int getNextIndex(int currentIndex) {
		currentIndex++;
		if (currentIndex == imageList.size()) {
			currentIndex = 0;
		}
		System.out.println(currentIndex);
		return currentIndex;
	}
}
