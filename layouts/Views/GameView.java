package layouts.Views;

import core.GameMoves;
import interfaces.GameLogic;
import utilities.Constants;
import utilities.GameModes;

import javax.swing.JFrame;
import java.awt.Dimension;

public class GameView extends View {
    private static GameView gameView;
    private final GameMoves gm;

    private GameView(GameLogic<Integer> logic, Dimension size) {
        init();
        gm = new GameMoves(logic, size);
        this.add(gm);
        this.setup(null, size);
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

    public static GameView getInstance(GameLogic<Integer> logic, GameModes gameMode) {
        if (gameView == null) {
            gameView = new GameView(logic, GameView.getSizeByMode(gameMode));
        }

        return gameView;
    }

    public static Dimension getSizeByMode(GameModes gameMode) {
        switch (gameMode) {
            case CLASSIC:
                return new Dimension(1150, 700);
            case MRI:
            case RUN:
            default:
                return new Dimension(1200, 700);
        }
    }
}
