import java.awt.Color;
import java.awt.Graphics;

public class Ball
{
   private int xPosition, yPosition, xVelocity, yVelocity, width, speed;
   private Color color;

   public Ball(Color color) {
      this.color = color;
      this.width = 20;
      this.yPosition = (750 - this.width) / 2;
      this.xPosition = (1200 - this.width) / 2;
      this.speed = 8;
      this.yVelocity = 0;
      this.xVelocity = 0;
   } 

   public Ball(Color color, int speed) {
      this(color);
      this.speed = speed;
   } 

   public void setStartingPosition(int screenWidth, int screenHeight) {
      this.xPosition = 1200 / 2;
      this.yPosition = (int)(750 / 2);
   }

   public void update() { 
      this.yPosition += this.yVelocity; 
      this.xPosition += this.xVelocity;
   }

   public void draw(Graphics g, int screenWidth, int screenHeight) {
      g.setColor(color);
      g.fillRect((int)(xPosition*screenWidth/1200), (int)(yPosition*screenHeight/750), 
            (int)(width*screenWidth/1200), (int)(width*screenHeight/750));
   }

   public void increaseSpeed() {
      if(Math.abs(xVelocity) < (speed * 3)){
         xVelocity = (int)(1.2 * xVelocity);
         yVelocity = (int)(1.2 * yVelocity);
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

   public int getWidth()
   {
      return width;
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

   public void setWidth(int width)
   {
      this.width = width;
   }

   public void setColor(Color color)
   {
      this.color = color;
   }

   public int getSpeed()
   {
      return speed;
   }

   public void setSpeed(int speed)
   {
      this.speed = speed;
   }
}
