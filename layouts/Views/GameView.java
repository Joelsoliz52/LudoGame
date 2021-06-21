package layouts.Views;

import components.BasicButton;
import components.utils.enums.ButtonTypes;
import core.GameMoves;
import interfaces.GameLogic;
import utilities.Constants;
import utilities.GameModes;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;

public class GameView extends View {
    private static GameView gameView;
    private final GameMoves gm;
    private BasicButton menuButton = new BasicButton("MENU", ButtonTypes.INVERTED);

    private GameView(GameLogic<Integer> logic, Dimension size, GameModes gameMode) {
        init();
        gm = new GameMoves(logic, size);
        this.setupMenuButton(gameMode);
        this.add(menuButton);
        this.add(gm);
        gm.setOpaque(false);
        this.setup(null, size, null);
        this.setActions();
        this.repaint();
    }

    private void init() {
        this.setTitle(Constants.TitleGame);
        this.setUndecorated(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setupMenuButton(GameModes gameMode) {
        switch (gameMode) {
            case CLASSIC:
                menuButton.setup(960,580, 100, 30, Color.BLACK);
                break;
            case MRI:
            case RUN:
            default:
                menuButton.setup(1045,600, 100, 30, Color.BLACK);
                break;
        }
    }

    private void setActions() {
        this.gm.setFocusable(true);
        this.gm.addKeyListener(this.gm);
        this.gm.addMouseListener(this.gm);
        this.menuButton.onClick(e -> {
            this.setNextView(ChooseModeView.getInstance());
            this.goNext();
            gameView.dispose();
            gameView = null;
        });
    }

    public static GameView getInstance(GameLogic<Integer> logic, GameModes gameMode) {
        if (gameView == null) {
            gameView = new GameView(logic, GameView.getSizeByMode(gameMode), gameMode);
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
