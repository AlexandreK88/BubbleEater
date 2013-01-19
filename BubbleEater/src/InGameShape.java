import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;


public class InGameShape {
	
	public static final int CIRCLE = 0;
	public static final int ROUND_RECTANGLE = 1;
	public static final int RECTANGLE = 2;
	
	private int shapeValue;
	private int xPos;
	private int yPos;
	private Shape shape;
	
	InGameShape(int shapeV) {
		double x = (double)Main.r.nextInt(Toolkit.getDefaultToolkit().getScreenSize().width - 200);
		double y = (double)Main.r.nextInt(Toolkit.getDefaultToolkit().getScreenSize().height);
		xPos = (int)x;
		yPos = (int)y;
		shapeValue = shapeV;
		switch(shapeV) {
		case CIRCLE:
			shape = new Ellipse2D.Double(x, y, 10, 10);
			break;
		case ROUND_RECTANGLE:
			shape = new RoundRectangle2D.Double();
			((RoundRectangle2D)shape).setFrame(x,y, 10, 10);
			break;
		case RECTANGLE:
			shape = new Rectangle2D.Double(x,y, 10, 10);
			break;
		}
	}
	
	public int getXPos() {
		return xPos;
	}
	
	public int getYPos() {
		return yPos;
	}
	
	public void setXPos(int x) {
		xPos = x;
	}
	
	public void setYPos(int y) {
		yPos = y;
	}
	
	public int getShapeValue() {
		return shapeValue;
	}
}
