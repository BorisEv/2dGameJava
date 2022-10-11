package creature;

import java.awt.Graphics2D;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

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
	String object_name; // specific characters
	int person_num;
	
	protected int spriteCounter = 0;
	protected int spriteNum = 1;
	
	public String direction;
	public List<String> pathList;
	public boolean onTheMove;
	public int cnt;
	
	public int worldNumber;
	public MyObject[] inventory;
	public int health_points;
	
	public Creature(GamePanel gp, int absX, int absY, int speed, int person_num, int worldNumber) {
		this.worldNumber = worldNumber;
		this.person_num = person_num;
		this.gp = gp;
		this.absX = absX;
		this.absY = absY;
		this.speed = speed;
		pathList = new ArrayList<String>();
		this.direction = "down";
	}
	
	public void update() {		
		if(pathList.isEmpty()) {
			onTheMove = false;
		} else {
			onTheMove = true;
			direction = pathList.get(0);
		}
		if (onTheMove) {
			gp.colCheck.checkTile(this);
			cnt += speed;
			if(cnt>=gp.tileSize) {
				cnt = 0;
				pathList.remove(0);
			}
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
		int x = absX-gp.player.absX+gp.player.playerX;
		int y = absY-gp.player.absY+gp.player.playerY;
		if(worldNumber == gp.player.worldNumber) {
			g2d.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
		}
	}
	
	public void attack() {
	}
	
	// x- in tiles, not the real x 
	public List<String> find_path(int startX, int startY, int finalX, int finalY, int[][][] worldsCollisionMap) {
		int [][] collisionMap = worldsCollisionMap[worldNumber];
		double[][] pathMap = new double[collisionMap.length][collisionMap[0].length];
		for(int i = 0; i < pathMap.length; i++) {
			for(int j = 0; j< pathMap[0].length; j++) {
				pathMap[i][j] = Double.POSITIVE_INFINITY;
			}
		}
		pathMap[startX][startY] = 0;
		for (int z = 0; z<collisionMap.length*collisionMap[0].length; z++) {
			for (int i = Math.max(startX - z,0); i< Math.min(startX + z,collisionMap.length); i++) {
				for (int j = Math.max(startY - z, 0); j< Math.min(startY + z, collisionMap[0].length); j++) {
					if(pathMap[i][j] != Double.POSITIVE_INFINITY && pathMap[i][j] != z) { // second part to avoid continuation down and right
						if (i != collisionMap.length-1) {
							if (collisionMap[i+1][j] == 0 && pathMap[i+1][j] > z) {
								pathMap[i+1][j] = z;
							}
						}
						if (i != 0) {
							if (collisionMap[i-1][j] == 0 && pathMap[i-1][j] > z) {
								pathMap[i-1][j] = z;
							}
						}
						if (j != collisionMap[0].length-1) {
							if (collisionMap[i][j+1] == 0 && pathMap[i][j+1] > z) {
								pathMap[i][j+1] = z;
							}
						}
						if (j != 0) {
							if (collisionMap[i][j-1] == 0 && pathMap[i][j-1] > z) {
								pathMap[i][j-1] = z;
							}
						}
					}
				}
			}
		}
		List<String> path = find_path_from_pathMap(startX, startY, finalX, finalY, pathMap);
		return path;
	}
	
	public List<String> find_path_from_pathMap (int startX, int startY, int finalX, int finalY, double[][] pathMap){
		List<String> path = new ArrayList<String>();
		if (pathMap[finalX][finalY] == Double.POSITIVE_INFINITY) {
			return path;
		}
		if(startX == finalX && startY == finalY) {
			return path;
		}
		if (pathMap[finalX-1][finalY] == pathMap[finalX][finalY]-1) {
			path = find_path_from_pathMap(startX,startY,finalX-1,finalY,pathMap);
			path.add("right");
			return path;
		}
		if (pathMap[finalX+1][finalY] == pathMap[finalX][finalY]-1) {
			path = find_path_from_pathMap(startX,startY,finalX+1,finalY,pathMap);
			path.add("left");
			return path;
		}
		if (pathMap[finalX][finalY+1] == pathMap[finalX][finalY]-1) {
			path = find_path_from_pathMap(startX,startY,finalX,finalY+1,pathMap);
			path.add("up");
			return path;
		}
		if (pathMap[finalX][finalY-1] == pathMap[finalX][finalY]-1) {
			path = find_path_from_pathMap(startX,startY,finalX, finalY -1,pathMap);
			path.add("down");
			return path;
		}
		return null; // should never be here
	}
}