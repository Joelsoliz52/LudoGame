package interfaces;

import java.awt.Color;
import java.awt.Graphics;
import entities.Player;
import entities.Position;
/**
 * Write a description of interface Comodin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public interface Comodin{
    public int aleatori();
    public boolean trustPositionPawn(Player player, int x, int y);
    public Position getPos();
    public void paint(Graphics gr, int h, int w,  int x, int y, Color color);
}
