package layouts.Views;

import components.BasicButton;
import components.BasicLabel;
import components.TextInput;
import components.utils.enums.ButtonTypes;
import core.BoardFactory;
import interfaces.GameLogic;
import utilities.GameModes;
import utilities.Helper;
import components.ImagePanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class GameSetupView extends View {
    private static GameSetupView gameSetupView;
    public int current = 0;
    private final GameModes gameMode;
    private String[] playersNames;
    private HashMap<Color, String> playersColors = Helper.getPlayersColors();

    // Labels
    private final BasicLabel player1Label = new BasicLabel("Jugador 1");
    private final BasicLabel player2Label = new BasicLabel("Jugador 2");
    private final BasicLabel player3Label = new BasicLabel("Jugador 3");
    private final BasicLabel player4Label = new BasicLabel("Jugador 4");
    private final BasicLabel playersNumberLabel = new BasicLabel("Numero de jugadores:");
    private final BasicLabel playerNameLabel = new BasicLabel("Nombre del jugador:");
    private final BasicLabel chooseColorLabel = new BasicLabel("Escoge el color");

    // Buttons
    private final BasicButton twoPlayersButton = new BasicButton("2");
    private final BasicButton threePlayersButton = new BasicButton("3");
    private final BasicButton fourPlayersButton = new BasicButton("4");
    private final BasicButton resetPlayersNumberButton = new BasicButton("Cambiar numero", ButtonTypes.INVERTED);
    private final BasicButton acceptButton = new BasicButton("Aceptar", ButtonTypes.INVERTED);
    private final BasicButton backButton = new BasicButton("Volver", ButtonTypes.INVERTED);
    private final BasicButton startButton = new BasicButton("Empezar", ButtonTypes.INVERTED);
    private final BasicButton blueColorButton = new BasicButton("Mr Azul");
    private final BasicButton greenColorButton = new BasicButton("Mr Verde");
    private final BasicButton redColorButton = new BasicButton("Mr Rojo");
    private final BasicButton yellowColorButton = new BasicButton("Mr Amarillo");

    // TextField
    private final TextInput playerNameInput = new TextInput();

    private GameSetupView(GameModes gameMode) {
        this.gameMode = gameMode;
        ImagePanel background = new ImagePanel("CreationBoards.jpg");

        playerNameInput.setup(30, 155, 170, 30);

        this.add(playerNameInput);

        this.setupButtons();
        this.setupLabels();
        this.setup(background, new Dimension(400, 480));

        this.setActions();
        this.repaint();
    }

    private void setupButtons() {
        twoPlayersButton.setup(30, 55, 45, 45, Color.YELLOW);
        threePlayersButton.setup(90, 55, 45, 45, Color.ORANGE);
        fourPlayersButton.setup(150, 55, 45, 45, Color.RED);
        resetPlayersNumberButton.setup(210, 55, 160, 45, Color.BLACK);
        acceptButton.setup(210, 155, 90, 30, Color.BLACK);
        backButton.setup(30, 420, 100, 40, Color.BLACK);
        startButton.setup(270, 420, 100, 40, Color.BLACK);
        blueColorButton.setup(30, 280, 170, 40, Color.BLUE);
        greenColorButton.setup(30, 360, 170, 40, Color.GREEN);
        redColorButton.setup(200, 240, 170, 40, Color.RED);
        yellowColorButton.setup(200, 320, 170, 40, Color.YELLOW);

        this.add(twoPlayersButton);
        this.add(threePlayersButton);
        this.add(fourPlayersButton);
        this.add(resetPlayersNumberButton);
        this.add(acceptButton);
        this.add(backButton);
        this.add(startButton);
        this.add(blueColorButton);
        this.add(greenColorButton);
        this.add(redColorButton);
        this.add(yellowColorButton);

        setEnabledInputNames(false);
        setEnabledPlayersColorsButtons(false);
        startButton.setEnabled(false);
    }

    private void setupLabels() {
        playersNumberLabel.setup(50, 10, Color.WHITE);
        playerNameLabel.setup(50, 110, Color.WHITE);
        chooseColorLabel.setup(50, 195, Color.WHITE);
        player1Label.setup(50, 233, Color.RED);
        player2Label.setup(50, 313, Color.YELLOW);
        player3Label.setup(250, 273, Color.BLUE);
        player4Label.setup(250, 353, Color.GREEN);

        this.add(playersNumberLabel);
        this.add(playerNameLabel);
        this.add(chooseColorLabel);
        this.add(player1Label);
        this.add(player2Label);
        this.add(player3Label);
        this.add(player4Label);
    }

    private void setActions() {
        // Select players number
        twoPlayersButton.onClick(e -> onSelectPlayersNumber(2));
        threePlayersButton.onClick(e -> onSelectPlayersNumber(3));
        fourPlayersButton.onClick(e -> onSelectPlayersNumber(4));

        playerNameInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    onAddPlayerName();
                }
            }
        });

        // Input player names
        acceptButton.onClick(e -> this.onAddPlayerName());

        // Select player colors
        blueColorButton.onClick(e -> {
            String playerName = onSelectPlayerColor(Color.BLUE);
            if (playerName != null) {
                player3Label.makeColored(playerName, Color.BLUE);
            }
            blueColorButton.setEnabled(false);
        });
        greenColorButton.onClick(e -> {
            String playerName = onSelectPlayerColor(Color.GREEN);
            if (playerName != null) {
                player4Label.makeColored(playerName, Color.GREEN);
            }
            greenColorButton.setEnabled(false);
        });
        redColorButton.onClick(e -> {
            String playerName = onSelectPlayerColor(Color.RED);
            if (playerName != null) {
                player1Label.makeColored(playerName, Color.RED);
            }
            redColorButton.setEnabled(false);
        });
        yellowColorButton.onClick(e -> {
            String playerName = onSelectPlayerColor(Color.YELLOW);
            if (playerName != null) {
                player2Label.makeColored(playerName, Color.YELLOW);
            }
            yellowColorButton.setEnabled(false);
        });

        // Special buttons
        resetPlayersNumberButton.onClick(e -> reset());
        backButton.onClick(e -> {
            this.setPrevView(gameMode == GameModes.CLASSIC ? ChooseModeView.getInstance() : ChooseModGameView.getInstance());
            this.goBack();
            gameSetupView.dispose();
            gameSetupView = null;
        });
        startButton.onClick(e -> {
            GameLogic<Integer> logic = new BoardFactory(this.gameMode, this.playersColors).getBoard();
            this.setNextView(GameView.getInstance(logic, this.gameMode));
            this.goNext();
        });
    }

    private void onAddPlayerName() {
        if (playersNames != null && current < playersNames.length) {
            playersNames[current] = playerNameInput.cleanInput();
            current++;

            if (current == playersNames.length) {
                current = 0;
                setEnabledInputNames(false);
                setEnabledPlayersColorsButtons(true);
            }
        }
    }

    private void onSelectPlayersNumber(int number) {
        playersNames = new String[number];
        setEnabledPlayersNumberButtons(false);
        setEnabledInputNames(true);
    }

    private String onSelectPlayerColor(Color playerColor) {
        String playerName = null;

        if (playersNames != null && current < playersNames.length) {
            playerName = playersNames[current];
            playersColors.replace(playerColor, playerName);
            current ++;

            if (current == playersNames.length) {
                setEnabledPlayersColorsButtons(false);
                startButton.setEnabled(true);
            }
        }

        return playerName;
    }

    private void setEnabledInputNames(boolean isEnabled) {
        playerNameInput.setEnabled(isEnabled);
        acceptButton.setEnabled(isEnabled);
    }

    private void setEnabledPlayersNumberButtons(boolean isEnabled) {
        twoPlayersButton.setEnabled(isEnabled);
        threePlayersButton.setEnabled(isEnabled);
        fourPlayersButton.setEnabled(isEnabled);
    }

    private void setEnabledPlayersColorsButtons(boolean isEnabled) {
        blueColorButton.setEnabled(isEnabled);
        greenColorButton.setEnabled(isEnabled);
        redColorButton.setEnabled(isEnabled);
        yellowColorButton.setEnabled(isEnabled);
    }

    private void reset() {
        playersNames = null;
        playersColors = Helper.getPlayersColors();
        setEnabledPlayersNumberButtons(true);
        setEnabledInputNames(false);
        playerNameInput.cleanInput();
        setEnabledPlayersColorsButtons(false);
        player1Label.makeColored("Jugador 1", Color.BLACK);
        player2Label.makeColored("Jugador 2", Color.BLACK);
        player3Label.makeColored("Jugador 3", Color.BLACK);
        player4Label.makeColored("Jugador 4", Color.BLACK);
        startButton.setEnabled(false);
    }

    public static GameSetupView getInstance(GameModes gameMode) {
        if (gameSetupView == null) {
            gameSetupView = new GameSetupView(gameMode);
        }

        return gameSetupView;
    }
}
