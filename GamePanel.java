import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel {
   private Color backgroundColor = Color.BLACK;

   private Paddle playerPaddle, botPaddle;
   private Ball ball, ghostBall;
   private Color themeColor;
   private double difficulty;

   public GamePanel(Color themeColor, double difficulty){
      this.difficulty = difficulty;
      this.themeColor = themeColor;
      playerPaddle = new Paddle(10, false, themeColor);
      botPaddle = new Paddle(10, true, themeColor);
      ball = new Ball(themeColor);
      ghostBall = new Ball(Color.RED, (int)(playerPaddle.getSpeed() * 1.5));
      resetBallMovement();
   }

   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      this.paintHalfLine(g, 2, themeColor);
      this.setBackground(backgroundColor);
      playerPaddle.draw(g, this.getWidth(), this.getHeight());
      botPaddle.draw(g, this.getWidth(), this.getHeight());
      ball.draw(g, this.getWidth(), this.getHeight());
      drawScores(g, themeColor);
//      ghostBall.draw(g, this.getWidth(), this.getHeight()); 
      if(winner()) {
         drawEndGame(g, themeColor);
      }
   }

   public void doActions(){
      ballCollisionChecker();
      ballScoreChecker();
      ghostBallScoreChecker();
      ball.update();
      ghostBall.update();
      botPaddle.update(ghostBall);
      repaint();
   }

   public void drawEndGame(Graphics g, Color color) {
      g.setColor(color);
      g.setFont(new Font("Times New Roman", Font.PLAIN, (int)(this.getWidth()/10)));
      if(playerPaddle.getScore() >= 10) {
         g.drawString("YOU WIN", (int)(this.getWidth() * 3/10), (int)(this.getHeight() * 5/10));
      }  
      else{
         g.drawString("GAME OVER", (int)(this.getWidth() * 2/10), (int)(this.getHeight() * 5/10));
      }
   }

   public boolean winner() {
      if(playerPaddle.getScore() >= 10 || botPaddle.getScore() >= 10) {
         return true;
      }
      return false;
   }

   public void drawScores(Graphics g, Color color) {
      g.setColor(color);
      g.setFont(new Font("Times New Roman", Font.PLAIN, (int)(this.getWidth()/20)));
      g.drawString("" + botPaddle.getScore(), (int)(this.getWidth()*4.2/10), (int)(this.getWidth()/12));
      g.drawString("" + playerPaddle.getScore(), (int)(this.getWidth()*5.5/10), (int)(this.getWidth()/12));
   }

   public void paintHalfLine(Graphics g, int width, Color color) {
      g.setColor(color);
      g.drawRect((int)(this.getWidth() * (600 - (width/2)) / 1200), 0, width, 
            (int)(this.getHeight() * 750  / 750));
      g.fillRect((int)(this.getWidth() * (600 - (width/2)) / 1200), 0, width, 
            (int)(this.getHeight() * 750  / 750));
   }

   public void ghostBallCopy() {
      Random r = new Random();
      double randomValue = (int)(Math.abs((ball.getxVelocity() * difficulty / ball.getSpeed()))) 
            * r.nextDouble();
      double randomValueTwo = (int)(Math.abs((ball.getxVelocity() * difficulty / ball.getSpeed())))
            * r.nextDouble();
      
      ghostBall.setxVelocity((int)((1.3 + randomValue) * ball.getxVelocity()));
      ghostBall.setyVelocity((int)((1.3 + randomValueTwo) * ball.getyVelocity()));
      ghostBall.setxPosition(ball.getxPosition());
      ghostBall.setyPosition(ball.getyPosition());
   }

   public void resetBallMovement() {
      ball.setxPosition(1200/2 - ball.getWidth()/2);
      ball.setyPosition((int)(750/2) - ball.getWidth()/2);

      if(Math.random() > 0.5) {
         ball.setxVelocity(ball.getSpeed());
      }
      else {
         ball.setxVelocity(-ball.getSpeed());
      }
      if(Math.random() > 0.5) {
         ball.setyVelocity(ball.getSpeed());
      }
      else {
         ball.setyVelocity(-ball.getSpeed());
      }
      ghostBallCopy();
   }

   public void ghostBallScoreChecker() {
      if(ghostBall.getxPosition() <= 20 + playerPaddle.getWidth()) {
         ghostBall.setxVelocity(0);
         ghostBall.setyVelocity(0);
      }
   }

   public void ballScoreChecker() {

      if(ball.getxPosition() <= 0) {
         playerPaddle.setScore(playerPaddle.getScore() + 1);
         resetBallMovement();
      }

      else if((ball.getxPosition() + ball.getWidth()) >= 1200){
         botPaddle.setScore(botPaddle.getScore() + 1);
         resetBallMovement();
      }
   }

   public void ballCollisionChecker() {
      //top bottom check for ball
      if(ball.getyPosition() <= 0) {
         ball.setyPosition(1);
         ball.setyVelocity(-ball.getyVelocity());
      }
      else if((ball.getyPosition() + ball.getWidth()) >= 750) {
         ball.setyPosition(750 - ball.getWidth() - 1);
         ball.setyVelocity(-ball.getyVelocity());
      }

      //top bottom check for ghostball
      if(ghostBall.getyPosition() <= 0) {
         ghostBall.setyPosition(1);
         ghostBall.setyVelocity(-ghostBall.getyVelocity());
      }
      else if((ghostBall.getyPosition() + ghostBall.getWidth()) >= 750) {
         ghostBall.setyPosition(750 - ghostBall.getWidth() - 1);
         ghostBall.setyVelocity(-ghostBall.getyVelocity());
      }


      if(ball.getyPosition() + ball.getWidth() > playerPaddle.getyPosition() &&
            ball.getyPosition() < playerPaddle.getyPosition() + playerPaddle.getHeight() &&
            ball.getxPosition() + ball.getWidth() > playerPaddle.getxPosition()){

         ball.setxPosition(playerPaddle.getxPosition() - ball.getWidth());
         ball.setxVelocity(-ball.getxVelocity());
         ball.increaseSpeed();
         ghostBallCopy();
      }

      if(ball.getyPosition() + ball.getWidth() > botPaddle.getyPosition() &&
            ball.getyPosition() < botPaddle.getyPosition()+botPaddle.getHeight() &&
            ball.getxPosition() < botPaddle.getxPosition()+botPaddle.getWidth()){
         
         ball.setxPosition(botPaddle.getxPosition() + botPaddle.getWidth());
         ball.setxVelocity(-ball.getxVelocity());
         ball.increaseSpeed();
      }

      //second check if it hits a paddle, inputting player and bot
   }

   public Paddle getPlayerPaddle(){
      return playerPaddle;
   }

}
