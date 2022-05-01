package main;

import Tile.TileManager;
import entity.Player;
import object.SuperObject;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    //Ustawienia Ekranu
    final int orgTileSize = 16;  //16x16
    final int scale = 3;

    public final int tileSize = orgTileSize * scale;  //48x48
    public final int maxScreenColumn = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenColumn;  //768 pixels
    public final int screenHeight = tileSize * maxScreenRow;    //576 pixels

    //Ustawienia mapy świata
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    TileManager tileManager = new TileManager(this);
    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    public CollisionCheck collisionCheck = new CollisionCheck(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public Player player = new Player(this,keyHandler);
    public SuperObject object[] = new SuperObject[10];

    int FPS = 60;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setupGame(){
        assetSetter.setObject();
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer=0;
        int drawCount=0;

        while(gameThread!=null){
            currentTime = System.nanoTime();
            delta += (currentTime-lastTime)/drawInterval;
            timer  += (currentTime-lastTime);
            lastTime = currentTime;
            //Ograniczenie do 60 FPS
            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
            //Wyświetlanie FPS
            if(timer>=1000000000){
                System.out.println("FPS: "+drawCount);
                drawCount = 0;
                timer = 0;
            }

        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D graphics2D = (Graphics2D) g;
        //Tile
        tileManager.draw(graphics2D);
        //Gracz
        player.draw(graphics2D);
        //Obiekt
        for(int i = 0; i < object.length; i++){
            if(object[i] != null){
                object[i].draw(graphics2D,this);
            }
        }
        graphics2D.dispose();
    }
}
