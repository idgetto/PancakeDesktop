package models;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public interface Stroke {
    public void paint(Graphics2D g2);
    public void addPoint(Point point);
    public List<Point> getPoints();
    public Color getColor();
    public void setColor(Color color);
}
