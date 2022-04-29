import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    //Ustawienia Ekranu
    final int orgTileSize = 16;  //16x16
    final int scale = 3;

    final int tileSize = orgTileSize * scale;  //48x48
    final int maxScreenColumn = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenColumn;  //768 pixels
    final int screenHeight = tileSize * maxScreenRow;    //576 pixels

    Thread gameThread;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameThread!=null){
            //System.out.println("Pętla działa.");
            update();
            repaint();
        }

    }

    public void update() {

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.setColor(Color.white);

        graphics2D.fillRect(100,100,tileSize,tileSize);

        graphics2D.dispose();
    }
}
