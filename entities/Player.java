package entities;

import java.awt.Color;
import java.io.Serializable;

import interfaces.Path;

/**
 * Entity Player
 * Logic of player.
 *
 * @author JoelS
 * @version 1
 */
public class Player implements Serializable {
    // Fields of the class.
    private int coin;
    private Color color;
    private final Pawn[] pawns;
    private int turn;
    private String name;
    private int flagBonus;
    private int flagTraps;
    private int loseTurn;
    /**
     * Player constructor.
     * @param height Height of the boxes.
     * @param width Width of the boxes.
     * @param path Path of the pawns.
     */
    public Player(String name, int height, int width, Path path) {
        this.name = name;
        coin = 0;
        pawns = new Pawn[4];
        flagBonus = 0;
        flagTraps = 0;
        loseTurn = 0;
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
    
    public void setColor(Color color) { this.color = color; }
    
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
    
    public void setTurn(int turn) { this.turn = turn; }
    
    /**
     * Return a bonus flag
     * @return flag
     */
    public int getFlagBonus(){return flagBonus;}
    
    /**
     * Return a trap flag
     * @return flag
     */
    public int getFlagTraps(){return flagTraps;}

    /**
     * Returns an integer turn loss
     * @return integer
     */
    public int getloseTurn(){return loseTurn;}
    
    /**
     * Modify a bonus flag
     */
    public void setFlagBonus(int flagBonus){this.flagBonus = flagBonus;}
    
    /**
     * Modify a trap flag
     */
    public void setFlagTraps(int flagTraps){this.flagTraps = flagTraps;}

    /**
     * Modifies an integer turn loss
     */
    public void setloseTurn(int loseTurn){this.loseTurn = loseTurn;}
    
    public String getName(){
        return name;
    }
    
    public void setName(String n){
        name = n;
    }
}