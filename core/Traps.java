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
        positiones = logic.getPositions();
        Position pos = new Position(x, y);
        Position pos1 = new Position(0, 9);
        Position pos2 = new Position(9, 0);
        Position pos3 = new Position(8, 18);
        Position pos4 = new Position(18, 8);
        int al = aleatori();
        Player player = returnPlayer(players, x, y);
        if(pos.equals(pos1)==false && pos.equals(pos2)==false && pos.equals(pos3)==false && pos.equals(pos4)==false){ 
            int estPos = positiones.indiceDe(pos);
        	positiones.mostrar(estPos).setFlagTraps(true);
        }
        if(trustPositionPawn(player, x, y) && (!pos.getFlagTraps())){
        	JOptionPane.showMessageDialog(null, "escoge una casilla");
            escogecasilla(x, y);
        	if(al == 1){
        		putTrapLoseTurn(players, x, y);
            }else if(al == 2){
            	Pawn pawn = returnPawn(player, pos);
                newPositionPawn(pawn, players);
            }
        }else if(pos.getFlagTraps()){
        	int al2 = (int)(Math.random()*3)+1;
            if(al2 == 1){
                putTrapReturnInit(player, x, y);
            }else{
                if(al2 == 2){
                    putTrapRecoil(player, x, y, logic.getDice());
                }else{
                    Pawn pawn = returnPawn(player, pos);
                    putTrapMine(players, pawn);
                }
            }
        }
    }
    
    /**
     * Metodo para seleccionar una trampa al azar
     */
    public int aleatori(){return (int)(Math.random()*2)+1;}
    
    /**
     * Metodo trampa para volver al inicio
     * @param Player, int, int
     */
    public void putTrapReturnInit(Player player, int x, int y){
        Position current = new Position(x, y);
        if(trustPositionPawn(player, x, y)){
            Pawn pawn = returnPawn(player, current);
            pawn.setCurrent(-1);
        }
    }
    
    /**
     * Metodo para colocar una trampa de retroceso
     * @param Player, int, int, Dice<Integer>
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
     * @param Player[], int, int
     */
    public void putTrapLoseTurn(Player[] players, int x, int y){
        Player player = returnPlayer(players, x, y);
        player.setFlagTraps(true);
    }
    
    /**
     * Metodo para colocar una trampa de congelar una ficha
     * @param Player, int, int
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
     * Metodo para escoger casilla donde ira una trampa
     * @param int, int
     */
    public void escogecasilla(int x, int y){
        logic.setdoubleClicked(true);
    }
    
    /**
     * Metodo para cambiar de posicion una ficha escoger ficha
     * @param Pawn, Player[]
     */
    public void newPositionPawn(Pawn pawn1, Player[] players){
        Pawn pawn2 = returnPawn(players, pawn1);
        if(trustPositionPawn(players, pawn1.getPosition())){
            if(pawn2 != null){
                int j= Math.abs(pawn1.getPosition().getY()- pawn2.getPosition().getY());
                pawn1.setCurrent(pawn1.getCurrent()+j);
                pawn2.setCurrent(pawn2.getCurrent()-j);
            }
        }
        int k = positiones.indiceDe(pawn2.getPosition());
        int k2 = positiones.indiceDe(pawn1.getPosition());
        int current = menorQue(k2, k);
        int current1 = mayorQue(k, k2);
        if(current <= current1) {
        	pawn1.setCurrent(pawn1.getCurrent() - current);
        	pawn2.setCurrent(pawn2.getCurrent() + current);
        } else {
        	pawn1.setCurrent(pawn1.getCurrent() - current1);
        	pawn2.setCurrent(pawn2.getCurrent() + current1);
        }
    }
   
    /**
     * Metodo para encontrar un camino hacia atras en un ListaSEC mediante indices de un valor
     * @param int, int
     * @return retorna el mayor camino hacia atras de una ficha en intercambio
     */
    private int menorQue(int k, int k2) {
    	int i = 0;
    	if(k == k2) {
    		return i;
    	} else {
    		return i = menorQue(k-1, k2)+1;
    	}
    }
    
    /**
     * Metodo para encontrar un camino hacia delante en un ListaSEC mediante indices de un valor
     * @param int, int
     * @return retorna el mayor camino hacia delante de una ficha en intercambio
     */
    private int mayorQue(int k, int k2) {
    	int i = 0;
    	if(k2 == k) {
    		return i;
    	} else {
    		return i = mayorQue(k, k2+1)+1;
    	}
    }
    
    /***
     * Method for place traps mine 
     * @param Pawn
     */
    public void putTrapMine(Player[] players, Pawn pawn){
        int pp = 1;
        int pp1= 2;
        Pawn[] pawnsadelante = addPawns(players, pawn, pp);
        Pawn[] pawnatras = addPawns(players, pawn, pp1);
        Pawn[] pawns = addPawns(players);
        int pos = 0;
        int pos1 =0;
        pawn.setCurrent(-1);
        int i = 0;
        int k = positiones.indiceDe(pawn.getPosition());
        while(i < pawns.length) {
        	int n = 1;
        	while(n < 4) {
        		if((k-n) == positiones.indiceDe(pawns[i].getPosition())) {
            		pawns[i].setCurrent(pawns[i].getCurrent()-3);
            	}else {
            		if((k+n) == positiones.indiceDe(pawns[i].getPosition())) {
            			pawns[i].setCurrent(pawns[i].getCurrent()+3);
            		}
            	}
        	}
        	i++;
        }
        
        while(pos < pawnsadelante.length){
            pawnsadelante[pos].setCurrent(pawnsadelante[pos].getCurrent()-3);
            pos++;
        }
        while(pos1 < pawnatras.length){
            pawnatras[pos1].setCurrent(pawnatras[pos1].getCurrent()+3);
            pos1++;
        }
    }
    
    /**
     * Agrega fichas que esten cerca de una mina
     * @param Player[], Pawn, int
     * @return arreglo de fichas que esten cerca de la mina
     */
    private Pawn[] addPawns(Player[] players, Pawn pawn, int pp){
        int tam = 1;
        int pos1 = 0;
        int n=1;
        Pawn[] pawns = new Pawn[tam];
        Position poss= pawn.getPosition();
        while(n<4){
            int x1 = poss.getX();
            int y1 =0;
            if(pp==1){
                y1 = poss.getY()+n;
            }else{  y1 = poss.getY()-n;}
            Position pos2 = new Position(x1, y1);
        
            while(pos1 < players.length){
                int i = 0;
                while(i < players[pos1].getPawns().length){
                if(players[pos1].getPawns()[i].getPosition().equals(pos2)){
                    pawns[tam-1] = players[pos1].getPawns()[i]; 
                    
                    tam++;
                }
                i++;
                }
                pos1++;
            }
            pos1=0;
            n++;
        }
        pawns[tam-1]=null;
       
        return pawns;
    }
    
    /**
     * Metodo para almacenar fichas
     * @param Player[]
     * @return devuelve todas las fichas en el camino principal
     */
    private Pawn[] addPawns(Player[] players) {
    	int i = 0;
    	int tam = 1;
    	Pawn[] pawns = new Pawn[tam];
    	while(i < players.length) {
    		int j = 0;
    		while(j < players[i].getPawns().length) {
    			if(players[i].getPawns()[j].getCurrent() != -1) {
	    			pawns[tam-1] = players[i].getPawns()[j];
	    			j++;
    			}
    		}
    		i++;
    	}
    	return pawns;
    }
    
    /**
     * Metodo sobreescrito
     * @param Player, int, int
     * @return verifica la posicion de una ficha cen choque con una trampa
     */
    public boolean trustPositionPawn(Player player, int x, int y){
        boolean test = false;
        int i = 0;
        Position pos = new Position(x, y);
        while(i < player.getPawns().length){
            if(pos.equals(player.getPawns()[i].getPosition())){
                return test = true;
            }
            i++;
        }
        return test;
    }
   
    /**
     * Metodo para verificar la posicion de una ficha
     * @param Player, Position
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
     * @param Player, Position
     * @return retorna la ficha que se encuentre en dichas posiciones
     */
    private Pawn returnPawn(Player[] players, Pawn pawn){ // devuelve otra posicion
        int i = 0;
        Position pos1 = new Position(0, 9);
            Position pos2 = new Position(9, 0);
            Position pos3 = new Position(8, 18);
            Position pos4 = new Position(18, 8);
            Position pos=null;
        if(pos1.equals(pawn.getPosition())){
            pos = new Position(0, 9-4);
        } else if(pos2.equals(pawn.getPosition())){
            pos = new Position(9+1,0+ 4);
        } else if(pos3.equals(pawn.getPosition())){
            pos = new Position(8, 18-4);
        } else if(pos4.equals(pawn.getPosition())){
            pos = new Position(18, 8+4);}
        
        while(i < players.length){
            int j = 0;
            while(j < players[i].getPawns().length){
                    if (pos.equals(players[i].getPawns()[j].getPosition())) {
                        return players[i].getPawns()[j];
                    }
                j++;
            }
            i++;
        }
        return null;
    } 
    
    /**
     * Metodo para devolver un jugador mediante una posicion
     * @param Player[], int, int
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
                    return player = players[i];
                }
                pos++;
            }
            i++;
        }
        return player;
    }
    
    /**
     * Metodo sobreescrito
     * @param Player[], Position
     * @return verifica la posicion de un jugador mediante la posicion de una ficha
     */
    public boolean trustPositionPawn(Player[] players, Position pos1){
        boolean test = false;
        int i = 0;
        while(i < players.length){
            int j = 0;
            while(j < players[i].getPawns().length){
                if(pos1.equals(players[i].getPawns()[j].getPosition())){
                    return test = true;
                }
                j++;
            }
            i++;
        }
        return test;
    }
    
    /**
     * Medoto que devuelve la posicion de la trampa que se obtuvo
     */
    public Position getPos(){
        return pos;   
    }
	
    /**
	 * Metodo para devolver posiciones
	 * @param ListaSEC<Posotion>
	 * @return retorna las posibles posiciones del camino principal
	 */
	public ListaSEC<Position> getPositions() {
		return positiones;
	}
	
	/**
	 * Metodo sobre escrito para dibujar un cuadrado del color del jugador
	 * @param Graphics, int, int, int, int, Color
	 */
	public void paint(Graphics gr, int h, int w, int x, int y, Color color) {
		gr.setColor(color);
		gr.drawRect(x, y, w, h);
	}
}
