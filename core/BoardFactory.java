package core;

import entities.Aliance;
import entities.Player;
import entities.Position;
import interfaces.Board;
import interfaces.GameLogic;
import interfaces.Path;
import layouts.Boards.ClassicBoard;
import layouts.Boards.MRIBoard;
import layouts.Boards.RunBoard;
import layouts.Paths.ClassicPath;
import layouts.Paths.MRIPath;
import layouts.Paths.RunPath;
import utilities.GameModes;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

public class BoardFactory {
    private final ArrayList<Player> playersList = new ArrayList<>();
    private Board board;
    private BuildPlayers builder;
    private final GameModes gameMode;
    private final ArrayList<Color> colors = new ArrayList<>();
    private int playersNumber = 0;

    public BoardFactory(GameModes gameMode, HashMap<Color, String> players) {
        this.gameMode = gameMode;
        setValidPlayers(players);
        init();
    }

    private void init() {
        Color[] colorsGame = colors.toArray(new Color[0]);
        switch (gameMode) {
            case CLASSIC:
                board = new ClassicBoard(new Position(80, 50), colorsGame);
                builder = new BuildPlayers(this.playersNumber, board.getHeight(), board.getWidth(), board);
                break;
            case MRI:
                board = new MRIBoard(new Position(80, 50), colorsGame);
                builder = new BuildPlayers(this.playersNumber, board.getHeight(), board.getWidth(), board);
                break;
            case RUN:
                board = new RunBoard(new Position(80, 50), colorsGame);
                builder = new BuildPlayers(this.playersNumber, board.getHeight(), board.getWidth(), board);
                break;
        }

        builder.addPlayers(playersList);
    }
    private int turn = 1;

    private Path getPath() {
        Color[] colorsGame = this.colors.toArray(new Color[0]);

        switch (gameMode) {
            case CLASSIC:
                return new ClassicPath(this.playersNumber, colorsGame);
            case MRI:
                return new MRIPath(this.playersNumber, colorsGame);
            case RUN:
                return new RunPath(this.playersNumber, colorsGame);
        }

        return null;
    }
    private void setValidPlayers(HashMap<Color, String> players) {
        players.forEach((k, v) -> {
            if(!v.equals("")) {
                colors.add(k);
            }
        });
        this.playersNumber = colors.size();

        players.forEach((k, v) -> {
            if (!v.equals("")) {
                Player player = new Player(v, 30, 30, this.getPath()) {};
                player.setColor(k);
                player.setTurn(turn);
                playersList.add(player);
                turn++;
            }
        });

    }

    public GameLogic<Integer> getBoard() {
        switch (gameMode) {
            case CLASSIC:
                ClassicLogic logic = new ClassicLogic((ClassicBoard) board);
                ((ClassicBoard) board).setBd(builder);
                logic.tam = this.playersNumber;
                logic.players = ((ClassicBoard) board).bd;
                return logic;
            case MRI:
                MRILogic mriLogic = new MRILogic((MRIBoard) board);
                ((MRIBoard) board).setBd(builder);
                mriLogic.tam = this.playersNumber;
                mriLogic.players = ((MRIBoard) board).bd;
                return mriLogic;
            case RUN:
                Aliance aliance = playersList.size() > 3 ? new Aliance(playersList.size(), builder, (RunBoard) board) : null;
                RunLogic runLogic = new RunLogic(board, aliance);
                ((RunBoard) board).setBd(builder);
                runLogic.tam = this.playersNumber;
                runLogic.players = ((RunBoard) board).bd;
                return runLogic;
        }

        return null;
    }
}
