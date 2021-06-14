package utilities;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

/**
 * Constants for the project.
 */
public class Constants {
    private final static Dimension Screen = Toolkit.getDefaultToolkit().getScreenSize();
    public final static int Width = Screen.width;
    public final static int Height = Screen.height;
    public static String TitleGame = "LUDO";
    public static String DefaultFontFamily = "Agency FB";
    public static int DefaultFontSize = 20;
    public static int DefaultFontType = Font.BOLD;
}
