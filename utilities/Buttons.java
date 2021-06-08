package utilities;
/**
 * Clase para mandar los botones a CreationBoard3 y CreationBoard4
 */
import javax.swing.*;
import java.awt.*;
public class Buttons{
    private JButton ba, b3, b4, b5, bb, bs, bc1, bc2, bc3, bc4;
    private JLabel lb1, lbn, lb2, nom1, nom2, nom3, nom4;
    private JTextField tx;
    public Buttons(JDialog jd){
        lb1 = new JLabel("Number of players");
        lb1.setBounds(50,4,200,50);
        lb1.setForeground(Color.white);
        lb1.setFont(new Font("Agency FB",Font.BOLD, 20));
        jd.add(lb1);
        b3 = new JButton("2");
        b3.setBounds(30,50,45,45);
        b3.setBackground(java.awt.Color.yellow);
        b3.setForeground(Color.black);
        b3.setFont(new Font("Agency FB",Font.BOLD, 20));
        jd.add(b3);
        b4 = new JButton("3");
        b4.setBounds(90,50,45,45);
        b4.setBackground(java.awt.Color.ORANGE);
        b4.setForeground(Color.black);
        b4.setFont(new Font("Agency FB",Font.BOLD, 20));
        jd.add(b4);
        b5 = new JButton("4");
        b5.setBounds(150,50,45,45);
        b5.setBackground(java.awt.Color.MAGENTA);
        b5.setForeground(Color.black);
        b5.setFont(new Font("Agency FB",Font.BOLD, 20));
        jd.add(b5);
        
        lbn = new JLabel("Name Player");
        lbn.setBounds(50,100,100,50);
        lbn.setForeground(Color.white);
        lbn.setFont(new Font("Agency FB",Font.BOLD, 20));
        jd.add(lbn);
        tx = new JTextField();
        tx.setBounds(30,140,170,30);
        jd.add(tx);
        ba = new JButton("Aceptar");
        ba.setBounds(210,140,90,28);
        ba.setBackground(java.awt.Color.black);
        ba.setForeground(Color.white);
        ba.setFont(new Font("Agency FB",Font.BOLD, 20));
        jd.add(ba);
        
        nom1 = new JLabel("Player 1");
        nom1.setBounds(50,206,165,45);
        nom1.setForeground(Color.black);
        nom1.setFont(new Font("Agency FB",Font.BOLD, 20));
        jd.add(nom1);
        nom2 = new JLabel("Player 2");
        nom2.setBounds(250,246,165,45);
        nom2.setForeground(Color.black);
        nom2.setFont(new Font("Agency FB",Font.BOLD, 20));
        jd.add(nom2);
        nom3 = new JLabel("Player 3");
        nom3.setBounds(50,286,165,45);
        nom3.setForeground(Color.black);
        nom3.setFont(new Font("Agency FB",Font.BOLD, 20));
        jd.add(nom3);
        nom4 = new JLabel("Player 4");
        nom4.setBounds(250,327,165,45);
        nom4.setForeground(Color.black);
        nom4.setFont(new Font("Agency FB",Font.BOLD, 20));
        jd.add(nom4);
        
        bb = new JButton("Back");
        bb.setBounds(30,400,100,40);
        bb.setBackground(java.awt.Color.black);
        bb.setForeground(Color.white);
        bb.setFont(new Font("Agency FB",Font.BOLD, 20));
        jd.add(bb);
        bs = new JButton("Strar");
        bs.setBounds(270,400,100,40);
        bs.setBackground(java.awt.Color.black);
        bs.setForeground(Color.white);
        bs.setFont(new Font("Agency FB",Font.BOLD, 20));
        jd.add(bs);
        lb2 = new JLabel("Chosse Color");
        lb2.setBounds(50,160,150,50);
        lb2.setForeground(Color.white);
        lb2.setFont(new Font("Agency FB",Font.BOLD, 20));
        jd.add(lb2);
        bc1 = new JButton("Ms Rojo");
        bc1.setBounds(200,210,170,40);
        bc1.setBackground(java.awt.Color.LIGHT_GRAY);
        bc1.setForeground(Color.red);
        bc1.setFont(new Font("Agency FB",Font.BOLD, 20));
        jd.add(bc1);
        bc2 = new JButton("Ms Azul");
        bc2.setBounds(30,250,170,40);
        bc2.setBackground(java.awt.Color.LIGHT_GRAY);
        bc2.setForeground(Color.blue);
        bc2.setFont(new Font("Agency FB",Font.BOLD, 20));
        jd.add(bc2);
        bc3 = new JButton("Ms Amarillo");
        bc3.setBounds(200,290,170,40);
        bc3.setBackground(java.awt.Color.LIGHT_GRAY);
        bc3.setForeground(Color.yellow);
        bc3.setFont(new Font("Agency FB",Font.BOLD, 20));
        jd.add(bc3);
        bc4 = new JButton("Ms Verde");
        bc4.setBounds(30,330,170,40);
        bc4.setBackground(java.awt.Color.LIGHT_GRAY);
        bc4.setForeground(Color.green);
        bc4.setFont(new Font("Agency FB",Font.BOLD, 20));
        jd.add(bc4);
    }
    public JButton getBa(){
        return ba;
    }
    public JButton getB3(){
        return b3;
    }
    public JButton getB4(){
        return b4;
    }
    public JButton getB5(){
        return b5;
    }
    public JButton getBb(){
        return bb;
    }
    public JButton getBs(){
        return bs;
    }
    public JButton getBc1(){
        return bc1;
    }
    public JButton getBc2(){
        return bc2;
    }
    public JButton getBc3(){
        return bc3;
    }
    public JButton getBc4(){
        return bc4;
    }
    public JTextField getTx(){
        return tx;
    }
    public JLabel getLb1(){
        return lb1;
    }
    public JLabel getLb2(){
        return lb2;
    }
    public JLabel getLbn(){
        return lbn;
    }
    public JLabel getNom1(){
        return nom1;
    }
    public JLabel getNom2(){
        return nom2;
    }
    public JLabel getNom3(){
        return nom3;
    }
    public JLabel getNom4(){
        return nom4;
    }
}
