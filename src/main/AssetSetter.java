package main;

import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_key;

public class AssetSetter {
    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void setObject(){

        gamePanel.object[0] = new OBJ_key();
        gamePanel.object[0].worldX = 23 * gamePanel.tileSize;
        gamePanel.object[0].worldY = 7 * gamePanel.tileSize;

        gamePanel.object[1] = new OBJ_key();
        gamePanel.object[1].worldX = 23 * gamePanel.tileSize;
        gamePanel.object[1].worldY = 40 * gamePanel.tileSize;

        gamePanel.object[2] = new OBJ_key();
        gamePanel.object[2].worldX = 38 * gamePanel.tileSize;
        gamePanel.object[2].worldY = 8 * gamePanel.tileSize;

        gamePanel.object[3] = new OBJ_Door();
        gamePanel.object[3].worldX = 10 * gamePanel.tileSize;
        gamePanel.object[3].worldY = 11 * gamePanel.tileSize;

        gamePanel.object[4] = new OBJ_Door();
        gamePanel.object[4].worldX = 8 * gamePanel.tileSize;
        gamePanel.object[4].worldY = 28 * gamePanel.tileSize;

        gamePanel.object[5] = new OBJ_Door();
        gamePanel.object[5].worldX = 12 * gamePanel.tileSize;
        gamePanel.object[5].worldY = 22 * gamePanel.tileSize;

        gamePanel.object[6] = new OBJ_Chest();
        gamePanel.object[6].worldX = 10 * gamePanel.tileSize;
        gamePanel.object[6].worldY = 7 * gamePanel.tileSize;
    }
}
