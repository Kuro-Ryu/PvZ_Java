package Zombie;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import Board.GamePanel;

public class NormalZombie extends Zombie {
	
	private GamePanel gp;
	private Graphics g;
	private Image normalZombieImage;
	
	public NormalZombie(GamePanel parent,int lane) {
		super(parent,lane);
		setHealth(100);
		normalZombieImage = new ImageIcon(this.getClass().getResource("/images/Zombie/Zombieidle.gif")).getImage();
		draw(normalZombieImage);
	}
	
	public void draw(Image I) {
		for (int i = 0; i < 5 ; i++) {
			for (Zombie z : gp.laneZombies.get(i))
				g.drawImage(I,z.posX,109+(i*120),null);
		}
	}
}
