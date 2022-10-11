package creature;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Human extends Creature{
	
	public Human(GamePanel gp, int absX, int absY, int speed, String object_name, int person_num,int worldNumber) {
		super(gp, absX, absY, speed, person_num, worldNumber);
		this.object_name = object_name;
		this.folder_name = "/humans";
		this.solidArea = new Rectangle(gp.tileSize/4, gp.tileSize/4, gp.tileSize/2,gp.tileSize/2);
		
		loadImages();
	}
	
	public void loadImages() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream(
					package_name+folder_name+object_name+"/"+person_num+"_1.png"));
			
			up2 = ImageIO.read(getClass().getResourceAsStream(
					package_name+folder_name+object_name+"/"+person_num+"_1.png"));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
