package views;

import models.PancakeModel;
import models.Stroke;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.List;

public class CanvasPanel extends JPanel {

    private PancakeModel _model;
    private int _width;
    private int _height;
    private MouseInputListener _listener;

    public CanvasPanel(PancakeModel model, int width, int height) {
        _model = model;
        _width = width;
        _height = height;
    }

    public void setCanvasListener(MouseInputListener listener) {
        _listener = listener;
        addMouseListener(_listener);
        addMouseMotionListener(_listener);
    }

    public Dimension getPreferredSize() {
        return new Dimension(_width, _height);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintBackground(g);
        paintStrokes(g, _model.getStrokes());
    }

    public void repaint(PancakeModel model) {
        _model = model;
        repaint();
    }

    private void paintBackground(Graphics g) {
        g.setColor(PancakePallete.WHITE);
        g.fillRect(0, 0, _width, _height);
    }

    private void paintStrokes(Graphics g, List<Stroke> strokes) {
        for (Stroke stroke : strokes) {
            paintStroke(g, stroke);
        }

        paintMousePreview(g);
    }

    private void paintStroke(Graphics g, Stroke stroke) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4));

        List<Point> points = stroke.getPoints();
        g2.setColor(stroke.getColor());
        for (int i = 0; i < points.size() - 1; ++i) {
            Point a = points.get(i);
            Point b = points.get(i + 1);
            g2.drawLine(a.x, a.y, b.x, b.y);
        }
    }

    private void paintMousePreview(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4));

        List<Stroke> strokes = _model.getStrokes();
        Stroke stroke = strokes.get(strokes.size() - 1);

        // show preview of next line where mouse is
        if (!stroke.getPoints().isEmpty()) {
            Point a = stroke.getLastPoint();
            Point b = _model.getMouseLocation();
            g2.setColor(stroke.getColor().brighter());
            g2.drawLine(a.x, a.y, b.x, b.y);
        }
    }

}
