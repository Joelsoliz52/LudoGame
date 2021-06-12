package core;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import entities.Dice;
import entities.Pawn;
import entities.Player;
import entities.Position;
import interfaces.Board;
import interfaces.GameLogic;
import layouts.Boards.MRIBoard;

/**
 * Logic of the First Custom Game.
 *
 * @author JoelS
 * @version 2
 */
public class MRILogic implements GameLogic<Integer> {
    // Fields of the class.
    public BuildPlayers players;
    private Board board;
    private int currentPlayer;
    private final Dice<Integer> dice;
    private int flag;
    private int kill;
    public int tam;
    private int pos;
    /**
     * MRILogic constructor.
     */
    public MRILogic(MRIBoard board) {
        if (board == null) {
            this.board = board = new MRIBoard(new Position(80, 50), new Color[tam]);
        } else {
            this.board = board;
        }

        currentPlayer = 1;
        dice = new Dice<>("int");
        dice.content = 0;
        flag = 0;
        kill = 0;
        players = new BuildPlayers(tam, board.getHeight(), board.getWidth(), board);
    }

    /**
     * Returns dice.
     * @return Dice.
     */
    public Dice<Integer> getDice() { return dice; }

    /**
     * Returns flag.
     * @return If there is an movement pending returns 1, otherwise 0.
     */
    public int getFlag() { return flag; }

    /**
     * OnKeyPressed logic.
     */
    public void onKeyPressed() {
        Player player = players.getPlayer(currentPlayer);
        for (int i = 0; i < 4; i++) {
            int current = player.getPawns()[i].getCurrent();

            if (current != -1 && current != 74 && (current + dice.content) <= 74) {
                flag = 1;
                break;
            }
        }

        if (flag == 0 && dice.content == 6) {
            for (int i = 0; i < 4; i++) {
                int current = player.getPawns()[i].getCurrent();

                if (current == -1) {
                    flag = 1;
                    break;
                }
            }
        }
    }

    /**
     * OnMouseClicked logic.
     * @param x X position of the click.
     * @param y Y position of the click.
     */
    public void onMouseClicked(int x, int y) {
        Player player = players.getPlayer(currentPlayer);

        if (dice.content == 6) {
            int value = movePawn(player, x, y);

            if (value == -1) {
                moveInitPawn(player, x, y);
            }
        } else {
            movePawn(player, x, y);
        }
    }

    /**
     * Ask if the pawn kill another pawn after his move.
     * @param i Player different to current player.
     * @param k Kill flag.
     * @param current Current pawn path position.
     * @return Kill flag.
     */
    private int killPawn(int i, int k, int current) {
        if (i != currentPlayer) {
            for (int j = 0; j < 4; j++) {
                int tem1 = board.getPath().getAX()[currentPlayer - 1][current];
                int tem2 = board.getPath().getAY()[currentPlayer - 1][current];
                Pawn pawn1 = players.getPlayer(i).getPawns()[j];
                Position position = pawn1.getPosition();

                if (position.equals(new Position(tem1, tem2))) {
                    pawn1.setCurrent(-1);
                    kill = 1;
                    k = 1;
                    break;
                }
            }
        }

        return k;
    }

    /**
     * Move a pawn from initial position.
     * @param player Current player.
     * @param x X position.
     * @param y Y position.
     */
    private void moveInitPawn(Player player, int x, int y) {

        for (int i = 0; i < 4; i++) {
            Pawn pawn = player.getPawns()[i];

            if (pawn.getCurrent() == -1) {
                // Select pawn is not working fine:
                // * Specific position click is mandatory for move the pawn
                Position posPawn = pawn.getPosition();
                Position[] possiblePositions = {
                        new Position(x, y),
                        new Position(x + 1, y),
                        new Position(x, y + 1),
                        new Position(x + 1, y + 1)
                };

                for (Position pos: possiblePositions) {
                    if (posPawn.equals(pos)) {
                        pawn.setCurrent(0);
                        flag = 0;
                        break;
                    }
                }
            }
        }
    }

    /**
     * Try to move a pawn in the board.
     * @param player Current player.
     * @param x X position.
     * @param y Y position.
     * @return Returns the value of the pawn to move if it exists, otherwise returns -1.
     */
    public int movePawn(Player player, int x, int y) {
        int value = -1;

        for (int i = 0; i < 4; i++) {
            Position posPawn = player.getPawns()[i].getPosition();
            int current = player.getPawns()[i].getCurrent();

            if (posPawn.equals(new Position(x, y)) && (current + dice.content) <= 74 && current != -1) {
                value = i;
                flag = 0;
                break;
            }
        }

        if (value != -1) {
            Pawn pawn = player.getPawns()[value];
            pawn.setCurrent(pawn.getCurrent() + dice.content);
            int current = pawn.getCurrent();
            int k = 0;
            pawn.setCurrentOptional(pawn.getCurrentOptional()+dice.content);
            if (current == 74)
                player.incrementCoin();

            if((dice.content==2||dice.content==4||dice.content==6)&& pawn.getOptional()==1){
                optionalInitial(pawn,current);
                current = pawn.getCurrent();
                pawn.setOptional(2);
            }else if (pawn.getOptional()==1){
                pawn.setOptional(0);
            }
            if((pawn.getCurrentOptional() > 8)&& pawn.getOptional()==2){
                optionalFinal(pawn);
                current = pawn.getCurrent();
                pawn.setOptional(0);
            }

            if(((current == 3)||(current == 20)||(current == 37)||(current == 54))&&pawn.getOptional()==0){
                pawn.setOptional(1);
            }

            if(((current % 17) != 0) && (current < 67) && (!pawn.getPathOptional())){
                for (int i = 1; i <= tam; i++) {
                    k = killPawn(i, k, current);
                    if (k == 1){
                        pawn.setPathOptional(false);
                        break;}
                }
            }
        }

        return value;
    }

    /**
     * method to enter optional path.
     * @param pawn Current pawn.
     * @param current Current pawn path position.
     */
    private void optionalInitial(Pawn pawn, int current){
        pawn.setCurrentOptional(0);
        pawn.setPathOptional(true);
        int j = 0;
        if(current == (20 + dice.content)){j = 1; pawn.setnumPathOp(1);}
        else if (current == (37 + dice.content)){j = 2; pawn.setnumPathOp(2);}
        else if(current == (54 + dice.content)){j = 3; pawn.setnumPathOp(3);}
        for(int i = 0; i < 4; i++){
            if(j == i){
                pawn.setCurrent((9 * i) + dice.content);
                pawn.setCurrentOptional(dice.content);
                break;
            }
        }
    }

    /**
     * method to get out of the optional path.
     * @param pawn Current pawn.
     */
    private void optionalFinal(Pawn pawn){
        pawn.setPathOptional(false);
        for(int i = 0; i < 4; i++){
            if(pawn.getnumPathOp() == i){
                int u =(dice.content - (dice.content - ( - 8 + pawn.getCurrentOptional()))) + (10 + (i*17));
                pawn.setCurrent(u);
                break;
            }
        }
        pawn.setCurrentOptional(0);
    }

    /**
     * Paint the current view.
     * @param graphics Drawing controller.
     */
    public void paint(Graphics2D graphics) {
        board.draw(graphics);
        players.draw(graphics);
        Player player = players.getPlayer(currentPlayer);

        if (player.getCoin() == 4) {
            graphics.setColor(Color.WHITE);
            graphics.fillRect(680, 100, 380, 130);
            graphics.setColor(player.getColor());
            graphics.setFont(new Font("serif", Font.BOLD, 40));
            graphics.drawString( players.players[pos].getName()  + " wins.", 690, 100);
            graphics.drawString( "Congratulations, ", 690, 150);
            graphics.drawString( "you're the boss.", 690, 200);
            currentPlayer = 1;
            board = new MRIBoard(new Position(80, 50), new Color[tam]);
            players = new BuildPlayers(tam, board.getHeight(), board.getWidth(), board);
            dice.content = 0;
            flag = 0;
        } else if (dice.content != 0) {
            if(pos == tam){
                pos = 0;
            }
            graphics.setColor(Color.WHITE);
            graphics.fillRect(680, 58, 250, 150);
            graphics.setColor(player.getColor());
            graphics.setFont(new Font("serif", Font.BOLD, 40));
            graphics.drawString(players.players[pos].getName()+ " " +"Your", 690, 100);
            graphics.drawString( "Number on " , 690, 150);
            graphics.drawString( "dice is " + dice.content, 690, 200);
        }

        if (flag == 0 && dice.content != 0 && dice.content != 6 && kill == 0) {
            currentPlayer = (currentPlayer + 1) % tam;

            if (currentPlayer == 0){
                currentPlayer = tam;
            }
            pos++;
        }

        kill = 0;
    }

    @Override
    public void doubleMouseClicked(int x, int y) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean getdoubleClicked() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setdoubleClicked(boolean doubleClicked) {
        // TODO Auto-generated method stub

    }
}