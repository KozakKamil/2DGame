import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    //Ustawienia Ekranu
    final int orgTileSize = 16;  //16x16
    final int scale = 3;

    final int tileSize = orgTileSize * scale;  //48x48
    final int maxScreenColumn = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenColumn;  //768 pixels
    final int screenHeight = tileSize * maxScreenRow;    //576 pixels

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }
}
