import java.awt.Color;
import java.awt.Graphics;

public class Paddle
{

   private int xPosition, yPosition, xVelocity, yVelocity, height, width, speed, score;
   boolean closePaddle;
   private Color color;
   private int screenHeight;

   //dont need screenWidth/screenHeight, assume everywhere it is 1200
   public Paddle(int speed, boolean closePaddle, Color color) {
      this.score = 0;
      this.closePaddle = closePaddle;
      this.color = color;
      this.height = (int)(100);
      this.width = (int)(25);
      this.speed = speed;
      this.screenHeight = 750;
      this.yPosition = (screenHeight - this.width) / 2;
      this.xPosition = this.xPositionCalculate(20);
      this.yVelocity = 0;
   }

   public void updateUp() {
      yPosition -= speed;
      if(yPosition <= 0){
         yPosition = 0;
      }
      else if(yPosition + height >= screenHeight){
         yPosition = screenHeight - height;
      }
   }

   public void updateDown() {
      yPosition += speed;
      if(yPosition<= 0){
         yPosition = 0;
      }
      else if(yPosition+height >= screenHeight){
         yPosition = screenHeight - height;
      }
   }
   
   public void update(Ball b){
      if(b.getyPosition() + b.getWidth()/2 - 10 > yPosition + height/2){
         yVelocity = speed;
      }
      else if(b.getyPosition() + b.getWidth()/2 + 10 < yPosition + height/2){
         yVelocity = -speed;
      }
      else {
         yVelocity = 0;
      }
      
      yPosition += yVelocity; 

      if(yPosition <= 0){
         yPosition = 0;
      }
      else if(yPosition + height >= screenHeight){
         yPosition = screenHeight - height;
      }
   }

   public void draw(Graphics g, int screenWidth, int screenHeight){
      g.setColor(this.color);
      g.fillRect((int)(xPosition*screenWidth/1200), (int)(yPosition*screenHeight/750), 
            (int)(width*screenWidth/1200), (int)(height*screenHeight/750));
   }
   
   public int xPositionCalculate(int distanceFromEdge) {
      if(this.closePaddle) {
         return distanceFromEdge;
      }
      else {
         return 1200 - distanceFromEdge - this.width;
      }  
   }

   public int getxPosition()
   {
      return xPosition;
   }

   public int getyPosition()
   {
      return yPosition;
   }

   public int getxVelocity()
   {
      return xVelocity;
   }

   public int getyVelocity()
   {
      return yVelocity;
   }

   public int getHeight()
   {
      return height;
   }

   public int getWidth()
   {
      return width;
   }

   public boolean isClosePaddle()
   {
      return closePaddle;
   }

   public Color getColor()
   {
      return color;
   }

   public void setxPosition(int xPosition)
   {
      this.xPosition = xPosition;
   }

   public void setyPosition(int yPosition)
   {
      this.yPosition = yPosition;
   }

   public void setxVelocity(int xVelocity)
   {
      this.xVelocity = xVelocity;
   }

   public void setyVelocity(int yVelocity)
   {
      this.yVelocity = yVelocity;
   }

   public void setHeight(int height)
   {
      this.height = height;
   }

   public void setWidth(int width)
   {
      this.width = width;
   }

   public void setClosePaddle(boolean closePaddle)
   {
      this.closePaddle = closePaddle;
   }

   public void setColor(Color color)
   {
      this.color = color;
   }

   public int getSpeed()
   {
      return speed;
   }

   public int getScore()
   {
      return score;
   }

   public void setSpeed(int speed)
   {
      this.speed = speed;
   }

   public void setScore(int score)
   {
      this.score = score;
   }
}
