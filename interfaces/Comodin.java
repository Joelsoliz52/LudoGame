package interfaces;

import entities.Player;
import entities.Position;

public interface Comodin{
    int aleatori();
    boolean trustPositionPawn(Player player, int x, int y);
    Position getPos();
}
