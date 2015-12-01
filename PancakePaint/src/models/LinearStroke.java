package models;

import views.PancakePallete;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;

/**
 * Created by isaac on 11/28/15.
 */
public class LinearStroke implements Stroke {
    private Color _color;
    private List<Point2D.Double> _points;

    public LinearStroke() {
        _points = new ArrayList<>();
    }

    public Point2D.Double getLastPoint() {
        if (_points.size() == 0) {
            return null;
        } else {
            return _points.get(_points.size() - 1);
        }
    }

    private void drawLines(Graphics2D g2) {
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
        for (Point2D.Double point : _points) {
            double w = 10;
            double h = 10;
            double x = point.getX() - (w / 2);
            double y = point.getY() - (h / 2);
            Ellipse2D.Double circle = new Ellipse2D.Double(x, y, w, h);
            g2.fill(circle);
        }
    }

    @Override
    public void paint(Graphics2D g2) {
        drawLines(g2);
        drawControlPoints(g2);
    }

    @Override
    public void addPoint(Point2D.Double point) {
       _points.add(point);
    }

    @Override
    public List<Point2D.Double> getPoints() {
        return _points;
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
