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
    public int hasKey = 0;


    public Player(GamePanel gamePanel, KeyHandler keyHandler){
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        screenX = gamePanel.screenWidth/2 - (gamePanel.tileSize);
        screenY = gamePanel.screenHeight/2 - (gamePanel.tileSize);

        solidArea = new Rectangle(8, 16, 25, 25);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

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
            }
            else if(keyHandler.downPressed){
                direction = "down";
            }
            else if(keyHandler.leftPressed){
                direction = "left";
            }
            else if(keyHandler.rightPressed){
                direction = "right";
            }

            //Sprawdzenie kolizji
            collisionOn = false;
            gamePanel.collisionCheck.CheckTile(this);

            //sprawdzanie kolizji z obiektem
            int objIndex = gamePanel.collisionCheck.checkObject(this,true);
            pickUpObject(objIndex);

            //collisionOn == false - gracz może się ruszać
            if(collisionOn == false){
                switch (direction){
                    case"up":
                        worldY -= speed;
                        break;
                    case"down":
                        worldY += speed;
                        break;
                    case"left":
                        worldX -= speed;
                        break;
                    case"right":
                        worldX += speed;
                        break;
                }
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

    //Definiuje co się stanie z obiektem po kolizji
    public void pickUpObject(int i){
        if(i != 999){
            String objectName = gamePanel.object[i].name;
            switch (objectName){
                case "Key":
                    gamePanel.playSoundEffect(1);
                    hasKey++;
                    gamePanel.object[i] = null;
                    gamePanel.ui.showMessage("Picked up key!");
                    break;
                case"Door":
                    gamePanel.playSoundEffect(3);
                    if(hasKey > 0){
                        gamePanel.object[i] = null;
                        hasKey--;
                        gamePanel.ui.showMessage("Opened a door!");
                    }
                    else {
                        gamePanel.ui.showMessage("Need a key!");
                    }
                    break;
                case"Boots":
                    gamePanel.playSoundEffect(2);
                    speed += 1;
                    gamePanel.object[i]=null;
                    gamePanel.ui.showMessage("Speed up!");
                    break;
                case "Chest":
                    gamePanel.ui.gameFinished = true;
                    gamePanel.stopMusic();
                    gamePanel.playSoundEffect(4);
                    break;
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
