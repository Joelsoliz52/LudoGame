package utilities.exceptions;

import java.io.PrintStream;

public class MusicPlayerException extends Exception {
    private final Throwable exception;

    public MusicPlayerException(String message, String path) {
        this(message, path, null);
    }

    public MusicPlayerException(String message, String path, Throwable exception) {
        super(message + path);
        this.exception = exception;
    }

    public void printStackTrace() {
        this.printStackTrace(System.err);
    }

    public void printStackTrace(PrintStream ps) {
        if (this.exception == null) {
            super.printStackTrace(ps);
        } else {
            this.exception.printStackTrace();
        }

    }
}
