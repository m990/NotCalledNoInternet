import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class flyingManager {
	ArrayList<FlyingEnemy> flyingList;
	static FlyingEnemy flyingEnemy = new FlyingEnemy(1700, 100, 10, 10);
	//static FlyingEnemy flyingEnemy2 = new FlyingEnemy(2400, 100, 10, 10);
	public flyingManager() {
		flyingList = new ArrayList<FlyingEnemy>();
	}
	void draw(Graphics g) {
		for (FlyingEnemy fe: flyingList) {
			g.setColor(Color.red);
			fe.draw(g);
		}
	}
	void update() {
		for (FlyingEnemy fe: flyingList) {
			fe.update(flyingEnemy);
		}
	}
	void reset() {
		flyingList.clear();
	}
	void add() {
		flyingList.add(flyingEnemy);
		//flyingList.add(flyingEnemy2);
	}
	void collisionDection(Dinosaur d) {
		for (FlyingEnemy fe: flyingList) {
			System.out.println("collision dection for loop");
			if (d.getCollisionBox().intersects(fe.getCollisionBox())) {
				d.setAlive(false);
			}
		}
	}
}
