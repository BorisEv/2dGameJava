package creature;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Human extends Creature{
	
	public Human(GamePanel gp, int absX, int absY, int speed, int person_num) {
		super(gp, absX, absY, speed, person_num);
		this.folder_name = "/humans";
		this.solidArea = new Rectangle(gp.tileSize/4, gp.tileSize/4, gp.tileSize/2,gp.tileSize/2);

		loadImages();
	}
	
	public void loadImages() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream(
					package_name+folder_name+"/player"+person_num+"_1.png"));
			
			up2 = ImageIO.read(getClass().getResourceAsStream(
					package_name+folder_name+"/player"+person_num+"_2.png"));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
