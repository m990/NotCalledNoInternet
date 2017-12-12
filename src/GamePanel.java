import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
//import java.io.IOException;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	static Timer timer;
	Dinosaur dinosaur;
	CactusManager cactusManager = new CactusManager();
	Clouds cloud;
	Clouds cloud2;
	Clouds cloud3;
	Mountian mountian1;
	Mountian mountian2;
	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	int CURRENT_STATE = MENU_STATE;
	Score score;
	int playerScore;
	flyingManager flyManager;
	public static BufferedImage cactusImage;
	public static BufferedImage pterodactylImage;
	public static BufferedImage pterodactylImage1;
	public static BufferedImage pterodactylImage2;
	public static BufferedImage pterodactylImage3;
	public static BufferedImage pterodactylImage4;
	public static BufferedImage pterodactylImage5;
	public static BufferedImage pterodactylImage6;
	public static BufferedImage pterodactylImage7;

	// Constructor
	public GamePanel() {
		timer = new Timer(1000 / 60, this);
		createDinsaur();
		cloud = new Clouds(200, 50, 75, 75);
		cloud2 = new Clouds(800, 25, 45, 45);
		cloud3 = new Clouds(600, 75, 60, 60);
		mountian1 = new Mountian(50, 20, 300, 300);
		mountian2 = new Mountian(600, 20, 300, 300);
		// cactus = new Cactus(800, 265, 10, 10);
		// cactus2 = new Cactus(1000, 275, 5, 5);
		// dinosaurY = 160;
		score = new Score(0);
		playerScore = 0;
		flyManager = new flyingManager();
		flyManager.add();
		try {
			cactusImage = ImageIO.read(this.getClass().getResourceAsStream("cactusImage.png"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "One of your images isn't working");
		}
	}

	void startGame() {
		timer.start();
		
		try {
		FileOutputStream out = new FileOutputStream("test.txt");
		out.write(1);
		out.close();
		} catch (IOException e) {
			
		}
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
		g.setColor(Color.white);
		g.fillRect(0, 0, NoInternet.width, NoInternet.height);
		mountian1.draw(g);
		mountian2.draw(g);
		cactusManager.draw(g);
		score.draw(g);
		flyManager.draw(g);
		cloud.draw(g);
		cloud2.draw(g);
		cloud3.draw(g);
		dinosaur.draw(g);
	}

	void drawEndState(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.drawString("You lost", 50, 50);
		g.drawString("Your final score: " + score.playerScore, 50, 100);
	}

	void updateMenuState() {
	}

	void updateGameState() {
		dinosaur.update();
		cactusManager.update();
		cactusManager.collisionDection(dinosaur);
		flyManager.collisionDection(dinosaur);
		score.update();
		cloud.update();
		cloud2.update();
		cloud3.update();
		flyManager.update();
		mountian1.update();
		mountian2.update();
		if (!dinosaur.isAlive) {
			CURRENT_STATE = END_STATE;
			if (dinosaur.isAlive == false) {
				createDinsaur();
				cactusManager.clear();
				cactusManager = new CactusManager();
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
		} else if (CURRENT_STATE == GAME_STATE) {
			updateGameState();
		} else if (CURRENT_STATE == END_STATE) {
			updateEndState();
		}

	}

	void createDinsaur() {
		dinosaur = new Dinosaur(50, NoInternet.height - 125, 50, 50);
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
		} else if (CURRENT_STATE == GAME_STATE) {
			drawGameState(g);
		} else if (CURRENT_STATE == END_STATE) {
			drawEndState(g);
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (CURRENT_STATE == MENU_STATE) {
				CURRENT_STATE = GAME_STATE;
			} else if (CURRENT_STATE == GAME_STATE) {
				CURRENT_STATE = END_STATE;
			} else if (CURRENT_STATE == END_STATE) {
				CURRENT_STATE = MENU_STATE;
				score.reset();
			}
		}
		if ((e.getKeyCode() == KeyEvent.VK_UP) && (CURRENT_STATE == GAME_STATE)) {
			dinosaur.jump();
			playSound("jump.wav");
		}
		if ((e.getKeyCode() == KeyEvent.VK_UP) && (CURRENT_STATE == GAME_STATE) && (dinosaur.onGround())) {
			dinosaur.jump();
		}
		if ((e.getKeyCode() == KeyEvent.VK_DOWN) && (CURRENT_STATE == GAME_STATE) && (!dinosaur.onGround())) {
			dinosaur.y = NoInternet.height - 125;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	private void playSound(String fileName) {
		AudioClip noise = JApplet.newAudioClip(getClass().getResource(fileName));
		noise.play();
	}
}
