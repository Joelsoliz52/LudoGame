package components;

import utilities.Helper;
import utilities.MusicPlayer;
import utilities.exceptions.MusicPlayerException;

import java.net.URL;

public class MusicBackground {
    private final MusicPlayer player;
    private final String filePath;

    public MusicBackground(String name) {
        this.filePath = Helper.getBasePath(name) + name;
        this.player = new MusicPlayer();
    }

    public void playMusic() {
        try {
            this.player.playInLoop(this.filePath);
        } catch (MusicPlayerException exception) {
            exception.printStackTrace();
        }
    }

    public void stopMusic() {
        try {
            this.player.stop();
        } catch (MusicPlayerException exception) {
            exception.printStackTrace();
        }
    }
}
