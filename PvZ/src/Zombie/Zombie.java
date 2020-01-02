package Zombie;

import javax.swing.*;
import java.awt.*;

import Board.Collider;
import Board.GamePanel;
import Board.GameWindow;

import java.awt.event.ActionEvent;

import Board.Character;

public class Zombie extends Character {

    private int Health;
    protected int Speed;

    private GamePanel gp;

    public int posX = 1000;
    public int myLane;
    public boolean isMoving = true;

    public Zombie(GamePanel parent,int lane){
        this.gp = parent;
        myLane = lane;
    }
    
    public void advance(){
        if(isMoving) {
            boolean isCollides = false;
            Collider collided = null;
            for (int i = myLane * 9; i < (myLane + 1) * 9; i++) {
                if (gp.getColliders()[i].assignedPlant != null && gp.getColliders()[i].isInsideCollider(posX)) {
                    isCollides = true;
                    collided = gp.getColliders()[i];
                }
            }
            if (!isCollides) {
                if(slowInt>0){
                    if(slowInt % 2 == 0) {
                        posX--;
                    }
                    slowInt--;
                }else {
                    posX -= 1;
                }
            } else {
                collided.assignedPlant.setHealth(-10);
                if (collided.assignedPlant.getHealth() < 0) {
                    collided.removePlant();
                }
            }
            if (posX < 0) {
                isMoving = false;
                JOptionPane.showMessageDialog(gp,"ZOMBIES ATE YOUR BRAIN !" + '\n' + "Starting the level again");
                GameWindow.getGw().dispose();
                GameWindow.setGw(new GameWindow());
            }
        }
    }

    int slowInt = 0;
    public void slow(){
        slowInt = 100;
    }
    public static Zombie getZombie(String type,GamePanel parent, int lane) {
        Zombie z = new Zombie(parent,lane);
       switch(type) {
           case "NormalZombie" : z = new NormalZombie(parent,lane);
                                 break;
           case "ConeZombie" : z = new ArmoredZombie(parent,lane);
                                 break;
    }
       return z;
    }
    
    public void setHealth(int health)
    {
        Health = health;
    }

	public int getHealth() {
		return Health;
	}
}