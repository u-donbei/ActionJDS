package src.io.github.yu.action;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Keyboard extends KeyAdapter {
	private static ArrayList<Integer> pressedkeys;
	public Keyboard() {
		pressedkeys = new ArrayList<>();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(!pressedkeys.contains((Integer)e.getKeyCode())) {
			pressedkeys.add((Integer)e.getKeyCode());
		}
		super.keyPressed(e);
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		pressedkeys.remove((Integer)e.getKeyCode());
		super.keyReleased(e);
	}
	
	public static boolean isKeyPressed(int key) {
		return pressedkeys.contains((Integer)key);
	}
}
