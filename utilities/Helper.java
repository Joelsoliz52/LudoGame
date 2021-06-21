package utilities;

import entities.Position;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Helper {
    private final static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final static ArrayList<String> imageExtensions = new ArrayList<>(Arrays.asList("jpg", "jpeg", "png"));
    private final static ArrayList<String> musicExtensions = new ArrayList<>(Arrays.asList("mp3", "m4a"));
    private final static String gifExtension = "gif";

    public static int randomNumber(int maxNumber) {
        return (int) (Math.random()*maxNumber) + 1;
    }

    public static Position calculateCenteredPosition(Dimension viewSize) {
        int x = (screenSize.width - viewSize.width) / 2;
        int y = (screenSize.height - viewSize.height) / 2;
        return new Position(x, y);
    }

    public static HashMap<Color, String> getPlayersColors() {
        HashMap<Color, String> playersColors = new HashMap<>();
        playersColors.put(Color.BLUE, "");
        playersColors.put(Color.GREEN, "");
        playersColors.put(Color.RED, "");
        playersColors.put(Color.YELLOW, "");

        return playersColors;
    }

    public static String getBasePath(String nameFile) {
        try {
            String fileExtension = nameFile.split("\\.")[1];
            if (imageExtensions.contains(fileExtension)) {
                return "/resources/images/";
            }

            if (fileExtension.equals(gifExtension)) {
                return "/resources/gifs/";
            }

            if (musicExtensions.contains(fileExtension)) {
                return "/resources/musicas/";
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return "./";
        }

        return "./";
    }

    public static boolean checkIfLimitsContainsNumber(int number, int min, int max) {
        return min <= number && number <= max;
    }

    public static String getFileMusic(GameModes gameMode) {
        switch (gameMode) {
            case CLASSIC:
                return "soundClassic.mp3";
            case MRI:
                return "soundMRI.mp3";
            case RUN:
                return "soundRun.mp3";
            default:
                return "";
        }
    }
}
