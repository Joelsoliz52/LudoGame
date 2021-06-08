package Ludo;


/**
 * Clase principal para comenzar a crear el juego
 */
import javax.swing.*;

import utilities.Fondo;

import java.awt.*;
import java.awt.event.*;

/**
 *  
 * @author Raul
 */
@SuppressWarnings("serial")
public class Menu2 extends JFrame implements ActionListener{
	private final static Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private final static int ancho = pantalla.width;
    private final static int alto = pantalla.height;
    private JButton b1, b2, b3, b4, bb;
    private JLabel lb;
    public static Fondo fn;
    JDialog lastMenu;
    public Menu2(){
        setLayout(null);
        
        lb = new JLabel("LudoGame");
        lb.setBounds(307,50,500,200);
        lb.setForeground(Color.black);
        lb.setFont(new Font("Agency FB",Font.BOLD, 20));
        add(lb);
        b1 = new JButton("Play Now :D");
        b1.setBounds(278,180,120,50);
        b1.setBackground(java.awt.Color.red);
        b1.setForeground(Color.black);
        b1.setFont(new Font("Agency FB",Font.BOLD, 20));
        add(b1);
        b1.addActionListener(this);
        b2 = new JButton("Exit :c");
        b2.setBounds(290,260,100,50);
        b2.setBackground(Color.BLUE);
        b2.setForeground(Color.black);
        b2.setFont(new Font("Agency FB",Font.BOLD, 20));
        add(b2);
        b2.addActionListener(this);
        
        //dialog lastMenu
        lastMenu = new JDialog(this, "  ");
        lastMenu.setLayout(null);
        lastMenu.setBounds(580,290,220,240);
        JLabel lb1 = new JLabel("Choose Mode Game ");
        lb1.setBounds(40,0,300,100);
        lb1.setForeground(Color.black);
        lb1.setFont(new Font("Agency FB",Font.BOLD, 20));
        lastMenu.add(lb1);
        b3 = new JButton("Classic Game");
        b3.setBounds(38,75,125,30);
        b3.setBackground(java.awt.Color.YELLOW);
        b3.setForeground(Color.black);
        b3.setFont(new Font("Agency FB",Font.BOLD, 20));
        lastMenu.add(b3);
        b3.addActionListener(this);
        b4 = new JButton("Mod Game");
        b4.setBounds(38,110,125,30);
        b4.setBackground(java.awt.Color.ORANGE);
        b4.setForeground(Color.black);
        b4.setFont(new Font("Agency FB",Font.BOLD, 20));
        lastMenu.add(b4);
        b4.addActionListener(this);
        bb = new JButton("Back");
        bb.setBounds(50,145,100,30);
        bb.setBackground(java.awt.Color.BLACK);
        bb.setForeground(Color.white);
        bb.setFont(new Font("Agency FB",Font.BOLD, 20));
        lastMenu.add(bb);
        bb.addActionListener(this);
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
            Menu2 m = new Menu2();
            m.setBounds(ancho/(17/4),alto/(241/47),720,450);
            m.setVisible(true);
            m.setResizable(false);
            b1.setEnabled(true);
            b2.setEnabled(true);
        }
    }
    public void paint(Graphics g) {
    	
     }
    public static void main(String[] args){
        Menu2 m = new Menu2();
        m.setBounds(ancho/(17/4),alto/(241/47),720,450);
        m.setVisible(true);
        m.setResizable(false);
    }
}
