package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gamePanel, KeyHandler keyHandler){
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        screenX = gamePanel.screenWidth/2 - (gamePanel.tileSize);
        screenY = gamePanel.screenHeight/2 - (gamePanel.tileSize);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX = gamePanel.tileSize * 23;
        worldY = gamePanel.tileSize * 21;
        speed=4;
        direction="down";
    }

    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        if(keyHandler.upPressed|| keyHandler.downPressed|| keyHandler.leftPressed|| keyHandler.rightPressed){
            if(keyHandler.upPressed){
                direction = "up";
                worldY -= speed;
            }
            else if(keyHandler.downPressed){
                direction = "down";
                worldY += speed;
            }
            else if(keyHandler.leftPressed){
                direction = "left";
                worldX -= speed;
            }
            else if(keyHandler.rightPressed){
                direction = "right";
                worldX += speed;
            }

            spriteCounter++;
            if(spriteCounter>12){
                if(spriteNumber == 1){
                    spriteNumber = 2;
                }
                else if(spriteNumber ==2){
                    spriteNumber = 1;
                }
                spriteCounter=0;
            }
        }

    }
    public void draw(Graphics2D graphics2D){
        /*graphics2D.setColor(Color.white);
        graphics2D.fillRect(x,y,gamePanel.tileSize,gamePanel.tileSize);*/

        BufferedImage image = null;

        switch(direction){
            case "up":
                if(spriteNumber==1){
                    image = up1;
                }
                if(spriteNumber==2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNumber==1){
                    image = down1;
                }
                if(spriteNumber==2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNumber==1){
                    image = left1;
                }
                if(spriteNumber==2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNumber==1){
                    image = right1;
                }
                if(spriteNumber==2){
                    image = right2;
                }
                break;
        }
        graphics2D.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize,null);
    }
}
