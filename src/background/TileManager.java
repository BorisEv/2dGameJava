package background;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	
	GamePanel gp;
	public Tile[][] tiles;
	public int[][][] mapTileNum;
	String package_name = "/background";

	public TileManager(GamePanel gp) {
		this.gp = gp;
		tiles = new Tile[gp.numberOfWorlds][30];
		this.mapTileNum = new int[gp.numberOfWorlds][gp.maxWorldCol][gp.maxWorldRow];
		getTileImage();
		loadMap("/maps/firstWorldMap.txt", 0);
		loadMap("/maps/secondWorldMap.txt", 1);
	}

	public void getTileImage() {
		try {
			tiles[0][0] = new Tile();
			tiles[0][0].image = ImageIO.read(getClass().getResourceAsStream(package_name + "/firstWorld/grass.png"));
			tiles[0][1] = new Tile();
			tiles[0][1].image = ImageIO.read(getClass().getResourceAsStream(package_name + "/firstWorld/tree.png"));
			tiles[0][2] = new Tile();
			tiles[0][2].image = ImageIO.read(getClass().getResourceAsStream(package_name + "/firstWorld/apple_tree.png"));
			tiles[0][3] = new Tile();
			tiles[0][3].image = ImageIO.read(getClass().getResourceAsStream(package_name + "/firstWorld/water.png"));
			tiles[0][3].solid = true;
			tiles[0][4] = new Tile();
			tiles[0][4].image = ImageIO.read(getClass().getResourceAsStream(package_name + "/firstWorld/road.png"));
			tiles[0][5] = new Tile();
			tiles[0][5].image = ImageIO.read(getClass().getResourceAsStream(package_name + "/firstWorld/wall.png"));
			tiles[0][5].solid = true;
			tiles[0][6] = new Tile();
			tiles[0][6].image = ImageIO.read(getClass().getResourceAsStream(package_name + "/firstWorld/stone_tile.png"));
			
			
			tiles[1][0] = new Tile();
			tiles[1][0].image = ImageIO.read(getClass().getResourceAsStream(package_name + "/secondWorld/grass.png"));
			tiles[1][1] = new Tile();
			tiles[1][1].image = ImageIO.read(getClass().getResourceAsStream(package_name + "/secondWorld/tree.png"));
			tiles[1][2] = new Tile();
			tiles[1][2].image = ImageIO.read(getClass().getResourceAsStream(package_name + "/secondWorld/apple_tree.png"));
			tiles[1][3] = new Tile();
			tiles[1][3].image = ImageIO.read(getClass().getResourceAsStream(package_name + "/secondWorld/water.png"));
			tiles[1][3].solid = true;
			tiles[1][4] = new Tile();
			tiles[1][4].image = ImageIO.read(getClass().getResourceAsStream(package_name + "/secondWorld/road.png"));
			tiles[1][5] = new Tile();
			tiles[1][5].image = ImageIO.read(getClass().getResourceAsStream(package_name + "/secondWorld/wall.png"));
			tiles[1][5].solid = true;
			tiles[1][6] = new Tile();
			tiles[1][6].image = ImageIO.read(getClass().getResourceAsStream(package_name + "/secondWorld/rubble.png"));

		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void loadMap(String mapPath, int worldNumber) {
		try {
			InputStream is = getClass().getResourceAsStream(mapPath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line;
			String[] numbers;
			for(int row = 0; row<gp.maxWorldRow; row++) {
				line = br.readLine();
				numbers = line.split(" ");
				for(int col = 0; col<gp.maxWorldCol; col++) {
					mapTileNum[worldNumber][col][row] = Integer.parseInt(numbers[col]);
					if(tiles[worldNumber][mapTileNum[worldNumber][col][row]].solid == true) {
						gp.collisionsMap[worldNumber][col][row] = 1;
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void draw(Graphics2D g2) {
		int worldX;
		int worldY;
		int x;
		int y;
		int tileNum;
		for(int row = 0; row<gp.maxWorldRow; row++) {
			for(int col = 0; col<gp.maxWorldCol; col++) {
				worldX = col*gp.tileSize;
				worldY = row*gp.tileSize;
				x = worldX-gp.player.absX+gp.player.playerX;
				y = worldY-gp.player.absY+gp.player.playerY;
				tileNum = mapTileNum[gp.player.worldNumber][col][row];
				if(worldX+gp.tileSize>gp.player.absX-gp.player.playerX &&
					worldX-gp.tileSize<gp.player.absX+gp.player.playerX &&
					worldY+gp.tileSize>gp.player.absY-gp.player.playerX &&
					worldY-gp.tileSize<gp.player.absY+gp.player.playerX) {
						g2.drawImage(tiles[gp.player.worldNumber][tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
				}
			}
		}
	}
}
