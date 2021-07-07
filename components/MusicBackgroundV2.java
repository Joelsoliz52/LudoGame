package components;

import utilities.Helper;
import utilities.MusicPlayer;
import utilities.exceptions.MusicPlayerException;

import java.net.URL;

public class MusicBackgroundV2 {
    private final MusicPlayer player;
    private final String filePath;

    public MusicBackgroundV2(String name) {
        this.filePath = this.getFilePath(Helper.getBasePath(name) + name);
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

    private String getFilePath(String path) {
        URL fileURL = this.getClass().getResource(path != null ? path : "");
        String filePath = fileURL.toString().replace("file:/", "");
        filePath = filePath.replace("%20", " ");

        return filePath.replace("/", "\\");
    }
}
