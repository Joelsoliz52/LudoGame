package utilities.exceptions;

public class FileNotFoundException extends Exception {
    public FileNotFoundException(String path) {
        super("Couldn't find the file: \"" + path + "\".");
    }
}
