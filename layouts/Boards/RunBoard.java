package layouts.Boards;

import core.BuildPlayers;
import entities.Player;
import entities.Position;
import interfaces.Board;
import interfaces.Path;
import layouts.Paths.RunPath;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Board of the First Custom Game.
 *
 * @author Daniel
 * @version 1
 */
public class RunBoard implements Board, Serializable {
    // Fields of the class.
    private final Color[] colors;
    private final int height;
    private final Position position;
    private final int width;
    public BuildPlayers bd;
    public int tam;

    /**
     * RunBoard constructor.
     * @param position Initial position of the board.
     */
    public RunBoard(Position position, Color[] colors) {
        this.position = position;
        this.colors = colors;
        height = 30;
        width = 30;
    }

    /**
     * Draw board.
     * @param graphics Drawing controller.
     */
    public void draw(Graphics2D graphics) {
        int x = position.getX();
        int y = position.getY();
        int[] xPoints1 = { x + (8 * width), x + (8 * width), x + 15 + (9 * width) };
        int[] yPoints1 = { y + (8 * height), y + (11 * height), y + 15 + (9 * width) };
        int[] xPoints2 = { x + (11 * width), x + (11 * width), x + 15 + (9 * width) };
        int[] yPoints2 = { y + (8 * height), y + (11 * height), y + 15 + (9 * width) };
        int[] xPoints3 = { x + (8 * width), x + (11 * width), x + 15 + (9 * width) };
        int[] yPoints3 = { y + (8 * height), y + (8 * height), y + 15 + (9 * width) };
        int[] xPoints4 = { x + (8 * width), x + (11 * width), x + 15 + (9 * width) };
        int[] yPoints4 = { y + (11 * height), y + (11 * height), y + 15 + (9 * width) };

        graphics.setColor(Color.WHITE);
        drawPathColor(graphics, x, y);
        graphics.setStroke(new BasicStroke(2));
        graphics.setColor(Color.BLACK);
        graphics.drawPolygon(xPoints1, yPoints1, 2);
        graphics.drawPolygon(xPoints2, yPoints2, 2);
        graphics.drawPolygon(xPoints3, yPoints3, 2);
        graphics.drawPolygon(xPoints4, yPoints4, 2);
        drawBorders(graphics, x, y);
        drawText(graphics);
    }

    /**
     * Draw borders of the boxes and figures.
     * @param graphics Drawing controller.
     * @param x X position.
     * @param y Y position.
     */
    private void drawBorders(Graphics2D graphics, int x, int y) {
        graphics.setColor(Color.BLACK);

        for(int j = 0; j < 2; j++) {
            graphics.drawRect(x + ((j + 3) * width),y + (2 * height), width, height);
            graphics.drawRect(x + ((j + 14) * width),y + (16 * height), width, height);
            graphics.drawRect(x + ((j + 14) * width),y+(2 * height), width, height);
            graphics.drawRect(x + ((j + 3) * width),y + (16 * height), width, height);
        }
        for(int j = 0; j < 4; j++) {
            graphics.drawRect(x + ((j + 7) * width),y, width, height);
            graphics.drawRect(x + ((j + 8) * width),y + (18 * height), width, height);
            graphics.drawRect(x + ((j+5) * width), y + (10 * height), width, height);
            graphics.drawRect(x + ((j+10) * width), y + (8 * height), width, height);
        }
        for(int j = 0; j < 5; j++) {
            graphics.drawRect(x + ((j + 4) * width), y + (8 * height), width, height);
            graphics.drawRect(x + ((j + 10) * width), y + (10 * height), width, height);
        }
        for(int j = 0; j < 6; j++) {
            graphics.drawRect(x + (7 * width), y + ((j) * height), width, height);
            graphics.drawRect(x + (11 * width), y + ((j + 13) * height), width, height);
            graphics.drawRect(x + (8 * width), y + ((j + 13) * height), width, height);
            graphics.drawRect(x + (10 * width), y + ((j) * height), width, height);
        }
        for(int j = 0; j < 7; j++){
            graphics.drawRect(x + (4 * width), y + ((j + 10) * height), width, height);
            graphics.drawRect(x + (14 * width), y + ((j+2)* height), width, height);
            graphics.drawRect(x + (4 * width), y + ((j + 2) * height), width, height);
            graphics.drawRect(x + (14 * width), y + ((j + 10) * height), width, height);
        }
        for (int j = 0; j < 8; j++) {
            graphics.drawRect(x + ((j) * width), y + (5 * height), width, height);
            graphics.drawRect(x + ((j + 10) * width), y + (5 * height), width, height);
            graphics.drawRect(x + ((j + 11) * width), y + (13 * height), width, height);
        }
        for (int j = 0; j < 9; j++) {
            graphics.drawRect(x + ((j) * width), y + (13 * height), width, height);
            graphics.drawRect(x , y + ((j + 5) * height), width, height);
            graphics.drawRect(x + (18 * width), y + ((j + 5) * height), width, height);
        }
        
        graphics.drawRect(x, y, 19 * width, 19 * height);

        // Blue
        graphics.drawRect(x, y, 3 * width, 3 * height);

        //Green
        graphics.drawRect(x + (16 * width), y, 3 * width, 3 * height);

        // Yellow
        graphics.drawRect(x, y + (16 * height), 3 * width, 3 * height);

        // Red
        graphics.drawRect(x + (16 * width), y + (16 * height), 3 * width, 3 * height);


    }

    /**
     * DrawPathColor initial boxes into board of the pawns.
     * @param graphics Drawing controller.
     */
    private void drawPathColor(Graphics2D graphics, int x, int y) {
        graphics.setColor(Color.LIGHT_GRAY);

        for(int j = 0; j < 2; j++){
            graphics.fillRect(x + ((j + 3) * width), y + (16 * height), width, height);
            graphics.fillRect(x + ((j + 14) * width), y + (2 * height), width, height);
            graphics.fillRect(x + ((j + 3) * width), y + (2 * height), width, height);
            graphics.fillRect(x + ((j + 14) * width), y + (16 * height), width, height);
        }
        for (int j = 0; j < 4; j++) {
            graphics.fillRect(x + ((j + 7) * width), y, width, height);
            graphics.fillRect(x + ((j + 8) * width), y + (18 * height), width, height);
        }
        for(int j = 0; j < 5; j++){
            graphics.fillRect(x + ((j + 4) * width), y + (8 * height), width, height);
            graphics.fillRect(x + ((j + 10) * width), y + (8 * height), width, height);
            graphics.fillRect(x + ((j + 4) * width), y + (10 * height), width, height);
            graphics.fillRect(x + ((j + 10) * width), y + (10 * height), width, height);
        }
        for (int j = 0; j < 6; j++) {
            graphics.fillRect(x + (7 * width), y + ((j) * height), width, height);
            graphics.fillRect(x + (11 * width), y + ((j + 13) * height), width, height);
            graphics.fillRect(x + (10 * width), y + (j * height), width, height);
            graphics.fillRect(x + (8 * width), y + ((j+13) * height), width, height);
        }
        for (int j = 0; j < 7; j++) {
            graphics.fillRect(x + (4 * width), y + ((j + 2) * height), width, height);
            graphics.fillRect(x + (14 * width), y + ((j + 10) * height), width, height);
            graphics.fillRect(x + (14 * width), y + ((j + 2) * height), width, height);
            graphics.fillRect(x + (4 * width), y + ((j + 10) * height), width, height);
        }
        for (int j = 0; j < 8; j++) {
            graphics.fillRect(x + ((j) * width), y + (5 * height), width, height);
            graphics.fillRect(x + ((j + 11) * width), y + (13 * height), width, height);
        }
        for (int j = 0; j < 9; j++) {
            graphics.fillRect(x + (j * width), y + (13 * height), width, height);
            graphics.fillRect(x + (18 * width), y + ((j + 5) * height), width, height);
            graphics.fillRect(x , y + ((j+5) * height), width, height);
            graphics.fillRect(x + ((j+10) * width), y + (5 * height), width, height);
        }
        
        graphics.setColor(Color.BLACK);
        graphics.fillRect(x , y + (9 * height), width, height);
        graphics.fillRect(x + (9 * width), y , width, height);
        graphics.fillRect(x + (8 * width), y + (18 * height), width, height);
        graphics.fillRect(x + (18 * width), y + (8 * height), width, height);
        
        graphics.setColor(Color.BLUE);
        for (int j = 0; j < 5; j++) {
            graphics.fillRect(x + ((j + 4) * width), y + (8 * height), width, height);
            graphics.fillRect(x + (4 * width), y + ((j + 3) * height), width, height);
        }
        for (int j = 0; j < 2; j++) {
            graphics.fillRect(x + ((j + 3) * width), y + (2 * height), width, height);
        }
        graphics.setColor(Color.GREEN);
        for (int j = 0; j < 7; j++) {
            graphics.fillRect(x + (14 * width), y + ((j+2) * height), width, height);
        }
        for (int j = 0; j < 2; j++) {
            graphics.fillRect(x + ((j + 14) * width), y+(2*height) , width, height);
        }
        for (int j = 0; j < 4; j++) {
            graphics.fillRect(x + ((j+10) * width), y + (8 * height), width, height);
        }
        
        graphics.setColor(Color.RED);
        for (int j = 0; j < 5; j++) {
            graphics.fillRect(x + ((j + 10) * width), y + (10 * height), width, height);
            graphics.fillRect(x + (14 * width), y + ((j + 11) * height), width, height);
        }
        for (int j = 0; j < 2; j++) {
            graphics.fillRect(x + ((j + 14) * width), y + (16 * height), width, height);
        }
        graphics.setColor(Color.YELLOW);
        for (int j = 0; j < 7; j++) {
            graphics.fillRect(x + (4 * width), y + ((j + 10) * height), width, height);
        }
        for (int j = 0; j < 2; j++) {
            graphics.fillRect(x + ((j + 3) * width), y + (16 * height), width, height);
        }
        for (int j = 0; j < 4; j++) {
            graphics.fillRect(x + ((j+5) * width), y + (10 * height), width, height);
        }
        
    }

    /**
     * Draw the text of the game.
     * @param graphics Drawing controller.
     */
    private void drawText(Graphics2D graphics) {
    	graphics.setFont(new Font("serif", Font.BOLD, 40));
        HashMap<Color, Boolean> map = new HashMap<>();
        map.put(Color.RED, false);
        map.put(Color.BLUE, false);
        map.put(Color.YELLOW, false);
        map.put(Color.GREEN, false);
        for(Player player : bd.players) {
            if(player.getColor() != null){
                map.replace(player.getColor(), true);
            }
        }
        if(map.get(Color.RED)){
            graphics.drawString(bd.getPlayer(Color.RED).getName(), 500, 650);
        }
        if(map.get(Color.BLUE)){
            graphics.drawString(bd.getPlayer(Color.BLUE).getName(), 90, 40);
        }
        if(map.get(Color.YELLOW)){
            graphics.drawString(bd.getPlayer(Color.YELLOW).getName(), 90, 650);
        }
        if(map.get(Color.GREEN)){
            graphics.drawString(bd.getPlayer(Color.GREEN).getName(), 500, 40);
        }
        graphics.setFont(new Font("serif", Font.BOLD, 30));
        graphics.drawString("Instrucciones:", 750,300);
        graphics.drawString("1.Presionar enter para lanzar", 700,340);
        graphics.drawString("el dado.", 700,380);
        graphics.drawString("2.Haga click sobre una ficha", 700,420);
        graphics.drawString("para moverla.", 700,460);
        graphics.drawString("3.Sin errores, se feliz.", 700,500);
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        RenderingHints.VALUE_ANTIALIAS_ON);

    }
    
    public int getHeight() { return height; }

    /**
     * Returns pawns initial X positions.
     * @return Initial X positions.
     */
    public int[][] getInitialX() {
        return new int[][]{
                {0, 1, 0, 1},
                {16, 17, 16, 17},
                {16, 17, 16, 17},
                {0, 1, 0, 1}
        };
    }

    /**
     * Returns pawns initial Y positions.
     * @return Initial Y positions.
     */
    public int[][] getInitialY() {
        return new int[][]{
                {0, 0, 1, 1},
                {0, 0, 1, 1},
                {16, 16, 17, 17},
                {16, 16, 17, 17}
        };
    }

    /**
     * Returns pawns path of the First Custom Game.
     * @return Pawns path.
     */
    public Path getPath() { return new RunPath(tam, colors); }

    /**
     * Returns width of the boxes.
     * @return Width of the boxes.
     */
    public int getWidth() { return width; }
    
    public void setBd(BuildPlayers bd){
        this.bd = bd;
    }
}
