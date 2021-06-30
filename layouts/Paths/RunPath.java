package layouts.Paths;

import java.awt.*;
import java.io.Serializable;

import interfaces.Path;

public class RunPath implements Path, Serializable {
    private int[][] AX;
    private int[][] AY;
    private int tam;
    public RunPath(int tam, Color[] colores){
        this.tam = tam;
        addAX(colores);
        addAY(colores);
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

    private void addAX(Color[] colores){
        int i = 0;
        int pos = 0;
        AX = new int[4][84];

        while(pos < tam){
            if(colores[pos].equals(Color.BLUE)){
                int[] a1 = {3,4,4,4,4,5,6,7,7,7,7,7,7,8,9,10,10,10,10,10,10,11,12,13,14,15,16,17,18,18,18,18,18,18,18,18,18,17,16,15,14,13,12,11,11,11,11,11,11,10,9,8,8,8,8,8,8,7,6,5,4,3,2,1,0,0,0,0,0,0,0,0,0,1,2,3,4,4,4,4,5,6,7,8};
                while(i < 84){
                    AX[pos][i] = a1[i];
                    i++;
                }
                i = 0;
            }
            if(colores[pos].equals(Color.GREEN)){
                int[] a2 = {15,14,13,13,13,13,13,13,14,15,16,17,18,18,18,18,18,18,18,18,18,17,16,15,14,13,12,11,11,11,11,11,11,10,9,8,8,8,8,8,8,7,6,5,4,3,2,1,0,0,0,0,0,0,0,0,0,1,2,3,4,5,6,7,7,7,7,7,7,8,9,10,10,10,10,10,10,10,10,10};
                while(i < 80){
                    AX[pos][i] = a2[i];
                    i++;
                }
                i = 0;
            }
            if(colores[pos].equals(Color.RED)){
                int[] a3 = {15,14,14,14,14,13,12,11,11,11,11,11,11,10,9,8,8,8,8,8,8,7,6,5,4,3,2,1,0,0,0,0,0,0,0,0,0,1,2,3,4,5,6,7,7,7,7,7,7,8,9,10,10,10,10,10,10,11,12,13,14,15,16,17,18,18,18,18,18,18,18,18,18,17,16,15,14,14,14,14,13,12,11,10};
                while(i < 84){
                    AX[pos][i] = a3[i];
                    i++;
                }
                i = 0;
            }
            if(colores[pos].equals(Color.YELLOW)){
                int[] a4 = {3,4,5,5,5,5,5,5,4,3,2,1,0,0,0,0,0,0,0,0,0,1,2,3,4,5,6,7,7,7,7,7,7,8,9,10,10,10,10,10,10,11,12,13,14,15,16,17,18,18,18,18,18,18,18,18,18,17,16,15,14,13,12,11,11,11,11,11,11,10,9,8,8,8,8,8,8,8,8,8};
                while(i < 80){
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

    private void addAY(Color[] colores){
        int pos = 0;
        int i = 0;
        AY = new int[4][84];
        while(pos < tam){
            if(colores[pos].equals(Color.BLUE)){
                int[] a1 = {2,2,3,4,5,5,5,5,4,3,2,1,0,0,0,0,1,2,3,4,5,5,5,5,5,5,5,5,5,6,7,8,9,10,11,12,13,13,13,13,13,13,13,13,14,15,16,17,18,18,18,18,17,16,15,14,13,13,13,13,13,13,13,13,13,12,11,10,9,8,7,6,5,5,5,5,5,6,7,8,8,8,8,8};
                while(i < 84){
                    AY[pos][i] = a1[i];
                    i++;
                }
                i = 0;
            }
            if(colores[pos].equals(Color.GREEN)){
                int[] a2 = {0,0,0,1,2,3,4,5,5,5,5,5,5,6,7,8,9,10,11,12,13,13,13,13,13,13,13,13,14,15,16,17,18,18,18,18,17,16,15,14,13,13,13,13,13,13,13,13,13,12,11,10,9,8,7,6,5,5,5,5,5,5,5,5,4,3,2,1,0,0,0,0,1,2,3,4,5,6,7,8};
                while(i < 80){
                    AY[pos][i] = a2[i];
                    i++;
                }
                i = 0;
            }
            if(colores[pos].equals(Color.RED)){
                int[] a3 = {16,16,15,14,13,13,13,13,14,15,16,17,18,18,18,18,17,16,15,14,13,13,13,13,13,13,13,13,13,12,11,10,9,8,7,6,5,5,5,5,5,5,5,5,4,3,2,1,0,0,0,0,1,2,3,4,5,5,5,5,5,5,5,5,5,6,7,8,9,10,11,12,13,13,13,13,13,12,11,10,10,10,10,10};
                while(i < 84){
                    AY[pos][i] = a3[i];
                    i++;
                }
                i = 0;
            }
            if(colores[pos].equals(Color.YELLOW)){
                int[] a4 = {18,18,18,17,16,15,14,13,13,13,13,13,13,12,11,10,9,8,7,6,5,5,5,5,5,5,5,5,4,3,2,1,0,0,0,0,1,2,3,4,5,5,5,5,5,5,5,5,5,6,7,8,9,10,11,12,13,13,13,13,13,13,13,13,14,15,16,17,18,18,18,18,17,16,15,14,13,12,11,10};

                while(i < 80){
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
     * @return Possible X positions.
     */
    public int[][] getOptionalAX() {
        return new int[][]{
                {1,2,3,4,5,6,7,7,7,7,7,7,8,9,10,10,10,10,10,10,11,12,13,14,15,16,17,18,18,18,18,18,18,18,18,18,17,16,15,14,13,12,11,11,11,11,11,11,10,9,8,8,8,8,8,8,7,6,5,4,3,2,1,0,0,0,0,0,0,0,0,0,1,2,3,4,5,6,7}
        };
    }

    /**
     * Returns possible X positions of the pawns.
     * @return Possible X positions.
     */
    public int[][] getOptionalAY() {
        return new int[][]{
                {5,5,5,5,5,5,5,4,3,2,1,0,0,0,0,1,2,3,4,5,5,5,5,5,5,5,5,5,6,7,8,9,10,11,12,13,13,13,13,13,13,13,13,14,15,16,17,18,18,18,18,17,16,15,14,13,13,13,13,13,13,13,13,13,12,11,10,9,8,7,6,5,5,5,5,5,5,5,5}
        };
    }}