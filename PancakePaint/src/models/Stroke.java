package models;

import java.util.List;
import java.util.ArrayList;
import java.awt.Point;

public class Stroke {
    private List<Point> _points;

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
}
