package layouts.Views;

import components.BasicButton;
import components.BasicLabel;
import components.utils.enums.ButtonTypes;
import utilities.GameModes;
import components.ImagePanel;

import java.awt.Color;
import java.awt.Dimension;

public class ChooseModeView extends View{
    private static ChooseModeView chooseModeView;
    private final BasicButton classicModeButton = new BasicButton("Juego Clasico");
    private final BasicButton modifiedModeButton = new BasicButton("Juego Mod");
    private final BasicButton backButton = new BasicButton("Volver", ButtonTypes.INVERTED);
    private ChooseModeView() {
        ImagePanel background = new ImagePanel("7299.jpg");

        BasicLabel title = new BasicLabel("Escoger modo de juego:");
        title.setup(20, 10);

        classicModeButton.setup(40, 75, 140, 30, Color.YELLOW);
        modifiedModeButton.setup(50, 110, 120, 30, Color.ORANGE);
        backButton.setup(60, 150, 100, 30, Color.BLACK);

        this.add(title);
        this.add(classicModeButton);
        this.add(modifiedModeButton);
        this.add(backButton);
        this.setup(background, new Dimension(220, 200), null);
        this.setActions();
        this.repaint();
    }

    private void setActions() {
        classicModeButton.onClick(e -> {
            this.setNextView(GameSetupView.getInstance(GameModes.CLASSIC));
            this.goNext();
        });
        modifiedModeButton.onClick(e -> {
            this.setNextView(ChooseModGameView.getInstance());
            this.goNext();
            
        });
        backButton.onClick(e -> {
            this.setPrevView(MainView.getInstance());
            this.goBack();
        });
    }

    public static ChooseModeView getInstance() {
        if (chooseModeView == null) {
            chooseModeView = new ChooseModeView();
        }

        return chooseModeView;
    }
    
}
