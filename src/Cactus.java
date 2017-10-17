import java.awt.Color;
import java.awt.Graphics;

public class Cactus extends GameObject {
	
	public Cactus(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.drawRect(x, y, width, height);
	}
	void update() {
		x -= 5;
		if (x  < 0) {
			x += 800;
		}
		super.update();
	}
}
