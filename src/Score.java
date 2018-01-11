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
		g.drawString("Score: " + Integer.toString(playerScore), 675, 50);
		g.drawString("High Score: " + highScore, 675, 75);
	}
	void update() {
		++playerScore;
	}
	void reset() {
		playerScore = 0;
	}
}
