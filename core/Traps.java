package core;
/**
 * Clase de trampas para poner o activar una trampa
 */

import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JOptionPane;
import entities.Dice;
import entities.Pawn;
import entities.Player;
import entities.Position;
import interfaces.Comodin;
import interfaces.GameLogic;
import utilities.ListaSEC;

public class Traps implements Comodin{
    private Position pos;
    private GameLogic<Integer> logic;
    private ListaSEC<Position> positiones;

    public Traps(){}

    public Traps(Player[] players, int x, int y, RunLogic logic,Pawn pawnA){
        this.logic = logic;
        Position pos = new Position(x, y);
        Position pos1 = new Position(0, 9);
        Position pos2 = new Position(9, 0);
        Position pos3 = new Position(8, 18);
        Position pos4 = new Position(18, 8);
        int al = aleatori();
        Player player = returnPlayer(players, x, y);
        if(!pos.equals(pos1) && !pos.equals(pos2) && !pos.equals(pos3) && !pos.equals(pos4)){
            pos.setFlagTraps(true);
        }
        if(trustPositionPawn(player, x, y) && (!pos.getFlagTraps())){
            if (al ==1){
                JOptionPane.showMessageDialog(null, "escoge una casilla");
                logic.setdoubleClicked(true);
            }else{
                if(al == 2){
                    putTrapLoseTurn(players, x, y);
                }else if(al == 3){
                    JOptionPane.showMessageDialog(null, "escoge una ficha");
                    logic.setnewPositionPawn(true);
                    logic.setdoubleClicked(true);
                }
            }
        }else if(logic.getnewPositionPawn()){
            logic.setnewPositionPawn(false);
            newPositionPawn(pawnA, x, y, players);
        }else if(pos.getFlagTraps() && !pawnA.getFlag()){
            int al2 = (int)(Math.random()*3)+1;
            if(al2 == 1){
                putTrapReturnInit(pawnA);
            }else{
                if(al2 == 2){
                    putTrapRecoil(player, x, y, logic.getDice());
                }else{
                    putTrapMine(players, pawnA);
                }
            }
        }else {
            pawnA.setFlag(false);
        }
    }

    /**
     * Metodo para seleccionar una trampa al azar
     */
    public int aleatori(){return (int)(Math.random()*3)+1;}

    /**
     * Metodo trampa para volver al inicio
     * @param pawn
     */
    public void putTrapReturnInit(Pawn pawn){//Player player, int x, int y){
        pawn.setCurrent(-1);
    }

    /**
     * Metodo para colocar una trampa de retroceso
     * @param player, x, y, dice
     */
    public void putTrapRecoil(Player player, int x, int y, Dice<Integer> dice){
        dice.throwDice();
        int pos = dice.content;
        Position current = new Position(x, y);
        if(trustPositionPawn(player, x, y)){
            Pawn pawn = returnPawn(player, current);
            pawn.setCurrent(pawn.getCurrent() - pos);
        }
    }

    /**
     * Metodo para colocar una trampa de perdida de turno
     * @param players, x, y
     */
    public void putTrapLoseTurn(Player[] players, int x, int y){
        Player player = returnPlayer(players, x, y);
        player.setFlagTraps(true);
    }

    /**
     * Metodo para colocar una trampa de congelar una ficha
     * @param player, x, y
     */
    public void putTrapFreezePawn(Player player, int x, int y){
        Position poss = new Position(x, y);
        Pawn pawn = returnPawn(player, poss);
        int i = 0;
        while(i < positiones.prop()) {
            if(pawn.getPosition().equals(positiones.mostrar(i))) {
                pawn.getPosition().setFlagTraps(true);
                break;
            }
            i++;
        }
    }

    /**
     * Metodo para cambiar de posicion una ficha escoger ficha
     * @param pawn1, x, y, players
     */
    public void newPositionPawn(Pawn pawn1, int x, int y, Player[] players){
        Position pos1 = pawn1.getPosition();
        Player player1 = returnPlayer(players, pawn1.getPosition().getX(), pawn1.getPosition().getY());
        Position pos = new Position (x,y);
        Pawn pawn2 = returnPawn(players, pos);
        Player player2 = returnPlayer(players, x, y);
        Position pos2 = pawn2.getPosition();

        int a =0; int b=0;
        while(a <84){
            int A = pawn1.getPath().getAX()[player1.getTurn()-1][a];
            int B = pawn1.getPath().getAY()[player1.getTurn()-1][a];
            if(pos2.equals(new Position(A,B))){
                break;
            }
            a++;
        }
        while(b<84){
            int A = pawn2.getPath().getAX()[player2.getTurn()-1][b];
            int B = pawn2.getPath().getAY()[player2.getTurn()-1][b];
            if(pos1.equals(new Position(A,B))){
                break;
            }
            b++;
        }
        pawn1.setCurrent(a);
        pawn2.setCurrent(b);
    }

    /***
     * Method for place traps mine
     * @param players, pawn
     */
    public void putTrapMine(Player[] players, Pawn pawn){
        Position[] pawnsadelante = new Position [3];
        Position[] pawnatras = new Position [3];
        int pos1 =0;
        int a = 3;
        while(a < 76){
            int A = pawn.getPath().getOptionalAX()[0][a];
            int B = pawn.getPath().getOptionalAY()[0][a];
            if(pawn.getPosition().equals(new Position(A,B))){
                for (int i=1; i<=3; i++){
                    int C = pawn.getPath().getOptionalAX()[0][a + i];
                    int D = pawn.getPath().getOptionalAY()[0][a + i];
                    int E = pawn.getPath().getOptionalAX()[0][a - i];
                    int F = pawn.getPath().getOptionalAY()[0][a - i];
                    pawnsadelante[i-1]= new Position(C,D);
                    pawnatras[i-1]= new Position(E,F);
                }
                break;
            }
            a++;
        }
        while(pos1 < players.length){
            int i = 0;
            while(i < players[pos1].getPawns().length){
                for(int j=0; j < 3; j++){
                    if(players[pos1].getPawns()[i].getPosition().equals(pawnsadelante[j])){
                        players[pos1].getPawns()[i].setCurrent(players[pos1].getPawns()[i].getCurrent()+3);
                    }
                    if(players[pos1].getPawns()[i].getPosition().equals(pawnatras[j])){
                        players[pos1].getPawns()[i].setCurrent(players[pos1].getPawns()[i].getCurrent()-3);
                    }
                }
                i++;
            }
            pos1++;
        }
        pawn.setCurrent(-1);
    }

    /**
     * Metodo sobreescrito
     * @param player, x, y
     * @return verifica la posicion de una ficha cen choque con una trampa
     */
    public boolean trustPositionPawn(Player player, int x, int y){
        boolean test = false;
        int i = 0;
        Position pos = new Position(x, y);
        while(i < player.getPawns().length){
            if(pos.equals(player.getPawns()[i].getPosition())){
                return true;
            }
            i++;
        }
        return test;
    }

    /**
     * Metodo para verificar la posicion de una ficha
     * @param player, pos
     * @return retorna una ficha en una posicion que pueda haber o no una trampa
     */
    private Pawn returnPawn(Player player, Position pos){
        int i = 0;
        while(i < player.getPawns().length){
            if(pos.equals(player.getPawns()[i].getPosition())){
                return player.getPawns()[i];
            }
            i++;
        }
        return null;
    }

    /**
     * Metodo para verificar la posicion de una ficha en casillas de comodin
     * @param players, pos1
     * @return retorna la ficha que se encuentre en dichas posiciones
     */
    private Pawn returnPawn(Player[] players, Position pos1){
        int i = 0;
        Pawn pawn = new Pawn(0,0,null);
        while(i < players.length){
            int pos = 0;
            Pawn[] pawns = players[i].getPawns();
            while(pos < pawns.length){
                if(pos1.equals(players[i].getPawns()[pos].getPosition())){
                    return players[i].getPawns()[pos];
                }
                pos++;
            }
            i++;
        }
        return pawn;
    }

    /**
     * Metodo para devolver un jugador mediante una posicion
     * @param players, x, y
     * @return Player
     */
    private Player returnPlayer(Player[] players, int x, int y){
        int i = 0;
        Position pos1 = new Position(x, y);
        Player player = new Player("",0,0, null);
        while(i < players.length){
            int pos = 0;
            Pawn[] pawns = players[i].getPawns();
            while(pos < pawns.length){
                if(pos1.equals(players[i].getPawns()[pos].getPosition())){
                    return players[i];
                }
                pos++;
            }
            i++;
        }
        return player;
    }

    /**
     * Medoto que devuelve la posicion de la trampa que se obtuvo
     */
    public Position getPos(){
        return pos;
    }

    /**
     * Metodo sobre escrito para dibujar un cuadrado del color del jugador
     * @param gr, int, int, int, int, Color
     */
    public void paint(Graphics gr, int h, int w, int x, int y, Color color) {
        gr.setColor(color);
        gr.drawRect(x, y, w, h);
    }
}
