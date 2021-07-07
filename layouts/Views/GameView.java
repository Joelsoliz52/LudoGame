package layouts.Views;

import components.BasicButton;
import components.ImagePanel;
import components.MusicBackgroundV2;
import components.utils.enums.ButtonTypes;
import core.GameMoves;
import utilities.Constants;
import utilities.GameModes;
import utilities.Helper;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.io.*;
import java.util.HashMap;

public class GameView extends View {
    private static GameView gameView;
    private GameMoves gm;
    private final BasicButton menuButton = new BasicButton("MENU", ButtonTypes.INVERTED);
    private final BasicButton restartButton = new BasicButton("Reiniciar", ButtonTypes.INVERTED);
    private final BasicButton saveGameButton = new BasicButton("Guardar juego", ButtonTypes.INVERTED);
    private final BasicButton loadGameButton = new BasicButton("Cargar juego", ButtonTypes.INVERTED);
    private final BasicButton undoButton = new BasicButton("Retroceder", ButtonTypes.INVERTED);

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
        this.add(this.undoButton);

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
                this.menuButton.setup(960,580, 100, 30, Color.BLACK);
                this.restartButton.setup(800, 580, 150, 30, Color.BLACK);
                this.saveGameButton.setup(600, 580, 150, 30, Color.BLACK);
                this.loadGameButton.setup(600, 10, 150, 30, Color.BLACK);
                this.undoButton.setup(800, 10, 150, 30, Color.BLACK);
                break;
            case MRI:
            case RUN:
            default:
                this.menuButton.setup(1045,580, 100, 30, Color.BLACK);
                this.restartButton.setup(875, 580, 150, 30, Color.BLACK);
                this.saveGameButton.setup(700, 580, 150, 30, Color.BLACK);
                this.loadGameButton.setup(700, 10, 150, 30, Color.BLACK);
                this.undoButton.setup(875, 10, 150, 30, Color.BLACK);
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
                JFileChooser fileSelector = new JFileChooser();
                fileSelector.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                fileSelector.showOpenDialog(this);
                File file = fileSelector.getSelectedFile();

                if ((file == null) || (file.getName().equals(""))) {
                    JOptionPane.showMessageDialog(this, "Nombre de archivo inválido", "Nombre de archivo inválido", JOptionPane.ERROR_MESSAGE);
                }else {
                    FileOutputStream fileToSave = new FileOutputStream(file.getPath());
                    ObjectOutputStream outputStream = new ObjectOutputStream(fileToSave);
                    outputStream.writeObject(this.gameMode);
                    outputStream.writeObject(this.gm);
                    outputStream.close();
                    fileToSave.close();
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        this.loadGameButton.onClick(e -> {

            try {
                JFileChooser fileSelector = new JFileChooser();
                fileSelector.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                fileSelector.showOpenDialog(this);
                File file = fileSelector.getSelectedFile();

                if ((file == null) || (file.getName().equals(""))) {
                    JOptionPane.showMessageDialog(this, "Nombre de archivo inválido", "Nombre de archivo inválido", JOptionPane.ERROR_MESSAGE);
                }else {
                    FileInputStream fileToLoad = new FileInputStream(file.getPath());
                    ObjectInputStream inputStream = new ObjectInputStream(fileToLoad);
                    this.remove(this.gm);
                    this.remove(this.background);
                    this.gameMode = (GameModes) inputStream.readObject();
                    this.gm = (GameMoves) inputStream.readObject();
                    this.gm.setGameCallback(() -> this.restartButton.setVisible(true));
                    this.add(this.gm);
                    this.musicBackground.stopMusic();
                    this.musicBackground = new MusicBackgroundV2(Helper.getFileMusic(this.gameMode));
                    this.musicBackground.playMusic();
                    this.setupButtons(this.gameMode);
                    this.setup(this.background, this.getSizeByMode(), this.musicBackground);
                    inputStream.close();
                    fileToLoad.close();
                }
            } catch (Exception ioException) {
                ioException.printStackTrace();
            }
            repaint();
        });

        this.undoButton.onClick(e -> this.gm.undoMovement());
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