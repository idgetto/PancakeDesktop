package models;

import java.awt.Point;
import java.util.*;

/**
 * Created by isaac on 11/29/15.
 */
public class CompositeCubicBezierCurve {
    private List<CubicBezierCurve> _curves;
    private Queue<Point> _pendingPoints;
    private CubicBezierCurve _currentCurve;

    public CompositeCubicBezierCurve() {
        _curves = new ArrayList<>();
        _pendingPoints = new LinkedList<>();
        _currentCurve = new CubicBezierCurve();
        _curves.add(_currentCurve);
    }

    public void addPoint(Point point) {
        _pendingPoints.add(point);
        System.out.println(_pendingPoints.size());
        if (_pendingPoints.size() == 4) {
            _currentCurve.setP0(_pendingPoints.remove());
            _currentCurve.setP1(_pendingPoints.remove());
            _currentCurve.setP2(_pendingPoints.remove());
            _currentCurve.setP3(_pendingPoints.element());

            _currentCurve = new CubicBezierCurve();
            _currentCurve.setP0(_pendingPoints.element());
            _currentCurve.setP1(_pendingPoints.element());
            _currentCurve.setP2(_pendingPoints.element());
            _currentCurve.setP3(_pendingPoints.element());
            _curves.add(_currentCurve);
        } else {
            Deque<Point> points = new LinkedList<>(_pendingPoints);
            while (points.size() < 4) {
                points.addLast(points.getLast());
                System.out.println(points);
            }
            _currentCurve.setP0(points.removeFirst());
            _currentCurve.setP1(points.removeFirst());
            _currentCurve.setP2(points.removeFirst());
            _currentCurve.setP3(points.removeFirst());
            System.out.println("set stuff");
        }
    }

    public List<Point> interpolate() {
        List<Point> points = new ArrayList<>();
        for (CubicBezierCurve curve : _curves) {
            List<Point> segment = curve.interpolate();
            points.addAll(segment);
        }
        return points;
    }

    public List<Point> getControlPoints() {
        List<Point> controlPoints = new ArrayList<>();
        for (CubicBezierCurve curve : _curves) {
            List<Point> points = curve.getControlPoints();
            controlPoints.addAll(points);
        }
        return controlPoints;
    }
}
