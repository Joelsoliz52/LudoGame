package utilities;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import utilities.enums.MusicPlayerActions;
import utilities.exceptions.MusicPlayerException;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class MusicPlayer {
    private FileInputStream FIS;
    private BufferedInputStream BIS;
    private Player player;
    private long pauseLocation;
    private long songTotalLength;
    private String fileLocation;
    private boolean isPlayInLoop = false;

    public void pause() throws MusicPlayerException {
        if (this.player != null) {
            try {
                this.isPlayInLoop = false;
                this.pauseLocation = this.FIS.available();
                this.player.close();
            } catch (Exception exception) {
                String message = getExceptionMessage(MusicPlayerActions.PAUSE);
                throw new MusicPlayerException(message, this.fileLocation, exception);
            }
        }
    }

    public void play(String path) throws MusicPlayerException {
        try {
            this.FIS = new FileInputStream(path);
            this.BIS = new BufferedInputStream(this.FIS);

            this.player = new Player(this.BIS);
            this.songTotalLength = this.FIS.available();
            this.fileLocation = path + "";
        } catch (Exception exception) {
            String message = this.getExceptionMessage(MusicPlayerActions.PAUSE);
            throw new MusicPlayerException(message, this.fileLocation, exception);
        }

        new Thread(() -> {
            try {
                this.player.play();

                if(this.isPlayInLoop)
                    this.play(this.fileLocation);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }).start();
    }

    public void playInLoop(String path) throws MusicPlayerException {
        this.isPlayInLoop = true;
        this.play(path);
    }

    public void resume() throws MusicPlayerException {
        try {
            this.FIS = new FileInputStream(this.fileLocation);
            this.BIS = new BufferedInputStream(this.FIS);

            this.player = new Player(this.BIS);
            this.FIS.skip(this.songTotalLength - this.pauseLocation);
        } catch (Exception exception) {
            String message = this.getExceptionMessage(MusicPlayerActions.PLAY);
            throw new MusicPlayerException(message, this.fileLocation, exception);
        }

        new Thread(() -> {
            try {
                this.player.play();
            } catch (JavaLayerException exception) {
                exception.printStackTrace();
            }
        }).start();
    }

    public void stop() throws MusicPlayerException {
        if (this.player != null) {
            try {
                this.isPlayInLoop = false;
                this.player.close();

                this.pauseLocation = 0;
                this.songTotalLength = 0;
            } catch (Exception exception) {
                String message = getExceptionMessage(MusicPlayerActions.STOP);
                throw new MusicPlayerException(message, this.fileLocation, exception);
            }
        }
    }

    private String getExceptionMessage(MusicPlayerActions action) {
        switch (action) {
            case PAUSE:
                return "Problem pausing: ";
            case PLAY:
                return "Problem playing: ";
            case STOP:
                return "Problem stopping: ";
            default:
                return "Some problem happen: ";
        }
    }
}
