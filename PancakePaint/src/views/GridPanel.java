import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
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

    private List<Point> _sequence;
    private PancakeModel _model;
    private GridListener _gridListener;
    
    private int _width, _height;

    public GridPanel(PancakeModel model, int width, int height) {
        _model = model;
        _width = width;
        _height = height;

        setBorder(BorderFactory.createLineBorder(Color.black));

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                _sequence = new ArrayList<Point>();
                Point cell = getTouchedCell(e.getX(), e.getY());
                _sequence.add(cell);

                _gridListener.onGridEvent(new GridEvent(cell.x, cell.y));
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point cell = getTouchedCell(e.getX(), e.getY());

                // don't activate twice for same cell on moves
                if (!_sequence.contains(cell)) {
                    _gridListener.onGridEvent(new GridEvent(cell.x, cell.y));
                    _sequence.add(cell);
                }
            }
        });
    }

    public void setGridListener(GridListener listener) {
        _gridListener = listener;
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
        Grid<Color> grid = _model.getGrid();
        int width = getWidth();
        int cellWidth = width / grid.getNumCols();
        return cellWidth;
    }

    private int getCellHeight() {
        Grid<Color> grid = _model.getGrid();
        int height = getHeight();
        int cellHeight = height / grid.getNumRows();
        return cellHeight;
    }

    public void repaint(PancakeModel model) {
        if (_model == null) {
            System.err.println("_model is null :(");
        }
        _model = model;
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Grid<Color> grid = _model.getGrid();
        for (int row = 0; row < grid.getNumRows(); ++row) {
            for (int col = 0; col < grid.getNumCols(); ++col) {
                int x = col * getCellWidth();
                int y = row * getCellHeight();

                Rectangle2D.Double rect = new Rectangle2D.Double(x, 
                                                                 y, 
                                                                 getCellWidth(), 
                                                                 getCellHeight());
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(grid.get(row, col));
                g2.fill(rect);

                // draw outline
                g2.setColor(Color.BLACK);
                g2.draw(rect);
            }
        }
    }
}
