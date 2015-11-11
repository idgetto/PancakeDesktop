import java.awt.geom.Rectangle2D;
import java.awt.Point;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.util.List;
import java.awt.Graphics2D;

public class SolverGridAdapter implements GridAdapter {

    private Recipe _recipe;
    private JPanel _panel;
    private Color[][] _grid;
    private int _rows;
    private int _cols;

    public SolverGridAdapter(Recipe recipe,
                             JPanel panel, 
                             Color[][] grid, 
                             int rows, 
                             int cols) {
        _recipe = recipe;
        _panel = panel;

        Color[][] g = new Color[grid.length][grid[0].length];
        for (int r = 0; r < grid.length; ++r) {
            for (int c = 0; c < grid[r].length; ++c) {
                g[r][c] = grid[r][c];
            }
        }
        _grid = g;

        _rows = rows;
        _cols = cols;
    }

    public void run(final Callback callback) {
        final Recipe r = _recipe;
        final List<Phase> phases = r.getPhases();
        final Timer t = new Timer(100, new ActionListener() {
            int phaseIndex = 0;
            int strokeIndex = 0;
            int pointIndex = 0;

            public void actionPerformed(ActionEvent e) {
                if (phases.size() > phaseIndex) {
                    Phase phase = phases.get(phaseIndex);

                    List<Stroke> strokes = phase.getStrokes();
                    if (strokes.size() > strokeIndex) {
                        Stroke stroke = strokes.get(strokeIndex);

                        List<Point> points = stroke.getPoints();
                        if (points.size() > pointIndex) {
                            Point point = points.get(pointIndex);
                            _grid[point.y][point.x] = Color.GREEN;
                            _panel.repaint();

                            pointIndex++;
                        } else {
                            pointIndex = 0;
                            strokeIndex++;
                        }
                    } else {
                        pointIndex = 0;
                        strokeIndex = 0;
                        phaseIndex++;
                    }
                } else {
                    ((Timer) e.getSource()).stop();
                    callback.run();
                }
            }

        });
        t.start();
    }

    public void cellTouched(Point cell) {
        // ignore
    }

    public void paintCell(Point cell, Graphics g, Rectangle2D.Double rect) {
        Graphics2D g2 = (Graphics2D) g;

        // fill
        g2.setColor(_grid[cell.y][cell.x]);
        g2.fill(rect);

        // draw outline
        g2.setColor(Color.BLACK);
        g2.draw(rect);
    }

    public int getRows() {
        return _rows;
    }

    public int getCols() {
        return _cols;
    }
}
