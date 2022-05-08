package main;

import object.OBJ_key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gamePanel;
    Font arial_40, arial_80;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter;
    public boolean gameFinished = false;
    double playTime;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gamePanel){
        this.gamePanel = gamePanel;

        arial_40 = new Font("Times",Font.PLAIN,40);
        arial_80 = new Font("Times",Font.BOLD,80);
        OBJ_key key = new OBJ_key();
        keyImage = key.image;
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    //Rysowanie informacji na temat ilości kluczy
    public void draw(Graphics2D graphics2D){
        if(gameFinished == true){
            graphics2D.setFont(arial_40);
            graphics2D.setColor(Color.white);
            
            //Znalezienie środka ekranu oraz wyświtalnie napisów
            String text;
            int textLength;
            int x;
            int y;

            text="Treasure found!";
            textLength = (int)graphics2D.getFontMetrics().getStringBounds(text,graphics2D).getWidth();

            x = gamePanel.screenWidth/2 - textLength/2 ;
            y = gamePanel.screenHeight/2 - (gamePanel.tileSize*3);
            graphics2D.drawString(text, x, y);

            graphics2D.setFont(arial_80);
            graphics2D.setColor(Color.yellow);

            text="Congratulations!";
            textLength = (int)graphics2D.getFontMetrics().getStringBounds(text,graphics2D).getWidth();

            x = gamePanel.screenWidth/2 - textLength/2 ;
            y = gamePanel.screenHeight/2 + (gamePanel.tileSize*2);
            graphics2D.drawString(text, x, y);

            graphics2D.setFont(arial_40);
            graphics2D.setColor(Color.white);

            text="Your time: " +decimalFormat.format(playTime);
            textLength = (int)graphics2D.getFontMetrics().getStringBounds(text,graphics2D).getWidth();

            x = gamePanel.screenWidth/2 - textLength/2 ;
            y = gamePanel.screenHeight/2 + (gamePanel.tileSize*3);
            graphics2D.drawString(text, x, y);

            gamePanel.gameThread = null;

        }
        else{
            graphics2D.setFont(arial_40);
            graphics2D.setColor(Color.white);
            graphics2D.drawImage(keyImage, gamePanel.tileSize/2,gamePanel.tileSize/2,gamePanel.tileSize,gamePanel.tileSize,null);
            graphics2D.drawString("x " + gamePanel.player.hasKey,74,65);

            //Czas
            playTime +=(double)1/60;
            graphics2D.drawString("Time "+decimalFormat.format(playTime),gamePanel.tileSize*11,65);

            //Wiadomość
            if(messageOn == true){

                graphics2D.setFont(graphics2D.getFont().deriveFont(30F));
                graphics2D.drawString(message,gamePanel.tileSize/2,gamePanel.tileSize * 5);

                messageCounter++;
                if(messageCounter > 120){
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }
}
