import javax.swing.*;

public class Main {
    public static void main(String[] args){
        JFrame game = new JFrame();
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setResizable(false);
        game.setTitle("Projekt");

        GamePanel gamePanel = new GamePanel();
        game.add(gamePanel);

        game.pack();

        game.setLocationRelativeTo(null);
        game.setVisible(true);

        gamePanel.startGameThread();
    }
}
