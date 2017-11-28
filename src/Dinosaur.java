import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Dinosaur extends GameObject {
	int speed;
	ArrayList<BufferedImage> imageList = new ArrayList<BufferedImage>();
	static boolean reachedTop = false;
	static boolean onGround = true;
	public static BufferedImage jongUn;
	static int downSpeed;
	int imageIndex = 0;

	public Dinosaur(int x, int y, int width, int height) {
		super(x, y, width, height);
		for (int i = 1; i < 13; i++) {
			String fileName = "d";
			fileName += i;
			fileName += ".png";
			try {
				imageList.add(ImageIO.read(this.getClass().getResourceAsStream(fileName)));
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "YOUR IMAGE IS BAD. -Your dinosaur");
			}
		}

		speed = 5;

		downSpeed = 3;

	}

	void draw(Graphics g) {
		imageIndex = updateIndex(imageIndex);
		g.drawImage(imageList.get(imageIndex), x, y, width, height, null);
	}

	int updateIndex(int currentImage) {
		currentImage++;
		if (currentImage == imageList.size()) {
			currentImage = 0;
		}
		return currentImage;
	}

	void update() {
		if (!onGround()) {
			// y += 3;
			y += downSpeed;
		}
		super.update();
	}

	void jump() {
		if (onGround()) {
			y -= 180;
		}
	}

	boolean onGround() {
		return ((y + height + 25) >= NoInternet.height);
	}
}
