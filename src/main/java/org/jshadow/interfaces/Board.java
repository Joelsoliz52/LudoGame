package org.jshadow.interfaces;

import java.awt.Graphics2D;

/**
 * Board view of the game.
 * Contract for all classes that implement this class.
 *
 * @author JoelS
 * @version 1
 */
public interface Board {
    void draw(Graphics2D graphics);
    int getHeight();
    int[][] getInitialX();
    int[][] getInitialY();
    Path getPath();
    int getWidth();
}
