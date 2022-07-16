package creature;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import object.MyObject;


public class Creature {
	GamePanel gp;

	public int speed;
	public int absX;
	public int absY;
	
	public Rectangle solidArea;
	public boolean collision = false;
	
	protected BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
	String package_name = "/creatures";
	String folder_name;
	int person_num;
	
	public String direction;
	protected int spriteCounter = 0;
	protected int spriteNum = 1;
	
	public MyObject[] inventory;
	public int health_points;
	
	public Creature(GamePanel gp, int absX, int absY, int speed, int person_num) {
		this.person_num = person_num;
		this.gp = gp;
		this.absX = absX;
		this.absY = absY;
		this.speed = speed;
		this.direction = "down";
	}
	
	public void update() {		
		spriteCounter++;
		if(spriteCounter>7) {
			if(spriteNum == 1) {
				spriteNum = 2;
			} else if(spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
	}
	
	public void draw(Graphics2D g2d) {
		BufferedImage image = null;
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = up1;
			}
			else if(spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image = up1;
			}
			else if(spriteNum == 2) {
				image = up2;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = up1;
			}
			else if(spriteNum == 2) {
				image = up2;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = up1;
			}
			else if(spriteNum == 2) {
				image = up2;
			}
			break;
		}
		int x = absX-gp.player.absX+gp.player.playerX;
		int y = absY-gp.player.absY+gp.player.playerY;
		g2d.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	}
}