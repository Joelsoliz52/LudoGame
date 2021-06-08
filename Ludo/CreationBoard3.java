package Ludo;


import javax.swing.*;

import core.GameMoves;

import core.BuildPlayers;
import core.ClassicLogic;
import entities.Player;
import entities.Position;
import layouts.Boards.ClassicBoard;
import utilities.Buttons;
import utilities.Constants;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
/**
 * Creation of panel for do a board Classic.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
@SuppressWarnings("serial")
public class CreationBoard3 extends JDialog implements ActionListener{
    private final Buttons butons;
    private ClassicBoard board;
    public BuildPlayers bul;
    private String[] name;
    private Color[] colores;
    private HashMap<Color, Boolean> map;
    private int tam;
    private int pos;
    public CreationBoard3(Menu2 parent,boolean modal){
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
            if(e.getSource() == butons.getBc4() && pos == tam){
                pos = 0;
                colores[pos] = Color.GREEN;
                butons.getNom4().setText(""+name[pos]);
                pos++;
                butons.getBc4().setEnabled(false);
            }
        }
        if(pos == tam){
            botonesF();
            map = new HashMap<Color, Boolean>();
            enterHashMap(map, colores);
            board = new ClassicBoard(new Position(80, 50), colores);
            board.tam = tam;
            bul = new BuildPlayers(tam, board.getHeight(), board.getWidth(), board);
            BuildP(bul.players); 
        }
        if(e.getSource() == butons.getBs()){
            this.setVisible(false);
            JFrame frame = new JFrame();
            frame.setBounds(10, 10, 1150, 700);
            frame.setTitle(Constants.TitleGame);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ClassicLogic logic = new ClassicLogic(board);
            logic.tam = this.tam;
            board.setBd(bul); 
            logic.players = board.bd;
            GameMoves gm = new GameMoves(logic);
            gm.setFocusable(true);
            gm.addKeyListener(gm);
            gm.addMouseListener(gm);
            frame.add(gm);
            frame.setVisible(true);
        }
    }
    
    /**
     * Metodo para apagar botones de colores en caso de que haber sido elegidos
     */
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
    
    /**
     * Metodo para verificar que color fue seleccionado
     * @param HashMap<Color, Boolean>
     * @param Color[]
     */
    private void enterHashMap(HashMap<Color, Boolean> m, Color[] colores){
        int pos = 0;
        while(pos < tam){
            m.put(colores[pos], true);
            pos++;
        }
    }
    
    /**
     * Metodo para almacenar jugadores y construir un BuildPlayers
     * @param players
     */
    private void BuildP(Player[] players){
        pos = 0;
        while(pos < tam){
            bul.players[pos].setName(name[pos]);
            bul.players[pos].setColor(colores[pos]);
            pos++;
        }
    }
    
    public Player[] getPlayeres(){
        return bul.players;
    }
}