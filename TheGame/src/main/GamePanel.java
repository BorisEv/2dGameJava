package main;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import background.TileManager;
import creature.Human;
import creature.Player;
import object.Door;
import object.MyKey;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable{

	final int originalTileSize = 16;
	final int scale = 3;
	public final int tileSize = originalTileSize*scale;
	
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize*maxScreenCol;
	public final int screenHeight = tileSize*maxScreenRow;
	
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize*maxWorldCol;
	public final int worldHeight = tileSize*maxWorldRow;
	
	final int fps = 60;
	final long drawInterval = 1000000000/fps;
	

	Thread gameThread;
	KeyHandler keyH;
	
	public int[][] collisionsMap;
	TileManager tileM;
	public CollisionCheck colCheck;
	
	public Player player;
	public Human human1;
	public Door door1;
	public MyKey key1;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.WHITE);
		this.setDoubleBuffered(true);
		this.setFocusable(true);		
		keyH = new KeyHandler();
		this.addKeyListener(keyH);
		
		collisionsMap = new int[maxWorldCol][maxWorldRow];
		tileM = new TileManager(this);
		colCheck= new CollisionCheck(this);
		player = new Player(this, keyH, 11*tileSize, 6*tileSize, 4, 2);
		human1 = new Human(this,6*tileSize,6*tileSize,4,1);
		door1 = new Door(5*tileSize,8*tileSize,"door1_close.png", this, keyH);
		key1 = new MyKey(15*tileSize, 5*tileSize, door1, "/objects/key.png", this, keyH);
		door1.key = key1;
	}

	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		long nextDrawTime = System.nanoTime()+drawInterval;
		
		while(gameThread!=null) {
			update();
			repaint();
			
			long remainingTime = (nextDrawTime-System.nanoTime())/1000000;  //in mili
			if(remainingTime>0) {
				try {
					Thread.sleep(remainingTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				nextDrawTime = System.nanoTime()+drawInterval;
			}
		}
		
	}
	
	public void update() {		
		player.update();
		human1.update();
		key1.update();
		door1.update();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		tileM.draw(g2d);
		door1.draw(g2d);
		key1.draw(g2d);
		human1.draw(g2d);
		player.draw(g2d);
		g2d.dispose();
	}
}
