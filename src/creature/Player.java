package creature;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import object.MyObject;

public class Player extends Human{
	KeyHandler keyH;
	
	public final int playerX;
	public final int playerY;
	
	public Player(GamePanel gp, KeyHandler keyH, int absX, int absY, int speed, String object_name, int person_num, int worldNumber) {
		super(gp, absX, absY, speed, object_name, person_num,worldNumber);
		this.inventory = new MyObject[10];
		this.keyH = keyH;
		
		playerX = gp.screenWidth/2 - gp.tileSize/2;
		playerY = gp.screenHeight/2 - gp.tileSize/2;
		this.solidArea = new Rectangle(gp.tileSize/4, gp.tileSize/4, gp.tileSize/2,gp.tileSize/2);

		loadImages();
	}
	
	public void loadImages() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream(package_name+folder_name+object_name+"/4.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream(package_name+folder_name+object_name+"/5.png"));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void update() {	
		if(keyH.spaceTyped) {
			keyH.spaceTyped = false;
			worldNumber++;
			if(worldNumber>gp.numberOfWorlds-1) {
				worldNumber = 0;
			}
		}
		if(keyH.upPressed || keyH.downPressed ||keyH.leftPressed ||keyH.rightPressed) {
			if(keyH.upPressed) {
				direction = "up";
			}
			if(keyH.downPressed) {
				direction = "down";
			}
			if(keyH.leftPressed) {
				direction = "left";
			}
			if(keyH.rightPressed) {
				direction = "right";
			}
			
			this.collision = false;
			gp.colCheck.checkTile(this);

			if(collision == false) {
				switch(direction) {
				case "up": absY -= speed; break;
				case "down": absY += speed; break;
				case "left": absX -= speed; break;
				case "right": absX += speed; break;
				}
			}
			
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
		g2d.drawImage(image, playerX, playerY, gp.tileSize, gp.tileSize, null);
	}
}
