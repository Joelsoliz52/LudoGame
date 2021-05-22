import java.awt.Graphics;
import java.awt.Color;
public class Traps implements Comodin{
    private BuildPlayers players;
    private boolean flag;
    private Aliance aliance;
    private Position pos;
    private final int heigth;
    private final int width;
    private GameLogic<Integer> logic;
    
    public Traps(){
        heigth = width = 30;
    }
    public Traps(BuildPlayers players, Aliance aliance, int x, int y, GameLogic<Integer> logic){
        this.players = players;
        this.aliance = aliance;
        this.logic = logic;
        pos = new Position(x, y);
        flag = true;
        int al = aleatori();
        Player player = returnPlayer(players.players, x, y);
        if(trustPositionPawn(player, x, y)){
            if(al == 1){
                putTrapReturnInit(player, x, y);
            }else{
                if(al == 2){
                    putTrapRecoil(player, x, y, logic.getDice());
                }else{
                    if(al == 3){
                        putTrapLoseTurn(players.players, x, y);
                    }else{
                        if(al == 4){
                            Pawn pawn = returnPawn(player, pos);
                            putTrapMine(pawn);
                        }else{
                            if(al == 5){
                               Pawn pawn = returnPawn(player, pos);
                               newPositionPawn(pawn, players.players); 
                            }else{
                               putTrapFreezePawn(players.players, x, y);
                            }
                        }
                    }
                }
            }
        }
        heigth = width = 30;
    }
    /**
     * Metodo para seleccionar una trampa al azar
     */
    public int aleatori(){return (int)(Math.random()*6)+1;}
    /**
     * Metodo para volver al inicio  escoger una casilla
     */
    public void putTrapReturnInit(Player player, int x, int y){
        Position current = new Position(x, y);
        if(trustPositionPawn(player, x, y)){
            Pawn pawn = returnPawn(player, current);
            pawn.setCurrent(-1);
        }
    }
    /**
     * Metodo para colocar una trampa retroceso escoger una casilla
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
     * Metodo para colocar una trampa perdida de turno    directo
     */
    public void putTrapLoseTurn(Player[] players, int x, int y){
        Player player = returnPlayer(players, x, y);
        player.setFlagTraps(true);
    }
    /**
     * Metodo para colocar una trampa despues de terminar una alianza
     */
    public void putTrapAfterAliance(int numrandom, Player player, Traps trap){
        flag = false;
    }
    /**
     * Metodo para colocar una trampa de congelar una ficha   NO TERMINADO
     */
    public void putTrapFreezePawn(Player[] players, int x, int y){
        Position poss = new Position(x, y);
        Pawn pawn = returnPawn(players, poss);
    }
    /**
     * Metodo para cambiar de posicion una ficha     escoger ficha
     */
    public void newPositionPawn(Pawn pawn1, Player[] players){
        Pawn pawn2 = returnPawn(players, pawn1);
        Position pos = pawn1.getPosition();
        Position pos2 = pawn2.getPosition();
        if(trustPositionPawn(players, pawn1.getPosition())){
            if(pawn2 != null){
                int j= Math.abs(pawn1.getPosition().getY()- pawn2.getPosition().getY());
                pawn1.setCurrent(pawn1.getCurrent()+j);
                pawn2.setCurrent(pawn2.getCurrent()-j);
            }
        }
    } 
    /***
     * Method for place traps mine 
     */
    public void putTrapMine(Pawn pawn){
        int pp = 1;
        int pp1= 2;
        Pawn[] pawnsadelante = addPawns(players.players, pawn, pp);
        Pawn[] pawnatras = addPawns(players.players, pawn, pp1);
        int pos = 0;
        int pos1 =0;
        pawn.setCurrent(-1);
        
        while(pos < pawnsadelante.length){
            pawnsadelante[pos].setCurrent(pawnsadelante[pos].getCurrent()-3);
            pos++;
        }
        while(pos1 < pawnatras.length){
            pawnatras[pos1].setCurrent(pawnatras[pos1].getCurrent()+3);
            pos1++;
        }
    }
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
    private Pawn returnPawn(Player[] players, Position pos1){
        int i = 0;
        Pawn pawn = new Pawn(0,0,null);
        while(i < players.length){
            int pos = 0;
            Pawn[] pawns = players[i].getPawns();
            while(pos < pawns.length){
                if(pos1.equals(players[i].getPawns()[pos].getPosition())){
                    return pawn = players[i].getPawns()[pos];
                }
                pos++;
            }
            i++;
        }
        return pawn;
    }
    private Pawn returnPawn(Player[] players, Pawn pawn){
        int i = 0;
        Position pos1 = new Position(0, 9);
        Position pos2 = new Position(9, 0);
        Position pos3 = new Position(8, 18);
        Position pos4 = new Position(18, 8);
        Position pos = null;
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
                        Pawn pawn2= players[i].getPawns()[j];
                        Color color= players[i].getColor();
                        return players[i].getPawns()[j];
                    }
                j++;
            }
            i++;
        }
        return null;
    } 
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
    public Position getPos(){
        return pos;   
    }
    public void paint(Graphics gr, int h, int g, int x, int y, Color color){
        
    }
}
