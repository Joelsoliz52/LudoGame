package core.classic;

import core.BuildPlayers;
import entities.Dice;
import entities.Pawn;
import entities.Player;
import entities.Position;
import interfaces.Board;
import interfaces.GameCallback;
import interfaces.GameLogic;
import layouts.Boards.ClassicBoard;
import utilities.Tuple;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.Stack;

/**
 * Logic of the Classic Game.
 *
 * @author JoelS
 * @version 1
 */
public class ClassicLogic implements GameLogic<Integer>, Serializable {
    // Fields of the class.
    public BuildPlayers players;
    private final Board board;
    private int currentPlayer;
    private final Dice<Integer> dice;
    private int flag;
    private int kill;
    public int tam;
    private GameCallback callback;
    private boolean passTurnFlag = false;
    private final Stack<Tuple<Integer, Pawn, Pawn>> stack = new Stack<>();

    /**
     * ClassicLogic constructor.
     */
    public ClassicLogic(ClassicBoard board) {
        if (board == null) {
            this.board = new ClassicBoard(new Position(80, 50), new Color[tam]);
        } else {
            this.board = board;
        }
        currentPlayer = 1;
        dice = new Dice<>("int");
        dice.content = 0;
        flag = 0;
        kill = 0;
        players = new BuildPlayers(tam, this.board.getHeight(), this.board.getWidth(), this.board);
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
        for (int i = 0; i < 4; i++) {
            int current = players.getPlayer(currentPlayer).getPawns()[i].getCurrent();

            if (current != -1 && current != 56 && (current + dice.content) <= 56) {
                flag = 1;
                break;
            }
        }
        if (flag == 0 && dice.content == 6) {
            for (int i = 0; i < 4; i++) {
                int current = players.getPlayer(currentPlayer).getPawns()[i].getCurrent();

                if (current == -1) {
                    flag=1;
                    break;
                }
            }
        }

        if (flag == 0) {
            this.stack.push(new Tuple<>(currentPlayer, null, null));
        }
    }

    /**
     * OnMouseClicked logic.
     * @param x X position of the click.
     * @param y Y position of the click.
     */
    public void onMouseClicked(int x, int y) {
        Player player = players.getPlayer(currentPlayer);

        if(dice.content == 6) {
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
                    stack.push(new Tuple<>(currentPlayer, null, pawn1));
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
                Position posPawn = pawn.getPosition();
                Position pos1 = new Position(x, y);
                Position pos2 = new Position(x + 1, y);

                if (posPawn.equals(pos1) || posPawn.equals(pos2)) {
                    pawn.setCurrent(0);
                    stack.push(new Tuple<>(currentPlayer, pawn, null));
                    flag = 0;
                    break;
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
    private int movePawn(Player player, int x, int y) {
        int value = -1;

        for (int i = 0; i < 4; i++) {
            Position posPawn = player.getPawns()[i].getPosition();
            int current = player.getPawns()[i].getCurrent();

            if (posPawn.equals(new Position(x, y)) && (current + dice.content) <= 56 && current != -1) {
                value = i;
                flag = 0;
                break;
            }
        }

        if (value != -1) {
            Pawn pawn = player.getPawns()[value];
            pawn.setCurrent(pawn.getCurrent() + dice.content);
            stack.push(new Tuple<>(currentPlayer, pawn, null));
            int current = pawn.getCurrent();
            int k = 0;

            if (current == 56)
                player.incrementCoin();

            if ((current % 13) != 0 && (current % 13) != 8 && current < 51) {
                for (int i = 1; i <= tam; i++) {

                   k = killPawn(i, k, current);

                    if (k == 1)
                        break;
                }
            }
        }

        return value;
    }

    /**
     * Paint the current view.
     * @param graphics Drawing controller.
     */
    public void paint(Graphics2D graphics) {
        board.draw(graphics);
        players.draw(graphics);
        Player player = players.getPlayer(currentPlayer);
        if(player.getCoin() == 4) {
            graphics.setColor(Color.WHITE);
            graphics.fillRect(590, 100, 380,160);
            graphics.setColor(player.getColor());
            graphics.setFont(new Font("serif", Font.BOLD, 40));
            graphics.drawString("Ganaste " + player.getName() + ".", 600, 150);
            graphics.drawString("Felicitaciones.", 600, 200);

            if (this.callback != null)
                this.callback.onRestart();

            return;
        } else if(dice.content != 0) {
            graphics.setColor(Color.WHITE);
            graphics.fillRect(590, 100, 360, 120);
            graphics.setColor(player.getColor());
            graphics.setFont(new Font("serif", Font.BOLD, 40));
            graphics.drawString(player.getName() + " " + "tu numero de", 600, 150);
            graphics.drawString("dado es " + dice.content, 600, 200);
        }

        if (passTurnFlag) {
            if(flag == 0 && dice.content != 6 && dice.content != 0 && kill == 0) {
                currentPlayer = (currentPlayer + 1) % tam;
                if(currentPlayer == 0){
                    currentPlayer = tam;
                }
            }
            kill=0;
        }

        passTurnFlag = false;
    }

    public void setGameCallback(GameCallback callback) {
        this.callback = callback;
    }

    @Override
    public void passTurn() {
        passTurnFlag = true;
    }

    @Override
    public void undoMovement() {
        if (!stack.empty()) {
            Tuple<Integer, Pawn, Pawn> lastMovement = this.stack.pop();

            currentPlayer = lastMovement.getEntityOne();

            if (lastMovement.getEntityTwo() == null && lastMovement.getEntityThree() == null) {
                return;
            }

            if (lastMovement.getEntityTwo() == null) {
                Tuple<Integer, Pawn, Pawn> possibleLastMovement = this.stack.pop();
                possibleLastMovement.getEntityTwo().undoMovement();
                lastMovement.getEntityThree().undoMovement();
            } else {
                lastMovement.getEntityTwo().undoMovement();
            }
        }
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

    @Override
    public boolean getnewPositionPawn(){
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setnewPositionPawn(boolean newPositionPawn){
        // TODO Auto-generated method stub
    }
}