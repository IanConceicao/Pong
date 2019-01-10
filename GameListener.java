import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

public class GameListener implements ActionListener, KeyListener{
   //actionListener is for time, keyListener is for finding key strokes

   private GamePanel panel;
   private Timer timer;
   private boolean up = false;
   private boolean down = false;
   private boolean pause = true;

   public GameListener(GamePanel panel){
      this.panel = panel;
      timer = new Timer(15, this);
      timer.start();
      panel.addKeyListener(this);
      panel.setFocusable(true);
      panel.requestFocusInWindow();
   }

   @Override
   public void keyTyped(KeyEvent e) {
   }

   @Override
   public void keyPressed(KeyEvent e) {
      if(e.getKeyCode() == e.VK_SPACE) {
         pause = !pause;
      }
      if(e.getKeyCode() == e.VK_UP){
         up = true;
      }
      else if (e.getKeyCode() == e.VK_DOWN){
         down = true;
      }
   }

   @Override
   public void keyReleased(KeyEvent e) {
      if(e.getKeyCode() == e.VK_UP){
         up = false;

      }
      else if (e.getKeyCode() == e.VK_DOWN){
         down = false;
      }
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if(!pause) {
         panel.doActions(); 

         if(up){
            panel.getPlayerPaddle().updateUp();
         }
         if(down){
            panel.getPlayerPaddle().updateDown();
         }
         if(panel.winner()) {
            timer.stop();
         }
      }
   }
}
