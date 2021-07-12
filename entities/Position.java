package entities;

import java.io.Serializable;

/**
 * Entity Position
 * Logic of position.
 *
 * @author JoelS
 * @version 1
 */
public class Position implements Serializable {
    // Fields of the class
    private final int x;
    private final int y;
    private boolean flagTraps;
    
    /**
     * Position constructor.
     * @param x Position in X.
     * @param y Position in Y.
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
        flagTraps = false;
    }

    /**
     * Returns X position.
     * @return X position.
     */
    public int getX() { return x; }

    /**
     * Returns Y positions.
     * @return Y position.
     */
    public int getY() { return y; }

    /**
     * Compare if one position is equal to another position.
     * @param object Object to compare.
     * @return If both positions are equals returns true, otherwise returns false.
     */
    public boolean equals (Object object) {
        if(object instanceof Position) {
            Position position = (Position) object;
            return this.x == position.getX() && this.y == position.getY();
        } else {
            return false;
        }
    }
    
    /**
     * Returns the boolean value of the trap flag
     * @return boolean
     */
    public boolean getFlagTraps(){return flagTraps;}
    
    /**
     * Modify the value of the trap flag if necessary
     * @param flagTraps trap flag
     */
    public void setFlagTraps(boolean flagTraps){this.flagTraps = flagTraps;}
}
