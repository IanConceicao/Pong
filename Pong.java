import java.awt.Color;
import java.awt.Graphics;

public class Pong
{
   //Collison does work if the ball hits the bottom of paddles 
   
   final static double EASY = 0.28;
   final static double MEDIUM = 0.24;
   final static double HARD = 0.15;
   static double launchDifficulty = MEDIUM;

   final static int[]  SMALL_WINDOW = {500, 400};
   final static int[]  MEDIUM_WINDOW = {1000, 625};
   final static int[] LARGE_WINDOW = {1200, 750};
   static int[] launchWindow = MEDIUM_WINDOW;

   static Color launchColor = Color.WHITE;

   public static void main(String[] args ) {
      char input;

      System.out.println("Press 'p' to play or type 's' for a launch with custom settings.");
      input = TextIO.getlnChar();

      if(input == 's' || input ==  'S') {
         settings();
      }        

      GameWindow gameWindow = new GameWindow(launchWindow[0], launchWindow[1], launchColor, launchDifficulty);
   }

   public static void settings() {
      int input;

      System.out.println("Window size? Options: "
            + "\n1. Small "
            + "\n2. Medium "
            + "\n3. Large ");
      input = TextIO.getlnInt();
      System.out.println(input);

      switch(input) {
      case 1: launchWindow = SMALL_WINDOW;
      break;
      case 2: launchWindow = MEDIUM_WINDOW;
      break;
      case 3: launchWindow = LARGE_WINDOW;
      break;
      }

      System.out.println("Bot opponent difficulty? Options: "
            + "\n1. Easy "
            + "\n2. Medium "
            + "\n3. Hard ");
      input = TextIO.getlnInt();
      switch(input) {
      case 1: launchDifficulty = EASY;
      break;
      case 2: launchDifficulty = MEDIUM;
      break;
      case 3: launchDifficulty = HARD;
      break;
      }

      System.out.println("Theme color? Options: "
            + "\n1. Blue "
            + "\n2. Red "
            + "\n3. Green "
            + "\n4. Purple "
            + "\n5. Orange "
            + "\n6. Yellow "
            + "\n7. White");
      input = TextIO.getlnInt();

      switch(input) {
      case 1: launchColor = Color.CYAN;
      break;
      case 2: launchColor = Color.RED;
      break;
      case 3: launchColor = Color.GREEN;
      break;
      case 4: launchColor = Color.MAGENTA;
      break;
      case 5: launchColor = Color.ORANGE;
      break;
      case 6: launchColor = Color.YELLOW;
      break;
      case 7: launchColor = Color.WHITE;
      break;
      }
   }
}
