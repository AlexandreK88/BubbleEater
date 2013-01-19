import java.awt.event.KeyEvent;
import java.util.LinkedList;


public class Game {

	private MainPanel playArea;
	private int score;
	private AI ai;
	private InGameShape playerShape;
	private InGameShape AIShape;
	private LinkedList<InGameShape> shapes;
	private LinkedList<int[]> playerMoves;
	private LinkedList<int[]> AIMoves;
	private boolean gameIsPlaying = false;

	public Game(MainPanel area) {
		playArea = area;
		shapes = new LinkedList<InGameShape>();
		playerMoves = new LinkedList<int[]>();
		AIMoves = new LinkedList<int[]>();
		System.out.println("Reaches here");
		AI aI = new AI(area, this);
		ai = aI;
		System.out.println("Reaches here");
		newGame();
	}

	public void newGame() {
		gameIsPlaying = false;
		ai.setGameIsPlaying(false);
		playerMoves.clear();
		AIMoves.clear();
		shapes.clear();
		synchronized(shapes) {
			for (int i = 0; i < 3; i++) {
				InGameShape shape = new InGameShape(i);
				if (i == InGameShape.RECTANGLE) {
					AIShape = shape;
				}
				if (i == InGameShape.ROUND_RECTANGLE) {
					playerShape = shape;
				}
				shapes.add(shape);
			}
		}
		playArea.setShapeList(shapes);
		gameIsPlaying = true;
		ai.setGameIsPlaying(true);
		ai.newGame(AIShape, playerShape, AI.HARD);
	}

	public void playerMove(char key) {
		synchronized(playerShape) {
			switch(key) {
			case 'w':
			case KeyEvent.VK_UP:
				playerMove(playerShape.getXPos(), playerShape.getYPos() - 10);
				break;
			case 's':
			case KeyEvent.VK_DOWN:
				playerMove(playerShape.getXPos(), playerShape.getYPos() + 10);
				break;
			case 'a':
			case KeyEvent.VK_LEFT:
				playerMove(playerShape.getXPos() - 10, playerShape.getYPos());
				break;
			case 'd':
			case KeyEvent.VK_RIGHT:
				playerMove(playerShape.getXPos() + 10, playerShape.getYPos());
				break;
			}
		}
	}

	public void playerMove(int x, int y) {
		int[] move = {x,y};
		playerMoves.addLast(move);
	}

	public void AIMove(int x, int y) {
		synchronized(AIShape) {
			int[] move = {AIShape.getXPos() + x,AIShape.getYPos() + y};
			AIMoves.addLast(move);
		}
	}

	public void playGame() {
		System.out.println("Game has started");
		while (gameIsPlaying) {
			if (!playerMoves.isEmpty()) {
				System.out.println("Player is attempting to move");
				synchronized(playerShape) {
					int[] playerMove = playerMoves.pop();
					if (Math.abs(playerShape.getXPos() - playerMove[0]) < 10 && Math.abs(playerShape.getYPos() - playerMove[1]) < 10) {
						playerShape.setXPos(playerMove[0]);
						playerShape.setYPos(playerMove[1]);
					} else {
						playerShape.setXPos(playerMove[0]);
						playerShape.setYPos(playerMove[1]);
					}
				}
			} else {
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (!AIMoves.isEmpty()) {
				int[] AIMove = AIMoves.pop();
				synchronized(AIShape) {
					AIShape.setXPos(AIMove[0]);
					AIShape.setYPos(AIMove[1]);
				}
			} else {
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			playArea.repaint();
		}
	}

}
