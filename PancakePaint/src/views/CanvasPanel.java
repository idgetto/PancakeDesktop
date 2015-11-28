package views;

import event.CanvasListener;
import models.PancakeModel;
import solver.Stroke;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;

public class CanvasPanel extends JPanel {

    private PancakeModel _model;
    private int _width;
    private int _height;
    private CanvasListener _listener;

    public CanvasPanel(PancakeModel model, int width, int height) {
        _model = model;
        _width = width;
        _height = height;
    }

    public void setCanvasListener(CanvasListener listener) {
        _listener = listener;
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
    }

    private void paintStroke(Graphics g, Stroke stroke) {
        List<Point> points = stroke.getPoints();
        for (int i = 0; i < points.size() - 1; ++i) {
            Point a = points.get(i);
            Point b = points.get(i + 1);
            g.drawLine(a.x, a.y, b.x, b.y);
        }
    }

}
