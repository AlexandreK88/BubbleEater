import java.util.Random;


public class Main {
	
	public static Random r;
	public static boolean goodToGo = false;
	public static Game game;
	public static void main(String args[]) {
		r = new Random();
		MainFrame frame = new MainFrame();
		while (!goodToGo)
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		MainPanel playArea = frame.getPlayArea();
		game = new Game(playArea);
		System.out.println("Reaches here");
		game.playGame();
	}
	
	public static void newGame() {
		if (game != null) {
			game.newGame();
		}
	}
	
	public static void playerMove(char key) {
		if (game != null) {
			System.out.println("Keyboard player moves");
			game.playerMove(key);
		}
	}
	
	public static void playerMove(int x, int y) {
		System.out.println("Click player moves");
		game.playerMove(x,y);
	}
}
