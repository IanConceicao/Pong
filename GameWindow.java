import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.util.Timer;

import javax.swing.JFrame;

public class GameWindow extends JFrame
{
   private GamePanel gamePanel;
   private GameListener gameListener;            
   
   public GameWindow(int screenWidth, int screenHeight, Color color, double difficulty) {
      this.setSize(screenWidth, screenHeight);
      this.setTitle("Pong");
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setLocationRelativeTo(null);
      this.gamePanel = new GamePanel(color, difficulty);
      this.gameListener = new GameListener(gamePanel);
      this.add(gamePanel);
      this.setVisible(true);
      System.out.println("Game has opened in another window, find it then press the space key to unpause the game.");
      System.out.println("Use arrow keys to move, you are the paddle on the right.");
   }
   
   public GameWindow(){
      this(1200, 750, Color.WHITE, 0.23);
   }
}
