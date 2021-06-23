package layouts.Views;

import components.BasicButton;
import components.MusicBackgroundV2;
import components.utils.enums.ButtonTypes;
import core.BoardFactory;
import core.GameMoves;
import interfaces.GameCallback;
import interfaces.GameLogic;
import utilities.Constants;
import utilities.GameModes;
import utilities.Helper;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;

public class GameView extends View {
    private static GameView gameView;
    private GameMoves gm;
    private final BasicButton menuButton = new BasicButton("MENU", ButtonTypes.INVERTED);
    private final BasicButton restartButton = new BasicButton("Reiniciar", ButtonTypes.INVERTED);
    private final GameModes gameMode;
    private final HashMap<Color, String> players;

    private GameView(GameModes gameMode, HashMap<Color, String> players) {
        MusicBackgroundV2 musicBackground = new MusicBackgroundV2(Helper.getFileMusic(gameMode));
        this.gameMode = gameMode;
        this.players = players;

        this.init();
        this.setupButtons(gameMode);
        this.add(this.menuButton);
        this.add(this.restartButton);

        this.restartButton.setVisible(false);
        this.initializeGameMoves();
        this.setup(null, this.getSizeByMode(), musicBackground);
        this.setActions();
        this.repaint();
    }

    private void init() {
        this.setTitle(Constants.TitleGame);
        this.setUndecorated(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initializeGameMoves() {
        this.gm = new GameMoves(this.gameMode, this.players, this.getSizeByMode());
        this.gm.setGameCallback(() -> this.restartButton.setVisible(true));
        add(this.gm);
        this.gm.setOpaque(false);
        this.gm.setFocusable(true);
        this.gm.addKeyListener(this.gm);
        this.gm.addMouseListener(this.gm);
    }

    private void onRestart() {
        this.gm.initializeGame();
        this.restartButton.setVisible(false);
        repaint();
    }

    private void setupButtons(GameModes gameMode) {
        switch (gameMode) {
            case CLASSIC:
                menuButton.setup(960,580, 100, 30, Color.BLACK);
                restartButton.setup(800, 580, 150, 30, Color.BLACK);
                break;
            case MRI:
            case RUN:
            default:
                menuButton.setup(1045,600, 100, 30, Color.BLACK);
                restartButton.setup(875, 580, 150, 30, Color.BLACK);
                break;
        }
    }

    private void setActions() {
        this.menuButton.onClick(e -> {
            this.setNextView(ChooseModeView.getInstance());
            this.goNext();
            gameView.dispose();
            gameView = null;
        });
        this.restartButton.onClick(e -> this.onRestart());
    }

    private Dimension getSizeByMode() {
        switch (this.gameMode) {
            case CLASSIC:
                return new Dimension(1150, 700);
            case MRI:
            case RUN:
            default:
                return new Dimension(1200, 700);
        }
    }

    public static GameView getInstance(GameModes gameMode, HashMap<Color, String> playersColors) {
        if (gameView == null) {
            gameView = new GameView(gameMode, playersColors);
        }

        return gameView;
    }
}
