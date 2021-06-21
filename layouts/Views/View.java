package layouts.Views;

import components.MusicBackgroundV2;
import entities.Position;
import utilities.Helper;
import components.ImagePanel;

import javax.swing.JFrame;
import java.awt.Dimension;

public class View extends JFrame {
    private View prevView;
    private View nextView;
    private MusicBackgroundV2 music;

    public View() {
        this.setUndecorated(true);
        this.setResizable(false);
        this.setLayout(null);
    }

    public void setup(ImagePanel background, Dimension viewSize, MusicBackgroundV2 music) {
        this.music = music;
        Position centeredPosition = Helper.calculateCenteredPosition(viewSize);
        this.setBounds(centeredPosition.getX(), centeredPosition.getY(), viewSize.width, viewSize.height);
        if (background != null)
            this.add(background);
    }

    public void setPrevView(View prevView) {
        this.prevView = prevView;
    }

    public void setNextView(View nextView) {
        this.nextView = nextView;
    }

    public void goBack() {
        this.setVisible(false);
        if (this.music != null)
            this.music.stopMusic();

        prevView.setVisible(true);
        if (this.prevView.music != null)
            this.prevView.music.playMusic();
    }

    public void goNext() {
        this.setVisible(false);
        if (this.music != null)
            this.music.stopMusic();

        nextView.setVisible(true);
        if (this.nextView.music != null)
            this.nextView.music.playMusic();
    }
}
