package debug6;
// DebugSix4.java
// Displays 5 random numbers between
// (and including) user-specified values
import java.util.Random;
import java.util.Scanner;
public class DebugSix4
{
   public static void main(String[] args)
   {
      int high, low, count = 0;
      final int NUM = 5;
      Scanner input = new Scanner(System.in);
      // Prompt user to enter high and low values
      System.out.print("This application displays " + NUM +
         " random numbers" +
         "\nbetween the low and high values you enter" +
         "\nEnter low value now... ");
      low = input.nextInt();
      System.out.print("Enter high value... ");
      high = input.nextInt();
      while(low >= high)
      {
         System.out.println("The number you entered for a high number, " +
            high + ", is not more than " + low);
         System.out.print("Enter a number higher than " + low + "... ");
         high = input.nextInt();
      }
      Random numRandom = new Random();
      double result;
      while(count < NUM)
      {
         do {
            result = numRandom.nextInt(high);
         }while (result < low);
         System.out.print(result + "  ");
         count++;
      }
      System.out.println();
   }
}
