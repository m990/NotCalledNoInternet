import java.awt.Color;
import java.awt.Graphics;

import javax.print.attribute.standard.MediaSize.Other;

public class Score {
	int playerScore;
	int otherScore;
	public Score(int score) {
		playerScore = score;
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
	}
	void update() {
		++playerScore;
	}
	void reset() {
		playerScore = 0;
	}
}
