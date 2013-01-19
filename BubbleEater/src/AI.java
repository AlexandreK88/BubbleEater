import java.util.LinkedList;


public class AI extends Thread {

	public static final int EASY = 200;
	public static final int MEDIUM = 100;
	public static final int HARD = 50;
	public static final int AI_SPEED = 10;

	private MainPanel playArea;
	private InGameShape AIShape;
	private InGameShape playerShape;
	private Game game;
	private int difficulty;
	private boolean newGame;
	private boolean gameIsPlaying = false;

	public AI(MainPanel area, Game g) {
		super();
		playArea = area;
		game = g;
		newGame = false;
		start();
	}

	public void newGame(InGameShape AIS, InGameShape playerS, int diff) {
		newGame = true;
		AIShape = AIS;
		playerShape = playerS;
		difficulty = diff;

	}


	public void run() {
		while(!newGame);
		newGame = true;
		System.out.println("AI sees there is a new game.");
		while(true) {
			while (!gameIsPlaying)
				try {
					Thread.sleep(difficulty);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
			if (newGame) {
				try {
					Thread.sleep(difficulty);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				newGame = false;
			}
			try {
				Thread.sleep(difficulty);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int xDiff, yDiff;
			synchronized (AIShape) {
				synchronized (playerShape) {
					xDiff = AIShape.getXPos() - playerShape.getXPos();
					yDiff = AIShape.getYPos() - playerShape.getYPos();
				}
			}
			if (xDiff == 0) {
				if (yDiff > AI_SPEED) {
					game.AIMove(0, -AI_SPEED);
				} else if (yDiff < - AI_SPEED) {
					game.AIMove(0, AI_SPEED);
				} else {
					game.AIMove(0, -yDiff);
				}
			} else if (Math.abs(xDiff) < AI_SPEED) {
				game.AIMove(-xDiff, 0);
				if (yDiff > AI_SPEED - xDiff) {
					game.AIMove(0, xDiff - AI_SPEED);
				} else if (yDiff < - AI_SPEED + xDiff) {
					game.AIMove(0, AI_SPEED - xDiff);
				} else {
					game.AIMove(0, -yDiff);
				}
			} else if (xDiff > 0){
				game.AIMove(-AI_SPEED, 0);
			} else {
				game.AIMove(AI_SPEED, 0);
			}
		}
	}

	public void setGameIsPlaying(boolean b) {
		gameIsPlaying = b;
	}

}
