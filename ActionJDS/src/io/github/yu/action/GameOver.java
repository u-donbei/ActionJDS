/**
 * 
 */
package src.io.github.yu.action;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class GameOver extends JPanel{
	BufferedImage image;

	private static final long serialVersionUID = 1L;
	
	public GameOver() {
		image = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(image, 0, 0, this);
	}
	
	public void draw() {
		repaint();
	}
	
}