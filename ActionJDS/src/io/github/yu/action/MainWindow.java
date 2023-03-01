package src.io.github.yu.action;

import java.awt.CardLayout;

import javax.swing.JFrame;

public class MainWindow extends JFrame {
	MainPanel mainPanel;
	GameOver gameOver;
	CardLayout layout;

	private static final long serialVersionUID = 1L;
	
	public MainWindow() {
		super("ActionJDS");
		layout = new CardLayout();
		mainPanel = new MainPanel();
		gameOver = new GameOver();
		this.setLayout(layout);
		this.add(gameOver, "ゲームオバー");
		this.add(mainPanel,"メイン");
		this.setSize(750, 800);
		this.setDefaultCloseOperation(3);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public void Focus(String name) {
		layout.show(getContentPane(), name);
		
	}
	
}
