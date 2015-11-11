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
import java.util.List;
import java.awt.geom.Rectangle2D;

public class GridPanel extends JPanel {

    private GridAdapter _gridAdapter;
    private List<Point> _sequence;
    
    private int _width, _height;

    public GridPanel(GridAdapter gridAdapter, int width, int height) {
        _gridAdapter = gridAdapter;
        _width = width;
        _height = height;

        setBorder(BorderFactory.createLineBorder(Color.black));

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                _sequence = new ArrayList<Point>();
                Point cell = getTouchedCell(e.getX(), e.getY());
                _sequence.add(cell);

                _gridAdapter.cellTouched(cell);

                repaint();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point cell = getTouchedCell(e.getX(), e.getY());

                // don't activate twice for same cell on moves
                if (!_sequence.contains(cell)) {
                    _gridAdapter.cellTouched(cell);
                    _sequence.add(cell);
                    repaint();
                }
            }
        });
    }

    public void setGridAdapter(GridAdapter adapter) {
        _gridAdapter = adapter;
    }

    public Dimension getPreferredSize() {
        return new Dimension(_width, _height);
    }

    private Point getTouchedCell(double touchX, double touchY) {
        int col = (int) (touchX / getCellWidth());
        int row = (int) (touchY / getCellHeight());

        return new Point(col, row);
    }

    private int getCellWidth() {
        int width = getWidth();
        int cellWidth = width / _gridAdapter.getCols();
        return cellWidth;
    }

    private int getCellHeight() {
        int height = getHeight();
        int cellHeight = height / _gridAdapter.getRows();
        return cellHeight;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int row = 0; row < _gridAdapter.getRows(); ++row) {
            for (int col = 0; col < _gridAdapter.getCols(); ++col) {
                int x = col * getCellWidth();
                int y = row * getCellHeight();

                Rectangle2D.Double rect = new Rectangle2D.Double(x, 
                                                                 y, 
                                                                 getCellWidth(), 
                                                                 getCellHeight());
                _gridAdapter.paintCell(new Point(col, row), g, rect);
            }
        }
    }
}
