import java.awt.Rectangle;

public class GameObject {
	int x;
	int y;
	int width;
	int height;
	boolean isAlive = true;
	private Rectangle collisionBox;
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
	void setCollisionBox(Rectangle r) {
		collisionBox = r;
	}
	Rectangle getCollisionBox() {
		return collisionBox;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public boolean isAlive() {
		return isAlive;
	}
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
}
