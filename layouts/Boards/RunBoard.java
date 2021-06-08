package layouts.Boards;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import core.BuildPlayers;
import interfaces.Board;
import interfaces.Path;
import layouts.Paths.RunPath;

/**
 * Board of the First Custom Game.
 *
 * @author daniel
 * @version 1
 */
public class RunBoard implements Board {
    // Fields of the class.
    private final int height;
    private final entities.Position position;
    private final int width;
    private Color[] colores;
    public BuildPlayers bd;
    public int tam;
    /**
     * RunBoard constructor.
     * @param position2 Initial position of the board.
     */
    public RunBoard(entities.Position position2, Color[] colores) {
        this.position = position2;
        this.colores = colores;
        height = 30;
        width = 30;
    }

    /**
     * Draw board.
     * @param graphics Drawing controller.
     */
    public void draw(Graphics2D graphics) {
        int x = (int)position.getX();
        int y = (int)position.getY();
        int[] xPoints1 = { x + (8 * width), x + (8 * width), x + 15 + (9 * width) };
        int[] yPoints1 = { y + (8 * height), y + (11 * height), y + 15 + (9 * width) };
        int[] xPoints2 = { x + (11 * width), x + (11 * width), x + 15 + (9 * width) };
        int[] yPoints2 = { y + (8 * height), y + (11 * height), y + 15 + (9 * width) };
        int[] xPoints3 = { x + (8 * width), x + (11 * width), x + 15 + (9 * width) };
        int[] yPoints3 = { y + (8 * height), y + (8 * height), y + 15 + (9 * width) };
        int[] xPoints4 = { x + (8 * width), x + (11 * width), x + 15 + (9 * width) };
        int[] yPoints4 = { y + (11 * height), y + (11 * height), y + 15 + (9 * width) };

        graphics.setColor(Color.WHITE);
        graphics.fillRect(x, y, 19 * width, 19 * height);
        drawQuarters(graphics, x, y);
        drawPawnQuarter(graphics, x, y);
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

        }
        for(int j = 0; j < 3; j++) {
            graphics.drawRect(x + ((j + 13) * width),y, width, height);
            graphics.drawRect(x + ((j + 3) * width),y + (18 * height), width, height);

        }
        for(int j = 0; j < 4; j++) {
            graphics.drawRect(x + ((j + 7) * width),y, width, height);
            graphics.drawRect(x + ((j + 8) * width),y + (18 * height), width, height);

        }
        for(int j = 0; j < 5; j++) {
            graphics.drawRect(x + ((j + 4) * width), y + (8 * height), width, height);
            graphics.drawRect(x + ((j + 10) * width), y + (10 * height), width, height);
        }

        for(int j = 0; j < 6; j++) {

            graphics.drawRect(x + (7 * width), y + ((j) * height), width, height);
            graphics.drawRect(x + (13 * width), y + ((j) * height), width, height);
            graphics.drawRect(x + (11 * width), y + ((j + 13) * height), width, height);
            graphics.drawRect(x + (5 * width), y + ((j + 13) * height), width, height);
        }


            for (int j = 0; j < 7; j++) {

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
                graphics.drawRect(x + (10 * width), y + ((j) * height), width, height);
                graphics.drawRect(x , y + ((j + 5) * height), width, height);
                graphics.drawRect(x + (18 * width), y + ((j + 5) * height), width, height);
                graphics.drawRect(x + (8 * width), y + ((j + 10) * height), width, height);
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

        for (int j = 0; j < 2; j++) {
            graphics.fillRect(x + ((j + 3) * width), y + (2 * height), width, height);
            graphics.fillRect(x + ((j + 14) * width), y + (16 * height), width, height);

        }
        for (int j = 0; j < 3; j++) {
            graphics.fillRect(x + ((j + 13) * width), y, width, height);
            graphics.fillRect(x + ((j + 3) * width), y + (18 * height), width, height);

        }
        for (int j = 0; j < 4; j++) {
            graphics.fillRect(x + ((j + 7) * width), y, width, height);
            graphics.fillRect(x + ((j + 8) * width), y + (18 * height), width, height);

        }
        for (int j = 0; j < 5; j++) {
            //graphics.fillRect(x + ((j + 4) * width), y + (8 * height), width, height);
            graphics.fillRect(x + ((j + 10) * width), y + (10 * height), width, height);
        }

        for (int j = 0; j < 6; j++) {

            graphics.fillRect(x + (7 * width), y + ((j) * height), width, height);
            graphics.fillRect(x + (13 * width), y + ((j) * height), width, height);
            graphics.fillRect(x + (11 * width), y + ((j + 13) * height), width, height);
            graphics.fillRect(x + (5 * width), y + ((j + 13) * height), width, height);
        }


        for (int j = 0; j < 7; j++) {

            //graphics.fillRect(x + (4 * width), y + ((j + 2) * height), width, height);
            graphics.fillRect(x + (14 * width), y + ((j + 10) * height), width, height);


        }
        for (int j = 0; j < 8; j++) {

            graphics.fillRect(x + ((j) * width), y + (5 * height), width, height);
            graphics.fillRect(x + ((j + 10) * width), y + (5 * height), width, height);
            graphics.fillRect(x + ((j + 11) * width), y + (13 * height), width, height);
        }
        for (int j = 0; j < 9; j++) {

            graphics.fillRect(x + ((j) * width), y + (13 * height), width, height);
            graphics.fillRect(x + (10 * width), y + ((j) * height), width, height);
            graphics.fillRect(x , y + ((j + 5) * height), width, height);
            graphics.fillRect(x + (18 * width), y + ((j + 5) * height), width, height);
            graphics.fillRect(x + (8 * width), y + ((j + 10) * height), width, height);
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
        for (int j = 0; j < 6; j++) {
            graphics.fillRect(x + (13 * width), y + ((j) * height), width, height);
        }
        for (int j = 0; j < 2; j++) {
            graphics.fillRect(x + ((j + 14) * width), y , width, height);
        }
        for (int j = 0; j < 4; j++) {
            graphics.fillRect(x + (10 * width), y + ((j + 5) * height), width, height);
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
        for (int j = 0; j < 6; j++) {
            graphics.fillRect(x + (5 * width), y + ((j + 13) * height), width, height);
        }
        for (int j = 0; j < 2; j++) {
            graphics.fillRect(x + ((j + 3) * width), y + (18 * height), width, height);
        }
        for (int j = 0; j < 4; j++) {
            graphics.fillRect(x + (8 * width), y + ((j + 10) * height), width, height);
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

            graphics.setColor(Color.WHITE);
            graphics.fillRect(x1, y, width, height);
            graphics.setColor(Color.WHITE);
            graphics.fillRect(x2, y2, width, height);
            graphics.setColor(Color.WHITE);
            graphics.fillRect(x2, y, width, height);
            graphics.setColor(Color.WHITE);
            graphics.fillRect(x1, y2, width, height);

            graphics.setColor(Color.WHITE);
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

            graphics.setColor(Color.WHITE);
            graphics.fillRect(x, y1, width, height);
            graphics.setColor(Color.WHITE);
            graphics.fillRect(x2, y2, width, height);
            graphics.setColor(Color.WHITE);
            graphics.fillRect(x2, y1, width, height);
            graphics.setColor(Color.WHITE);
            graphics.fillRect(x, y2, width, height);

            graphics.setColor(Color.WHITE);
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
        graphics.setColor(Color.WHITE);
        drawQuarterRed(graphics, x, y);
        graphics.setColor(Color.WHITE);
        drawQuarterYellow(graphics, x, y);
        graphics.setColor(Color.WHITE);
        drawQuarterBlue(graphics, x, y);
        graphics.setColor(Color.WHITE);
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
        graphics.drawString("Player 1", 90, 40);
        graphics.drawString("Player 2", 500, 40);
        graphics.drawString("Player 4", 90, 650);
        graphics.drawString("Player 3", 500, 650);
        graphics.drawString("Instruction:", 670,300);
        graphics.drawString("1.Press enter to roll dice.", 670,350);
        graphics.drawString("2.Click on coin to move.", 670,400);
        graphics.drawString("3.Don't worry, be happy.", 670,450);
        graphics.drawString("LUDO", 150,400);
        graphics.setFont(new Font("arial", Font.ITALIC, 25));
        graphics.drawString("FINISH", 322,342);

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
    public Path getPath() { return new RunPath(tam, colores); }

    /**
     * Returns width of the boxes.
     * @return Width of the boxes.
     */
    public int getWidth() { return width; }
    
    public void setBd(BuildPlayers bd){
        this.bd = bd;
    }
}
