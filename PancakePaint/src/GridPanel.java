import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.Point;
import java.util.ArrayList;

public class GridPanel extends JPanel {

    private int _rows;
    private int _cols;
    private boolean[][] _touched;
    private boolean _floodFill;

    public GridPanel(int rows, int cols) {
        _rows = rows;
        _cols = cols;
        _touched = new boolean[_rows][_cols];

        setBorder(BorderFactory.createLineBorder(Color.black));

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                Point cell = getCell(e.getX(), e.getY());

                if (_floodFill) {
                    floodFill(cell);
                } else {
                    _touched[cell.y][cell.x] = !_touched[cell.y][cell.x];
                }

                if (cell.x == 0 && cell.y == 0) {
                    _floodFill = !_floodFill;
                }
                repaint();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point cell = getCell(e.getX(), e.getY());
                _touched[cell.y][cell.x] = true;
                repaint();
            }
        });
    }

    public Dimension getPreferredSize() {
        return new Dimension(800, 400);
    }

    private void floodFill(Point p) {
        System.out.println("Flood Fill!");
        System.out.println("row: " + p.y);
        System.out.println("col: " + p.x);

        if (inBounds(p) && !_touched[p.y][p.x]) {
            _touched[p.y][p.x] = true;

            floodFill(new Point(p.x-1, p.y));
            floodFill(new Point(p.x+1, p.y));
            floodFill(new Point(p.x, p.y-1));
            floodFill(new Point(p.x, p.y+1));
        }
    }

    private boolean inBounds(Point p) {
        return p.x >= 0 && p.y >= 0 && p.x < _cols && p.y < _rows;
    }

    private Point getCell(double x, double y) {
        int col = (int) (x / getCellWidth());
        int row = (int) (y / getCellHeight());

        System.out.println("row: " + row);
        System.out.println("col: " + col);
        return new Point(col, row);
    }

    private int getCellWidth() {
        int width = getWidth();
        int cellWidth = width / _cols;
        return cellWidth;
    }

    private int getCellHeight() {
        int height = getHeight();
        int cellHeight = height / _rows;
        return cellHeight;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);

        for (int row = 0; row < _rows; ++row) {
            for (int col = 0; col < _cols; ++col) {
                int x = col * getCellWidth();
                int y = row * getCellHeight();

                if (_touched[row][col]) {
                    g.fillRect(x, y, getCellWidth(), getCellHeight());
                } else {
                    g.drawRect(x, y, getCellWidth(), getCellHeight());
                }
            }
        }
    }
}
