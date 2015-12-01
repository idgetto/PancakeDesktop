package models;

import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by isaac on 11/29/15.
 */
public class CompositeCubicCurve {
    private List<Point> _points;
    private SplineInterpolator _splineInterpolator;
    private PolynomialSplineFunction _xSpline, _ySpline;

    public CompositeCubicCurve() {
        _points = new ArrayList<>();
        _splineInterpolator = new SplineInterpolator();
    }

    public void addPoint(Point point) {
        _points.add(point);
        calculateSplines(_points);
    }

    public List<Point> interpolate() {
        if (_xSpline == null || _ySpline == null) {
            return _points;
        }

        double tStep = 0.01;
        List<Point> points = new ArrayList<>();
        for (double time = 0; time < _points.size() - 1; time += tStep) {
            int xVal = (int) _xSpline.value(time);
            int yVal = (int) _ySpline.value(time);
            points.add(new Point(xVal, yVal));
        }
        return points;
    }

    private void calculateSplines(List<Point> points) {
        if (points.size() < 3) {
            return;
        }

        double[] times = new double[_points.size()];
        double[] xVals = new double[_points.size()];
        double[] yVals = new double[_points.size()];
        for (int i = 0; i < times.length; ++i) {
            times[i] = i;
            xVals[i] = points.get(i).x;
            yVals[i] = points.get(i).y;
        }

        _xSpline = _splineInterpolator.interpolate(times, xVals);
        _ySpline = _splineInterpolator.interpolate(times, yVals);
    }
}
