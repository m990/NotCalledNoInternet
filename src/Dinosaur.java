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
	static int downSpeed;
	int imageIndex = 0;
	int jumpHeight;
	boolean jumping;
	int upSpeed;

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
			jumpHeight = NoInternet.height - 240;
			jumping = false;
		}

		speed = 8;

		downSpeed = 5;
		upSpeed = 13;

	}

	void draw(Graphics g) {
		if (onGround()) {
			imageIndex = updateIndex(imageIndex);
		}
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
			y += downSpeed;
		}
		if (jumping) {
			y -= upSpeed;
		}
		if (y <= jumpHeight) {
			jumping = false;
		}
		if (GamePanel.score.playerScore %300 == 0) {
			if (GamePanel.score.playerScore <= 600) {
				jumpHeight += 20;
			}
		}
		super.update();
	}

	void jump() {
		if (onGround()) {
			jumping = true;
		}
	}

	boolean onGround() {
		return ((y + height + 25) >= NoInternet.height);
	}
}
