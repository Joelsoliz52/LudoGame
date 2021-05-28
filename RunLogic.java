import java.awt.*;
import java.util.*;
/**
 * Write a description of class RunLogic here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RunLogic implements GameLogic<Integer>{
    public BuildPlayers players;
    private Board board;
    private Comodin comodin;
    private int currentPlayer;
    private Aliance aliance;
    private final Dice<Integer> dice;
    private int flag;
    public int tam;
    private int pos;
    private int loseTurn;
    public boolean doubleClicked;
    public ArrayList<Position> positionbox;
    public RunLogic(Board board, Aliance aliance){
        if (board == null) {
            this.board = board = new MRIBoard(new Position(80, 50), new Color[tam]);
        } else {
            this.board = board;
        }
        this.aliance = aliance;
        currentPlayer = 1;
        dice = new Dice<Integer>("int");
        dice.content = 0;
        flag = 0;
        loseTurn=0;
        doubleClicked=false;
        positionbox=new ArrayList<>();
        players = new BuildPlayers(tam, board.getHeight(), board.getWidth(), board);
    }
    
    /**
     * Returns dice.
     * @return Dice.
     */
    public Dice<Integer> getDice(){
        return dice;
    }
    
    /**
     * Returns flag.
     * @return If there is an movement pending returns 1, otherwise 0.
     */
    public int getFlag(){
        return flag;
    }
    
    /**
     * OnKeyPressed logic.
     */
    public void onKeyPressed() {
        for (int i = 0; i < 4; i++) {
            int current = players.getPlayer(currentPlayer).getPawns()[i].getCurrent();
            if (current != -1 && current != 83 || current != 79 && (current + dice.content) < 84) {
                flag = 1;
                break;
            }
        }
        if (flag == 0) {
            for (int i = 0; i < tam; i++) {
                int current = players.getPlayer(currentPlayer).getPawns()[i].getCurrent();

                if (current == -1) {
                    flag=1;
                    break;
                }
            }
        }
    }
    
    /**
     * OnMouseClicked logic.
     * @param x X position of the click.
     * @param y Y position of the click.
     */
    public void onMouseClicked(int x, int y) {
        Player player = players.getPlayer(currentPlayer);
        Position posi = new Position(x,y);
        if(!player.getFlagTraps()){
            int value = movePawn(player, x, y);
            if (value == -1) {
                moveInitPawn(player, x, y);
            }else{
                Position pos1 = new Position(0, 9);
                Position pos2 = new Position(9, 0);
                Position pos3 = new Position(8, 18);
                Position pos4 = new Position(18, 8);
                int al = (int)(Math.random()*2)+1;
                int poss1 = 0;
                while(poss1 < player.getPawns().length){
                    int j=0;
                    if(player.getPawns()[poss1].getPosition().equals(pos1) || 
                      player.getPawns()[poss1].getPosition().equals(pos2) ||
                      player.getPawns()[poss1].getPosition().equals(pos3) ||
                      player.getPawns()[poss1].getPosition().equals(pos4)){
                        if(al == 1){
                            comodin = new Traps();
                            chooseCasilla(comodin, x, y,player.getPawns()[poss1]);
                        }else if(al==2){
                            comodin = new Bonus();
                            chooseCasilla(comodin,x, y,player.getPawns()[poss1]); 
                        }
                    }else if(positionbox !=null){
                        while(j < positionbox.size()){
                            if(player.getPawns()[poss1].getPosition().equals(positionbox.get(j))){
                                comodin = new Traps();
                                chooseCasilla(comodin, x, y,player.getPawns()[poss1]);
                                positionbox.remove(j);
                            }
                            j++;
                        }
                    }
                    poss1++;
                }
            }
        }
    }
    
    /**
     * doubleMouseClicked logic.
     * @param x X position of the click.
     * @param y Y position of the click.
     */
    public void doubleMouseClicked(int x, int y) {
        positionbox.add(new Position(x,y));
    }
    
    /**
     * Move a pawn from initial position.
     * @param player Current player.
     * @param x X position.
     * @param y Y position.
     */
    private void moveInitPawn(Player player, int x, int y) {
        for (int i = 0; i < 4; i++) {
            Pawn pawn = player.getPawns()[i];

            if (pawn.getCurrent() == -1) {
                Position posPawn = pawn.getPosition();
                Position pos1 = new Position(x, y);
                Position pos2 = new Position(x + 1, y);
                
                if (posPawn.equals(pos1) || posPawn.equals(pos2)) {
                    pawn.setCurrent(0);
                    flag = 0;
                    break;
                }
            }
        }
    }
    
    /**
     * Try to move a pawn in the board.
     * @param player Current player.
     * @param x X position.
     * @param y Y position.
     * @return Returns the value of the pawn to move if it exists, otherwise returns -1.
     */
    private int movePawn(Player player, int x, int y) {
        int value = -1;

        for (int i = 0; i < 4; i++) {
            Position posPawn = player.getPawns()[i].getPosition();
            int current = player.getPawns()[i].getCurrent();
            int num = 0;
            
            if(players.getPlayer(currentPlayer).getColor() == Color.BLUE){num = 84;}
            else if(players.getPlayer(currentPlayer).getColor() == Color.GREEN){num = 80;}
            else if(players.getPlayer(currentPlayer).getColor() == Color.RED){num = 84;}
            else if(players.getPlayer(currentPlayer).getColor() == Color.YELLOW){num = 80;}

            if (posPawn.equals(new Position(x, y)) && (current + dice.content) < num && current != -1) {
                value = i;
                flag = 0;
                break;
            }
        }

        if (value != -1) {
            Pawn pawn = player.getPawns()[value];
            pawn.setCurrent(pawn.getCurrent() + dice.content);
            int current = pawn.getCurrent();
            
            if(player.getColor() == Color.BLUE){
                if (current == 83) player.incrementCoin();
            }else if(player.getColor() == Color.GREEN){
                if (current == 79) player.incrementCoin();//79
            }else if(player.getColor() == Color.RED){
                if (current == 83) player.incrementCoin();
            }else if(player.getColor() == Color.YELLOW){
                if (current == 79) player.incrementCoin();
            }
        }

        return value;
    }
    
    private int movePawnAliance(Player player, Player player1, int x, int y){
        int value = -1;

        for (int i = 0; i < 4; i++) {
            Position posPawn = player.getPawns()[i].getPosition();
            Position posPawn1 = player1.getPawns()[i].getPosition();
            int current = player.getPawns()[i].getCurrent();
            int current1 = player1.getPawns()[i].getCurrent();
            if ((posPawn.equals(new Position(x, y)) && (current + dice.content) <= 56 && current != -1) ) {
                value = i;
                flag = 0;
                break;
            }
        }

        if (value != -1) {
            Pawn pawn = player.getPawns()[value];
            Pawn pawn1 = player1.getPawns()[value];
            pawn.setCurrent(pawn.getCurrent() + dice.content);
            pawn1.setCurrent(pawn1.getCurrent() + dice.content);
            int current = pawn.getCurrent();
            int current1 = pawn1.getCurrent();
            int k = 0;

            if (current == 56 || current1 == 56)
                player.incrementCoin();

        }

        return value;
    }
    
    private void chooseCasilla(Comodin comodin, int x, int y, Pawn pawn){
        if(comodin instanceof Traps){
            comodin = new Traps(this.players, this.aliance, x, y, this, pawn);
        }else{
            comodin = new Bonus(this.players, this.aliance, x, y, this, pawn);
        }
    }
    
    private Comodin getComodin(){
        return comodin;
    }
    
    /**
     * Paint the current view.
     * @param graphics Drawing controller.
     */
    public void paint(Graphics2D graphics) {
        board.draw(graphics);
        players.draw(graphics);
        Player player = players.getPlayer(currentPlayer);
        //areglar
        if(player.getCoin() == 4) {
            graphics.setColor(Color.WHITE);
            graphics.fillRect(590, 100, 380,130);
            graphics.setColor(player.getColor());
            graphics.setFont(new Font("serif", Font.BOLD, 40));
            graphics.drawString(players.players[pos].getName()+ " " + " wins.", 600, 150);
            graphics.drawString("Congratulations.", 600, 200);
            currentPlayer = 1;
            board = new RunBoard(new Position(80, 50), new Color[tam]);
            players = new BuildPlayers(tam, board.getHeight(), board.getWidth(), board);
            dice.content = 0;
            flag = 0;
        } else if(dice.content != 0){
            if(pos == tam){
                pos = 0;
            }
            graphics.setColor(Color.WHITE);
            graphics.fillRect(690, 100, 260,180);
            graphics.setColor(player.getColor());
            graphics.setFont(new Font("serif", Font.BOLD, 40));
            graphics.drawString(players.players[pos].getName() + " " + "you", 700, 150);
            if(!player.getFlagTraps()){
                graphics.drawString("Number on ", 700, 200);
                graphics.drawString("dice is " + dice.content, 700, 250);
            }else {
                graphics.drawString("Lose Turn ", 700, 200);
                currentPlayer = (currentPlayer + 1) % tam;
                if(player.getloseTurn()<=loseTurn){
                    player.setloseTurn(player.getloseTurn()+1);
                }else{player.setFlagTraps(false);}
                if(currentPlayer == 0){
                    currentPlayer = tam;
                }
            }
        }
        
        if(flag == 0 && dice.content != 0  && dice.content != 6 && !player.getFlagBonus()) {
            currentPlayer = (currentPlayer + 1) % tam;
            
            if(currentPlayer == 0){
                currentPlayer = tam;
            }
            pos++;
        } else if(player.getFlagBonus()){ // el jugador es el que tiene el bonus, pero talves deberia 
            player.setFlagBonus(false);
        }
    }
    
    public boolean getdoubleClicked()
    {
        return doubleClicked;
    }
    
    public void setdoubleClicked(boolean doubleClicked)
    {
        this.doubleClicked=doubleClicked;
    }
}