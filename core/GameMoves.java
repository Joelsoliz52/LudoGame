package core;

 

//import org.jshadow.interfaces.GameLogic;

import javax.swing.JPanel;

import interfaces.GameLogic;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
/**
 * GameMoves
 * Controller of game logic.
 *
 * @author JoelS
 * @version 1
 */
public class GameMoves extends JPanel implements KeyListener, ActionListener, MouseListener {
    // Fields of the class.
    private static final long serialVersionUID = 1L;
    private final GameLogic<Integer> logic;
    
    
    /**
     * GameMoves constructor.
     * @param logic Logic of the game.
     */
    public GameMoves(GameLogic<Integer> logic) {
        setFocusTraversalKeysEnabled(false);
        requestFocus();
        this.logic = logic;
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
            repaint();}
        }else if(logic.getdoubleClicked()){
            if (e.getClickCount() == 2) {
                int x = (e.getX()-80) / 30;
                int y = (e.getY()-50) / 30;
                logic.setdoubleClicked(false);
                logic.doubleMouseClicked(x, y);
                JOptionPane.showMessageDialog(null, "Se ha producido un doble click en"+ x +", "+ y);
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
    
}
