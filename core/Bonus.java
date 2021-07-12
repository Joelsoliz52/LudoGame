package core;

import entities.Dice;
import entities.Pawn;
import entities.Player;
import entities.Position;
import interfaces.Comodin;
import interfaces.ComodinCallback;

import java.io.Serializable;

/**
 * Class to activate bonus
 */

public class Bonus implements Comodin, Serializable {
    private Position pos;
    private ComodinCallback callback;
    
    public Bonus(){}

    public Bonus(Player[] players, int x, int y, RunLogic logic,Pawn pawn, ComodinCallback callback){
        this.callback = callback;
        int al = aleatori();
        pos = new Position(x, y);
        Player player = returnPlayer1(players, pawn);
        if(trustPositionPawn(player, x, y)){
            if(al == 1){
                bonusShield(pawn);
                player.setFlagBonus(1);
            }else{
                if(al == 2){
                    bonusJumperTime(pawn);
                    player.setFlagBonus(2);
                }else{
                    if(al == 3){
                        bonusNewThrowDice(player);
                    }else{
                        bonusSpeed(logic.getDice(), pawn);
                        player.setFlagBonus(4);
                    }
                }
            }
        }
    }
    
    /**
     * Method to choose a random bonus
     */
    public int aleatori(){return (int)(Math.random()*4)+1;}

    /**
     * Method to obtain shield against any trap
     * @param pawn, pawn that won bonus.
     */
    public void bonusShield(Pawn pawn){
    	pawn.setFlag(true);
    }
    
    /**
     * Method for having time jump bonus
     * @param pawn, pawn that won bonus
     */
    public void bonusJumperTime(Pawn pawn){
        int alc = (int)(Math.random()*2)+1;
        if(alc == 1){
            if((pawn.getCurrent() + 10) < 83) {
                pawn.setCurrent(pawn.getCurrent() + 10);
            }else {
                pawn.setCurrent(82);
            }
        }else{
            pawn.setCurrent(pawn.getCurrent()-10);
        }
        callback.onCallback(pawn);
    }
     
    /**
     * Method to roll dice again
     * @param player, player that won bonus
     */
    public void bonusNewThrowDice(Player player){
        player.setFlagBonus(3);
    }
    
    /**
     * Method for having bonus velocity
     * @param dice, amount to advance
     * @param pawn, pawn that won bonus
     */
    public void bonusSpeed(Dice<Integer> dice, Pawn pawn){
        dice.throwDice();
        int pos = dice.content;
        pawn.setCurrent(pawn.getCurrent() + pos);
        callback.onCallback(pawn);
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
     * Method to return position of the pawn according to x and y
     * @param player, player that won bonus
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
     * Method that returns the position of the trap that was obtained
     */
    public Position getPos(){return pos;}

}