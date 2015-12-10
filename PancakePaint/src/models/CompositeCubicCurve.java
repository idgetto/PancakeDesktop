package models;

import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.IOException;

/**
 * Created by isaac on 11/29/15.
 */
public class CompositeCubicCurve implements Serializable {
    private List<Point2D.Double> _points;
    private transient SplineInterpolator _splineInterpolator;
    private transient PolynomialSplineFunction _xSpline, _ySpline;

    public CompositeCubicCurve() {
        _points = new ArrayList<>();
        _splineInterpolator = new SplineInterpolator();
    }

    public void addPoint(Point2D.Double point) {
        _points.add(point);
        calculateSplines(_points);
    }

    public List<Point2D.Double> getPoints() {
        return _points;
    }

    public void setPoints(List<Point2D.Double> points) {
        _points = points;
    }

    public List<Point2D.Double> interpolate() {
        if (_xSpline == null || _ySpline == null) {
            return _points;
        }

        double tStep = 0.01;
        List<Point2D.Double> points = new ArrayList<>();
        for (double time = 0; time < _points.size() - 1; time += tStep) {
            double xVal = (double) _xSpline.value(time);
            double yVal = (double) _ySpline.value(time);
            points.add(new Point2D.Double(xVal, yVal));
        }
        return points;
    }

    private void calculateSplines(List<Point2D.Double> points) {
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

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        setup();
    }

    private void setup() {
        _splineInterpolator = new SplineInterpolator();
        calculateSplines(getPoints());
    }
}
