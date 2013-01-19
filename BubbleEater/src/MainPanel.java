import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.*;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;


public class MainPanel extends JPanel implements MouseListener {
	LinkedList<InGameShape> visibleShapes;

	public MainPanel() {
		super(true);
		this.setLayout(null);
		//Insets insets = this.getInsets();
		this.setPreferredSize(new Dimension(800,600));
		setBackground(Color.white);
		visibleShapes = new LinkedList<InGameShape>();
		setVisible(true);
		this.addMouseListener(this);
	}

	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D g2d = (Graphics2D)graphics;
		synchronized(visibleShapes) {
			for (InGameShape shape: visibleShapes) {
				synchronized(shape) {
					switch (shape.getShapeValue()) {
					case InGameShape.CIRCLE:
						Ellipse2D ell = new Ellipse2D.Double(shape.getXPos(), shape.getYPos(), 10, 10);
						g2d.fill(ell);
						g2d.setColor(Color.green);
						g2d.draw(ell);
						break;
					case InGameShape.RECTANGLE:
						Rectangle2D rect = new Rectangle2D.Double(shape.getXPos(), shape.getYPos(),10, 10);
						g2d.fill(rect);
						g2d.setColor(Color.red);
						g2d.draw(rect);
						break;
					case InGameShape.ROUND_RECTANGLE:
						Rectangle2D rRec = new Rectangle2D.Double(shape.getXPos(), shape.getYPos(),10, 10);
						g2d.fill(rRec);
						g2d.setColor(Color.blue);
						g2d.draw(rRec);
						break;
					}
				}
			}
		}

	}

	public void setShapeList(LinkedList<InGameShape> shapes) {
		visibleShapes = shapes;
	}
	
	public void addShape(InGameShape shape) {
		visibleShapes.add(shape);
	}

	public void removeShape(InGameShape shape) {
		if (visibleShapes.remove(shape)) {
			System.out.println("Shape removed successfully");
		} else {
			System.out.println("Shape not found in visible shapes");
		}
	}




	@Override
	public void mouseClicked(MouseEvent e) {	
		System.out.println("Mouse was clicked");
		Main.playerMove(e.getX(), e.getY());
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
