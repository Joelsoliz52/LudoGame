package Ludo;


/**
 * Clase principal para comenzar a crear el juego
 */
import javax.swing.*;

import utilities.Fondo;

import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URL;

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
    public static Fondo fn;
    JDialog lastMenu;
    public Menu2() throws MalformedURLException{
        setLayout(null);
        final String IMAGEN = "file:///D:/ProyectsEclipse/src/utilities/20210611_175351.gif";
        URL urlGif = new URL(IMAGEN);
        Icon iconGif = new ImageIcon(urlGif);
        JLabel animacionGif = new JLabel(iconGif);
        animacionGif.setBounds(0, 0, 100, 100);
        add(animacionGif);
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
        Label lb1 = new Label("Choose Mode Game ");
        lb1.setBounds(12,10,200,40);
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
        }
        if(e.getSource() == b3){
            CreationBoard3 crb = new CreationBoard3(this, false);
            crb.setVisible(true);
            lastMenu.setVisible(false);
        }
        if(e.getSource() == b4){
            CreationBoard4 crb = new CreationBoard4(this, false);
            crb.setVisible(true);
            lastMenu.setVisible(false);
        }
        if(e.getSource() == bb){
            lastMenu.setVisible(false);
            Menu2 m;
			try {
				m = new Menu2();
				m.setBounds(ancho/(17/4),alto/(241/47),720,450);
	            m.setVisible(true);
	            m.setResizable(false);
	            b1.setEnabled(true);
	            b2.setEnabled(true);
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            
        }
    }
    public void paint(Graphics g) {
     }
    public static void main(String[] args) throws MalformedURLException{
        Menu2 m = new Menu2();
        m.setBounds(ancho/(17/4),alto/(241/47),720,450);
        m.setVisible(true);
        m.setResizable(false);
    }
}
