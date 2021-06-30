package layouts.Paths;

    
import java.awt.*;
import java.io.Serializable;

import interfaces.Path;
/**
 * Path of the Classic Game.
 *
 * @author JoelS
 * @version 1
 */
public class ClassicPath implements Path, Serializable {
    private int[][] AX;
    private int[][] AY;
    private int tam;
    public ClassicPath(int tam, Color[] colores){
        this.tam = tam;
        addAX(AX, colores);
        addAY(AY, colores);
        getAX();
        getAY();
    }
    
    /**
     * Returns possible X positions of the pawns.
     * @return Possible X positions.
     */
    public int[][] getAX() {
        return AX;
    }
    
    private void addAX(int[][] x, Color[] colores){
        int i = 0;
        int pos = 0;
        AX = new int[4][57];
        
        while(pos < tam){
            if(colores[pos].equals(Color.BLUE)){
                int[] a1 = {1,2,3,4,5,6,6,6,6,6,6,7,8,8,8,8,8,8,9,10,11,12,13,14,14,14,13,12,11,10,9,8,8,8,8,8,8,7,6,6,6,6,6,6,5,4,3,2,1,0,0,1,2,3,4,5,6};
                while(i < 57){
                    AX[pos][i] = a1[i];
                    i++;
                }
                i = 0;
            }
            if(colores[pos].equals(Color.GREEN)){
                int[] a2 = {8,8,8,8,8,9,10,11,12,13,14,14,14,13,12,11,10,9,8,8,8,8,8,8,7,6,6,6,6,6,6,5,4,3,2,1,0,0,0,1,2,3,4,5,6,6,6,6,6,6,7,7,7,7,7,7,7};
                while(i < 57){
                    AX[pos][i] = a2[i];
                    i++;
                }
                i = 0;
            }
            if(colores[pos].equals(Color.RED)){
                int[] a3 = {13,12,11,10,9,8,8,8,8,8,8,7,6,6,6,6,6,6,5,4,3,2,1,0,0,0,1,2,3,4,5,6,6,6,6,6,6,7,8,8,8,8,8,8,9,10,11,12,13,14,14,13,12,11,10,9,8};
                while(i < 57){
                    AX[pos][i] = a3[i];
                    i++;
                }
                i = 0;
            }
            if(colores[pos].equals(Color.YELLOW)){
                int[] a4 = {6,6,6,6,6,5,4,3,2,1,0,0,0,1,2,3,4,5,6,6,6,6,6,6,7,8,8,8,8,8,8,9,10,11,12,13,14,14,14,13,12,11,10,9,8,8,8,8,8,8,7,7,7,7,7,7,7};
                while(i < 57){
                    AX[pos][i] = a4[i];
                    i++;
                }
                i = 0;
            }
            pos++;
        }
    }
    
    /**
     * Returns possible Y positions of the pawns.
     * @return Possible Y positions.
     */
    public int[][] getAY() {
        return AY;
    }

    private void addAY(int[][] y, Color[] colores){
        int pos = 0;
        int i = 0;
        AY = new int[4][57];
        while(pos < tam){
            if(colores[pos].equals(Color.BLUE)){
                int[] a1 = {6,6,6,6,6,5,4,3,2,1,0,0,0,1,2,3,4,5,6,6,6,6,6,6,7,8,8,8,8,8,8,9,10,11,12,13,14,14,14,13,12,11,10,9,8,8,8,8,8,8,7,7,7,7,7,7,7};
                while(i < 57){
                    AY[pos][i] = a1[i];
                    i++;
                }
                i = 0;
            }
            if(colores[pos].equals(Color.GREEN)){
                int[] a2 = {1,2,3,4,5,6,6,6,6,6,6,7,8,8,8,8,8,8,9,10,11,12,13,14,14,14,13,12,11,10,9,8,8,8,8,8,8,7,6,6,6,6,6,6,5,4,3,2,1,0,0,1,2,3,4,5,6};
                while(i < 57){
                    AY[pos][i] = a2[i];
                    i++;
                }
                i = 0;
            }
            if(colores[pos].equals(Color.RED)){
                int[] a3 = {8,8,8,8,8,9,10,11,12,13,14,14,14,13,12,11,10,9,8,8,8,8,8,8,7,6,6,6,6,6,6,5,4,3,2,1,0,0,0,1,2,3,4,5,6,6,6,6,6,6,7,7,7,7,7,7,7};
                while(i < 57){
                    AY[pos][i] = a3[i];
                    i++;
                }
                i = 0;
            }
            if(colores[pos].equals(Color.YELLOW)){
                int[] a4 = {13,12,11,10,9,8,8,8,8,8,8,7,6,6,6,6,6,6,5,4,3,2,1,0,0,0,1,2,3,4,5,6,6,6,6,6,6,7,8,8,8,8,8,8,9,10,11,12,13,14,14,13,12,11,10,9,8};
                while(i < 57){
                    AY[pos][i] = a4[i];
                    i++;
                }
                i = 0;
            }
            pos++;
        }
    }
    
    /**
     * Returns optional X positions of the pawns.
     * @return null.
     */
    public int[][] getOptionalAX() { return null; }

    /**
     * Returns possible X positions of the pawns.
     * @return null.
     */
    public int[][] getOptionalAY() { return null; }
    
}
