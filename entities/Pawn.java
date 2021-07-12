package entities;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.Stack;

import interfaces.Path;
import utilities.Tuple;

/**
 * Entity Pawn
 * Logic of pawn.
 *
 * @author JoelS
 * @version 2
 */
public class Pawn implements Serializable {
    private final Path path;
    private Position position;
    private int current;
    private int currentOptional;
    private final int height;
    private final int width;
    private boolean pathOptional;
    private int optional;
    private int numPathOp;
    private boolean flagBonus;
    private boolean freezePawn;
    private final Stack<Integer> pawnMovements = new Stack<>();
    private final Stack<Tuple<Integer, Integer, Boolean>> pawnOptionalMovements = new Stack<>();
    private boolean classicGame = true;
    private boolean UndoMovementOptional;
    /**
     * Pawn constructor.
     * @param height Height of pawn boxes.
     * @param width Width of pawn boxes.
     * @param path Path of the pawn.
     */
    public Pawn(int height, int width, Path path) {
        current = -1;
        this.height = height;
        this.path = path;
        this.position = new Position(-1, -1);
        this.width = width;
        pathOptional = false;
        optional = 0;
        numPathOp = 0;
        flagBonus = false;
        freezePawn = false;
        UndoMovementOptional = true;
        pawnMovements.push(current);
        pawnOptionalMovements.push(new Tuple<>(optional, current, pathOptional));
    }

    /**
     * Draw pawn.
     * @param graphics Drawing controller.
     * @param x Initial X position.
     * @param y Initial Y position.
     * @param player Current player.
     */
    public void draw(Graphics2D graphics, int x, int y, Player player) {

        int temp1;
        int temp2;

        if (current == -1) {
            temp1 = 80 + (height / 2);
            temp2 = 50 + (width / 2);
        } else {
            temp1 = 80;
            temp2 = 50;
            if(!pathOptional) {
                x = path.getAX()[player.getTurn() - 1][current];
                y = path.getAY()[player.getTurn() - 1][current];
                setUndoMovementOptional(true);
            }
            else{
                if (UndoMovementOptional) {
                    x = path.getOptionalAX()[player.getTurn() - 1][current];
                    y = path.getOptionalAY()[player.getTurn() - 1][current];
                    setUndoMovementOptional(true);
                }else {
                    x = path.getOptionalAX()[player.getTurn() - 1][currentOptional];
                    y = path.getOptionalAY()[player.getTurn() - 1][currentOptional];
                }
            }
        }
        position = new Position(x, y);
        graphics.setColor(player.getColor());
        drawNewPosition(graphics, temp1, temp2);

    }

    public void setPathOptional(boolean pathOptional){
        this.pathOptional = pathOptional;
    }

    public boolean getPathOptional(){
        return pathOptional;
    }

    /**
     * Returns Path.
     * @return Path.
     */
    public Path getPath() { return path; }

    /**
     * Draw new position of the pawn.
     * @param graphics Drawing controller.
     * @param temp1 Position X of the pawn box.
     * @param temp2 Position Y of the pawn box.
     */
    private void drawNewPosition(Graphics2D graphics, int temp1, int temp2) {
        int posX = temp1 + 5 + (position.getX() * width);
        int posY = temp2 + 5 + (position.getY() * height);
        graphics.fillOval(posX, posY, width - 10, height - 10);
        graphics.setStroke(new BasicStroke(2));
        graphics.setColor(Color.BLACK);
        graphics.drawOval(posX, posY, width - 10, height - 10);
    }

    /**
     * Returns current path position of the pawn.
     * @return Current path position.
     */
    public int getCurrent(){ return current; }

    /**
     * Set current path position.
     * @param current New current path position.
     */
    public void setCurrent(int current) {
        pawnMovements.push(current);
        this.current = current;
    }

    /**
     * Returns current pathOptional position of the pawn.
     * @return Current pathOptional position.
     */
    public int getCurrentOptional(){ return currentOptional; }

    /**
     * Set current pathOptional position.
     * @param currentOptional New current pathOptional position.
     */
    public void setCurrentOptional(int currentOptional) {
        this.currentOptional = currentOptional;
    }

    /**
     * Returns current position of the pawn.
     * @return Current position.
     */
    public Position getPosition() { return position; }

    public void setOptional(int optional){
        this.optional = optional;
    }

    public int getOptional(){
        return optional;
    }

    /**
     * Set optional path number.
     */
    public int getnumPathOp(){
        return numPathOp;
    }

    /**
     * Set optional path number.
     * @param numPathOp New optional path number.
     */
    public void setnumPathOp(int numPathOp){
        this.numPathOp= numPathOp;
    }

    public boolean getFlag() { return flagBonus; }

    public void setFlag(boolean flagBonus) { this.flagBonus = flagBonus;}

    public boolean getFreezePawn() { return freezePawn; }

    public void setFreezePawn(boolean freezePawn) { this.freezePawn = freezePawn;}

    public void setCheckPoint(int optional, int current, boolean pathOptional) {
        this.classicGame = false;
        pawnOptionalMovements.push(new Tuple<>(optional, current, pathOptional));
    }

    public void undoMovement() {
        setUndoMovementOptional(false);
        if (this.classicGame) {
            this.pawnMovements.pop();
            this.current = this.pawnMovements.lastElement();
            return;
        }

        this.pawnOptionalMovements.pop();
        Tuple<Integer, Integer, Boolean> lastMovement = this.pawnOptionalMovements.lastElement();
        if (lastMovement.getEntityOne() != 2) {
            this.current = lastMovement.getEntityTwo();
            this.pathOptional = false;
        }

        if (lastMovement.getEntityOne() == 2) {
            this.currentOptional = lastMovement.getEntityTwo();
            this.pathOptional = true;
        }
        this.optional = lastMovement.getEntityOne();
    }

    public void setUndoMovementOptional(boolean UndoMovementOptional){
        this.UndoMovementOptional = UndoMovementOptional;
    }
}
