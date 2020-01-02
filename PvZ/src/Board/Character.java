package Board;

import javax.swing.JPanel;

public abstract class Character extends JPanel {
	
	enum PlantType {
		None,
		Sunflower,
		Peashooter
	}
	
	enum Zombie {
		None,
		NormalZombie,
		ConeZombie
	}
}
