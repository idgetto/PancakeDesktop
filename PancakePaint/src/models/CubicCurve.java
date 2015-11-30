package models;

import java.awt.Point;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by isaac on 11/29/15.
 */
public class CubicCurve {
    private Point p0, p1, p2, p3;

    public CubicCurve() {
        p0 = new Point();
        p1 = new Point();
        p2 = new Point();
        p3 = new Point();
    }

    public CubicCurve(Point p0, Point p1, Point p2, Point p3) {
        this.p0 = p0;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public List<Point> interpolate(double start, double end, double step) {
        List<Point> points = new ArrayList<>();
        for (double u = start; u <= end; u += step) {
            Point point = calculatePoint(u);
            points.add(point);
        }
        return points;
    }

    public Point calculatePoint(double u) {
        int x = (int) calculateX(u);
        int y = (int) calculateY(u);
        return new Point(x, y);
    }

    private double calculateX(double u) {
        return _calculate(u, p0.x, p1.x, p2.x, p3.x);
    }

    private double calculateY(double u) {
        return _calculate(u, p0.y, p1.y, p2.y, p3.y);
    }

    private double _calculate(double u, double a0, double a1, double a2, double a3) {
        double u3 = Math.pow(u, 3);
        double u2 = Math.pow(u, 2);


        return u3 * ((-9.0/2) * a0 + (27.0/2) * a1 + (-27.0/2) * a2 + (9.0/2) * a3) +
               u2 * (9 * a0 - (45.0/2) * a1 + 18 * a2 + (-9.0/2) * a3) +
               u * ((-11.0/2) * a0 + 9 * a1 + (-9.0/2) * a2 + a3) +
               a0;
    }

    public Point getP0() {
        return p0;
    }

    public void setP0(Point p0) {
        this.p0 = p0;
    }

    public Point getP1() {
        return p1;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    public Point getP3() {
        return p3;
    }

    public void setP3(Point p3) {
        this.p3 = p3;
    }

}
