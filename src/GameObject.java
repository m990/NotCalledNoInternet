import java.awt.Rectangle;

public class GameObject {
	int x;
	int y;
	int width;
	int height;
	boolean isAlive = true;
	Rectangle collisionBox;
	public GameObject(int x, int y, int width, int height) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		collisionBox = new Rectangle(x, y, width, height);
	}
	void update() {
		collisionBox.setBounds(x, y, width, height);
	}
}
