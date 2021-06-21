package layouts.Views;

import components.BasicButton;
import components.ImagePanel;
import components.MusicBackgroundV2;

import java.awt.Color;
import java.awt.Dimension;

public class MainView extends View {
    private static MainView mainView;
    private final BasicButton playButton = new BasicButton("Jugar ahora :D");
    private final BasicButton exitButton = new BasicButton("Salir :c");
    // private static MusicBackground music = new MusicBackground("D:\\ProjectsIDENetBeans\\JavaApplication2\\src\\resources\\musicas\\soundInitGame.mp3");
    private MainView(){
        Dimension viewSize = new Dimension(720, 450);
        ImagePanel background = new ImagePanel("20210611_175351.gif");
        ImagePanel coin = new ImagePanel("20210611_175039.gif", 480, 120, viewSize);
        MusicBackgroundV2 musicBackground = new MusicBackgroundV2("soundInitGame.mp3");
        
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
