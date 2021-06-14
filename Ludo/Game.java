package Ludo;

import layouts.Views.MainView;

import java.util.Arrays;

public class Game {
    public static void main(String[] args) {
        try {
            java.awt.EventQueue.invokeLater(() -> {
                MainView game = MainView.getInstance();
                game.setVisible(true);
            });
        } catch (Exception e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
        }
    }
}
