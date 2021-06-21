package core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import entities.Dice;
import entities.Pawn;
import entities.Player;
import entities.Position;
import interfaces.Board;
import interfaces.GameCallback;
import interfaces.GameLogic;
import layouts.Boards.ClassicBoard;
/**
 * Logic of the Classic Game.
 *
 * @author JoelS
 * @version 1
 */
public class ClassicLogic implements GameLogic<Integer> {
    // Fields of the class.
    public BuildPlayers players;
    private Board board;
    private int currentPlayer;
    private final Dice<Integer> dice;
    private int flag;
    private int kill;
    private int pos;
    public int tam;
    private GameCallback callback;

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
        pos = 0;
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
            //arreglar
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
                //arreglar
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
                Position posPawn = pawn.getPosition();
                Position pos1 = new Position(x, y);
                Position pos2 = new Position(x + 1, y);

                if (posPawn.equals(pos1) || posPawn.equals(pos2)) {
                    pawn.setCurrent(0);
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
            int current = pawn.getCurrent();
            int k = 0;

            if (current == 56)
                player.incrementCoin();

            // safeZone: 0, 8, 13, 21, 26, 34, 39, 47
            //arreglar
            if ((current % 13) != 0 && (current % 13) != 8 && current < 51) {
                for (int i = 1; i <= tam; i++) {
                   //arreglar
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
            graphics.fillRect(590, 100, 380,180);
            graphics.setColor(player.getColor());
            graphics.setFont(new Font("serif", Font.BOLD, 40));
            graphics.drawString("Ganaste " + player.getName() + ".", 600, 150);
            graphics.drawString("Felicitaciones.", 600, 200);

            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (this.callback != null)
                this.callback.onRestart();
        } else if(dice.content != 0) {
            if(pos == tam){
                pos = 0;
            }
            graphics.setColor(Color.WHITE);
            graphics.fillRect(590, 100, 360,120);
            graphics.setColor(player.getColor());
            graphics.setFont(new Font("serif", Font.BOLD, 40));
            graphics.drawString(players.players[pos].getName() + " " + "tu numero de", 600, 150);
            graphics.drawString("dado es " + dice.content, 600, 200);
        }
        if(flag == 0 && dice.content != 0 && dice.content != 6 && kill == 0) {
            currentPlayer = (currentPlayer + 1) % tam;
            if(currentPlayer == 0){
                currentPlayer = tam;
            }
            pos++;
        }
        kill=0;
    }

    public void setGameCallback(GameCallback callback) {
        this.callback = callback;
    }

    public BuildPlayers getBuildPlayers(){
        return players;
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