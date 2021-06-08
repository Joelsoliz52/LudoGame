package core;

import entities.Aliance;
import entities.Dice;
import entities.Pawn;
import entities.Player;
import entities.Position;
import interfaces.Comodin;
import java.awt.*;

public class Bonus implements Comodin{
    private Position pos;
    private Aliance aliance;
    public Bonus(){}
    public Bonus(Player[] players, Aliance aliance,int x, int y, RunLogic logic,Pawn pawn){
        this.aliance = aliance;
        int al = aleatori();
        pos = new Position(x, y);
        Player player = returnPlayer(players, pos);
        if(trustPositionPawn(player, x, y)){
            if(al == 1){
                bonusShield(player, x, y);
            }else{
                if(al == 2){
                    bonusJumperTime(player, pawn);
                }else{
                    if(al == 3 && player.getFlagAliance()){
                        bonusTwoThrowDice(player);
                    }else{
                        if(al == 4){
                            bonusNewThrowDice(player, pawn,x ,y);
                        }else{
                            bonusSpeed(player, logic.getDice(), x, y, pawn);
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Metodo para elegir al azar un bonus
     */
    public int aleatori(){return (int)(Math.random()*5)+1;}
    /**
     * Metodo escudo para cualquier trampa
     * @param Player, int, int, Traps
     * @return void
     */
    public void bonusShield(Player player, int x, int y){
    	Position pos = new Position(x, y);
    	Pawn pawn = returnPawn(player, pos);
    	pawn.setFlag(true);
    }
    
    /**
     * Metodos para verificar la posicion de una ficha
     * @param Player, Position
     * @return Pawn
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
     * Metodo para solo alianzas usar dos dados  // FALTA
     * @param Player
     * @return void
     */
    public void bonusTwoThrowDice(Player player){
    	if(aliance.containsPlayerAliance1(player)) {
        	aliance.getAliance1()[0].setFlagBonus(true);
        	aliance.getAliance1()[1].setFlagBonus(true);
        }else {
        	aliance.getAliance2()[0].setFlagBonus(true);
        	aliance.getAliance2()[1].setFlagBonus(true);
        }
    }
    
    /**
     * Metodo salto de tiempo
     * @param Player, Pawn
     * @return void
     */
    public void bonusJumperTime(Player player, Pawn pawn){
        int alc = (int)(Math.random()*2)+1;
        if(alc == 1)
                pawn.setCurrent(pawn.getCurrent()+10);
            else
                pawn.setCurrent(pawn.getCurrent()-10);
        
    }
     
    /**
     * Metodo para lanzar nuevamente dado
     * @param Player, Pawn, int, int
     * @return void
     */
    public void bonusNewThrowDice(Player player, Pawn pawn,int x, int y){
        player.setFlagBonus(true);
    }
    
    /**
     * Method for having bonus velocity
     * @param Player, Dice<Integer>, int, int, Pawn
     * @return void
     */
    public void bonusSpeed(Player player, Dice<Integer> dice, int x, int y, Pawn pawn){
        dice.throwDice();
        int pos = dice.content;
        pawn.setCurrent(pawn.getCurrent()+pos);
    }
    
    /**
     * Metodo para devolver un jugador 
     * @param Player[], Position
     * @return Player 
     */
    private Player returnPlayer(Player[] players, Position pos1){
        int i = 0;
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
     * Metodo para devuelve posicion ficha segun x y y se usa en bonus speed, jumptime
     * @param Player, int, int
     * @return boolean
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
     * Metodo sobreescrito de comodin 
     */
    public Position getPos(){return pos;}
	@Override
	public void paint(Graphics gr, int h, int w, int x, int y, Color color) {
		// TODO Auto-generated method stub
		
	}
    
}
