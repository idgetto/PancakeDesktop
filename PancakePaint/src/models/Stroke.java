package models;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Ellipse2D;
import java.util.List;
import java.io.Serializable;

public abstract class Stroke implements Serializable {
    private static final double NEARBY_DISTANCE = 5.0;

    protected Color _color;
    protected boolean _closed;
    protected Point2D.Double _previewPoint;

    public abstract void paint(Graphics2D g2);
    public abstract void addPoint(Point2D.Double point);
    public abstract List<Point2D.Double> getKnots();
    public abstract List<Point2D.Double> getPoints();

    public boolean nearStartPoint(Point2D.Double point) {
        return nearby(getStrokeStart(), point);
    }

    protected boolean nearby(Point2D.Double a, Point2D.Double b) {
        if (a == null || b == null) {
            return false;
        }
        return a.distance(b) <= NEARBY_DISTANCE;
    }

    protected Point2D.Double getStrokeStart() {
        List<Point2D.Double> points = getKnots();
        if (points.isEmpty()) {
            return null;
        }
        return points.get(0);
    }

    protected void drawPoint(Graphics2D g2, Point2D.Double point) {
        double w = 10;
        double h = 10;
        double x = point.getX() - (w / 2);
        double y = point.getY() - (h / 2);
        Ellipse2D.Double circle = new Ellipse2D.Double(x, y, w, h);
        g2.fill(circle);
    }

    public Color getColor() {
        return _color;
    }

    public void setColor(Color color) {
        _color = color;
    }

    public boolean isClosed() {
        return _closed;
    }

    public void setClosed(boolean closed) {
        _closed = closed;
    }

    public Point2D.Double getPreviewPoint() {
        return _previewPoint;
    }

    public void setPreviewPoint(Point2D.Double point) {
        _previewPoint = point;
    }

}
