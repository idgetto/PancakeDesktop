package views;

import models.PancakeModel;
import models.Stroke;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
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

        // paintMousePreview(g);
    }

    private void paintStroke(Graphics g, Stroke stroke) {
        Graphics2D g2 = (Graphics2D) g;
        stroke.paint(g2);
    }

    private void paintMousePreview(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4));

        List<Stroke> strokes = _model.getStrokes();
        Stroke stroke = strokes.get(strokes.size() - 1);
        List<Point2D.Double> points = stroke.getPoints();

        // show preview of next line where mouse is
        if (!points.isEmpty()) {
            Point2D.Double a = points.get(points.size() - 1);
            Point2D.Double b = _model.getMouseLocation();
            g2.setColor(stroke.getColor().brighter());
            Line2D.Double line = new Line2D.Double(a.getX(), a.getY(), b.getX(), b.getY());
            g2.draw(line);
        }
    }

}
