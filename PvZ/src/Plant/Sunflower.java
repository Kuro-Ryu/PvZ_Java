package Plant;

import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import Board.Collider;
import Board.GamePanel;

public class Sunflower extends Plant {
	
	private Graphics g;
	private Timer sunProduceTimer;
	private Image sunflowerImage;
		
	public Sunflower(GamePanel parent, int x, int y) {
		super(parent, x, y);
		sunflowerImage = new ImageIcon(this.getClass().getResource("/images/plants/sunflower.gif")).getImage();
		setHealth(100);
		sunProduceTimer = new Timer(15000,(ActionEvent e) -> {
			Sun sta = new Sun(gp,60 + x*100,110 + y*120,130 + y*120);
			gp.activeSuns.add(sta);
			gp.add(sta, 1);
			});
		sunProduceTimer.start();
	}
	
	@Override
	 protected void paintComponent(Graphics g)  {
		for (int i = 0; i < 5 ; i++) {
			Collider c = gp.getColliders()[i];
			Plant p = c.assignedPlant;
			g.drawImage(sunflowerImage,60 + (i%9)*100,129 + (i/9)*120,null);
		}
	}

}
