import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class MainFrame extends JFrame implements ActionListener, KeyListener {
	private Container c;
	private LinkedList<JButton> buttonList;
	private MainPanel playArea;
	private JButton startButton;
	
	public MainFrame() {
		super("SuperBoard");
		c = getContentPane();
		c.setLayout(new BorderLayout());
		this.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		playArea = new MainPanel();
		startButton = new JButton("Start Game!");
		c.add(startButton, BorderLayout.CENTER);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		d.setSize(this.getWidth(), this.getHeight());
		startButton.setMaximumSize(d);
		startButton.addActionListener(this);
		this.addKeyListener(this);
		setButtons();
		if (isFocusable()) {
			this.setFocusable(true);
		}
		setUndecorated(true);
		setResizable(false);
		pack();
		setVisible(true);
	}
	
	private void setButtons() {
		JPanel buttonPanel = new JPanel();
		Dimension panelSize = new Dimension(200, this.getHeight());
		Dimension buttonSize = new Dimension(200, 100);
		buttonPanel.setBackground(Color.gray);
		buttonPanel.setPreferredSize(panelSize);
		c.add(buttonPanel, BorderLayout.EAST);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
		buttonList = new LinkedList<JButton>();
		buttonList.add(new JButton("New Game"));
		buttonList.add(new JButton("Options"));
		buttonList.add(new JButton("High Scores"));
		buttonList.add(new JButton("Exit"));
		
		int buttonValue = 0;
		for (JButton button: buttonList) {
			buttonPanel.add(button);
			button.addActionListener(this);
			button.setMaximumSize(buttonSize);
			buttonPanel.add(Box.createRigidArea(new Dimension(0,50)));
			buttonValue++;
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Start Game!")) {
			Main.goodToGo = true;
			startButton.setVisible(false);
			c.add(playArea, BorderLayout.CENTER);
			Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
			d.setSize(this.getWidth(), this.getHeight());
			Rectangle rect = new Rectangle(d);
			rect.setBounds(0,0,(int)rect.getWidth(), (int)rect.getHeight());
			playArea.setBounds(0,0, rect.width, rect.height);
		}
		if (e.getActionCommand().equals("New Game")) {
			//Game resets.
			return;
		}
		if (e.getActionCommand().equals("Options")) {
			//Options available
			return;
		}
		if (e.getActionCommand().equals("High Scores")) {
			// Show high scores
			return;
		}
		if (e.getActionCommand().equals("Exit")) {
			System.exit(0);
			return;
		}
	}
	
	@Override
	public void keyPressed(KeyEvent keyEvent) {
		System.out.println("Key was pressed");
		char keyPressed = keyEvent.getKeyChar();
		System.out.println((int)keyPressed);
		Main.playerMove(keyPressed);

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}
	
	public MainPanel getPlayArea() {
		return playArea;
	}
}
