import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class flyingManager {
	ArrayList<FlyingEnemy> flyingList;
	public flyingManager() {
		flyingList = new ArrayList<FlyingEnemy>();
		flyingList.add(new FlyingEnemy(1700, 100, 10, 10));
	}
	void draw(Graphics g) {
		for (FlyingEnemy fe: flyingList) {
			g.setColor(Color.red);
			fe.draw(g);
		}
	}
	void update() {
		for (FlyingEnemy fe: flyingList) {
			fe.update();
		}
	}
	void reset() {
		flyingList.clear();
	}
}
