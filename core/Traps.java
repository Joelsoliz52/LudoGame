package core;

import entities.Dice;
import entities.Pawn;
import entities.Player;
import entities.Position;
import interfaces.Comodin;
import interfaces.ComodinCallback;

import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 * Class to set or activate trap
 */
public class Traps implements Comodin, Serializable {
    private Position pos;
    private ComodinCallback callback;

    public Traps(){}

    public Traps(Player[] players, int x, int y, RunLogic logic,Pawn pawnA, ComodinCallback callback){
        this.callback = callback;
        pos = new Position(x, y);
        Position pos1 = new Position(0, 9);
        Position pos2 = new Position(9, 0);
        Position pos3 = new Position(8, 18);
        Position pos4 = new Position(18, 8);
        int al = aleatori();
        Player player = returnPlayer(players, pawnA);
        if(!pos.equals(pos1) && !pos.equals(pos2) && !pos.equals(pos3) && !pos.equals(pos4)){
            pos.setFlagTraps(true);
        }
        if(trustPositionPawn2(pawnA, players) && (!pos.getFlagTraps())){
            if (al ==1){
                JOptionPane.showMessageDialog(null, "Escoge una casilla realizando el doble click.");
                logic.setdoubleClicked(true);
            }else{
                if(al == 2){
                    putTrapLoseTurn(player);
                }else if(al == 3){
                    JOptionPane.showMessageDialog(null, "Escoge una ficha realizando el doble click.");
                    logic.setnewPositionPawn(true);
                    logic.setdoubleClicked(true);
                }
            }
        }else if(logic.getnewPositionPawn()){
            logic.setnewPositionPawn(false);
            newPositionPawn(pawnA, x, y, players);
        }else if(pos.getFlagTraps() && !pawnA.getFlag()&& player!=null){
            int al2 = (int)(Math.random()*4)+1;
            if(al2 == 1){
                putTrapReturnInit(pawnA);
                player.setFlagTraps(3);
            }else{
                if(al2 == 2){
                    putTrapRecoil(logic.getDice(), pawnA);
                    player.setFlagTraps(4);
                }else if(al2 == 3){
                    putTrapFreezePawn(pawnA);
                    player.setFlagTraps(5);
                }else{
                    putTrapMine(players, pawnA);
                    player.setFlagTraps(6);
                }
            }
        }else {
            pawnA.setFlag(false);
        }
    }

    /**
     * Method to choose a random trap
     */
    public int aleatori(){return (int)(Math.random()*3)+1;}

    @Override
    public boolean trustPositionPawn(Player player, int x, int y) {
        return false;
    }

    /**
     * Method to return to start
     * @param pawn Pawn that fell into the trap.
     */
    public void putTrapReturnInit(Pawn pawn){
        pawn.setCurrent(-1);
    }

    /**
     * Method of going back
     * @param dice amount to go back.
     * @param pawn Pawn that fell into the trap.
     */
    public void putTrapRecoil(Dice<Integer> dice, Pawn pawn){
        dice.throwDice();
        int pos = dice.content;
        pawn.setCurrent(pawn.getCurrent() - pos);
        callback.onCallback(pawn);
    }

    /**
     * Metodo para colocar una trampa de perdida de turno
     * @param player Player who fell into the trap.
     */
    public void putTrapLoseTurn(Player player){
        player.setFlagTraps(1);
    }

    /**
     * Method to freeze a pawn.
     * @param pawn Pawn that fell into the trap.
     */
    public void putTrapFreezePawn(Pawn pawn){
        pawn.setFreezePawn(true);
    }

    /**
     * Method to change the position of a pawn with another
     * @param pawn1, current player's pawn.
     * @param x X position of the pawn chosen.
     * @param y Y position of the pawn chosen.
     * @param players All the players, to know which one is in position.
     */
    public void newPositionPawn(Pawn pawn1, int x, int y, Player[] players){
        Position pos1 = pawn1.getPosition();
        Player player1 = returnPlayer(players, pawn1);
        Position pos = new Position (x,y);
        Pawn pawn2 = returnPawn(players, pos);
        Player player2 = returnPlayer(players, x, y);
        Position pos2 = pawn2.getPosition();

        int a =0; int b=0;
        if(pawn2.getPath() != null){
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
            callback.onCallback(pawn1, pawn2);
        }
    }

    /***
     * Method for place traps mine
     * @param players All the players, to know which one is in position.
     * @param pawn Pawn that fell into the mine.
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
                        callback.onCallback(pawn);
                    }
                    if(players[pos1].getPawns()[i].getPosition().equals(pawnatras[j])){
                        players[pos1].getPawns()[i].setCurrent(players[pos1].getPawns()[i].getCurrent()-3);
                        callback.onCallback(pawn);
                    }
                }
                i++;
            }
            pos1++;
        }
        pawn.setCurrent(-1);
    }

    /**
     * Method that checks position
     * @param pawn Pawn whose position is verified.
     * @param players All players.
     * @return verification
     */
    public boolean trustPositionPawn2(Pawn pawn, Player[] players){
        int i = 0;
        while(i < players.length){
            int pos = 0;
            Pawn[] pawns = players[i].getPawns();
            while(pos < pawns.length){
                if(pawn.equals(players[i].getPawns()[pos])){
                    return true;
                }
                pos++;
            }
            i++;
        }
        return false;
    }

    /**
     * Method to check the position of a pawn in wild card squares.
     * @param players Players whose pawn positions will be seen.
     * @param pos1 where to find a pawn.
     * @return pawn that is in position
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
     * Method to return a player by a position
     * @param players All players
     * @param x X position
     * @param y Y position
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
     * Method to return a player through a pawn
     * @param players All players
     * @param pawn Pawn to compare
     * @return Player
     */
    private Player returnPlayer(Player[] players, Pawn pawn){
        int i = 0;
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
        return null;
    }

    /**
     * Method that returns the position of the trap that was obtained
     */
    public Position getPos(){
        return pos;
    }
}
