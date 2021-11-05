package debug4;

public class DebugBox
{
   private int width;
   private int length;
   private int height;
   private void FixDebugBox()
   {
      length = 1;
      width = 1;
      height = 1;
   }
   public DebugBox(int width, int length, int height)
   {
      width = width;
      length = length;
      height = height;
   }

   public void showData(int width, int length, int height)
   {
     System.out.println("Width: "  + width + "  Length: " +
       length + "  Height: " + height);
   }
   public double getVolume(int width, int length, int height)
   {
     double vol = length * width * height;
     return vol;
   }
}
