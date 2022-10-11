package object;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class MyKey extends MyObject{
	public Door door;
	boolean isInInventory;
	
	public MyKey(int absX, int absY, Door door, String image_path, GamePanel gp, KeyHandler keyH) {
		this.absX = absX;
		this.absY = absY;
		this.door = door;
		this.gp = gp;
		this.keyH = keyH;
		try {
			this.image =  ImageIO.read(getClass().getResourceAsStream(image_path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void update() {	
		if(keyH.fPressed == true) {
			if(super.isInPlayerRange()) {
				isInInventory = true;
				gp.player.inventory[0] = this;
			}
		}
	}
	
	public void draw(Graphics2D g2d) {
		if(isInInventory == false) {
			super.draw(g2d);
			if(super.isInPlayerRange() == true) {
				g2d.setFont(new Font("Lucida console", Font.BOLD, 20));
				g2d.setPaint(Color.WHITE);
				g2d.drawString("press \"F\" to take the key", 200, 500);
			}
		}
	}
}
