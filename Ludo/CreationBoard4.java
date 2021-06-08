package Ludo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import core.BuildPlayers;
import core.GameMoves;
import core.MRILogic;
import core.RunLogic;
import entities.Aliance;
import entities.Player;
import entities.Position;
import layouts.Boards.MRIBoard;
import layouts.Boards.RunBoard;
import utilities.Buttons;
import utilities.Constants;

/**
 * Clase para crear un tablero MRI o Run.
 * @author RAUL
 *
 */
@SuppressWarnings("serial")
public class CreationBoard4 extends JDialog implements ActionListener{
    private JButton b1, b2, bd;
    private final JDialog boardMod;
    private Aliance aliance;
    private MRIBoard board;
    private RunBoard boardr;
    public BuildPlayers bul;
    public BuildPlayers bul1;
    private String[] name;
    private Color[] colores;
    private Buttons butons;
    private int tam;
    private int pos;
    public CreationBoard4(Menu2 parent,boolean modal){
        super(parent, modal);
        setLayout(null);
        setBounds(495,150,420,490);
        butons = new Buttons(this);
        butons.getB3().addActionListener(this);
        butons.getB4().addActionListener(this);
        butons.getB5().addActionListener(this);
        butons.getBa().addActionListener(this);
        butons.getBb().addActionListener(this);
        butons.getBs().addActionListener(this);
        butons.getBc1().addActionListener(this);
        butons.getBc2().addActionListener(this);
        butons.getBc3().addActionListener(this);
        butons.getBc4().addActionListener(this);
        
        boardMod = new JDialog();
        boardMod.setLayout(null);
        boardMod.setBounds(495,200,310,225);
        b1 = new JButton();
        b1.setBounds(33,30,100,100);
        boardMod.add(b1);
        b1.addActionListener(this);
        b2 = new JButton();
        b2.setBounds(166,30,100,100);
        boardMod.add(b2);
        b2.addActionListener(this);
        bd = new JButton("BACK");
        bd.setBounds(115,150,70,30);
        bd.setBackground(java.awt.Color.BLACK);
        bd.setForeground(Color.white);
        bd.setFont(new Font("Agency FB",Font.BOLD, 20));
        boardMod.add(bd);
        bd.addActionListener(this);
        
        pos = 0;
    }
    public void actionPerformed(ActionEvent e){
        //botones para elegir un arreglo de tamaño n;
        if(e.getSource() == butons.getB3()){
            tam = 2;
            name = new String[tam];
            colores = new Color[tam];
            butons.getB3().setEnabled(false);
            butons.getB4().setEnabled(false);
            butons.getB5().setEnabled(false);
        }
        if(e.getSource() == butons.getB4()){
            tam = 3;
            name = new String[tam];
            colores = new Color[tam];
            butons.getB3().setEnabled(false);
            butons.getB4().setEnabled(false);
            butons.getB5().setEnabled(false);
        }
        if(e.getSource() == butons.getB5()){
            tam = 4;
            name = new String[tam];
            colores = new Color[tam];
            butons.getB3().setEnabled(false);
            butons.getB4().setEnabled(false);
            butons.getB5().setEnabled(false);
        }
        
        if(butons.getB4().isEnabled() == false && butons.getB5().isEnabled() == false && butons.getB3().isEnabled() == false){
            
        }
        // boton para añadir un jugador al arreglo
       if(e.getSource() == butons.getBa()){
            name[pos] = butons.getTx().getText();
            pos++;
            if(pos == tam){
                butons.getBa().setEnabled(false);
                pos = 0;
            }
            butons.getTx().setText("");
        }
        Menu2 m = new Menu2();
        if(e.getSource() == butons.getBb()){
            this.setVisible(false);
            m.lastMenu.setVisible(true);
        }
        // botones para elegir el color
        if(butons.getBa().isEnabled()){
            butons.getBc1().setEnabled(false);
            butons.getBc2().setEnabled(false);
            butons.getBc3().setEnabled(false);
            butons.getBc4().setEnabled(false);
            butons.getBs().setEnabled(false);
        }
        if(!butons.getBc1().isEnabled()){
            butons.getBc1().setEnabled(true);
            butons.getBc2().setEnabled(true);
            butons.getBc3().setEnabled(true);
            butons.getBc4().setEnabled(true);
            butons.getBs().setEnabled(true);
        }
        if(e.getSource() == butons.getBc1() && pos < tam){
            colores[pos] = Color.RED;
            butons.getNom1().setText(""+name[pos]);
            pos++;
            butons.getBc1().setEnabled(false);
        }else{
            if(e.getSource() == butons.getBc1() && pos == tam){
                pos = 0;
                colores[pos] = Color.RED;
                butons.getNom1().setText(""+name[pos]);
                pos++;
                butons.getBc1().setEnabled(false);
            }
        }
        if(e.getSource() == butons.getBc2() && pos < tam){
            colores[pos] = Color.BLUE;
            butons.getNom2().setText(""+name[pos]);
            pos++;
            butons.getBc2().setEnabled(false);
        }else{
            if(e.getSource() == butons.getBc2() && pos == tam){
                pos = 0;
                colores[pos] = Color.BLUE;
                butons.getNom2().setText(""+name[pos]);
                pos++;
                butons.getBc2().setEnabled(false);
            }
        }
        if(e.getSource() == butons.getBc3() && pos < tam){
            colores[pos] = Color.YELLOW;
            butons.getNom3().setText(""+name[pos]);
            pos++;
            butons.getBc3().setEnabled(false);
        }else{
            if(e.getSource() == butons.getBc3() && pos == tam){
                pos = 0;
                colores[pos] = Color.YELLOW;
                butons.getNom3().setText(""+name[pos]); 
                pos++;
                butons.getBc3().setEnabled(false);
            }
        }
        if(e.getSource() == butons.getBc4() && pos < tam){
            colores[pos] = Color.GREEN;
            butons.getNom4().setText(""+name[pos]);
            pos++;
            butons.getBc4().setEnabled(false);
        }else{
            if(e.getSource() == butons.getBc4() && pos < tam){
                pos = 0;
                colores[pos] = Color.GREEN;
                butons.getNom4().setText(""+name[pos]);
                pos++;
                butons.getBc4().setEnabled(false);
            }
        }
        if(pos == tam){
            botonesF();
            board = new MRIBoard(new Position(80, 50), colores);
            board.tam = tam;
            boardr = new RunBoard(new Position(80, 50), colores);
            boardr.tam = tam;
            bul = new BuildPlayers(tam, board.getHeight(), board.getWidth(), board);
            bul1 = new BuildPlayers(tam, board.getHeight(), board.getWidth(), boardr);
            BuildP(bul);
            BuildP(bul1);
            if(tam > 3){
                aliance = new Aliance(tam,bul1, boardr);
            }
        }
        if(e.getSource() == butons.getBs()){
            this.setVisible(false);
            boardMod.setVisible(true);
        }
        if(e.getSource() == b1){
            boardMod.setVisible(false);
            JFrame frame = new JFrame();
            frame.setBounds(10, 10, 1250, 700); //1150
            frame.setTitle(Constants.TitleGame);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            MRILogic logic = new MRILogic(board);
            board.setBd(bul);
            logic.tam = this.tam;
            logic.players = board.bd;
            GameMoves gm = new GameMoves(logic);
            gm.setFocusable(true);
            gm.addKeyListener(gm);
            gm.addMouseListener(gm);
            frame.add(gm);
            frame.setVisible(true);
        }
        if(e.getSource() == b2){
            boardMod.setVisible(false);
            JFrame frame = new JFrame();
            frame.setBounds(10, 10, 1250, 700); //1150
            frame.setTitle(Constants.TitleGame);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            RunLogic logic = new RunLogic(boardr, aliance);
            boardr.setBd(bul1);
            logic.tam = this.tam;
            logic.players = boardr.bd;
            GameMoves gm = new GameMoves(logic);
            gm.setFocusable(true);
            gm.addKeyListener(gm);
            gm.addMouseListener(gm);
            frame.add(gm);
            frame.setVisible(true);
        }
        if(e.getSource() == bd){
            boardMod.setVisible(false);
            this.setVisible(true);
        }
    }
    
    private void botonesF(){
        if(pos == tam && butons.getBc1().isEnabled() == false && butons.getBc2().isEnabled() == false){
            butons.getBc3().setEnabled(false);
            butons.getBc4().setEnabled(false);
            pos = 0;
        }
        if(pos == tam && butons.getBc3().isEnabled() == false && butons.getBc4().isEnabled() == false){
            butons.getBc1().setEnabled(false);
            butons.getBc2().setEnabled(false);
            pos = 0;
        }
        if(pos == tam && butons.getBc1().isEnabled() == false && butons.getBc3().isEnabled() == false){
            butons.getBc2().setEnabled(false);
            butons.getBc4().setEnabled(false);
            pos = 0;
        }
        if(pos == tam && butons.getBc1().isEnabled() == false && butons.getBc4().isEnabled() == false){
            butons.getBc3().setEnabled(false);
            butons.getBc2().setEnabled(false);
            pos = 0;
        }
        if(pos == tam && butons.getBc2().isEnabled() == false && butons.getBc4().isEnabled() == false){
            butons.getBc3().setEnabled(false);
            butons.getBc1().setEnabled(false);
            pos = 0;
        }
        if(pos == tam && butons.getBc3().isEnabled() == false && butons.getBc2().isEnabled() == false){
            butons.getBc1().setEnabled(false);
            butons.getBc4().setEnabled(false);
            pos = 0;
        }
        if(pos == tam && butons.getBc1().isEnabled() == false && butons.getBc2().isEnabled() == false && butons.getBc3().isEnabled() == false){
            butons.getBc4().setEnabled(false);
            pos = 0;
        }
        if(pos == tam && butons.getBc1().isEnabled() == false && butons.getBc2().isEnabled() == false && butons.getBc4().isEnabled() == false){
            butons.getBc3().setEnabled(false);
            pos = 0;
        }
        if(pos == tam && butons.getBc3().isEnabled() == false && butons.getBc2().isEnabled() == false && butons.getBc4().isEnabled() == false){
            butons.getBc1().setEnabled(false);
            pos = 0;
        }
        if(pos == tam && butons.getBc1().isEnabled() == false && butons.getBc3().isEnabled() == false && butons.getBc4().isEnabled() == false){
            butons.getBc2().setEnabled(false);
            pos = 0;
        }
    }
    
    private void BuildP(BuildPlayers players){
        pos = 0;
        while(pos < tam){
            players.players[pos].setName(name[pos]);
            players.players[pos].setColor(colores[pos]);
            pos++;
        }
    }
    
    public Player[] getJugadores(){
        return bul.players;
    }
}