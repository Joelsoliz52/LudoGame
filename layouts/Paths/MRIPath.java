package layouts.Paths;

import interfaces.Path;

import java.awt.Color;
import java.io.Serializable;

/**
 * Path of the First Custom Game.
 *
 * @author JoelS
 * @version 2
 */
public class MRIPath implements Path, Serializable {
    private final int tam;
    private int[][] AX;
    private int[][] AY;
    private int[][] OptionalAX;
    private int[][] OptionalAY;

    public MRIPath(int tam, Color[] colors){
        this.tam = tam;
        addAX(colors);
        addAY(colors);
        getAX();
        getAY();
        addOptionalAX(colors);
        addOptionalAY(colors);
        getOptionalAX();
        getOptionalAY();
    }

    /**
     * Returns possible X positions of the pawns.
     * @return Possible X positions.
     */
    public int[][] getAX() {
        return AX;
    }

    private void addAX(Color[] colors){
        int i = 0;
        int pos = 0;
        AX = new int[4][75];

        while(pos < tam){
            if(colors[pos].equals(Color.BLUE)){
                int[] a1 =  { 1,2,3,4,5,6,7,8,8,8,8,8,8,8,8,9,10,10,10,10,10,10,10,10,11,12,13,14,15,16,17,18,18,18,17,16,15,14,13,12,11,10,10,10,10,10,10,10,10,9,8,8,8,8,8,8,8,8,7,6,5,4,3,2,1,0,0,1,2,3,4,5,6,7,8 };
                while(i < a1.length){
                    AX[pos][i] = a1[i];
                    i++;
                }
                i = 0;
            }
            if(colors[pos].equals(Color.GREEN)){
                int[] a2 = { 10,10,10,10,10,10,10,11,12,13,14,15,16,17,18,18,18,17,16,15,14,13,12,11,10,10,10,10,10,10,10,10,9,8,8,8,8,8,8,8,8,7,6,5,4,3,2,1,0,0,0,1,2,3,4,5,6,7,8,8,8,8,8,8,8,8,9,9,9,9,9,9,9,9,9 };
                while(i < a2.length){
                    AX[pos][i] = a2[i];
                    i++;
                }
                i = 0;
            }
            if(colors[pos].equals(Color.RED)){
                int[] a3 = { 17,16,15,14,13,12,11,10,10,10,10,10,10,10,10,9,8,8,8,8,8,8,8,8,7,6,5,4,3,2,1,0,0,0,1,2,3,4,5,6,7,8,8,8,8,8,8,8,8,9,10,10,10,10,10,10,10,10,11,12,13,14,15,16,17,18,18,17,16,15,14,13,12,11,10 };
                while(i < a3.length){
                    AX[pos][i] = a3[i];
                    i++;
                }
                i = 0;
            }
            if(colors[pos].equals(Color.YELLOW)){
                int[] a4 = { 8,8,8,8,8,8,8,7,6,5,4,3,2,1,0,0,0,1,2,3,4,5,6,7,8,8,8,8,8,8,8,8,9,10,10,10,10,10,10,10,10,11,12,13,14,15,16,17,18,18,18,17,16,15,14,13,12,11,10,10,10,10,10,10,10,10,9,9,9,9,9,9,9,9,9 };
                while(i < a4.length){
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

    private void addAY(Color[] colors){
        int pos = 0;
        int i = 0;
        AY = new int[4][75];
        while(pos < tam){
            if(colors[pos].equals(Color.BLUE)){
                int[] a1 =  { 8,8,8,8,8,8,8,7,6,5,4,3,2,1,0,0,0,1,2,3,4,5,6,7,8,8,8,8,8,8,8,8,9,10,10,10,10,10,10,10,10,11,12,13,14,15,16,17,18,18,18,17,16,15,14,13,12,11,10,10,10,10,10,10,10,10,9,9,9,9,9,9,9,9,9 };
                while(i < a1.length){
                    AY[pos][i] = a1[i];
                    i++;
                }
                i = 0;
            }
            if(colors[pos].equals(Color.GREEN)){
                int[] a2 = { 1,2,3,4,5,6,7,8,8,8,8,8,8,8,8,9,10,10,10,10,10,10,10,10,11,12,13,14,15,16,17,18,18,18,17,16,15,14,13,12,11,10,10,10,10,10,10,10,10,9,8,8,8,8,8,8,8,8,7,6,5,4,3,2,1,0,0,1,2,3,4,5,6,7,8 };
                while(i < a2.length){
                    AY[pos][i] = a2[i];
                    i++;
                }
                i = 0;
            }
            if(colors[pos].equals(Color.RED)){
                int[] a3 =  { 10,10,10,10,10,10,10,11,12,13,14,15,16,17,18,18,18,17,16,15,14,13,12,11,10,10,10,10,10,10,10,10,9,8,8,8,8,8,8,8,8,7,6,5,4,3,2,1,0,0,0,1,2,3,4,5,6,7,8,8,8,8,8,8,8,8,9,9,9,9,9,9,9,9,9 };
                while(i < a3.length){
                    AY[pos][i] = a3[i];
                    i++;
                }
                i = 0;
            }
            if(colors[pos].equals(Color.YELLOW)){
                int[] a4 = { 17,16,15,14,13,12,11,10,10,10,10,10,10,10,10,9,8,8,8,8,8,8,8,8,7,6,5,4,3,2,1,0,0,0,1,2,3,4,5,6,7,8,8,8,8,8,8,8,8,9,10,10,10,10,10,10,10,10,11,12,13,14,15,16,17,18,18,17,16,15,14,13,12,11,10 };
                while(i < a4.length){
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
     * @return Possible Y positions.
     */
    public int[][] getOptionalAX() {
        return OptionalAX;
    }

    private void addOptionalAX(Color[] colors){
        int i = 0;
        int pos = 0;
        OptionalAX = new int[4][32];

        while(pos < tam){
            if(colors[pos].equals(Color.BLUE)){
                int[] a1 =  { 4,4,4,4,4,5,6,7,10,11,12,13,14,14,14,14,14,14,14,14,14,13,12,11,8,7,6,5,4,4,4,4 };
                while(i < a1.length){
                    OptionalAX[pos][i] = a1[i];
                    i++;
                }
                i = 0;
            }
            if(colors[pos].equals(Color.GREEN)){
                int[] a2 = { 10,11,12,13,14,14,14,14,14,14,14,14,14,13,12,11,8,7,6,5,4,4,4,4,4,4,4,4,4,5,6,7 };
                while(i < a2.length){
                    OptionalAX[pos][i] = a2[i];
                    i++;
                }
                i = 0;
            }
            if(colors[pos].equals(Color.RED)){
                int[] a3 = { 14,14,14,14,14,13,12,11,8,7,6,5,4,4,4,4,4,4,4,4,4,5,6,7,10,11,12,13,14,14,14,14 };
                while(i < a3.length){
                    OptionalAX[pos][i] = a3[i];
                    i++;
                }
                i = 0;
            }
            if(colors[pos].equals(Color.YELLOW)){
                int[] a4 = { 8,7,6,5,4,4,4,4,4,4,4,4,4,5,6,7,10,11,12,13,14,14,14,14,14,14,14,14,14,13,12,11 };
                while(i < a4.length){
                    OptionalAX[pos][i] = a4[i];
                    i++;
                }
                i = 0;
            }
            pos++;
        }

    }

    /**
     * Returns optional Y positions of the pawns.
     * @return Possible Y positions.
     */
    public int[][] getOptionalAY() {
        return OptionalAY;
    }
    private void addOptionalAY(Color[] colors){
        int i = 0;
        int pos = 0;
        OptionalAY = new int[4][32];

        while(pos < tam){
            if(colors[pos].equals(Color.BLUE)){
                int[] a1 =  { 8,7,6,5,4,4,4,4,4,4,4,4,4,5,6,7,10,11,12,13,14,14,14,14,14,14,14,14,14,13,12,11 };
                while(i < a1.length){
                    OptionalAY[pos][i] = a1[i];
                    i++;
                }
                i = 0;
            }
            if(colors[pos].equals(Color.GREEN)){
                int[] a2 = { 4,4,4,4,4,5,6,7,10,11,12,13,14,14,14,14,14,14,14,14,14,13,12,11,8,7,6,5,4,4,4,4 };
                while(i < a2.length){
                    OptionalAY[pos][i] = a2[i];
                    i++;
                }
                i = 0;
            }
            if(colors[pos].equals(Color.RED)){
                int[] a3 = { 10,11,12,13,14,14,14,14,14,14,14,14,14,13,12,11,8,7,6,5,4,4,4,4,4,4,4,4,4,5,6,7 };
                while(i < a3.length){
                    OptionalAY[pos][i] = a3[i];
                    i++;
                }
                i = 0;
            }
            if(colors[pos].equals(Color.YELLOW)){
                int[] a4 = { 14,14,14,14,14,13,12,11,8,7,6,5,4,4,4,4,4,4,4,4,4,5,6,7,10,11,12,13,14,14,14,14 };
                while(i < a4.length){
                    OptionalAY[pos][i] = a4[i];
                    i++;
                }
                i = 0;
            }
            pos++;
        }

    }
}
