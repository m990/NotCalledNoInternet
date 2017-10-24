import java.awt.Color;
import java.awt.Graphics;

public class Score {
	int playerScore;
	public Score(int score) {
		playerScore = score;
	}
	void draw(Graphics g) {
		g.setColor(Color.CYAN);
		g.drawString("Score: " + Integer.toString(playerScore), 700, 50);
	}
	void update() {
		playerScore++;
	}
	void reset() {
		playerScore = 0;
	}
}
