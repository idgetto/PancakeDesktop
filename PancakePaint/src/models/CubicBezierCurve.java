package models;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by isaac on 11/29/15.
 */
public class CubicBezierCurve {
    private Point2D.Double _p0, _p1, _p2, _p3;

    public CubicBezierCurve() {
        _p0 = new Point2D.Double();
        _p1 = new Point2D.Double();
        _p2 = new Point2D.Double();
        _p3 = new Point2D.Double();
    }

    public CubicBezierCurve(Point2D.Double p0, Point2D.Double p1, Point2D.Double p2, Point2D.Double p3) {
        _p0 = p0;
        _p1 = p1;
        _p2 = p2;
        _p3 = p3;
    }

    public List<Point2D.Double> interpolate() {
        double step = 0.01;
        List<Point2D.Double> points = new ArrayList<>();
        for (double u = 0; u <= 1; u += step) {
            Point2D.Double point = calculate(u);
            points.add(point);
        }
        return points;
    }

    /**
     * Calculate the (x, y) point at the parametric term u
     * @param u
     * @return the position
     */
    public Point2D.Double calculate(double u) {
        double x = calculateX(u);
        double y = calculateY(u);
        return new Point2D.Double(x, y);
    }

    private double calculateX(double u) {
        return _calculate(u, _p0.x, _p1.x, _p2.x, _p3.x);
    }

    private double calculateY(double u) {
        return _calculate(u, _p0.y, _p1.y, _p2.y, _p3.y);
    }

    private double _calculate(double u, double a0, double a1, double a2, double a3) {
        double u3 = Math.pow(u, 3);
        double u2 = Math.pow(u, 2);

        return  (-u3 + 3 * u2 - 3 * u + 1) * a0 +
                (3 * u3 - 6 * u2 + 3 * u) * a1 +
                (-3 * u3 + 3 * u2) * a2 +
                u3 * a3;
    }

    public List<Point2D.Double> getControlPoints() {
        return Arrays.asList(_p1, _p2);
    }

    public Point2D.Double getP0() {
        return _p0;
    }

    public void setP0(Point2D.Double p0) {
        _p0 = p0;
    }

    public Point2D.Double getP1() {
        return _p1;
    }

    public void setP1(Point2D.Double p1) {
        _p1 = p1;
    }

    public Point2D.Double getP2() {
        return _p2;
    }

    public void setP2(Point2D.Double p2) {
        _p2 = p2;
    }

    public Point2D.Double getP3() {
        return _p3;
    }

    public void setP3(Point2D.Double p3) {
        _p3 = p3;
    }
}
