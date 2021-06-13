package utilities;

/**
 * Clase para definir un fondo en cualquier componente JDialog, JFrame, JPanel;
 */
import java.awt.*;
import javax.swing.ImageIcon;

public class Fondo{
	private String fondoClassicGame = "ClassicBoard.jpg";
	private String fondoMRIGame = "MRIBoard.jpg";
	private String fondoRunGame = "RunBoard.jpg";
	private Image imagenClassicGame;
	private Image imagenMRIGame;
	private Image imagenRunGame;
    private int anchoFG;
    private int altoFG;
    public Fondo(){
    	anchoFG = 1150;
    	altoFG = 700;
    	ImageIcon imgClassicGame = new ImageIcon(this.getClass().getResource(fondoClassicGame));
    	ImageIcon imgMRIGame = new ImageIcon(this.getClass().getResource(fondoMRIGame));
    	ImageIcon imgRunGame = new ImageIcon(this.getClass().getResource(fondoRunGame));
    	imagenClassicGame = imgClassicGame.getImage();
    	imagenMRIGame = imgMRIGame.getImage();
    	imagenRunGame = imgRunGame.getImage();
    }
    
    public int getAltoFG() { return altoFG; }
    
    public int getAnchoFG() { return anchoFG;}
    
    public Image getImagenRunGame() { return imagenRunGame; }
    
    public Image getImagenMRIGame() { return imagenMRIGame; }
    
    public Image getImagenClassicGame() { return imagenClassicGame; }
    
     
}
