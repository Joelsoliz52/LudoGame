package layouts.Views;

import components.BasicButton;
import components.BasicLabel;
import components.ImagePanel;
import components.MusicBackground;
import components.utils.enums.ButtonTypes;
import utilities.GameModes;
import utilities.Helper;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChooseModGameView extends View {
    private static ChooseModGameView chooseModGameView;
    private final ImagePanel background = new ImagePanel("7299.jpg");
    private final BasicButton backButton = new BasicButton("Volver", ButtonTypes.INVERTED);
    private MusicBackground music = new MusicBackground("");
    private ChooseModGameView() {
        BasicLabel titleLabel = new BasicLabel("Escoge uno:");
        ImagePanel mriBoard = new ImagePanel("mriBoard.jpg", 30, 50, new Dimension(310, 210));
        ImagePanel runBoard = new ImagePanel("runBoard.jpg", 180, 50, new Dimension(310, 210));
        titleLabel.setup(30, 0);
        backButton.setup(105, 165, 100, 30, Color.BLACK);

        this.add(titleLabel);
        this.add(backButton);
        this.add(runBoard);
        this.add(mriBoard);
        this.setup(background, new Dimension(310, 210));
        runBoard.setOpaque(false);
        mriBoard.setOpaque(false);

        this.setActions();
        this.repaint();
    }

    private boolean checkSquareLimits(int x, int minX, int maxX, int y) {
        return Helper.checkIfLimitsContainsNumber(x, minX, maxX) &&
                Helper.checkIfLimitsContainsNumber(y, 50, 150);
    }

    private void setActions() {
        background.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (checkSquareLimits(e.getX(), 30, 130, e.getY())) {
                    setNextView(GameSetupView.getInstance(GameModes.MRI));
                    goNext();
                    music.suspend();
                    GameSetupView.getInstance(GameModes.MRI).setMusic(new MusicBackground("D:\\ProjectsIDENetBeans\\JavaApplication2\\src\\resources\\musicas\\soundMRI.mp3"));
                    GameSetupView.getInstance(GameModes.MRI).getMusic().start();
                }
                if (checkSquareLimits(e.getX(), 180, 280, e.getY())) {
                    setNextView(GameSetupView.getInstance(GameModes.RUN));
                    goNext();
                    music.suspend();
                    GameSetupView.getInstance(GameModes.RUN).setMusic(new MusicBackground("D:\\ProjectsIDENetBeans\\JavaApplication2\\src\\resources\\musicas\\soundRun.mp3"));
                    GameSetupView.getInstance(GameModes.RUN).getMusic().start();
                }
            }
        });
        
        backButton.onClick(e -> {
            this.setPrevView(ChooseModeView.getInstance());
            music.suspend();
            this.goBack();
        });
    }

    public static ChooseModGameView getInstance() {
        if (chooseModGameView == null) {
            chooseModGameView = new ChooseModGameView();
        }

        return chooseModGameView;
    }
    
    public MusicBackground getMusic(){
        return music;
    }
    
    public void setMusic(MusicBackground music){
        this.music = music;
    }
}
