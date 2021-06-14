package components;

import utilities.Constants;

import java.awt.*;

public class TextInput extends TextField {
    private final Font font;

    public TextInput() {
        font = new Font(Constants.DefaultFontFamily, Constants.DefaultFontType, 16);
    }

    public void setup(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setFont(font);
    }

    public String cleanInput() {
        String text = this.getText();
        this.setText("");

        return text;
    }
}
