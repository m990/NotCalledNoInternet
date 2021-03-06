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
import java.util.Arrays;

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
	static Score score = new Score(0);
	int playerScore;
	flyingManager flyManager;
	int timesRun = 0;
	int oldScore;
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
		System.out.println(NoInternet.height/2);
		timer.start();
	}

	// Later I'll add more states here, but for now this is just the game state
	void drawMenuState(Graphics g) {
		g.setColor(Color.black);
		g.drawString("Weird Dinosaur Game!", 305, 100);
		g.drawString("High score: " + getHighScore() + " - " + getHighScoreName(), 300, NoInternet.height/2);
		g.drawString("Press enter to start, use space or the up arrow to jump.", 215, NoInternet.height/2 + 50);
		if (timesRun >= 1) {
			g.drawString("Your score: " + oldScore, 335, 175);
		}
	}

	void drawGameState(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, NoInternet.width, NoInternet.height);
		mountian1.draw(g);
		mountian2.draw(g);
		cactusManager.draw(g);
		flyManager.draw(g);
		cloud.draw(g);
		cloud2.draw(g);
		cloud3.draw(g);
		dinosaur.draw(g);
		score.draw(g, ""+getHighScore());
	}

	void drawEndState(Graphics g) {
		writeHighScore();
		CURRENT_STATE = MENU_STATE;
		score.reset();
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
		scoreSound();
		if (!dinosaur.isAlive) {
			CURRENT_STATE = END_STATE;
			if (dinosaur.isAlive == false) {
				oldScore = score.playerScore;
				playSound("death.wav");
				createDinsaur();
				cactusManager.clear();
				cactusManager = new CactusManager();
				flyManager.reset();
				flyManager.add();
				timesRun++;
			}
		}
	}

	void updateEndState() {

	}
	void scoreSound() {
		if (score.playerScore %400 == 0) {
			playSound("score.wav");
		}
	}
	void writeHighScore() {
			
		try {
			int prevScore = getHighScore();
			
			if (score.playerScore > prevScore) {
				String name = "player";
				name = JOptionPane.showInputDialog("New high score! Your score is " + score.playerScore + "\nEnter your name");
				name = name.replaceAll("[0-9]", "");
				FileWriter fw = new FileWriter("test.txt");
				fw.write(score.playerScore + name);
				fw.close();
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	int getHighScore(){
		String contents="";
		try {
			
			FileReader fr = new FileReader("test.txt");
			if (!fr.ready()) {
				System.out.println("no file");
			}
			int c = fr.read();
			while(c != -1){
				contents+=(char)c;
				c = fr.read();
				
			}
			fr.close();
		} catch (IOException e) {
			//e.printStackTrace();
			//System.out.println("No file!");
		}
		return Integer.parseInt(contents.replaceAll("[^0-9]", ""));
	}
	String getHighScoreName() {
		String contents="";
		try {
			
			FileReader fr = new FileReader("test.txt");
			int c = fr.read();
			while(c != -1){
				contents+=(char)c;
				c = fr.read();
				
			}
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contents.replaceAll("[0-9]", "");
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
			}
		}
		if ((e.getKeyCode() == KeyEvent.VK_UP) && (CURRENT_STATE == GAME_STATE) && (dinosaur.onGround())) {
			dinosaur.jump();
			playSound("jump.wav");
		}
		if ((e.getKeyCode() == KeyEvent.VK_SPACE) && (CURRENT_STATE == GAME_STATE) && (dinosaur.onGround())) {
			dinosaur.jump();
			playSound("jump.wav");
		}
		if ((e.getKeyCode() == KeyEvent.VK_DOWN) && (CURRENT_STATE == GAME_STATE) && (!dinosaur.onGround())) {
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
