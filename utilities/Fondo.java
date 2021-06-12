package utilities;

/**
 * Clase para definir un fondo en el menu
 */
import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

@SuppressWarnings("serial")
public class Fondo extends Frame{
    private Image imagen;
    public Fondo(String ruta, final int ancho, final int alto) throws MalformedURLException {
        URL urlGif = new URL(ruta);
        Icon iconGif = new ImageIcon(urlGif);
        JLabel animacionGif = new JLabel(iconGif);
    }
    /**
     * Metodo para pintar la imagen en fondo
     * @param gr
     * @param x
     * @param y
     * @param width
     * @param height
     * @param f
     */
    public void paint(Graphics gr, int x, int y, int width, int height, Frame f){
        gr.drawImage(imagen, x, y, width, height, f);
        super.paint(gr);
    }
}
