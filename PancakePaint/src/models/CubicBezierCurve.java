package models;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by isaac on 11/29/15.
 */
public class CubicBezierCurve {
    private Point _p0, _p1, _p2, _p3;

    public CubicBezierCurve() {
        _p0 = new Point();
        _p1 = new Point();
        _p2 = new Point();
        _p3 = new Point();
    }

    public CubicBezierCurve(Point p0, Point p1, Point p2, Point p3) {
        _p0 = p0;
        _p1 = p1;
        _p2 = p2;
        _p3 = p3;
    }

    public List<Point> interpolate() {
        double step = 0.01;
        List<Point> points = new ArrayList<>();
        for (double u = 0; u <= 1; u += step) {
            Point point = calculate(u);
            points.add(point);
        }
        return points;
    }

    /**
     * Calculate the (x, y) point at the parametric term u
     * @param u
     * @return the position
     */
    public Point calculate(double u) {
        int x = (int) calculateX(u);
        int y = (int) calculateY(u);
        return new Point(x, y);
    }

    private double calculateX(double u) {
        return _calculate(u, _p0.x, _p1.x, _p2.x, _p3.x);
    }

    private double calculateY(double u) {
        return _calculate(u, _p0.y, _p1.y, _p2.y, _p3.y);
    }

    private double _calculate(double u, int a0, int a1, int a2, int a3) {
        double u3 = Math.pow(u, 3);
        double u2 = Math.pow(u, 2);

        return  (-u3 + 3 * u2 - 3 * u + 1) * a0 +
                (3 * u3 - 6 * u2 + 3 * u) * a1 +
                (-3 * u3 + 3 * u2) * a2 +
                u3 * a3;
    }

    public List<Point> getControlPoints() {
        return Arrays.asList(_p1, _p2);
    }

    public Point getP0() {
        return _p0;
    }

    public void setP0(Point p0) {
        _p0 = p0;
    }

    public Point getP1() {
        return _p1;
    }

    public void setP1(Point p1) {
        _p1 = p1;
    }

    public Point getP2() {
        return _p2;
    }

    public void setP2(Point p2) {
        _p2 = p2;
    }

    public Point getP3() {
        return _p3;
    }

    public void setP3(Point p3) {
        _p3 = p3;
    }
}
