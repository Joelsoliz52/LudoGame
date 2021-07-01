package components;

import entities.Position;
import utilities.Helper;
import utilities.exceptions.FileNotFoundException;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
    private final Dimension dimension;
    private Image image;
    private final Position position;

    public ImagePanel(String name) {
        this(name, null);
    }

    public ImagePanel(String name, Dimension dimension) {
        this(name, 0, 0, dimension);
    }

    public ImagePanel(String name, int x, int y) {
        this(name, "background-image", x, y);
    }

    public ImagePanel(String name, int x, int y, Dimension dimension) {
        this(name, "background-image", x, y, dimension);
    }

    public ImagePanel(String name, String description, int x, int y) {
        this(name, description, x, y, null);
    }

    public ImagePanel(String name, String description, int x, int y, Dimension dimension) {
        position = new Position(x, y);
        this.dimension = dimension;
        resetImage(name, description);
    }

    private ImageIcon createImageIcon(String path, String description) throws FileNotFoundException {
        URL imgURL = getClass().getResource(path != null ? path : "");

        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            throw new FileNotFoundException(path);
        }
    }

    private void resetImage(String name, String description) {
        String path = Helper.getBasePath(name) + name;

        try {
            image = createImageIcon(path, description).getImage();
            init();
        } catch (FileNotFoundException ex) {
            image = null;
        }

    }

    private void init() {
        Dimension size = dimension != null ? dimension : new Dimension(image.getWidth(null), image.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
    }

    public void setBackground(String name) {
        resetImage(name, "background-image");
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image != null) {
            g.drawImage(image, position.getX(), position.getY(), this);
        }
        setOpaque(false);
    }
}
