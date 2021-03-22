package org.jshadow.Ludo;

import org.jshadow.core.ClassicLogic;
import org.jshadow.core.GameMoves;
import org.jshadow.core.MRILogic;
import org.jshadow.interfaces.GameLogic;
import org.jshadow.utilities.Constants;

import javax.swing.JFrame;

/**
 * Create and run the game.
 *
 * @author JoelS
 * @version 1
 */
public class Game {

    /**
     * Run the game.
     * - Uncomment the line of the game that you
     *   want and comment the other.
     *   - MRILogic for first custom game.
     *   - ClassicLogic for classic game.
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setBounds(10, 10, 1150, 700);
        frame.setTitle(Constants.TitleGame);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameLogic<Integer> logic = new ClassicLogic();
        // GameLogic<Integer> logic = new MRILogic();
        GameMoves gm = new GameMoves(logic);
        gm.setFocusable(true);
        gm.addKeyListener(gm);
        gm.addMouseListener(gm);
        frame.add(gm);
        frame.setVisible(true);
    }
}
