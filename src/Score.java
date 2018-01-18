import java.awt.Color;
import java.awt.Graphics;

import javax.print.attribute.standard.MediaSize.Other;

public class Score {
	int playerScore;
	int otherScore;
	int speed;
	public Score(int score) {
		playerScore = score;
		speed = 0;
	}
	void draw(Graphics g, String highScore) {
		g.setColor(Color.black);
		if (Integer.parseInt(highScore) <= playerScore) {
			g.setColor(Color.red);
		}
		g.drawString("Score: " + Integer.toString(playerScore), 675, 50);
		if (Integer.parseInt(highScore) <= playerScore) {
			g.drawString("High Score: " + playerScore, 675, 75);
		}
		else {
			g.drawString("High Score: " + Integer.parseInt(highScore), 675, 75);
		}
		g.drawString("Speed: " +speed, 675, 100);
	}
	void update() {
		updateSpeed();
		++playerScore;
	}
	void reset() {
		speed = 0;
		playerScore = 0;
	}
	void updateSpeed() {
		if (GamePanel.score.playerScore %400 == 0) {
			speed++;
		}
	}
}
