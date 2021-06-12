package utilities;
/**
 * Clase para mandar los botones a CreationBoard3 y CreationBoard4
 */
import java.awt.*;

public class Buttons{
    private Button ba, b3, b4, b5, bb, bs, bc1, bc2, bc3, bc4, br;
    private Label lb1, lbn, lb2, nom1, nom2, nom3, nom4;
    private TextField tx;
    public Buttons(Dialog jd){
        lb1 = new Label("Number of players");
        lb1.setBounds(50,10,200,25);
        lb1.setForeground(Color.white);
        lb1.setFont(new Font("Agency FB",Font.BOLD, 20));
        jd.add(lb1);
        b3 = new Button("2");
        b3.setBounds(30,50,45,45);
        b3.setBackground(java.awt.Color.yellow);
        b3.setForeground(Color.black);
        b3.setFont(new Font("Agency FB",Font.BOLD, 20));
        jd.add(b3);
        b4 = new Button("3");
        b4.setBounds(90,50,45,45);
        b4.setBackground(java.awt.Color.ORANGE);
        b4.setForeground(Color.black);
        b4.setFont(new Font("Agency FB",Font.BOLD, 20));
        jd.add(b4);
        b5 = new Button("4");
        b5.setBounds(150,50,45,45);
        b5.setBackground(java.awt.Color.MAGENTA);
        b5.setForeground(Color.black);
        b5.setFont(new Font("Agency FB",Font.BOLD, 20));
        jd.add(b5);
        br = new Button("Reset Numbers");
        br.setBounds(210,50,160,45);
        br.setBackground(java.awt.Color.BLACK);
        br.setForeground(Color.white);
        br.setFont(new Font("Agency FB",Font.BOLD, 20));
        jd.add(br);
        
        lbn = new Label("Name Player");
        lbn.setBounds(50,110,120,25);
        lbn.setForeground(Color.white);
        lbn.setFont(new Font("Agency FB",Font.BOLD, 20));
        jd.add(lbn);
        tx = new TextField();
        tx.setBounds(30,150,170,30);
        tx.setFont(new Font("Agency FB",Font.BOLD, 16));
        jd.add(tx);
        ba = new Button("Aceptar");
        ba.setBounds(210,150,90,28);
        ba.setBackground(java.awt.Color.black);
        ba.setForeground(Color.white);
        ba.setFont(new Font("Agency FB",Font.BOLD, 20));
        jd.add(ba);
        
        nom1 = new Label("Player 1");
        nom1.setBounds(50,240,100,35);
        nom1.setForeground(Color.black);
        nom1.setFont(new Font("Agency FB",Font.BOLD, 20));
        jd.add(nom1);
        nom2 = new Label("Player 2");
        nom2.setBounds(250,280,100,35);
        nom2.setForeground(Color.black);
        nom2.setFont(new Font("Agency FB",Font.BOLD, 20));
        jd.add(nom2);
        nom3 = new Label("Player 3");
        nom3.setBounds(50,320,100,35);
        nom3.setForeground(Color.black);
        nom3.setFont(new Font("Agency FB",Font.BOLD, 20));
        jd.add(nom3);
        nom4 = new Label("Player 4");
        nom4.setBounds(250,360,100, 35);
        nom4.setForeground(Color.black);
        nom4.setFont(new Font("Agency FB",Font.BOLD, 20));
        jd.add(nom4);
        
        bb = new Button("Back");
        bb.setBounds(30,420,100,40);
        bb.setBackground(java.awt.Color.black);
        bb.setForeground(Color.white);
        bb.setFont(new Font("Agency FB",Font.BOLD, 20));
        jd.add(bb);
        bs = new Button("Strar");
        bs.setBounds(270,420,100,40);
        bs.setBackground(java.awt.Color.black);
        bs.setForeground(Color.white);
        bs.setFont(new Font("Agency FB",Font.BOLD, 20));
        jd.add(bs);
        lb2 = new Label("Chosse Color");
        lb2.setBounds(50,195,150,25);
        lb2.setForeground(Color.white);
        lb2.setFont(new Font("Agency FB",Font.BOLD, 20));
        jd.add(lb2);
        bc1 = new Button("Ms Red");
        bc1.setBounds(200,240,170,40);
        bc1.setBackground(java.awt.Color.RED);
        bc1.setForeground(Color.BLACK);
        bc1.setFont(new Font("Agency FB",Font.BOLD, 20));
        jd.add(bc1);
        bc2 = new Button("Ms Blue");
        bc2.setBounds(30,280,170,40);
        bc2.setBackground(java.awt.Color.BLUE);
        bc2.setForeground(Color.BLACK);
        bc2.setFont(new Font("Agency FB",Font.BOLD, 20));
        jd.add(bc2);
        bc3 = new Button("Ms Yellow");
        bc3.setBounds(200,320,170,40);
        bc3.setBackground(java.awt.Color.YELLOW);
        bc3.setForeground(Color.BLACK);
        bc3.setFont(new Font("Agency FB",Font.BOLD, 20));
        jd.add(bc3);
        bc4 = new Button("Ms Green");
        bc4.setBounds(30,360,170,40);
        bc4.setBackground(java.awt.Color.GREEN);
        bc4.setForeground(Color.BLACK);
        bc4.setFont(new Font("Agency FB",Font.BOLD, 20));
        jd.add(bc4);
    }
    public Button getBa(){
        return ba;
    }
    public Button getB3(){
        return b3;
    }
    public Button getB4(){
        return b4;
    }
    public Button getB5(){
        return b5;
    }
    public Button getBb(){
        return bb;
    }
    public Button getBs(){
        return bs;
    }
    public Button getBc1(){
        return bc1;
    }
    public Button getBc2(){
        return bc2;
    }
    public Button getBc3(){
        return bc3;
    }
    public Button getBc4(){
        return bc4;
    }
    public Button getBr() {
    	return br;    	
    }
    public TextField getTx(){
        return tx;
    }
    public Label getLb1(){
        return lb1;
    }
    public Label getLb2(){
        return lb2;
    }
    public Label getLbn(){
        return lbn;
    }
    public Label getNom1(){
        return nom1;
    }
    public Label getNom2(){
        return nom2;
    }
    public Label getNom3(){
        return nom3;
    }
    public Label getNom4(){
        return nom4;
    }
    public void reset() {
    	getB3().setEnabled(true);
	    getB4().setEnabled(true);
	    getB5().setEnabled(true);
	    getBa().setEnabled(true);
	    getNom1().setText("Player 1");
	    getNom2().setText("Player 2");
	    getNom3().setText("Player 3");
	    getNom4().setText("Player 4");
	    getNom1().setForeground(Color.BLACK);
	    getNom2().setForeground(Color.BLACK);
	    getNom3().setForeground(Color.BLACK);
	    getNom4().setForeground(Color.BLACK);
    }
    /**
     * Metodo para apagar botones de colores en caso de que haber sido elegidos
     */
    public void botonesF(){
        getBc1().setEnabled(false);
        getBc2().setEnabled(false);
        getBc3().setEnabled(false);
        getBc4().setEnabled(false);
    }
}
