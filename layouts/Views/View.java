package layouts.Views;

import entities.Position;
import utilities.Helper;
import components.ImagePanel;

import javax.swing.JFrame;
import java.awt.Dimension;

public class View extends JFrame {
    private View prevView;
    private View nextView;

    public View() {
        this.setUndecorated(true);
        this.setResizable(false);
        this.setLayout(null);
    }

    public void setup(ImagePanel background, Dimension viewSize) {
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
        prevView.setVisible(true);
    }

    public void goNext() {
        this.setVisible(false);
        nextView.setVisible(true);
    }
}
