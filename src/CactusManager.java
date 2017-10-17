import java.awt.Graphics;
import java.util.ArrayList;

public class CactusManager {
	ArrayList<Cactus> cactusList;
	public CactusManager() {
		cactusList = new ArrayList<Cactus>();
		cactusList.add(GamePanel.cactus);
		cactusList.add(GamePanel.cactus2);
		cactusList.add(GamePanel.cactus3);
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
}
