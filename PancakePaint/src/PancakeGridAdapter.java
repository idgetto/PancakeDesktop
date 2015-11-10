import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class PancakeGridAdapter implements GridAdapter {
    private static final int NUM_ROWS = 16;
    private static final int NUM_COLS = 32;

    private boolean[][] _touchGrid;
    private Color[][] _colorGrid;
    
    private PancakePaintBrush _brush;

    public PancakeGridAdapter(PancakePaintBrush brush) {
        _brush = brush;

        _touchGrid = new boolean[NUM_ROWS][NUM_COLS];
        _colorGrid = new Color[NUM_ROWS][NUM_COLS];

        // Setup color grid to be white
        for (int row = 0; row < NUM_ROWS; ++row) {
            for (int col = 0; col < NUM_COLS; ++col) {
                _colorGrid[row][col] = Color.WHITE;
            }
        }
    }

    public void cellTouched(Point cell) {
        Color brushColor = _brush.getColor();

        boolean touched = _touchGrid[cell.y][cell.x];
        boolean differentColor = _colorGrid[cell.y][cell.x] != brushColor;

        if (!touched || differentColor) {
            if (_brush.isFill()) {
                floodFill(cell, brushColor, _colorGrid[cell.y][cell.x]);
            } else {
                touchCell(cell, brushColor);
            }
        } else if (touched && !differentColor) {
            untouchCell(cell);
        }
    }

    public int getRows() {
        return NUM_ROWS;
    }

    public int getCols() {
        return NUM_COLS;
    }

    private void floodFill(Point cell, Color color, Color initialColor) {
        if (inBounds(cell) && _colorGrid[cell.y][cell.x] == initialColor) {
            touchCell(cell, color);

            floodFill(new Point(cell.x-1, cell.y), color, initialColor);
            floodFill(new Point(cell.x+1, cell.y), color, initialColor);
            floodFill(new Point(cell.x, cell.y-1), color, initialColor);
            floodFill(new Point(cell.x, cell.y+1), color, initialColor);
        }
    }

    private boolean inBounds(Point p) {
        return p.x >= 0 && p.y >= 0 && p.x < NUM_COLS && p.y < NUM_ROWS;
    }

    private void touchCell(Point cell, Color color) {
        _touchGrid[cell.y][cell.x] = true;
        _colorGrid[cell.y][cell.x] = color;
    }

    private void untouchCell(Point cell) {
        _touchGrid[cell.y][cell.x] = false;
        _colorGrid[cell.y][cell.x] = Color.WHITE;
    }

    public void paintCell(Point cell, Graphics g, Rectangle2D.Double rect) {
        Graphics2D g2 = (Graphics2D) g;

        // fill
        g2.setColor(_colorGrid[cell.y][cell.x]);
        g2.fill(rect);

        // draw outline
        g2.setColor(Color.BLACK);
        g2.draw(rect);

    }

}
