package core;

 

//import org.jshadow.interfaces.GameLogic;

import javax.swing.JPanel;

import components.BasicButton;
import components.utils.enums.ButtonTypes;
import interfaces.GameCallback;
import interfaces.GameLogic;
import utilities.GameModes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 * GameMoves
 * Controller of game logic.
 *
 * @author JoelS
 * @version 1
 */
public class GameMoves extends JPanel implements KeyListener, ActionListener, MouseListener, Serializable {
    // Fields of the class.
    private static final long serialVersionUID = 1L;
    private GameLogic<Integer> logic;
    private GameModes gameMode;
    private HashMap<Color, String> players;
    private transient GameCallback callback;
    public GameMoves(GameLogic<Integer> logic) {
        init(new Dimension(1200, 700));
        this.logic = logic;
    }

    public GameMoves(GameModes gameMode, HashMap<Color, String> players) {
        this(gameMode, players, new Dimension(1200, 700));
    }

    /**
     * GameMoves constructor.
     * @param gameMode Logic of the game.
     */
    public GameMoves(GameModes gameMode, HashMap<Color, String> players, Dimension size) {
        this.gameMode  = gameMode;
        this.players = players;
        this.initializeGame();
        this.init(size);
    }

    private void init(Dimension size) {
        setFocusTraversalKeysEnabled(false);
        requestFocus();
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
    }

    /**
     * Key pressed logic.
     * @param e Key event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER && logic.getFlag() == 0) {
            logic.getDice().throwDice();
            repaint();
            logic.onKeyPressed();
            logic.passTurn();
        }
    }

    /**
     * Mouse click logic.
     * @param e Mouse event.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if(logic.getFlag() == 1) {
            if (e.getClickCount() == 1) {
                int x = (e.getX() - 80) / 30;
                int y = (e.getY() - 50) / 30;
                logic.onMouseClicked(x, y);
                repaint();
                logic.passTurn();
            }
        }else if(logic.getdoubleClicked()){
            if (e.getClickCount() == 2) {
                int x = (e.getX()-80) / 30;
                int y = (e.getY()-50) / 30;
                logic.setdoubleClicked(false);
                logic.doubleMouseClicked(x, y);
                JOptionPane.showMessageDialog(null, "Se ha producido un doble click en "+ x +", "+ y);
                logic.passTurn();
            }
        }
    }
    /**
     * Paint the current view.
     * @param graphics Drawing controller.
     */
    @Override
    public void paint(Graphics graphics) {
        logic.paint((Graphics2D) graphics);
    }

    public void setGameCallback(GameCallback callback) {
        this.callback = callback;
    }

    public void onRestart() {
        this.callback.onRestart();
    }

    public void initializeGame() {
        this.logic = new BoardFactory(this.gameMode, this.players).getBoard();
        this.logic.setGameCallback((GameCallback & Serializable) this::onRestart);
    }

    // Auto-generated method
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    // Auto-generated method
    @Override
    public void keyReleased(KeyEvent e) {
    }

    // Auto-generated method
    @Override
    public void keyTyped(KeyEvent e) {
    }

    // Auto-generated method
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    // Auto-generated method
    @Override
    public void mouseExited(MouseEvent e) {
    }

    // Auto-generated method
    @Override
    public void mousePressed(MouseEvent e) {
    }

    // Auto-generated method
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    public void undoMovement() {
        this.logic.undoMovement();
        repaint();
    }
}
