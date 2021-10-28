package debug6;

// DebugSix2.java
// Display every character between Unicode 65 and 122
// Start new line after 20 characters
public class DebugSix2 {
    public static void main(String args[]) {
        int a;
        final int MIN = 65;
        final int MAX = 122;
        final int STOPLINE1 = 85;
        final int STOPLINE2 = 105;
        char letter;
        for (a = MIN; a <= MAX; a++) {
            letter = (char) a;
            System.out.print(" " + letter);
            if ((a == STOPLINE1) || (a == STOPLINE2)) {
                System.out.println();
            }
        }
        System.out.println("\nEnd of application");
    }
}

