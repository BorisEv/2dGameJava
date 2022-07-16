package main;

import creature.Creature;

public class CollisionCheck {
	GamePanel gp;
	
	public CollisionCheck(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Creature creature) {
		int left = creature.absX + creature.solidArea.x;
		int right = creature.absX + creature.solidArea.x + creature.solidArea.width;
		int top = creature.absY + creature.solidArea.y;
		int bottom = creature.absY + creature.solidArea.y + creature.solidArea.height;
		
		int leftCol = left/gp.tileSize;
		int rightCol = right/gp.tileSize;
		int topRow = top/gp.tileSize;
		int bottomRow = bottom/gp.tileSize;
		int tile1, tile2;
		switch(creature.direction) {
		case "up":
			topRow = (int) Math.floor(((double)(top-creature.speed))/gp.tileSize);
			if(topRow < 0) {
				creature.collision = true;
				break;
			}
			tile1 = gp.collisionsMap[leftCol][topRow];
			tile2 = gp.collisionsMap[rightCol][topRow];
			if(tile1 == 1 || tile2 == 1) {
				creature.collision = true;
			}
			break;
		case "down":
			bottomRow = (bottom+creature.speed)/gp.tileSize;
			if(bottomRow > 49) {
				creature.collision = true;
				break;
				}
			tile1 = gp.collisionsMap[leftCol][bottomRow];
			tile2 = gp.collisionsMap[rightCol][bottomRow];
			if(tile1 == 1 || tile2 == 1) {
				creature.collision = true;
			}
			break;
		case "left":
			leftCol = (int) Math.floor(((double)(left-creature.speed))/gp.tileSize);
			if(leftCol < 0) {
				creature.collision = true;
				break;
			}
			tile1 = gp.collisionsMap[leftCol][topRow];
			tile2 = gp.collisionsMap[leftCol][bottomRow];
			if(tile1 == 1 || tile2 == 1) {
				creature.collision = true;
			}
			break;
		case "right":
			rightCol = (right+creature.speed)/gp.tileSize;
			if(rightCol > 49) {
				creature.collision = true;
				break;
				}
			tile1 = gp.collisionsMap[rightCol][topRow];
			tile2 = gp.collisionsMap[rightCol][bottomRow];
			if(tile1 == 1 || tile2 == 1) {
				creature.collision = true;
			}
			break;
		}
	}
}
