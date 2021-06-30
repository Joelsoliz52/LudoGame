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
    private boolean flagAliance;
    private int loseTurn;
    /**
     * Player constructor.
     * @param color Player color.
     * @param height Height of the boxes.
     * @param turn Player turn.
     * @param width Width of the boxes.
     * @param path Path of the pawns.
     */
    public Player(String name, int height, int width, Path path) {
        this.name = name;
        coin = 0;
        pawns = new Pawn[4];
        flagBonus = 0;
        flagTraps = 0;
        flagAliance = false;
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
     * Retorna una bandera de bonus
     * @return
     */
    public int getFlagBonus(){return flagBonus;}
    
    /**
     * Retorna una bandera de trampa
     * @return
     */
    public int getFlagTraps(){return flagTraps;}
    
    /**
     * Retorna una bandera de alianza
     * @return
     */
    public boolean getFlagAliance(){return flagAliance;}
    
    /**
     * Retorna un entero de perdida de turno
     * @return
     */
    public int getloseTurn(){return loseTurn;}
    
    /**
     * Modifica una bandera de bonus
     * @return
     */
    public void setFlagBonus(int flagBonus){this.flagBonus = flagBonus;}
    
    /**
     * Modifica una bandera de trampa
     * @return
     */
    public void setFlagTraps(int flagTraps){this.flagTraps = flagTraps;}
    
    /**
     * Modifica una bandera de alianza
     * @return
     */
    public void setFlagAliance(boolean flagAliance){this.flagAliance = flagAliance;}
    
    /**
     * Modifica un entero de perdida de turno
     * @return
     */
    public void setloseTurn(int loseTurn){this.loseTurn = loseTurn;}
    
    public String getName(){
        return name;
    }
    
    public void setName(String n){
        name = n;
    }
}