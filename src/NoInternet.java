import javax.swing.JFrame;

public class NoInternet {
	JFrame frame;
	static final int width = 800;
	static int height = 300;
	GamePanel gamePanel;
	public static void main(String[] args) {
		NoInternet noInternet = new NoInternet();
		noInternet.setup();
	}
	
	
	// constructor
	public NoInternet() {
		frame = new JFrame();
		gamePanel = new GamePanel();
	}
	
	
	void setup() {
		frame.add(gamePanel);
		frame.setVisible(true);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(gamePanel);
		frame.setTitle("No Internet");
		gamePanel.startGame();
	}
}
