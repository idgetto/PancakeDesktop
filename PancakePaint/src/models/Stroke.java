package models;

import java.awt.Color;
import java.awt.Point;
import java.util.List;
import java.util.ArrayList;

public class Stroke {
    private List<Point> _points;
    private Color _color;

    public Stroke() {
        _points = new ArrayList<Point>();
    }

    public void addPoint(Point point) {
        _points.add(point);
    }

    public List<Point> getPoints() {
        return _points;
    }

    public Point getLastPoint() {
        if (_points.size() == 0) {
            return null;
        } else {
            return _points.get(_points.size() - 1);
        }
    }

    public Color getColor() {
        return _color;
    }

    public void setColor(Color color) {
        _color = color;
    }
}
