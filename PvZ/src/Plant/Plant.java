package Plant;

import Board.GamePanel; 
import Board.Character;

public abstract class Plant extends Character {
	private int health;
	
	protected int x;
	protected int y;
	
	protected GamePanel gp;
	
	public Plant(GamePanel parent,int x,int y) {
		this.x = x;
	    this.y = y;
	    this.gp = parent;
	}
	
	public void setHealth(int health) {
		health = health;
	}
	public int getHealth() {
		return health;
	}
	
	public void stop() {
	}
}
