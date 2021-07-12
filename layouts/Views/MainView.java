package layouts.Views;

import components.BasicButton;
import components.ImagePanel;
import components.MusicBackground;

import java.awt.Color;
import java.awt.Dimension;

public class MainView extends View {
    private static MainView mainView;
    private final BasicButton playButton = new BasicButton("Jugar ahora :D");
    private final BasicButton exitButton = new BasicButton("Salir :c");

    private MainView(){
        Dimension viewSize = new Dimension(720, 450);
        ImagePanel background = new ImagePanel("mainBackground.gif");
        ImagePanel coin = new ImagePanel("coin.gif", 480, 120, viewSize);
        MusicBackground musicBackground = new MusicBackground("soundInitGame.mp3");
        
        playButton.setup(290, 260, Color.RED);
        exitButton.setup(290, 340, Color.BLUE);
        this.add(playButton);
        this.add(exitButton);
        this.add(coin);
        this.setup(background, viewSize, musicBackground);

        musicBackground.playMusic();
        this.setActions();
        this.repaint();
    }

    private void setActions() {
        playButton.onClick(e -> {
            this.setNextView(ChooseModeView.getInstance());
            this.goNext();
        });
        exitButton.onClick(e -> System.exit(0));
    }

    public static MainView getInstance() {
        if (mainView == null) {
            mainView = new MainView();
        }

        return mainView;
    }
}
