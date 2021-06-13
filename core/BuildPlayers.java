package core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import entities.Pawn;
import entities.Player;
import interfaces.Board;
import interfaces.Path;
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
    public int tam;
    /**
     * BuildPlayers constructor.
     * @param height Height of the boxes.
     * @param width Width of the boxes.
     * @param board Board of the game.
     */
    public BuildPlayers(int tam, int height, int width, Board board) {
        this.board = board;
        this.tam = tam;
        players = new Player[tam];
        Path p = board.getPath();
        addPalyers(players, height, width, p);
        getturnPlayers(players);
    }

    /**
     * Draw pawns.
     * @param graphics Drawing controller.
     */
    public void draw(Graphics2D graphics) {
        int pos = 0;
        int j = 0;
        while(pos < tam){
            Player player = players[pos];
            while(j < 4){
                if(players[pos].getColor() == Color.RED){
                    Pawn pawn = player.getPawns()[j];
                    int posX = board.getInitialX()[2][j];
                    int posY = board.getInitialY()[2][j];
                    pawn.draw(graphics, posX, posY, player);
                }
                if(players[pos].getColor() == Color.BLUE){
                    Pawn pawn = player.getPawns()[j];
                    int posX = board.getInitialX()[0][j];
                    int posY = board.getInitialY()[0][j];
                    pawn.draw(graphics, posX, posY, player);
                }
                if(players[pos].getColor() == Color.GREEN){
                    Pawn pawn = player.getPawns()[j];
                    int posX = board.getInitialX()[1][j];
                    int posY = board.getInitialY()[1][j];
                    pawn.draw(graphics, posX, posY, player);
                }
                if(players[pos].getColor() == Color.YELLOW){
                    Pawn pawn = player.getPawns()[j];
                    int posX = board.getInitialX()[3][j];
                    int posY = board.getInitialY()[3][j];
                    pawn.draw(graphics, posX, posY, player);
                }
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);
                j++;
            }
            pos++;
            j = 0;
        }
        
    }
    
    /**
     * Metodo para agregar nuevos jugadores para modificarlos por color y nombre 
     * @param players
     * @param height
     * @param width
     * @param p
     */
    private void addPalyers(Player[] players, int height, int width, Path p){
        int pos = 0;
        while(pos < players.length){
            players[pos] = new Player("", height, width, p);
            pos++;
        }
    }

    /**
     * Method for add players with an arrayList.
     * @param players
     */
    public void addPlayers(ArrayList<Player> players) {
        this.players = players.toArray(new Player[0]);
    }

    /**
     * Metodo para dar un turno especifico para cada jugador
     * @param players
     */
    private void getturnPlayers(Player[] players){
        int turn = 1;
        int pos = 0;
        if(players != null){
            while(pos < tam){
                players[pos].setTurn(turn);
                turn++;
                pos++;
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