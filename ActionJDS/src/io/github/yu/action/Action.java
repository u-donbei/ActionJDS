package src.io.github.yu.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public final class Action{
	
	final static int b1 = 50;
	static int cx=16,cy=8;
	static int dx = 0,dy = 8;
	static boolean loop = true;
	static int course_num = 1;
	static int[][] course_1 = new int[cx][cy];

	public final static void main(String[] args) {
		BufferedImage jimen=null,sora=null,chara1=null,chara2=null;
		int px=10,py=300;
		boolean jump = false;
		int jumpcount = 0;
		try {
			jimen = ImageIO.read(Action.class.getResource("jimen.png"));
			sora = ImageIO.read(Action.class.getResource("sora.png"));
			chara1 = ImageIO.read(Action.class.getResource("character_1.png"));
			chara2 = ImageIO.read(Action.class.getResource("character_2.png"));
		} catch (IOException e2) {
			// TODO 自動生成された catch ブロック
			e2.printStackTrace();
		}
		for(int i=0;i<15;i++) {
			course_1[i][7] = 1;
		}
		MainWindow window = new MainWindow();
		window.Focus("メイン");
		
		Graphics2D gra = window.mainPanel.image.createGraphics();
		window.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				loop = false;
				super.windowClosed(e);
			}
		});
		
		//FPS
		int fps =60;
		long startTime = 0;
		window.mainPanel.addKeyListener(new Keyboard());
		window.mainPanel.requestFocus();
		window.gameOver.addKeyListener(new Keyboard());
		while(loop) {
			startTime = System.currentTimeMillis();
			gra.setColor(Color.WHITE);
			gra.fillRect(0, 0, 800, 800);
			
			
			for(int x=0;x<=(cx-1);x+=1) {
				for(int y=0;y<=(cy-1);y+=1) {
					if(course_1[x][y] == 1) {
						gra.drawImage(jimen, x*b1, y*b1, null);
					}
					else if (course_1[x][y] == 0) {
						gra.drawImage(sora, x*b1, y*b1, null);
					}
				}
			}

			if(Keyboard.isKeyPressed(KeyEvent.VK_D)) {
				px+=3;
			}
			if(Keyboard.isKeyPressed(KeyEvent.VK_A)) {
				px-=3;
			}
			if(Keyboard.isKeyPressed(KeyEvent.VK_SPACE)) {
				if(course_1[px/b1+1][py/b1+1] == 1) {
					jump = true;
				}
			}
			
			try {
				if(course_1[px/b1+1][py/b1+1] == 0) {
					py+=5;
				}
			}catch (ArrayIndexOutOfBoundsException e) {
				window.Focus("ゲームオバー");
				window.gameOver.requestFocus();
				gra = window.gameOver.image.createGraphics();
				
				gra.setColor(Color.WHITE);
				gra.fillRect(0, 0, 800, 800);
				gra.setColor(Color.BLACK);
				gra.setFont(new Font("DialogInput", Font.BOLD, 100));
				gra.drawString("ゲームオバー!", 40, 100);
				gra.setFont(new Font("Serif", Font.PLAIN, 30));
				gra.drawString("Enterキーを押すとゲームに復帰します", 100, 200);
				px = 10;
				py = 300;
				
				if(Keyboard.isKeyPressed(KeyEvent.VK_ENTER)) {
					System.out.println("return.");
					window.Focus("メイン");
					window.mainPanel.requestFocus();
					gra = window.mainPanel.image.createGraphics();
				}
				window.gameOver.draw();
			}
			
			if(jump) {
				if(jumpcount == 200) {
					jumpcount=0;
					jump = false;
				}
				py-=10;
				jumpcount+=10;
			}
			
			gra.drawImage(chara1, px, py, null);
			
			window.mainPanel.draw();
			
			try {
				long runTime = System.currentTimeMillis() - startTime;
				if (runTime > 1000 / fps) {
					continue;
				}     
				Thread.sleep(1000 / fps - runTime);
				
			} catch (InterruptedException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
		}
	}
	
}
