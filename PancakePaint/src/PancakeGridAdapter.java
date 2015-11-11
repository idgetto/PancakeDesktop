import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class PancakeGridAdapter implements GridAdapter {
    private boolean[][] _touchGrid;
    private Color[][] _colorGrid;
    
    private JPanel _panel;
    private int _rows, _cols;
    private PancakePaintBrush _brush;

    public PancakeGridAdapter(JPanel panel, 
                              PancakePaintBrush brush, 
                              int rows, 
                              int cols) {
        _panel = panel;
        _rows = rows;
        _cols = cols;
        _brush = brush;

        _touchGrid = new boolean[_rows][_cols];
        _colorGrid = new Color[_rows][_cols];

        // Setup color grid to be white
        for (int row = 0; row < _rows; ++row) {
            for (int col = 0; col < _cols; ++col) {
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
        return _rows;
    }

    public int getCols() {
        return _cols;
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
        return p.x >= 0 && p.y >= 0 && p.x < _cols && p.y < _rows;
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

    public Color[][] getColorGrid() {
        return _colorGrid;
    }

}
