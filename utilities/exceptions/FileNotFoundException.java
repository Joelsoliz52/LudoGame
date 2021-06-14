package utilities.exceptions;

public class FileNotFoundException extends Exception {
    public FileNotFoundException(String path) {
        super("Couldn't find the file: \"" + path + "\".");
    }

    public FileNotFoundException(String path, String reason) {
        super("Couldn't find the file: \"" + path + "\". Something fail when: " + reason);
    }
}
