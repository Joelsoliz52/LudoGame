package components;

import utilities.Constants;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class BasicLabel extends JLabel {
    private Font font;

    public BasicLabel(String content) {
        super(content);
        font = new Font(Constants.DefaultFontFamily, Constants.DefaultFontType, 24);
    }

    public void setFont(int fontSize) {
        setFont(Constants.DefaultFontFamily, fontSize);
    }

    public void setFont(String fontFamily) {
        setFont(fontFamily, Constants.DefaultFontSize);
    }

    public void setFont(String fontFamily, int fontSize) {
        setFont(fontFamily, Constants.DefaultFontType, fontSize);
    }

    public void setFont(String fontFamily, int fontType, int fontSize) {
        font = new Font(fontFamily, fontType, fontSize);
    }

    public void setup(int x, int y) {
        setup(x, y, Color.BLACK);
    }

    public void setup(int x, int y, Color foreground) {
        setup(x, y, 200, 50, foreground);
    }

    public void setup(int x, int y, int width, int height) {
        setup(x, y, width, height, Color.BLACK);
    }

    public void setup(int x, int y, int width, int height, Color foreground) {
        this.setBounds(x, y, width, height);
        this.setFont(font);
        this.setForeground(foreground);
        this.setFocusable(false);
        this.setOpaque(false);
    }

    public void makeColored(String content, Color textColor) {
        this.setText(content);
        this.setForeground(textColor);
    }
}
