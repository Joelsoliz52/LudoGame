package org.jshadow.core;

import org.jshadow.entities.Pawn;
import org.jshadow.entities.Player;
import org.jshadow.interfaces.Board;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Build Players controller.
 *
 * @author JoelS
 * @version 1
 */
public class BuildPlayers {
    // Fields of the class.
    public Player[] players;
    private final Board board;

    /**
     * BuildPlayers constructor.
     * @param height Height of the boxes.
     * @param width Width of the boxes.
     * @param board Board of the game.
     */
    public BuildPlayers(int height, int width, Board board) {
        this.board = board;
        players = new Player[4];
        players[0] = new Player(Color.BLUE, height, 1, width, board.getPath());
        players[1] = new Player(Color.GREEN, height, 2, width, board.getPath());
        players[2] = new Player(Color.RED, height, 3, width, board.getPath());
        players[3] = new Player(Color.YELLOW, height, 4, width, board.getPath());
    }

    /**
     * Draw pawns.
     * @param graphics Drawing controller.
     */
    public void draw(Graphics2D graphics) {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                Player player = players[i];
                Pawn pawn = player.getPawns()[j];
                int posX = board.getInitialX()[i][j];
                int posY = board.getInitialY()[i][j];
                pawn.draw(graphics, posX, posY, player);
            }
        }
    }

    /**
     * Search a player by turn.
     * @param turn Player turn.
     * @return Returns the player if it exists, otherwise returns null.
     */
    public Player getPlayer(int turn) {
        for(Player player : players) {
            if (player.getTurn() == turn) {
                return player;
            }
        }

        return null;
    }
}
