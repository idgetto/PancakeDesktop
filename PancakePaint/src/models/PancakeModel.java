package models;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.ArrayList;

public class PancakeModel {

    private List<Stroke> _strokes;
    private PancakePaintBrush _brush;
    private Point2D.Double _mouseLocation;

    public PancakeModel() {
        _strokes = new ArrayList<Stroke>();
        _brush = new PancakePaintBrush();
    }

    public List<Stroke> getStrokes() {
        return _strokes;
    }

    public void setStrokes(List<Stroke> strokes) {
        _strokes = strokes;
    }

    public PancakePaintBrush getBrush() {
        return _brush;
    }

    public void setBrush(PancakePaintBrush brush) {
        _brush = brush;
    }

    public Point2D.Double getMouseLocation() {
        return _mouseLocation;
    }

    public void setMouseLocation(Point2D.Double point) {
        _mouseLocation = point;
    }
}
