package Plant;

import java.awt.*; 
import java.awt.Graphics;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import Board.Collider;
import Board.GamePanel;

public class Peashooter extends Plant {

	private Graphics g;
	private Timer shootTime;
	private Image peashooterImage;
	
	public Peashooter(GamePanel parent, int x, int y) {
		super(parent, x, y);
		setHealth(100);
		peashooterImage = new ImageIcon(this.getClass().getResource("/images/Plant/peashooter.gif")).getImage();
        shootTime = new Timer(2000,(ActionEvent e) -> {
            if(gp.laneZombies.get(y).size() > 0) {
                gp.laneBullets.get(y).add(new Bullet(gp, y, 103 + this.x * 100));
            }
        });
        shootTime.start();
    }

    @Override
    public void stop(){
        shootTime.stop();
    }
    
    @Override
	 protected void paintComponent(Graphics g) {
		for (int i = 0; i < 45 ; i++) {
			Collider c = gp.getColliders()[i];
				Plant p = c.assignedPlant;
				g.drawImage(peashooterImage,60 + (i%9)*100,129 + (i/9)*120,null);
		}
	}
	
	
}
