package layouts.Boards;

import java.awt.*;
import java.util.*;

import javax.swing.ImageIcon;

import core.BuildPlayers;
import entities.Player;
import interfaces.Board;
import interfaces.Path;
import layouts.Paths.ClassicPath;
/**
 * Board of the Classic Game.
 *
 * @author JoelS
 * @version 1
 */
public class ClassicBoard implements Board {
    // Fields of the class.
    private final int height;
    private final entities.Position position;
    private final int width;
    private Color[] colores;
    public BuildPlayers bd;
    private String fondo = "/utilities/ClassicBoard.jpg";
    private Image imagen;
    public int tam;
    /**
     * ClassicBoard constructor.
     * @param position Initial position of the board.
     */
    public ClassicBoard(entities.Position position, Color[] colores) {
        this.position = position;
        this.colores = colores;
        ImageIcon img =  new ImageIcon(getClass().getResource(fondo));
        imagen = img.getImage();
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
        int temp1 = x + 45;
        int temp2 = y + 45;
        int[] xPoints1 = { x + (6 * width), x + (6 * width), x + 15 + (7 * width) };
        int[] yPoints1 = { y + (6 * height), y + (9 * height), y + 15 + (7 * width) };
        int[] xPoints2 = { x + (9 * width), x + (9 * width), x + 15 + (7 * width) };
        int[] yPoints2 = { y + (6 * height), y + (9 * height), y + 15 + (7 * width) };
        int[] xPoints3 = { x + (6 * width), x + (9 * width), x + 15 + (7 * width) };
        int[] yPoints3 = { y + (6 * height), y + (6 * height), y + 15 + (7 * width) };
        int[] xPoints4 = { x + (6 * width), x + (9 * width), x + 15 + (7 * width) };
        int[] yPoints4 = { y + (9 * height), y + (9 * height), y + 15 + (7 * width)};
        
        paint(graphics);
        graphics.setColor(Color.WHITE);
        graphics.fillRect(x, y, 15 * width, 15 * height);
        drawQuarters(graphics, x, y);
        drawPathWin(graphics, x, y);
        drawInitialPosition(graphics, x, y);
        drawPawnQuarter(graphics, temp1, temp2);
        drawTriangle(graphics, Color.BLUE, xPoints1, yPoints1);
        drawTriangle(graphics, Color.RED, xPoints2, yPoints2);
        drawTriangle(graphics, Color.GREEN, xPoints3, yPoints3);
        drawTriangle(graphics, Color.YELLOW, xPoints4, yPoints4);
        graphics.setStroke(new BasicStroke(2));
        graphics.setColor(Color.BLACK);
        graphics.drawPolygon(xPoints1, yPoints1, 3);
        graphics.drawPolygon(xPoints2, yPoints2, 3);
        graphics.drawPolygon(xPoints3, yPoints3, 3);
        graphics.drawPolygon(xPoints4, yPoints4, 3);
        drawBorders(graphics, x, y, temp1, temp2);
        drawSafeZone(graphics, x, y);
        drawText(graphics);
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        RenderingHints.VALUE_ANTIALIAS_ON);
    }

    /**
     * Draw borders of the boxes and figures.
     * @param graphics Drawing controller.
     * @param x X position.
     * @param y Y position.
     * @param temp1 Position X, initial boxes of pawn.
     * @param temp2 Position Y, initial boxes of pawn.
     */
    private void drawBorders(Graphics2D graphics, int x, int y, int temp1, int temp2) {
        graphics.setColor(Color.BLACK);
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 6; j++) {
                graphics.drawRect(x + ((i + 6) * width), y + (j * height), width, height);
                graphics.drawRect(x + (j * width), y + ((i + 6) * height), width, height);
                graphics.drawRect(x + ((i + 6) * width), y + ((j + 9) * height), width, height);
                graphics.drawRect(x + ((j + 9) * width), y + ((i + 6) * height), width, height);
            }
        }
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 2; j++) {
                int x1 = temp1 + (2 * i * width) + 9 * width;
                int y1 = temp2 + (2 * j * height) + 9 * height;
                graphics.drawRect(temp1 + (2 * i * width), temp2 + (2 * j * height), width, height);
                graphics.drawRect(x1, y1, width, height);
                graphics.drawRect(x1,temp2 + (2 * j * height), width, height);
                graphics.drawRect(temp1 + (2 * i * width), y1, width, height);
            }
        }

        graphics.drawRect(x + width, y + height, 4 * width, 4 * height);
        graphics.drawRect(x + (10 * width), y + height, 4 * width, 4 * height);
        graphics.drawRect(x + width, y + (10 * height), 4 * width, 4 * height);
        graphics.drawRect(x + (10 * width), y + (10 * height), 4 * width, 4 * height);
        graphics.drawRect(x, y, 15 * width, 15 * height);
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        RenderingHints.VALUE_ANTIALIAS_ON);
    }

    /**
     * Draw initial boxes into board of the pawns.
     * @param graphics Drawing controller.
     * @param x Position X, initial boxes of pawn.
     * @param y Position Y, initial boxes of pawn.
     */
    private void drawInitialPosition(Graphics2D graphics, int x, int y) {
        graphics.setColor(Color.BLUE);
        graphics.fillRect(x + width, y + (6 * height), width, height);
        graphics.setColor(Color.RED);
        graphics.fillRect(x + (13 * width), y + (8 * height), width, height);
        graphics.setColor(Color.GREEN);
        graphics.fillRect(x + (8 * width), y + height, width, height);
        graphics.setColor(Color.YELLOW);
        graphics.fillRect(x + (6 * width), y + (13 * height), width, height);
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        RenderingHints.VALUE_ANTIALIAS_ON);
    }

    /**
     * Draw path of win of each color.
     * @param graphics Drawing controller.
     * @param x Initial X position.
     * @param y Initial Y position.
     */
    private void drawPathWin(Graphics2D graphics, int x, int y) {
        for(int i = 1; i < 6; i++) {
            graphics.setColor(Color.BLUE);
            graphics.fillRect(x + (i * width), y + (7 * height), width, height);
            graphics.setColor(Color.RED);
            graphics.fillRect(x + ((8 + i) * width), y + (7 * height), width, height);
            graphics.setColor(Color.GREEN);
            graphics.fillRect(x + (7 * width), y + (i * height), width, height);
            graphics.setColor(Color.YELLOW);
            graphics.fillRect(x + (7 * width), y + ((8 + i) * height), width, height);
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
            RenderingHints.VALUE_ANTIALIAS_ON);
        }
    }

    /**
     * Draw pawn initial boxes.
     * @param graphics Drawing controller.
     * @param temp1 Position X, pawn initial boxes.
     * @param temp2 Position Y, pawn initial boxes.
     */
    private void drawPawnQuarter(Graphics2D graphics, int temp1, int temp2) {
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 2; j++) {
                int x1 = temp1 + (2 * i * width);
                int x2 = temp1 + (2 * i * width) + 9 * width;
                int y1 = temp2 + (2 * j * height);
                int y2 = temp2 + (2 * j * height) + 9 * height;

                graphics.setColor(Color.BLUE);
                graphics.fillRect(x1, y1, width, height);
                graphics.setColor(Color.RED);
                graphics.fillRect(x2, y2, width, height);
                graphics.setColor(Color.GREEN);
                graphics.fillRect(x2, y1, width, height);
                graphics.setColor(Color.YELLOW);
                graphics.fillRect(x1, y2, width, height);
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);
            }
        }
    }

    /**
     * Draw containers of pawn initial boxes.
     * @param graphics Drawing controller.
     * @param x Initial X position.
     * @param y Initial Y position.
     */
    private void drawQuarters(Graphics2D graphics, int x, int y) {
        for(int i=0; i < 6; i++) {
            int xRB1 = x + (i * width);
            int xRB2 = x + (5 * width);
            int xGY1 = x + ((i + 9) * width);
            int xGY2 = x + (9 * width);
            int xGY3 = x + (14 * width);
            int yRG1 = y + (i * height);
            int yRG2 = y + (5 * height);
            int yYB1 = y + ((i + 9) * height);
            int yYB2 = y + (9 * height);
            int yYB3 = y + (14 * height);

            graphics.setColor(Color.BLUE);
            graphics.fillRect(xRB1, y, width, height);
            graphics.fillRect(x, yRG1, width, height);
            graphics.fillRect(xRB1, yRG2, width, height);
            graphics.fillRect(xRB2, yRG1, width, height);

            graphics.setColor(Color.GREEN);
            graphics.fillRect(xGY1, y, width, height);
            graphics.fillRect(xGY2, yRG1, width, height);
            graphics.fillRect(xGY1, yRG2, width, height);
            graphics.fillRect(xGY3, yRG1, width, height);

            graphics.setColor(Color.RED);
            graphics.fillRect(xGY1, yYB2, width, height);
            graphics.fillRect(xGY2, yYB1, width, height);
            graphics.fillRect(xGY1, yYB3, width, height);
            graphics.fillRect(xGY3, yYB1, width, height);

            graphics.setColor(Color.YELLOW);
            graphics.fillRect(xRB1, yYB2, width, height);
            graphics.fillRect(x, yYB1, width, height);
            graphics.fillRect(xRB1, yYB3, width, height);
            graphics.fillRect(xRB2, yYB1, width, height);
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
            RenderingHints.VALUE_ANTIALIAS_ON);
        }
    }

    /**
     * Draw safe positions.
     * @param graphics Drawing controller.
     * @param x Initial X positions.
     * @param y Initial Y positions.
     */
    private void drawSafeZone(Graphics2D graphics, int x, int y) {
        graphics.setColor(Color.BLACK);
        graphics.drawOval(x + 5 + (6 * width), y + 5 + (2 * height), width - 10, height - 10);
        graphics.drawOval(x + 5 + (12 * width), y + 5 + (6 * height), width - 10, height - 10);
        graphics.drawOval(x + 5 + (8 * width), y + 5 + (12 * height), width - 10, height - 10);
        graphics.drawOval(x + 5 + (2 * width), y + 5 + (8 * height), width - 10, height - 10);
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        RenderingHints.VALUE_ANTIALIAS_ON);
    }

    /**
     * Draw the text of the game.
     * @param graphics Drawing controller.
     */
    private void drawText(Graphics2D graphics) {
        
        graphics.setFont(new Font("serif", Font.BOLD, 40));
        int pos = 0;
        HashMap<Color, Boolean> map = new HashMap<Color, Boolean>();
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
            graphics.drawString(bd.players[pos].getName(), 370, 540);
            pos++;
        }
        if(map.get(Color.BLUE)){
            graphics.drawString(bd.players[pos].getName(), 90, 35);
            pos++;
        }
        if(map.get(Color.YELLOW)){
            graphics.drawString(bd.players[pos].getName(), 90, 540);
            pos++;
        }
        if(map.get(Color.GREEN)){
            graphics.drawString(bd.players[pos].getName(), 370, 35);
        }
        graphics.drawString("Instrucciones:", 600,300);
        graphics.drawString("1.Presionar enter para lanzar", 550,350);
        graphics.drawString("el dado.", 550,400);
        graphics.drawString("2.Haga click sobre una ficha", 550,450);
        graphics.drawString("para moverla.", 550,500);
        graphics.drawString("3.Sin errores, se feliz.", 550,550);
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        RenderingHints.VALUE_ANTIALIAS_ON);
    }

    
    /**
     * Draw triangle.
     * @param graphics Drawing controller.
     * @param color Color of the triangle.
     * @param xPoints Array of X points.
     * @param yPoints Array of Y points.
     */
    private void drawTriangle(Graphics2D graphics, Color color, int[] xPoints, int[] yPoints) {
        graphics.setColor(color);
        graphics.fillPolygon(xPoints, yPoints, 3);
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        RenderingHints.VALUE_ANTIALIAS_ON);
    }
    
    public void paint(Graphics2D gr2d) {
		gr2d.drawImage(imagen, 0, 0, 1150, 700, new Panel());
	}
    
    /**
     * Returns height of the boxes.
     * @return Height of the boxes.
     */
    public int getHeight() { return height; }

    /**
     * Returns pawns initial X positions.
     * @return Initial X positions.
     */
    public int[][] getInitialX() {
        return new int[][]{
                {1, 1, 3, 3},
                {10, 10, 12, 12},
                {10, 10, 12, 12},
                {1, 1, 3, 3}
        };
    }

    /**
     * Returns pawns initial Y positions.
     * @return Initial Y positions.
     */
    public int[][] getInitialY() {
        return new int[][]{
                {1, 3, 1, 3},
                {1, 3, 1, 3},
                {10, 12, 10, 12},
                {10, 12, 10, 12}
        };
    }

    /**
     * Returns pawns path of the Classic Game.
     * @return Pawns path.
     */
    public Path getPath() { return new ClassicPath(tam, colores); }

    /**
     * Returns width of the boxes.
     * @return Width of the boxes.
     */
    public int getWidth() { return width; }

    public void setBd(BuildPlayers bd) {this.bd = bd; }
}