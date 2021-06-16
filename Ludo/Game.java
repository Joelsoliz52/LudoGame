package Ludo;

import java.io.IOException;
import layouts.Views.MainView;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import utilities.exceptions.FileNotFoundException;

public class Game {
    public static void main(String[] args) {
        try {
            java.awt.EventQueue.invokeLater(() -> {
                    MainView game;
                    game = MainView.getInstance();
                    game.setVisible(true);
                    
                
            });
        } catch (Exception e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
        }
    }
}
