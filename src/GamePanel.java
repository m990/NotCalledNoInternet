import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.Timer;

import org.omg.CORBA.Current;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	static Timer timer;
	Dinosaur dinosaur;
	CactusManager cactusManager = new CactusManager();
	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE  = 2;
	int CURRENT_STATE = MENU_STATE;
	static int dinosaurY = 180;
	Score score;
	int playerScore;
	flyingManager flyManager;
	// Constructor
	public GamePanel() {
		timer = new Timer(1000/60, this);
		dinosaur = new Dinosaur(50, 165, 100, 100);
		//cactus = new Cactus(800, 265, 10, 10);
		//cactus2 = new Cactus(1000, 275, 5, 5);
		dinosaurY = 160;
		score = new Score(0);
		playerScore = 0;
		flyManager = new flyingManager();
		flyManager.add();
	}
	void startGame() {
		timer.start();
	}
	
	// Later I'll add more states here, but for now this is just the game state
	void drawMenuState(Graphics g) {
		g.setColor(Color.black);
		g.drawString("There is no internet connection", 300, 50);
		g.drawString("• Checking the network cables, modem, and router", 300, 75);
		g.drawString("• Reconnecting to Wi-Fi", 300, 100);
		g.drawString("• Running Network Diagnostics", 300, 125);
	}
	
	void drawGameState(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, NoInternet.width, NoInternet.height);
		dinosaur.draw(g);
		cactusManager.draw(g);
		score.draw(g);
		flyManager.draw(g);
	}
	void drawEndState(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawString("You lost", 50, 50);
	}
	
	void updateMenuState() {
		
	}
	void updateGameState() {
		dinosaur.update();
		cactusManager.update();
		cactusManager.collisionDection(dinosaur);
		score.update();
		flyManager.update();
		if (!dinosaur.isAlive) {
			CURRENT_STATE = END_STATE;
			if (dinosaur.isAlive == false) {
				dinosaur = new Dinosaur(50, 165, 100, 100);
				cactusManager.clear();
				cactusManager = new CactusManager();
				score.reset();
				score = new Score(playerScore);
				flyManager.reset();
				flyManager = new flyingManager();
			}
		}
	}
	void updateEndState() {
		
	}
	
	// I don't know what this is for
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.paintImmediately(getBounds());
		if (CURRENT_STATE == MENU_STATE) {
			updateMenuState();
		}
		else if (CURRENT_STATE == GAME_STATE) {
			updateGameState();
		}
		else if (CURRENT_STATE == END_STATE) {
			updateEndState();
		}
	}
	

	// Key listener methods
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		if (CURRENT_STATE == MENU_STATE) {
			drawMenuState(g);
		}
		else if (CURRENT_STATE == GAME_STATE) {
			drawGameState(g);
		}
		else if (CURRENT_STATE == END_STATE) {
			drawEndState(g);
		}
			
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (CURRENT_STATE == MENU_STATE) {
				CURRENT_STATE = GAME_STATE;
			}
			else if (CURRENT_STATE == GAME_STATE) {
				CURRENT_STATE = END_STATE;
			}
			else if (CURRENT_STATE == END_STATE) {
				CURRENT_STATE = MENU_STATE;
			}
		}
		if ((e.getKeyCode() == KeyEvent.VK_UP) && (CURRENT_STATE == GAME_STATE)) {
			dinosaur.jump();
		}
		if ((e.getKeyCode() == KeyEvent.VK_UP) && (CURRENT_STATE == GAME_STATE) && (dinosaur.onGround())) {
			dinosaur.jump();
		}
		if ((e.getKeyCode() == KeyEvent.VK_DOWN) && (CURRENT_STATE == GAME_STATE) && (!dinosaur.onGround())) {
			//dinosaur.
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
