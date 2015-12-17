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
public class LinearStroke extends Stroke {
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
        g2.setStroke(new BasicStroke(8));

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
            drawPoint(g2, point);
        }
    }

    private void drawStrokeCloseHighlight(Graphics2D g2) {
        Point2D.Double strokeStart = getStrokeStart();
        if (_previewPoint != null &&
            strokeStart != null
            && nearby(_previewPoint, strokeStart)) {
            g2.setStroke(new BasicStroke(4));
            g2.setColor(PancakePallete.RED);
            drawPoint(g2, strokeStart);
        }
    }

    @Override
    public void paint(Graphics2D g2) {
        drawLines(g2);
        drawControlPoints(g2);
        drawStrokeCloseHighlight(g2);
    }

    @Override
    public void addPoint(Point2D.Double point) {
       _points.add(point);
    }

    @Override
    public List<Point2D.Double> getKnots() {
        return _points;
    }

    @Override
    public List<Point2D.Double> getPoints() {
        List<Point2D.Double> points = new ArrayList<>(_points);
        if (_previewPoint != null) {
            points.add(_previewPoint);
        }
        return points;
    }
}
