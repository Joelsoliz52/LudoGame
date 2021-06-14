package components;

import components.utils.enums.ButtonTypes;
import utilities.Constants;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

public class BasicButton extends JButton {
    private final ButtonTypes buttonType;
    private Font font;

    public BasicButton(String content) {
        this(content, ButtonTypes.NORMAL);
    }

    public BasicButton(String content, ButtonTypes type) {
        super(content);
        buttonType = type;
        font = new Font(Constants.DefaultFontFamily, Constants.DefaultFontType, Constants.DefaultFontSize);
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
        setup(x, y, Color.WHITE);
    }

    public void setup(int x, int y, Color backgroundColor) {
        setup(x, y, 130, 50, backgroundColor);
    }

    public void setup(int x, int y, int width, int height, Color backgroundColor) {
        this.setBackground(backgroundColor);
        this.setBounds(x, y, width, height);
        this.setFont(font);
        this.setForegroundColor();
        this.setFocusable(false);
        this.setFocusPainted(false);
    }

    private void setForegroundColor() {
        switch (buttonType) {
            case INVERTED:
                this.setForeground(Color.WHITE);
                break;
            case NORMAL:
            default:
                this.setForeground(Color.BLACK);
                break;
        }
    }

    public void onClick(ActionListener action) {
        this.addActionListener(action);
    }
}
