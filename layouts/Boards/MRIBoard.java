package layouts.Boards;

import java.awt.*;
import java.util.*;

import javax.swing.ImageIcon;

import core.BuildPlayers;
import entities.Player;
import interfaces.Board;
import interfaces.Path;
import layouts.Paths.MRIPath;
/**
 * Board of the First Custom Game.
 *
 * @author JoelS
 * @version 1
 */
public class MRIBoard implements Board {
    // Fields of the class.
    private final int height;
    private final entities.Position position;
    private final int width;
    public int tam;
    private String fondo = "/utilities/MRIBoard.jpg";
	private Image imagen;
    public BuildPlayers bd;
    private Color[] colores;
    /**
     * MRIBoard constructor.
     * @param position2 Initial position of the board.
     */
    public MRIBoard(entities.Position position2, Color[] colores) {
        position = position2;
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
        int[] xPoints1 = { x + (8 * width), x + (8 * width), x + 15 + (9 * width) };
        int[] yPoints1 = { y + (8 * height), y + (11 * height), y + 15 + (9 * width) };
        int[] xPoints2 = { x + (11 * width), x + (11 * width), x + 15 + (9 * width) };
        int[] yPoints2 = { y + (8 * height), y + (11 * height), y + 15 + (9 * width) };
        int[] xPoints3 = { x + (8 * width), x + (11 * width), x + 15 + (9 * width) };
        int[] yPoints3 = { y + (8 * height), y + (8 * height), y + 15 + (9 * width) };
        int[] xPoints4 = { x + (8 * width), x + (11 * width), x + 15 + (9 * width) };
        int[] yPoints4 = { y + (11 * height), y + (11 * height), y + 15 + (9 * width) };

        paint(graphics);
        graphics.setColor(Color.WHITE);
        graphics.fillRect(x, y, 19 * width, 19 * height);
        drawQuarters(graphics, x, y);
        drawPawnQuarter(graphics, x, y);
        drawInitialPosition(graphics, x, y);
        drawPathWin(graphics, x, y);
        drawOptionalPath(graphics, x, y);
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
        drawBorders(graphics, x, y);
        drawText(graphics);
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                    RenderingHints.VALUE_ANTIALIAS_ON);
    }

    /**
     * Draw borders of the boxes and figures.
     * @param graphics Drawing controller.
     * @param x X position.
     * @param y Y position.
     */
    private void drawBorders(Graphics2D graphics, int x, int y) {
        graphics.setColor(Color.BLACK);
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 8; j++) {
                graphics.drawRect(x + ((i + 8) * width), y + (j * height), width, height);
                graphics.drawRect(x + (j * width), y + ((i + 8) * height), width, height);
                graphics.drawRect(x + ((i + 8) * width), y + ((j + 11) * height), width, height);
                graphics.drawRect(x + ((j + 11) * width),y + ((i + 8) * height), width, height);
            }
        }
        for(int j = 0; j < 11; j++) {
            graphics.drawRect(x + ((j + 4) * width),y + (4 * height), width, height);
            graphics.drawRect(x + ((j + 4) * width),y + (14 * height), width, height);
            graphics.drawRect(x + (4 * width),y + ((j + 4) * height), width, height);
            graphics.drawRect(x + (14 * width),y + ((j + 4) * height), width, height);
        }

        graphics.drawRect(x, y, 19 * width, 19 * height);

        // Blue
        graphics.drawRect(x, y, width, height);
        graphics.drawRect(x, y, 2 * width, 2 * height);

        // Green
        graphics.drawRect(x + (18 * width), y, width, height);
        graphics.drawRect(x + (17 * width), y, 2 * width, 2 * height);

        // Yellow
        graphics.drawRect(x, y + (18 * height), width, height);
        graphics.drawRect(x, y + (17 * height), 2 * width, 2 * height);

        // Red
        graphics.drawRect(x + (18 * width), y + (18 * height), width, height);
        graphics.drawRect(x + (17 * width), y + (17 * height), 2 * width, 2 * height);

        for(int i = 0; i < 2; i++) {
            graphics.drawRect(x + ((15 * i + 1) * width), y + (3 * height), 2 * width, 4 * height);
            graphics.drawRect(x + ((15 * i + 1) * width), y + (12 * height), 2 * width, 4 * height);
            graphics.drawRect(x + (3 * width), y + ((15 * i + 1) * height), 4 * width, 2 * height);
            graphics.drawRect(x + (12 * width), y + ((15 * i + 1) * height), 4 * width, 2 * height);
        }
    }

    /**
     * Draw initial boxes into board of the pawns.
     * @param graphics Drawing controller.
     * @param x Position X, initial boxes of pawn.
     * @param y Position Y, initial boxes of pawn.
     */
    private void drawInitialPosition(Graphics2D graphics, int x, int y) {
        graphics.setColor(Color.BLUE);
        graphics.fillRect(x + width, y + (8 * height), width, height);
        graphics.setColor(Color.RED);
        graphics.fillRect(x + (17 * width), y + (10 * height), width, height);
        graphics.setColor(Color.GREEN);
        graphics.fillRect(x + (10 * width), y + height, width, height);
        graphics.setColor(Color.YELLOW);
        graphics.fillRect(x + (8 * width), y + (17 * height), width, height);
    }

    /**
     * Draw optional path.
     * @param graphics Drawing controller.
     * @param x Initial X position.
     * @param y Initial Y position.
     */
    private void drawOptionalPath(Graphics2D graphics, int x, int y) {
        for(int i = 0; i < 2; i++) {
            graphics.setColor(Color.LIGHT_GRAY);
            graphics.fillRect(x + ((7 * i + 4) * width), y + (4 * height), width * 4, height);
            graphics.fillRect(x + ((7 * i + 4) * width), y + (14 * height), width * 4, height);
            graphics.fillRect(x + (4 * width), y + ((7 * i + 4) * height), width, height * 4);
            graphics.fillRect(x + (14 * height), y + ((7 * i + 4) * height), width, height * 4);
        }
    }

    /**
     * Draw path of win of each color.
     * @param graphics Drawing controller.
     * @param x Initial X position.
     * @param y Initial Y position.
     */
    private void drawPathWin(Graphics2D graphics, int x, int y) {
        for(int i = 1; i < 8; i++) {
            graphics.setColor(Color.BLUE);
            graphics.fillRect(x + (i * width), y + (9 * height), width, height);
            graphics.setColor(Color.RED);
            graphics.fillRect(x + ((10 + i) * width), y + (9 * height), width, height);
            graphics.setColor(Color.GREEN);
            graphics.fillRect(x + (9 * width), y + (i * height), width, height);
            graphics.setColor(Color.YELLOW);
            graphics.fillRect(x + (9 * width), y + ((10 + i) * height), width, height);
        }
    }

    /**
     * Draw pawn initial boxes.
     * @param graphics Drawing controller.
     * @param x Position X, pawn initial boxes.
     * @param y Position Y, pawn initial boxes.
     */
    private void drawPawnQuarter(Graphics2D graphics, int x, int y) {
        drawPawnHorizontalQuarter(graphics, x, y);
        drawPawnVerticalQuarter(graphics, x, y);
    }

    /**
     * Draw pawn initial horizontal boxes.
     * @param graphics Drawing controller.
     * @param x Position X, pawn initial boxes.
     * @param y Position Y, pawn initial boxes.
     */
    private void drawPawnHorizontalQuarter(Graphics2D graphics, int x, int y) {
        x = x + 105;
        y = y + 45;
        for(int i = 0; i < 2; i++) {
            int x1 = x + (2 * i * width);
            int x2 = x + (2 * i * width) + 9 * width;
            int y2 = y + 15 * height;

            graphics.setColor(Color.BLUE);
            graphics.fillRect(x1, y, width, height);
            graphics.setColor(Color.RED);
            graphics.fillRect(x2, y2, width, height);
            graphics.setColor(Color.GREEN);
            graphics.fillRect(x2, y, width, height);
            graphics.setColor(Color.YELLOW);
            graphics.fillRect(x1, y2, width, height);

            graphics.setColor(Color.BLACK);
            graphics.drawRect(x1, y, width, height);
            graphics.drawRect(x2, y2, width, height);
            graphics.drawRect(x2, y, width, height);
            graphics.drawRect(x1, y2, width, height);
        }
    }

    /**
     * Draw pawn initial vertical boxes.
     * @param graphics Drawing controller.
     * @param x Position X, pawn initial boxes.
     * @param y Position Y, pawn initial boxes.
     */
    private void drawPawnVerticalQuarter(Graphics2D graphics, int x, int y) {
        x = x + 45;
        y = y + 105;
        for(int i = 0; i < 2; i++) {
            int x2 = x + 15 * width;
            int y1 = y + (2 * i * height);
            int y2 = y + (2 * i * height) + 9 * height;

            graphics.setColor(Color.BLUE);
            graphics.fillRect(x, y1, width, height);
            graphics.setColor(Color.RED);
            graphics.fillRect(x2, y2, width, height);
            graphics.setColor(Color.GREEN);
            graphics.fillRect(x2, y1, width, height);
            graphics.setColor(Color.YELLOW);
            graphics.fillRect(x, y2, width, height);

            graphics.setColor(Color.BLACK);
            graphics.drawRect(x, y1, width, height);
            graphics.drawRect(x2, y2, width, height);
            graphics.drawRect(x2, y1, width, height);
            graphics.drawRect(x, y2, width, height);
        }
    }

    /**
     * Draw containers of pawns initial boxes.
     * @param graphics Drawing controller.
     * @param x Initial X position.
     * @param y Initial Y position.
     */
    private void drawQuarters(Graphics2D graphics, int x, int y) {
        graphics.setColor(Color.RED);
        drawQuarterRed(graphics, x, y);
        graphics.setColor(Color.YELLOW);
        drawQuarterYellow(graphics, x, y);
        graphics.setColor(Color.BLUE);
        drawQuarterBlue(graphics, x, y);
        graphics.setColor(Color.GREEN);
        drawQuarterGreen(graphics, x, y);
    }

    /**
     * Draw containers of blue pawns initial boxes.
     * @param graphics Drawing controller.
     * @param x Initial X position.
     * @param y Initial Y position.
     */
    private void drawQuarterBlue(Graphics2D graphics, int x, int y) {
        graphics.fillRect(x, y, width, height);
        for(int i = 3; i < 8; i++) {
            graphics.fillRect(x + (i * width), y, width, height);
            graphics.fillRect(x + (i * width), y + (3 * height), width, height);
            graphics.fillRect(x  , y + (i * height), width, height);
            graphics.fillRect(x + (3 * width), y + (i * height), width, height);
        }
        for(int i = 0; i < 3; i++) {
            graphics.fillRect(x + (i * width), y + (2 * height), width, height);
            graphics.fillRect(x + (i * width), y + (7 * height), width, height);
            graphics.fillRect(x + (2 * width), y + (i * height), width, height);
            graphics.fillRect(x + (7 * width), y + (i * height), width, height);
        }
    }

    /**
     * Draw containers of green pawns initial boxes.
     * @param graphics Drawing controller.
     * @param x Initial X position.
     * @param y Initial Y position.
     */
    private void drawQuarterGreen(Graphics2D graphics, int x, int y) {
        x += 11 * width;
        graphics.fillRect(x + (7 * width), y, width, height);

        for(int i = 0; i < 5; i++) {
            graphics.fillRect(x + (i * width), y, width, height);
            graphics.fillRect(x + (i * width), y + (3 * height), width, height);
        }
        for(int i = 5; i < 8; i++) {
            graphics.fillRect(x + (i * width), y + (2 * height), width, height);
            graphics.fillRect(x + (i * width), y + (7 * height), width, height);
        }
        for(int i = 3; i < 8; i++) {
            graphics.fillRect(x + (4 * width), y + (i * height), width, height);
            graphics.fillRect(x + (7 * width), y + (i * height), width, height);
        }
        for(int i = 0; i < 3; i++) {
            graphics.fillRect(x, y + (i * height), width, height);
            graphics.fillRect(x + (5 * width), y + (i * height), width, height);
        }
    }

    /**
     * Draw containers of red pawns initial boxes.
     * @param graphics Drawing controller.
     * @param x Initial X position.
     * @param y Initial Y position.
     */
    private void drawQuarterRed(Graphics2D graphics, int x, int y) {
        x += 11 * width;
        y += 11 * height;
        graphics.fillRect(x + (7 * width), y + (7 * height), width, height);

        for(int i = 0; i < 5; i++) {
            graphics.fillRect(x + (i * width), y + (4 * height), width, height);
            graphics.fillRect(x + (i * width), y + (7 * height), width, height);
            graphics.fillRect(x + (4 * width), y + (i * height), width, height);
            graphics.fillRect(x + (7 * width), y + (i * height), width, height);
        }
        for(int i = 5; i < 8; i++) {
            graphics.fillRect(x + (i * width), y, width, height);
            graphics.fillRect(x + (i * width), y + (5 * height), width, height);
            graphics.fillRect(x, y + (i * height), width, height);
            graphics.fillRect(x + (5 * width), y + (i * height), width, height);
        }
    }

    /**
     * Draw containers of yellow pawns initial boxes.
     * @param graphics Drawing controller.
     * @param x Initial X position.
     * @param y Initial Y position.
     */
    private void drawQuarterYellow(Graphics2D graphics, int x, int y) {
        y += 11 * height;
        graphics.fillRect(x, y + (7 * height), width, height);

        for(int i = 0; i < 5; i++) {
            graphics.fillRect(x  , y + (i * height), width, height);
            graphics.fillRect(x + (3 * width), y + (i * height), width, height);
        }
        for(int i = 5; i < 8; i++) {
            graphics.fillRect(x + (2 * width), y + (i * height), width, height);
            graphics.fillRect(x + (7 * width), y + (i * height), width, height);
        }
        for(int i = 3; i < 8; i++) {
            graphics.fillRect(x + (i * width), y + (4 * height), width, height);
            graphics.fillRect(x + (i * width), y + (7 * height), width, height);
        }
        for(int i = 0; i < 3; i++) {
            graphics.fillRect(x + (i * width), y, width, height);
            graphics.fillRect(x + (i * width), y + (5 * height), width, height);
        }
    }

    /**
     * Draw the text of the game.
     * @param graphics Drawing controller.
     */
    private void drawText(Graphics2D graphics) {
        graphics.setFont(new Font("serif", Font.BOLD, 40));

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
        graphics.drawString("3.Casillas grises: te ayuadran", 700,500);
        graphics.drawString("a salir del camino principal y ", 700,540);
        graphics.drawString("evitaras ser comido.", 700,580);
        graphics.drawString("4.Sin errores, se feliz.", 700,620);
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
    }

    public void paint(Graphics2D gr2d) {
		gr2d.drawImage(imagen, 0, 0, 1200, 700, new Panel());
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
                {1, 1, 3, 5},
                {12, 14, 16, 16},
                {12, 14, 16, 16},
                {1, 1, 3, 5}
        };
    }

    /**
     * Returns pawns initial Y positions.
     * @return Initial Y positions.
     */
    public int[][] getInitialY() {
        return new int[][]{
                {3, 5, 1, 1},
                {1, 1, 3, 5},
                {16, 16, 12, 14},
                {12, 14, 16, 16}
        };
    }

    /**
     * Returns pawns path of the First Custom Game.
     * @return Pawns path.
     */
    public Path getPath() { return new MRIPath(colores.length, colores); }

    /**
     * Returns width of the boxes.
     * @return Width of the boxes.
     */
    public int getWidth() { return width; }
    
    public void setBd(BuildPlayers bd){
        this.bd = bd;
    }
}
