package debug4;

// This class uses a DebugBox class to instantiate two Box objects
public class DebugFour3 {
   public static void main(String args[]) {
      int widthBox1 = 12;
      int lengthBox1 = 10;
      int heightBox1 = 8;
      int widthBox2 = 10;
      int lengthBox2 = 10;
      int heightBox2 = 10;
      DebugBox box1 = new DebugBox(widthBox1, lengthBox1, heightBox1);
      DebugBox box2 = new DebugBox(widthBox2, lengthBox2, heightBox2);
      System.out.println("The dimensions of the first box are");
      box1.showData(widthBox1, lengthBox1, heightBox1);
      System.out.print("  The volume of the first box is ");
      showVolume(box1, widthBox1, lengthBox1, heightBox1);
      System.out.println("The dimensions of the second box are");
      box2.showData(widthBox2, lengthBox2, heightBox2);
      System.out.print("  The volume of the second box is ");
      showVolume(box2, widthBox2, lengthBox2, heightBox2);
   }

   public static void showVolume(DebugBox aBox, int width, int length, int height ) {
      double vol = aBox.getVolume(width, length, height);
      System.out.println(vol);
   }
}

