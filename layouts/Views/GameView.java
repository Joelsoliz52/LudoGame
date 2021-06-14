package layouts.Views;

import core.GameMoves;
import interfaces.GameLogic;
import utilities.Constants;

import javax.swing.JFrame;
import java.awt.Dimension;

public class GameView extends View {
    private static GameView gameView;
    private final GameMoves gm;

    private GameView(GameLogic<Integer> logic) {
        init();
        gm = new GameMoves(logic);
        this.add(gm);
        this.setup(null, new Dimension(1200, 700));
        this.setActions();
        this.repaint();
    }

    private void init() {
        this.setTitle(Constants.TitleGame);
        this.setUndecorated(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setActions() {
        this.gm.setFocusable(true);
        this.gm.addKeyListener(this.gm);
        this.gm.addMouseListener(this.gm);
    }

    public static GameView getInstance(GameLogic<Integer> logic) {
        if (gameView == null) {
            gameView = new GameView(logic);
        }

        return gameView;
    }
}
