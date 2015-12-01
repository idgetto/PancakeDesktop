package models;

import views.PancakePallete;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
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
        drawControlPoints(g2);
    }

    private void drawCurves(Graphics2D g2) {
        g2.setStroke(new BasicStroke(4));

        List<Point2D.Double> points = getPoints();
        g2.setColor(getColor());
        for (int i = 0; i < points.size() - 1; ++i) {
            Point2D.Double a = points.get(i);
            Point2D.Double b = points.get(i + 1);
            Line2D.Double line = new Line2D.Double(a.getX(), a.getY(), b.getX(), b.getY());
            g2.draw(line);
        }
    }

    private void drawControlPoints(Graphics2D g2) {
        g2.setStroke(new BasicStroke(4));
        g2.setColor(PancakePallete.GREEN);
        for (Point2D.Double point : _curve.getPoints()) {
            double w = 10;
            double h = 10;
            double x = point.getX() - (w / 2);
            double y = point.getY() - (h / 2);
            Ellipse2D.Double circle = new Ellipse2D.Double(x, y, w, h);
            g2.fill(circle);
        }
    }

    @Override
    public void addPoint(Point2D.Double point) {
        _curve.addPoint(point);
    }

    @Override
    public List<Point2D.Double> getPoints() {
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
