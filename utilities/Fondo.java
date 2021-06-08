package utilities;

/**
 * Clase para definir un fondo en el menu
 */
import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Fondo extends JPanel{
    private Image imagen;
    public final int[] pixels;
    private final int ancho;
    private final int alto;
    public Fondo(String ruta, final int ancho, final int alto) {
    	this.ancho = ancho;
    	this.alto = alto;
    	pixels = new int[ancho*alto];
    	if(ruta != null) {
    		imagen = new ImageIcon(getClass().getResource(ruta)).getImage();
    	}
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
    public void paint(Graphics gr, int x, int y, int width, int height, JFrame f){
        gr.drawImage(imagen, x, y, width, height, f);
        setOpaque(false);
        super.paint(gr);
    }
}
