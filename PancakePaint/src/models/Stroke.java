package models;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

public interface Stroke {
    public void paint(Graphics2D g2);
    public void addPoint(Point2D.Double point);
    public List<Point2D.Double> getPoints();
    public Color getColor();
    public void setColor(Color color);
}
