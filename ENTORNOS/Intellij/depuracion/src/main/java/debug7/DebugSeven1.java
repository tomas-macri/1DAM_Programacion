package debug7;

// Makes String comparisons
public class DebugSeven1
{
   public static void main(String[] args)
   {
      String name1 = "Roger";
      String name2 = "Roger";
      String name3 = "Stacy";

      if(name1.equalsIgnoreCase(name2))
        System.out.println(name1 + " and " + name2 +
          " are the same");
      if(name1.equalsIgnoreCase(name3))
        System.out.println(name1 + " and " + name3 +
          " are the same");
      if(name1.equalsIgnoreCase("roger"))
        System.out.println(name1 + " and 'roger' are the same");
      if(name1.equalsIgnoreCase("Roger"))
        System.out.println(name1 + " and 'Roger' are the same");
   }
}
