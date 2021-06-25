package core;

import entities.Dice;
import entities.Pawn;
import entities.Player;
import entities.Position;
import interfaces.Comodin;
import java.awt.*;

public class Bonus implements Comodin{
    private Position pos;
    public Bonus(){}
    public Bonus(Player[] players, int x, int y, RunLogic logic,Pawn pawn){
        int al = aleatori();
        pos = new Position(x, y);
        Player player = returnPlayer1(players, pawn);
        if(trustPositionPawn(player, x, y)){
            if(al == 1){
                bonusShield(pawn);
                player.setFlagBonus(1);
            }else{
                if(al == 2){
                    bonusJumperTime(pawn, player);
                    player.setFlagBonus(2);
                }else{
                    if(al == 3){
                        bonusNewThrowDice(player);
                    }else{
                        bonusSpeed(logic.getDice(), pawn, player);
                        player.setFlagBonus(4);
                    }
                }
            }
        }
    }
    
    /**
     * Metodo para elegir al azar un bonus
     */
    public int aleatori(){return (int)(Math.random()*4)+1;}
    /**
     * Metodo escudo para cualquier trampa
     * @param pawn, x, y
     */
    public void bonusShield(Pawn pawn){
    	pawn.setFlag(true);
    }
    
    /**
     * Metodo salto de tiempo
     */
    public void bonusJumperTime(Pawn pawn, Player player){
        int alc = (int)(Math.random()*2)+1;
        if(alc == 1){
            if(pawn.getCurrent()+10 > 79 && (player.getColor()==Color.GREEN || player.getColor()==Color.YELLOW)){
                pawn.setCurrent(78);
            }else if(pawn.getCurrent()+10 > 83 && (player.getColor()==Color.BLUE || player.getColor()==Color.RED)){
                pawn.setCurrent(82);
            }else{
                pawn.setCurrent(pawn.getCurrent() + 10);
            }
        }else{
                pawn.setCurrent(pawn.getCurrent()-10);
        }
        
    }
     
    /**
     * Metodo para lanzar nuevamente dado
     * @param player, pawn, x, y
     */
    public void bonusNewThrowDice(Player player){
        player.setFlagBonus(3);
    }
    
    /**
     * Method for having bonus velocity
     */
    public void bonusSpeed(Dice<Integer> dice, Pawn pawn, Player player){
        dice.throwDice();
        int pos = dice.content;
        if(pawn.getCurrent()+pos > 79 && (player.getColor()==Color.GREEN || player.getColor()==Color.YELLOW)){
            pawn.setCurrent(78);
        }else if(player.getColor() == Color.GREEN || player.getColor() == Color.YELLOW){
            pawn.setCurrent(pawn.getCurrent() + pos);
        }else{pawn.setCurrent(pawn.getCurrent() + pos);
        }
        
    }
    
    private Player returnPlayer1(Player[] players, Pawn pawn){
        int i = 0;
        Player player = new Player("",0,0, null);
        while(i < players.length){
            int pos = 0;
            Pawn[] pawns = players[i].getPawns();
            while(pos < pawns.length){
                if(pawn.equals(players[i].getPawns()[pos])){
                    return players[i];
                }
                pos++;
            }
            i++;
        }
        return player;
    }
    
    /**
     * Metodo para devuelve posicion ficha segun x y y se usa en bonus speed, jumptime
     * @param player, x, y
     * @return boolean
     */
    public boolean trustPositionPawn(Player player, int x, int y){
        int i = 0;
        Position pos = new Position(x, y);
        while(i < player.getPawns().length){
            if(pos.equals(player.getPawns()[i].getPosition())){
                return true;
            }
            i++;
        }
        return false;
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