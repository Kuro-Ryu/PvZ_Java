package Zombie;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

import Board.GamePanel;

public class ArmoredZombie extends Zombie{
	
	private GamePanel gp;
	
	private Graphics g;
	private Image armouredZombieImage;
			
	public ArmoredZombie (GamePanel parent, int lane) {
		super(parent,lane);
		setHealth(180);
		armouredZombieImage = new ImageIcon(this.getClass().getResource("/images/Zombie/zombie2.png")).getImage();
	}
	
	@Override
	 protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < 5 ; i++) {
			for (Zombie z : gp.laneZombies.get(i))
				g.drawImage(armouredZombieImage,z.posX,109+(i*120),null);
        }
    }
}
