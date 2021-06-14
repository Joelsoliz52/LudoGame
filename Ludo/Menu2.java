package Ludo;


/**
 * Clase principal para comenzar a crear el juego
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
/** 
 *  
 * @author Raul
 */
@SuppressWarnings("serial")
public class Menu2 extends Frame implements ActionListener{
	private final static Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private final static int ancho = pantalla.width;
    private final static int alto = pantalla.height;
    private Button b1, b2, b3, b4, bb;
    public JDialog lastMenu;
    private String fondo = "/utilities/20210611_175351.gif";
    private String audioName = "";
    private AudioClip sound;
    private Image imagen;
    private String complFondo = "/utilities/20210611_175039.gif";
    private Image imagenCompl;
    public Menu2(){
        setLayout(null);
        ImageIcon img =  new ImageIcon(getClass().getResource(fondo));
        imagen = img.getImage();
        ImageIcon imgC =  new ImageIcon(getClass().getResource(complFondo));
        imagenCompl = imgC.getImage();
        sound = java.applet.Applet.newAudioClip(getClass().getResource(audioName));
        sound.play();
        b1 = new Button("Play Now :D");
        b1.setBounds(290,276,140,50);
        b1.setBackground(java.awt.Color.red);
        b1.setForeground(Color.black);
        b1.setFont(new Font("Agency FB",Font.BOLD, 20));
        add(b1);
        b1.addActionListener(this);
        b2 = new Button("Exit :c");
        b2.setBounds(310,356,100,50);
        b2.setBackground(Color.BLUE);
        b2.setForeground(Color.black);
        b2.setFont(new Font("Agency FB",Font.BOLD, 20));
        add(b2);
        b2.addActionListener(this);
        
        //dialog lastMenu
        lastMenu = new JDialog(this, "  ");
        lastMenu.setLayout(null);
        lastMenu.setBounds(580,290,220,200);
        lastMenu.setBackground(Color.LIGHT_GRAY);
        Label lb1 = new Label("Choose Mode Game ");
        lb1.setBounds(12,10,200,40);
        lb1.setBackground(Color.LIGHT_GRAY);
        lb1.setForeground(Color.black);
        lb1.setFont(new Font("Agency FB",Font.BOLD, 20));
        lastMenu.add(lb1);
        b3 = new Button("Classic Game");
        b3.setBounds(30,75,160,30);
        b3.setBackground(java.awt.Color.YELLOW);
        b3.setForeground(Color.black);
        b3.setFont(new Font("Agency FB",Font.BOLD, 20));
        lastMenu.add(b3);
        b3.addActionListener(this);
        b4 = new Button("Mod Game");
        b4.setBounds(45,110,130,30);
        b4.setBackground(java.awt.Color.ORANGE);
        b4.setForeground(Color.black);
        b4.setFont(new Font("Agency FB",Font.BOLD, 20));
        lastMenu.add(b4);
        b4.addActionListener(this);
        bb = new Button("Back");
        bb.setBounds(60,145,100,30);
        bb.setBackground(java.awt.Color.BLACK);
        bb.setForeground(Color.white);
        bb.setFont(new Font("Agency FB",Font.BOLD, 20));
        lastMenu.add(bb);
        bb.addActionListener(this);
        lastMenu.setUndecorated(true);
        setUndecorated(true);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == b1){
            this.setVisible(false);
            lastMenu.setVisible(true);
        }
        if(e.getSource() == b2){
            System.exit(0);
            sound.stop();
        }
        if(e.getSource() == b3){
            CreationBoard3 crb = new CreationBoard3(this, false);
            crb.setVisible(true);
            lastMenu.setVisible(false);
            sound.stop();
        }
        if(e.getSource() == b4){
            CreationBoard4 crb = new CreationBoard4(this, false);
            crb.setVisible(true);
            lastMenu.setVisible(false);
            sound.stop();
        }
        if(e.getSource() == bb){
            lastMenu.setVisible(false);
            Menu2 m;
				m = new Menu2();
				m.setBounds(ancho/(17/4),alto/(241/47),720,450);
	            m.setVisible(true);
	            m.setResizable(false);
	            b1.setEnabled(true);
	            b2.setEnabled(true);
		
        }
    }
    public void paint(Graphics gr) {
    	Graphics2D g2d = (Graphics2D) gr;
    	g2d.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
    	g2d.drawImage(imagenCompl, 460, 110, 100, 100, this);
    }
    public static void main(String[] args){
        Menu2 m = new Menu2();
        m.setBounds(ancho/(17/4),alto/(241/47),720,450);
        m.setVisible(true);
        m.setResizable(false);
    }
}
