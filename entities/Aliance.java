package entities;

import java.util.*;

import core.BuildPlayers;
import core.RunLogic;
import core.Traps;
import layouts.Boards.RunBoard;

/**
 * Write a description of class Aliance here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Aliance{
    private BuildPlayers players;
    private RunBoard boardr;
    private int pos;
    private final Dice<Integer> dice;
    private Player[] aliance1;
    private Player[] aliance2;
    private HashMap<Integer, Boolean> map;
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public Aliance(int tam, BuildPlayers players, RunBoard boardr){
        this.boardr = boardr;
    	dice = new Dice<>("int");
        map = new HashMap();
        aliance1 = new Player[2];
        aliance2 = new Player[2];
        this.players = players;
        pos = 0;
        hashMap(map);
        addAliance(aliance1, players.players, map);
        addAliance(aliance2, players.players, map);
    }
    
    /**
     * Metodo para agregar una arreglo de jugadores al azar para formar la alianza
     * @param p
     * @param players
     * @param map
     */
    private void addAliance(Player[] p, Player[] players, HashMap<Integer, Boolean> map){
        pos = 0;
        while(pos < 2){
            int est = dice.throwDiceRun();
            int pos1 = est-1;
            if(knowTurn(est, map)){
                map.put(players[pos1].getTurn(), true);
                players[pos1].setFlagAliance(true);
                p[pos] = players[pos1];
                pos++;
            }
        }
    }
    
    /**
     * Metodo para agregar valores a un hashMap 
     * @param map
     */
    private void hashMap(HashMap<Integer, Boolean> map){
        map.put(1, false);
        map.put(2, false);
        map.put(3, false);
        map.put(4, false);
    }
    /**
     * Metodo para verificar si mediante un turno un jugador esta en una alianza
     * @param est
     * @param map
     * @return
     */
    private boolean knowTurn(int est, HashMap<Integer, Boolean> map){
        boolean b = false;
        if(map.get(est)){
            b = true;
        }
        return b;
    }
    
    /**
     * Metodo para crear una alianza
     * @param Player[], Player, Player
     */
    public void creatingAliance(Player[] p, Player p1, Player p2){
    	p1.setFlagAliance(true);
    	p2.setFlagAliance(true);
        map.put(p1.getTurn(), true);
        map.put(p2.getTurn(), true);
        if(!knowTurn(p1.getTurn(), map) && !knowTurn(p2.getTurn(), map)){
            if(p1.getTurn() < p2.getTurn()){
                p[0] = p2;
                p[1] = p1;
            }else{
                p[0] = p1;
                p[1] = p2;
            }
        }
    }
    
    
    /**
     * Metodos para contener una jugador en una alianza
     * @param Player
     * @return si el jugador esta en una alianza retorna true si no false
     */
    public boolean containsPlayerAliance1(Player player){
        if(getAliance1()[0].equals(player)){
            return true;
        }else{
            if(getAliance1()[1].equals(player)){
                return true;
            }else {
            	return false;
            }
        }
    }
    public boolean containsPlayerAliance2(Player player) {
    	if(getAliance2()[0].equals(player)){
            return true;
        }else{
            if(getAliance2()[1].equals(player)){
                return true;
            }else{
                return false;
            }
    	}
    }
    
    /**
     * Metodo para romper una alianza
     * @param Player[], Player
     * @return retorna el valor de true para verificar que se rompio una alianza
     */
    public boolean breakAliance(Player[] p, Player player){
    	p[0].setFlagAliance(false);
    	p[1].setFlagAliance(false);
        p = new Player[2];
        Traps trap = new Traps();
        putTrapAfterAliance(player, trap);
        return true;
    }
    
    /**
     * Metodo para colocar una trampa despues de terminar una alianza
     * @param int, Player, Traps
     */
    public void putTrapAfterAliance(Player player, Traps trap){
    	int x = player.getPawns()[0].getPosition().getX();
    	int y = player.getPawns()[0].getPosition().getY();
    	trap = new Traps(players.players, x, y, new RunLogic(boardr, this),player.getPawns()[0]);
    }
    
    /**
     * Retorna la primera alianza
     * @return
     */
    public Player[] getAliance1(){
        return aliance1;
    }
    
    /**
     * Retorna la segunda alianza
     * @return
     */
    public Player[] getAliance2(){
        return aliance2;
    }
}
