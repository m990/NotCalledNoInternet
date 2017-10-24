import java.awt.Color;
import java.awt.Graphics;

public class FlyingEnemy extends GameObject {
	public FlyingEnemy(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	void draw(Graphics g) {
		g.setColor(Color.RED);
		g.drawRect(x, y, width, height);
	}
	void update() {
		x -= 3;
	}
}
