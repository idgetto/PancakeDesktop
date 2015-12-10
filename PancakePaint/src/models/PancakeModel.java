package models;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

public class PancakeModel implements Serializable {
    private static final Double NEARBY_DISTANCE = 5.0;

    private List<Stroke> _strokes;
    private Stroke _currentStroke;
    private PancakePaintBrush _brush;
    private Point2D.Double _mouseLocation;

    public PancakeModel() {
        _strokes = new ArrayList<Stroke>();
        _brush = new PancakePaintBrush();
    }

    public void addPoint(Point2D.Double point) {
            if (_currentStroke == null) {
                setupStroke();
            }
            _currentStroke.addPoint(point);
    }

    public boolean nearStartPoint(Point2D.Double point) {
        if (_currentStroke == null) {
            return false;
        }
        return _currentStroke.nearStartPoint(point);
    }

    public void closeStroke() {
        if (_currentStroke == null) {
            return;
        }

        List<Point2D.Double> points = _currentStroke.getKnots();
        if (points.isEmpty()) {
            return;
        }

        addPoint(points.get(0));
        _currentStroke.setClosed(true);
        finishStroke();
    }

    public void setupStroke() {
        switch (_brush.getMode()) {
            case LINEAR_STROKE:
                System.out.println("linear mode");
                _currentStroke = new LinearStroke();
                break;
            case CURVED_STROKE:
                System.out.println("curved mode");
                _currentStroke = new CurvedStroke();
                break;
        }

        _currentStroke.setColor(_brush.getColor());
        _strokes.add(_currentStroke);
    }

    public void finishStroke() {
        if (_currentStroke != null) {
            _currentStroke.setPreviewPoint(null);
            _currentStroke = null;
        }
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
        if (_currentStroke != null) {
            _currentStroke.setPreviewPoint(point);
        }
    }
}
