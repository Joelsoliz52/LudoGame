import javax.swing.*;
import java.awt.*;
public class Bonus implements Comodin{
    private BuildPlayers players;
    private boolean flag;
    private Position pos;
    private Aliance aliance;
    private GameLogic<Integer> logic;
    private Pawn pawn;
    private final int heigth;
    private final int width;
    public Bonus(){
        heigth = width = 30;
    }
    public Bonus(BuildPlayers players, Aliance aliance,int x, int y, GameLogic<Integer> logic,Pawn pawn){
        this.players = players;
        this.aliance = aliance;
        this.logic = logic;
        this.pawn = pawn;
        int al = aleatori();
        pos = new Position(x, y);
        Player player = returnPlayer(players.players, pos);
        if(trustPositionPawn(player, x, y)){
            if(al == 1){
                bonusShield(player, x, y, new Traps());
            }else{
                if(al == 2){
                    bonusJumperTime(player, pawn);
                }else{
                    if(al == 0 && player.getFlagAliance()){
                            
                    }else{
                        if(al == 4){
                            bonusNewThrowDice(player, pawn,x ,y);
                        }else{
                            bonusSpeed(player, new Dice<Integer>("int"), x, y, pawn);
                        }
                    }
                }
            }
        }
        flag = false;
        heigth = width = 30;
    }
    /**
     * Metodo para elegir al azar un bonus
     */
    public int aleatori(){return (int)(Math.random()*5)+1;}
    /**
     * Metodo escudo para cualquier trampa
     */
    public void bonusShield(Player player, int x, int y, Traps trap){
        Position pos = new Position(x,y);
        if(pos.equals(trap.getPos())){
            trap = null;
        }
    }
    /**
     * Metodo para solo alianzas usar dos dados  // FALTA
     */
    public void bonusTwoThrowDice(Player player){
        flag = true;
    }
    /**
     * Metodo salto de tiempo
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
     */
    public void bonusNewThrowDice(Player player, Pawn pawn,int x, int y){
        flag = true;
        player.setFlagBonus(flag);
    }
    /**
     * Method for having bonus velocity
     */
    public void bonusSpeed(Player player, Dice<Integer> dice, int x, int y, Pawn pawn){
        dice.throwDice();
        int pos = dice.content;
        pawn.setCurrent(pawn.getCurrent()+pos);
    }
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
    public Position getPos(){
        return pos;
    }
    public void paint(Graphics gr, int h, int w,  int x, int y, Color color){
    }
    
}
