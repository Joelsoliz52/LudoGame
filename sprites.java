import javax.swing.JFrame;
import java.awt.*;
import java.util.logging.*;
import java.awt.image.*;

public class Sprite extends JFrame implements Runnable{
int tall=600;
int breadth=500;

Image img;
Thread var;
int increment=0;
BufferedImage bi;


    public Sprite(){
        setSize(breadth,tall);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("animation");
        setResizable(false);
        var=new Thread(this);
        bi=new BufferedImage(breadth,tall,BufferedImage.TYPE_INT_RGB);
        Toolkit tools = Toolkit.getDefaultToolkit();
        img=tools.getImage(getClass().getResource("/imagenes2/explosion.jpg"));
        var.start();

    }
    @Override
    public void paint(Graphics g){
        Graphics2D g2d;
        
        g.drawImage(bi,0,0,this);
        g2d=bi.createGraphics();
        g2d.fillRect(0,0,breadth,tall);

        int inx=(increment%6)*86;
        int iny=(increment/6)*86;


        g2d.drawImage(img,200,200,200+86,200+86,inx,iny,inx+86,iny+86,this);
        repaint();
    }
    @Override
    public void run(){
        while(true){
            try{
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(Sprite.class.getName()).log(Level.SEVERE,null,ex);

            }
            increment++;

            if(increment>20){
                increment=0;
            }
        }

    }

    public static void main(String[]args){
        new Sprite().setVisible(true);

    }

}
