import java.awt.Graphics2D;

/**
 * Logic of the game.
 * Contract for all classes that implement this class.
 * @param <T> Dice type.
 *
 * @author JoelS
 * @version 1
 */
public interface GameLogic<T> {
    Dice<T> getDice();
    int getFlag();
    void onKeyPressed();
    void onMouseClicked(int x, int y);
    void doubleMouseClicked(int x, int y);
    boolean getdoubleClicked();
    void setdoubleClicked(boolean doubleClicked);
    void paint(Graphics2D graphics);
}
