import java.awt.Color;
import java.awt.Graphics;

import javax.print.attribute.standard.MediaSize.Other;

public class Score {
	int playerScore;
	int otherScore;
	public Score(int score) {
		playerScore = score;
	}
	void draw(Graphics g) {
		g.setColor(Color.black);
		g.drawString("Score: " + Integer.toString(playerScore), 700, 50);
	}
	void update() {
		++playerScore;
	}
	void reset() {
		playerScore = 0;
	}
}
