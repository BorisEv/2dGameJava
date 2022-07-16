package object;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Door extends MyObject{
	boolean animated;
	int animationCounter = 0;
	public MyKey key;
	public boolean isClosed;
	public BufferedImage[] animation;
	String package_path = "/objects/doors/";

	public Door(int absX, int absY, String image_path, GamePanel gp, KeyHandler keyH) {
		isClosed = true;
		this.absX = absX;
		this.absY = absY;
		this.gp = gp;
		this.keyH = keyH;
		gp.collisionsMap[absX/gp.tileSize][absY/gp.tileSize] = 1;
		animation = new BufferedImage[15];
		try {
			this.image =  ImageIO.read(getClass().getResourceAsStream(package_path+image_path));
			for(int i = 0; i<15;i++) {
				animation[i] = ImageIO.read(getClass().getResourceAsStream(package_path+"animations/door"+(i+1)+".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public void update() {	
		if(keyH.fPressed == true && isClosed == true) {
			if(super.isInPlayerRange() && key.isInInventory == true) {
				isClosed = false;
				animated = true;
			}
		}
	}
	
	public void draw(Graphics2D g2d) {
		if(animated == true) {
			if(animationCounter == 15) {
				animated = false;
				gp.collisionsMap[absX/gp.tileSize][absY/gp.tileSize] = 0;
				animationCounter = 0;
				try {
					this.image =  ImageIO.read(getClass().getResourceAsStream(package_path+"door1_open.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else {
				image = animation[animationCounter];
				animationCounter++;
			}
			super.draw(g2d);
		} else {
			super.draw(g2d);
			if(super.isInPlayerRange() == true && keyH.fPressed == true 
					&& isClosed == true && key.isInInventory == false) {
				g2d.setFont(new Font("Lucida console", Font.BOLD, 20));
				g2d.setPaint(Color.WHITE);
				g2d.drawString("You dont have the key!", 200, 500);
			}
		}
	}
}
