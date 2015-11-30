package models;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by isaac on 11/28/15.
 */
public class LinearStroke implements Stroke {
    private Color _color;
    private List<Point> _points;

    public LinearStroke() {
        _points = new ArrayList<>();
    }

    public Point getLastPoint() {
        if (_points.size() == 0) {
            return null;
        } else {
            return _points.get(_points.size() - 1);
        }
    }

    @Override
    public void paint(Graphics2D g2) {
        g2.setStroke(new BasicStroke(4));

        List<Point> points = getPoints();
        g2.setColor(getColor());
        for (int i = 0; i < points.size() - 1; ++i) {
            Point a = points.get(i);
            Point b = points.get(i + 1);
            g2.drawLine(a.x, a.y, b.x, b.y);
        }
    }

    @Override
    public void addPoint(Point point) {
       _points.add(point);
    }

    @Override
    public List<Point> getPoints() {
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
