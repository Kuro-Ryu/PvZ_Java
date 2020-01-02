package Board;

import javax.swing.*;

import Board.Character;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import Zombie.NormalZombie;
import Zombie.Zombie;
import Plant.*; 



public class GamePanel extends JLayeredPane implements MouseMotionListener {

    Image bgImage;

    
    Collider[] colliders;
    Character.PlantType activePlantingBrush = Character.PlantType.None;
    
    public ArrayList<ArrayList<Zombie>> laneZombies;
    public ArrayList<ArrayList<Bullet>> laneBullets;
    public ArrayList<Sun> activeSuns;

    private Timer redrawTimer;
    private Timer advancerTimer;
    private Timer sunProducer;
    private Timer zombieProducer;
    private JLabel sunScoreboard;

    private int mouseX , mouseY;

    private int sunScore;

    public int getSunScore() {
        return sunScore;
    }
    
    public void setSunScore(int sunScore) {
        this.sunScore = sunScore;
        sunScoreboard.setText(String.valueOf(sunScore));
    }

    public GamePanel(JLabel sunScoreboard){
        setSize(1012,785);
        setLayout(null);
        addMouseMotionListener(this);
        this.sunScoreboard = sunScoreboard;
        setSunScore(150); 

        bgImage  = new ImageIcon(this.getClass().getResource("/images/GamePanel.png")).getImage();
        
        laneZombies = new ArrayList<>();
        laneZombies.add(new ArrayList<>()); //line 1
        laneZombies.add(new ArrayList<>()); //line 2
        laneZombies.add(new ArrayList<>()); //line 3
        laneZombies.add(new ArrayList<>()); //line 4
        laneZombies.add(new ArrayList<>()); //line 5

        laneBullets = new ArrayList<>();
        laneBullets.add(new ArrayList<>()); //line 1
        laneBullets.add(new ArrayList<>()); //line 2
        laneBullets.add(new ArrayList<>()); //line 3
        laneBullets.add(new ArrayList<>()); //line 4
        laneBullets.add(new ArrayList<>()); //line 5

        colliders = new Collider[45];
        for (int i = 0; i < 45; i++) {
            Collider a = new Collider();
            a.setLocation(44 + (i%9)*100,109 + (i/9)*120);
            a.setAction(new PlantActionListener((i%9),(i/9)));
            colliders[i] = a;
            add(a,new Integer(0));
        }
        /*colliders[9].setPlant(new Peashooter(this,0,1));
        laneZombies.get(1).add(new NormalZombie(this,1));*/

        activeSuns = new ArrayList<>();

        redrawTimer = new Timer(25,(ActionEvent e) -> {
            repaint();
        });
        redrawTimer.start();

        advancerTimer = new Timer(60,(ActionEvent e) -> advance());
        advancerTimer.start();

        sunProducer = new Timer(10000,(ActionEvent e) -> {
            Random rnd = new Random();
            Sun sta = new Sun(this,rnd.nextInt(800)+100,0,rnd.nextInt(300)+200);
            activeSuns.add(sta);
            add(sta,1);
        });
        
        sunProducer.start();

        zombieProducer = new Timer(7000,(ActionEvent e) -> {
            Random rnd = new Random();
            LevelData lvl = new LevelData();
            String [] Level = lvl.Level[Integer.parseInt(lvl.Lvl)-1];
            int [][] LevelValue = lvl.LevelValue[Integer.parseInt(lvl.Lvl)-1];
            int l = rnd.nextInt(5);
            int t = rnd.nextInt(100);
            Zombie z = null;
            for(int i = 0;i<LevelValue.length;i++) {
                if(t>= LevelValue[i][0] && t<= LevelValue[i][1]) {
                    z = Zombie.getZombie(Level[i],GamePanel.this,l);
                }
            }
            laneZombies.get(l).add(z);
        });
        zombieProducer.start();

    }

    private void advance(){
        for (int i = 0; i < 5 ; i++) {
            for(Zombie z : laneZombies.get(i)){
                z.advance();
            }

            for (int j = 0; j < laneBullets.get(i).size(); j++) {
                Bullet b = laneBullets.get(i).get(j);
                b.advance();
            }

        }

        for (int i = 0; i < activeSuns.size() ; i++) {
            activeSuns.get(i).advance();
        }

    }

    @Override
    protected void paintComponent(Graphics g) { // change this in to polymorphism 
        super.paintComponent(g);
        g.drawImage(bgImage,0,0,null);
    }
    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }
    static int progress = 0;
    
    public static void setProgress(int num) {
        progress = progress + num;
        System.out.println(progress);
        if(progress>=150) {
           if("1".equals(LevelData.Lvl)) {
            JOptionPane.showMessageDialog(null,"Level Completed !!!" + '\n' + "Starting next Level");
            GameWindow.getGw().dispose();
            LevelData.write("2");
            GameWindow.setGw(new GameWindow());
            }  else {
               JOptionPane.showMessageDialog(null,"Level Completed !!!" + '\n' + "More Levels will come soon !!!" + '\n' + "Resetting data");
               LevelData.write("1");
               System.exit(0);
           }
           progress = 0;
        }
    }
    
    class PlantActionListener implements ActionListener {

        int x,y;

        public PlantActionListener(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
    	public void actionPerformed(ActionEvent e) {
    		if(activePlantingBrush == Character.PlantType.Sunflower){
                if(getSunScore()>=50) {
                    colliders[x + y * 9].setPlant(new Sunflower(GamePanel.this, x, y));
                    setSunScore(-50);
                }
            }
            if(activePlantingBrush == Character.PlantType.Peashooter){
                if(getSunScore() >= 100) {
                    colliders[x + y * 9].setPlant(new Peashooter(GamePanel.this, x, y));
                    setSunScore(-100);
                }
            }

            /*if(activePlantingBrush == Character.PlantType.Walnut){
                if(gp.getSunScore() >= 175) {
                    colliders[x + y * 9].setPlant(new Walnut(gp, x, y));
                    gp.setSunScore(-50);
                }
            }*/
            activePlantingBrush = Character.PlantType.None;
        }		
    }

	public Collider[] getColliders() {
		return colliders;
	}

	public void setColliders(Collider[] colliders) {
		this.colliders = colliders;
	}

}