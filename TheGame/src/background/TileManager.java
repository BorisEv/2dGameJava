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
	public Tile[] tiles;
	public int[][] mapTileNum;
	

	public TileManager(GamePanel gp) {
		this.gp = gp;
		tiles = new Tile[30];
		this.mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		getTileImage();
		loadMap("/maps/map.txt");
	}

	public void getTileImage() {
		try {
			tiles[0] = new Tile();
			tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/background/grass.png"));
			tiles[1] = new Tile();
			tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/background/tree.png"));
			tiles[2] = new Tile();
			tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/background/apple_tree.png"));
			tiles[3] = new Tile();
			tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/background/water.png"));
			tiles[3].solid = true;
			tiles[4] = new Tile();
			tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/background/road.png"));
			
			
			tiles[5] = new Tile();
			tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/background/wall.png"));
			tiles[5].solid = true;
			tiles[6] = new Tile();
			tiles[6].image = ImageIO.read(getClass().getResourceAsStream("/background/rubble.png"));

		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void loadMap(String mapPath) {
		try {
			InputStream is = getClass().getResourceAsStream(mapPath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line;
			String[] numbers;

			for(int row = 0; row<gp.maxWorldRow; row++) {
				line = br.readLine();
				numbers = line.split(" ");
				for(int col = 0; col<gp.maxWorldCol; col++) {
					mapTileNum[col][row] = Integer.parseInt(numbers[col]);
					if(tiles[mapTileNum[col][row]].solid == true) {
						gp.collisionsMap[col][row] = 1;
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
				tileNum = mapTileNum[col][row];
				if(worldX+gp.tileSize>gp.player.absX-gp.player.playerX &&
					worldX-gp.tileSize<gp.player.absX+gp.player.playerX &&
					worldY+gp.tileSize>gp.player.absY-gp.player.playerX &&
					worldY-gp.tileSize<gp.player.absY+gp.player.playerX) {
					g2.drawImage(tiles[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
				}
			}
		}
	}
}
