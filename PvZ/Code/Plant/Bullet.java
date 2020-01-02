package Plant;

import javax.swing.*;
import java.awt.*;

import Board.Character;
import Board.GamePanel;
import Zombie.Zombie; 

public class Bullet {
	protected GamePanel gp;
	
	private Image bulletImage;
	private Graphics g;
	private int posX;
	private int myLane;
	
	public Bullet(GamePanel parent,int lane,int startX){
        this.gp = parent;
        this.myLane = lane;
        posX = startX;
        bulletImage = new ImageIcon(this.getClass().getResource("/images/pea.png")).getImage();
        draw(bulletImage);
    }
	
	
	public void advance() {
		Rectangle pRect = new Rectangle(posX,130+myLane*120,28,28);
        for (int i = 0; i < gp.laneZombies.get(myLane).size(); i++) {
            Zombie z = gp.laneZombies.get(myLane).get(i);
            Rectangle zRect = new Rectangle(z.posX,109 + myLane*120,400,120);
            if(pRect.intersects(zRect)){
                z.setHealth(-30);
                boolean exit = false;
                if(z.getHealth() < 0){
                    System.out.println("ZOMBIE DIE");
                    
                    gp.laneZombies.get(myLane).remove(i);
                    GamePanel.setProgress(10);
                    exit = true;
                }
                gp.laneBullets.get(myLane).remove(this);
                if(exit) break;
            }
        }
        /*if(posX > 2000){
            gp.getlaneBullets().get(myLane).remove(this);
        }*/
        posX += 15;
	}
	
	public void draw(Image I) {
		for (int i = 0; i < 5 ; i++) {
			for (int j = 0; j < gp.laneBullets.get(i).size(); j++) {
				Bullet b = gp.laneBullets.get(i).get(j);
				g.drawImage(I,b.posX,109+(i*120),null);
			}
		}
	}
}
