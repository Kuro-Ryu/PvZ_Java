package Board;

import javax.swing.*;
import java.awt.event.ActionEvent;

import Board.Character.PlantType;

public class GameWindow extends JFrame {
    
    public GameWindow(){
        setSize(1012,785);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel sun = new JLabel("SUN");
        sun.setLocation(37,80);
        sun.setSize(60,20);

        GamePanel gp = new GamePanel(sun);
        gp.setLocation(0,0);
        getLayeredPane().add(gp, new Integer(0));
        
        PlantCard sunflower = new PlantCard(new ImageIcon(this.getClass().getResource("/images/cards/card_sunflower.png")).getImage());
        sunflower.setLocation(110,8);
        sunflower.setAction((ActionEvent e) -> {
            gp.activePlantingBrush = PlantType.Sunflower;
        });
        getLayeredPane().add(sunflower, new Integer(3));

        PlantCard peashooter = new PlantCard(new ImageIcon(this.getClass().getResource("/images/cards/card_peashooter.png")).getImage());
        peashooter.setLocation(175,8);
        peashooter.setAction((ActionEvent e) -> {
            gp.activePlantingBrush = PlantType.Peashooter;
        });
        getLayeredPane().add(peashooter,new Integer(3));

       /* PlantCard wallnut = new PlantCard(new ImageIcon(this.getClass().getResource("/images/cards/card_wallnut.png")).getImage());
        wallnut.setLocation(240,8);
        wallnut.setAction((ActionEvent e) -> {
            gp.activePlantingBrush = PlantType.Walnut;
        });
        getLayeredPane().add(wallnut, 3);
*/


        getLayeredPane().add(sun,new Integer(2));
        setResizable(false);
        setVisible(true);
    }
    public GameWindow(boolean b) {
        Menu menu = new Menu();
        menu.setLocation(0,0);
        setSize(1012, 785);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getLayeredPane().add(menu, 0);
        menu.repaint();
        setResizable(false);
        setVisible(true);
    }
    private static GameWindow gw;
    public static void begin() {
    	getGw().dispose();
    	new GameWindow();
    }
    public static void main(String[] args) {
    	setGw(new GameWindow(true));
    }
	public static GameWindow getGw() {
		return gw;
	}
	public static void setGw(GameWindow gw) {
		GameWindow.gw = gw;
	}

}