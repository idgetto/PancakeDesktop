package models;

import views.PancakePallete;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by isaac on 11/28/15.
 */
public class CurvedStroke implements Stroke {

    private CompositeCubicCurve _curve;
    private Color _color;

    public CurvedStroke() {
        _curve = new CompositeCubicCurve();
    }

    @Override
    public void paint(Graphics2D g2) {
        drawCurves(g2);
//        drawControlPoints(g2);
    }

    private void drawCurves(Graphics2D g2) {
        g2.setStroke(new BasicStroke(4));

        List<Point> points = getPoints();
        g2.setColor(getColor());
        for (int i = 0; i < points.size() - 1; ++i) {
            Point a = points.get(i);
            Point b = points.get(i + 1);
            g2.drawLine(a.x, a.y, b.x, b.y);
        }
    }

    private void drawControlPoints(Graphics2D g2) {
//        g2.setStroke(new BasicStroke(4));
//        g2.setColor(PancakePallete.GREEN);
//        for (Point point : _curve.getPoints()) {
//            int w = 10;
//            int h = 10;
//            int x = point.x - (w / 2);
//            int y = point.y - (h / 2);
//            Ellipse2D.Double circle = new Ellipse2D.Double(x, y, w, h);
//            g2.fill(circle);
//        }
    }

    @Override
    public void addPoint(Point point) {
        _curve.addPoint(point);
    }

    @Override
    public List<Point> getPoints() {
        return _curve.interpolate();
    }

    @Override
    public Color getColor() {
        return _color;
    }

    @Override
    public void setColor(Color color) {
        _color = color;
    }
}
