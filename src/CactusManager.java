import java.awt.Graphics;
import java.util.ArrayList;

public class CactusManager {
	ArrayList<Cactus> cactusList;
	public CactusManager() {
		cactusList = new ArrayList<Cactus>();
		cactusList.add(new Cactus(800, 255, 20, 20));
		cactusList.add(new Cactus(1200, 255, 40, 40));
		cactusList.add(new Cactus(1600, 270, 15, 15));
		cactusList.add(new Cactus(2400, 255, 40, 40));
	}
	public void add(Cactus cactusToAdd) {
		cactusList.add(cactusToAdd);
	}
	void draw(Graphics g) {
		for(Cactus c: cactusList) {
			c.draw(g);
		}
	}
	void update() {
		for(Cactus c: cactusList) {
			c.update();
		}
	}
	void collisionDection(Dinosaur d) {
		for(Cactus c: cactusList) {
			if (d.getCollisionBox().intersects(c.getCollisionBox())) {
				d.setAlive(false);
			}
		}
	}
	void clear() {
		cactusList.clear();
	}
	boolean proximityDetection(FlyingEnemy fe) {
		for (Cactus c: cactusList) {
			if  (Math.abs(c.x - fe.x) < 30) {
				return true;
			}
		}
		return false;
	}
}
