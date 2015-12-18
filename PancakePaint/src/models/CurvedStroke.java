package models;

import views.PancakePallete;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by isaac on 11/28/15.
 */
public class CurvedStroke extends Stroke {
    private CompositeCubicCurve _curve;

    public CurvedStroke() {
        _curve = new CompositeCubicCurve();
    }

    @Override
    public void paint(Graphics2D g2) {
        drawCurves(g2);
        drawControlPoints(g2);
        drawStrokeCloseHighlight(g2);
    }

    private void drawCurves(Graphics2D g2) {
        g2.setStroke(new BasicStroke(12));

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
        g2.setStroke(new BasicStroke(8));
        g2.setColor(PancakePallete.GREEN);
        for (Point2D.Double point : getKnots()) {
            drawPoint(g2, point);
        }

    }

    private void drawStrokeCloseHighlight(Graphics2D g2) {
        Point2D.Double strokeStart = getStrokeStart();
        if (_previewPoint != null &&
            strokeStart != null
            && nearby(_previewPoint, strokeStart)) {
            g2.setStroke(new BasicStroke(6));
            g2.setColor(PancakePallete.RED);
            drawPoint(g2, strokeStart);
        }
    }


    @Override
    public void addPoint(Point2D.Double point) {
        // close the shape if clicking near the start point
        Point2D.Double strokeStart = getStrokeStart();
        if (strokeStart != null &&
            nearby(_previewPoint, strokeStart)) {
            _curve.addPoint(strokeStart);
            _closed = true;
        } else {
            _curve.addPoint(point);
        }
    }

    @Override
    public List<Point2D.Double> getKnots() {
        return _curve.getPoints();
    }

    @Override
    public List<Point2D.Double> getPoints() {
        if (_previewPoint == null) {
            return _curve.interpolate();
        } else {
            return getPointsWithPreview();
        }
    }

    private List<Point2D.Double> getPointsWithPreview() {
        CompositeCubicCurve curve = new CompositeCubicCurve();
        List<Point2D.Double> points = new ArrayList<>(_curve.getPoints());

        curve.setPoints(points);
        curve.addPoint(_previewPoint);
        return curve.interpolate();
    }

}
