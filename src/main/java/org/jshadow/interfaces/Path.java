package org.jshadow.interfaces;

/**
 * Path of the pawns in the game.
 * Contract for all classes that implement this class.
 *
 * @author JoelS
 * @version 1
 */
public interface Path {
    int[][] getAX();
    int[][] getAY();
    int[][] getOptionalAX();
    int[][] getOptionalAY();
}
