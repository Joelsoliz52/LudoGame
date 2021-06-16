package utilities;
/**
 * Clase para mandar los botones a CreationBoard3 y CreationBoard4
 */
import java.awt.*;


public class Buttons{
    private Button ba, b3, b4, b5, bb, bs, bc1, bc2, bc3, bc4, br;
    private TextField tx;
    private String j1 = "Jugador 1";
    private String j2 = "Jugador 2";
    private String j3 = "Jugador 3";
    private String j4 = "Jugador 4";
    public Buttons(Dialog jd){
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
    
    public Button getBa(){return ba;}
    public Button getB3(){return b3;}
    public Button getB4(){return b4;}
    public Button getB5(){return b5;}
    public Button getBb(){return bb;}
    public Button getBs(){return bs;}
    public Button getBc1(){return bc1;}
    public Button getBc2(){return bc2;}
    public Button getBc3(){return bc3;}
    public Button getBc4(){return bc4;}
    public Button getBr() {return br;}
    public TextField getTx(){return tx;}
    
    public String getJ1() {return j1;}
    
    public String getJ2() {return j2;}
    
    public String getJ3() {return j3;}
    
    public String getJ4() {return j4;}
    
    public void setJ1(String j1) {this.j1 = j1;}
    
    public void setJ2(String j2) {this.j2 = j2;}
    
    public void setJ3(String j3) {this.j3 = j3;}
    
    public void setJ4(String j4) {this.j4 = j4;}
    
    public void paint(Graphics2D gr) {
    	gr.setFont(new Font("Agency FB", Font.BOLD, 22));
    	gr.setColor(Color.WHITE);
    	gr.drawString("Numero de Jugadores", 50, 30);
    	gr.drawString("Nombre del Jugador", 50, 130);
    	gr.drawString("Escoger Color", 50,215);
    	gr.setColor(Color.RED);
    	gr.drawString(getJ1(), 50,265);
    	gr.setColor(Color.BLUE);
    	gr.drawString(getJ2(), 250,305);
    	gr.setColor(Color.YELLOW);
    	gr.drawString(getJ3(), 50,345);
    	gr.setColor(Color.GREEN);
    	gr.drawString(getJ4(), 250, 385);
    }
    
    public void reset() {
    	getB3().setEnabled(true);
	    getB4().setEnabled(true);
	    getB5().setEnabled(true);
	    getBa().setEnabled(true);
	    setJ1("Jugador 1");
	    setJ2("Jugador 2");
	    setJ3("Jugador 3");
	    setJ4("Jugador 4");
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
