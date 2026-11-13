// HasMenu.java
// Any class that implements this must provide a menu and a start loop.

import java.util.Scanner;

public interface HasMenu {
    // Fields in an interface are automatically public static final.
    // Every class that implements HasMenu shares this one Scanner,
    // so multiple Scanners never fight over System.in.
    Scanner input = new Scanner(System.in);

    public String menu();
    public void start();
} // end interface
