package object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.KeyHandler;


public class MyObject {
	GamePanel gp;
	KeyHandler keyH;
	
	public int absX;
	public int absY;
	public BufferedImage image;
	
	public MyObject() {
	}
	
	public void draw(Graphics2D g2d) {
		int x = absX-gp.player.absX+gp.player.playerX;
		int y = absY-gp.player.absY+gp.player.playerY;
		g2d.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	}
	
	public boolean isInPlayerRange() {
		int x = this.absX-gp.player.absX+gp.player.playerX;
		int y = this.absY-gp.player.absY+gp.player.playerY;
		if(Math.abs(x-gp.player.playerX) < gp.tileSize && Math.abs(y-gp.player.playerY) < gp.tileSize) {
			return true;
		}
		return false;
	}
}
