package layouts.Views;

import components.BasicButton;
import components.ImagePanel;
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
import java.io.*;
import java.net.URL;
import java.util.HashMap;

public class GameView extends View {
    private static GameView gameView;
    private GameMoves gm;
    private final BasicButton menuButton = new BasicButton("MENU", ButtonTypes.INVERTED);
    private final BasicButton restartButton = new BasicButton("Reiniciar", ButtonTypes.INVERTED);
    private final BasicButton saveGameButton = new BasicButton("Guardar juego", ButtonTypes.INVERTED);
    private final BasicButton loadGameButton = new BasicButton("Cargar juego", ButtonTypes.INVERTED);
    private final ImagePanel background = new ImagePanel("");
    private MusicBackgroundV2 musicBackground;
    private GameModes gameMode;
    private final HashMap<Color, String> players;

    private GameView(GameModes gameMode, HashMap<Color, String> players) {
        this.gameMode = gameMode;
        this.players = players;
        this.musicBackground = new MusicBackgroundV2(Helper.getFileMusic(gameMode));

        this.init();
        this.setupButtons(gameMode);
        this.add(this.menuButton);
        this.add(this.restartButton);
        this.add(this.saveGameButton);
        this.add(this.loadGameButton);

        this.restartButton.setVisible(false);
        this.initializeGameMoves();
        this.setBackgroundByGameMode();
        this.setup(background, this.getSizeByMode(), musicBackground);
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

    private void setBackgroundByGameMode() {
        switch (gameMode) {
            case CLASSIC:
                background.setBackground("classicBoardBackground.jpg");
                break;
            case MRI:
                background.setBackground("mriBoardBackground.jpg");
                break;
            case RUN:
                background.setBackground("runBoardBackground.jpg");
                break;
            default:
                break;
        }
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
                saveGameButton.setup(600, 580, 150, 30, Color.BLACK);
                loadGameButton.setup(600, 10, 150, 30, Color.BLACK);
                break;
            case MRI:
            case RUN:
            default:
                menuButton.setup(1045,580, 100, 30, Color.BLACK);
                restartButton.setup(875, 580, 150, 30, Color.BLACK);
                saveGameButton.setup(700, 580, 150, 30, Color.BLACK);
                loadGameButton.setup(700, 10, 150, 30, Color.BLACK);
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
        this.saveGameButton.onClick(e -> {
            try {
                // Guardar juego - sobreescribir juego
                // leemos string - guardar
                URL fileURL = getClass().getResource("/resources/savedGames");
                String path = fileURL.toString().replace("file:/", "").replace("/", "\\");
                FileOutputStream fileToSave = new FileOutputStream(path+"\\gameOne.obj");
                ObjectOutputStream outputStream = new ObjectOutputStream(fileToSave);
                outputStream.writeObject(this.gameMode);
                outputStream.writeObject(this.gm);
                outputStream.close();
                fileToSave.close();
                System.out.println("Game saved");
            } catch (IOException ioException) {
                ioException.printStackTrace();
                System.out.println("Game don't saved");
            }
        });
        this.loadGameButton.onClick(e -> {
            try {
                URL fileURL = getClass().getResource("/resources/savedGames/gameOne.obj");
                String path = fileURL.toString().replace("file:/", "").replace("/", "\\");
                FileInputStream fileToLoad = new FileInputStream(path);
                ObjectInputStream inputStream = new ObjectInputStream(fileToLoad);
                this.remove(this.gm);
                this.remove(this.background);
                this.gameMode = (GameModes) inputStream.readObject();
                this.gm = (GameMoves) inputStream.readObject();
                this.gm.setGameCallback(() -> this.restartButton.setVisible(true));
                this.add(this.gm);
                this.musicBackground = new MusicBackgroundV2(Helper.getFileMusic(this.gameMode));
                this.setupButtons(this.gameMode);
                this.setup(this.background, this.getSizeByMode(), this.musicBackground);
                inputStream.close();
                fileToLoad.close();
                System.out.println("Game loaded");
            } catch (Exception ioException) {
                ioException.printStackTrace();
                System.out.println("Game don't loaded");
            }
            repaint();
        });
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
