package org.jshadow.entities;

import org.jshadow.interfaces.Path;

import java.awt.Color;

/**
 * Entity Player
 * Logic of player.
 *
 * @author JoelS
 * @version 1
 */
public class Player {
    // Fields of the class.
    private int coin;
    private final Color color;
    private final Pawn[] pawns;
    private final int turn;

    /**
     * Player constructor.
     * @param color Player color.
     * @param height Height of the boxes.
     * @param turn Player turn.
     * @param width Width of the boxes.
     * @param path Path of the pawns.
     */
    public Player(Color color, int height, int turn, int width, Path path) {
        this.color = color;
        this.turn = turn;
        coin = 0;
        pawns = new Pawn[4];

        for(int i = 0; i < 4; i++) {
             pawns[i] = new Pawn(height, width, path);
        }
    }

    /**
     * Increment pawns that are in finish box.
     */
    public void incrementCoin() { this.coin++; }

    /**
     * Returns color of the player.
     * @return Player color.
     */
    public Color getColor() { return color; }

    /**
     * Returns current pawns that are in finish box.
     * @return Player pawns that finished.
     */
    public int getCoin() { return coin; }

    /**
     * Returns all pawns of the player.
     * @return Player pawns.
     */
    public Pawn[] getPawns() { return pawns; }

    /**
     * Returns player turn.
     * @return Player turn.
     */
    public int getTurn() { return turn; }
}
