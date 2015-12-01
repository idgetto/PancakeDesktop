package models;

import Jama.Matrix;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by isaac on 11/29/15.
 */
public class CubicCurve {
    private List<Point2D.Double> _points;

    public CubicCurve() {
        _points = new ArrayList<>();
    }

    public void addPoint(Point2D.Double point) {
        _points.add(point);
    }

    public List<Point2D.Double> getPoints() {
        return _points;
    }

    public List<Point2D.Double> interpolate() {
        if (_points.size() < 4) {
            return new ArrayList<>();
        }

        List<Point2D.Double> points = new ArrayList<>();
        double step = 0.01;
        for (double u = 0; u <= 1; u += step) {
            Point2D.Double point = calculatePoint(u);
            points.add(point);
        }
        return points;
    }

    public Point2D.Double calculatePoint(double u) {
        double x = calculateX(u);
        double y = calculateY(u);
        return new Point2D.Double(x, y);
    }

    private double calculateX(double u) {
        List<Double> xs = new ArrayList<>();
        for (Point2D.Double point : _points) {
            xs.add(point.getX());
        }
        return _calculate(u, xs);
    }

    private double calculateY(double u) {
        List<Double> ys = new ArrayList<>();
        for (Point2D.Double point : _points) {
            ys.add(point.getY());
        }
        return _calculate(u, ys);
    }

    private double _calculate(double u, List<Double> vals) {
        double u3 = Math.pow(u, 3);
        double u2 = Math.pow(u, 2);


        if (vals.isEmpty()) {
            return 0;
        }

        double bArr[][] = new double[100][vals.size()];
        for (int row = 0; row < 100; ++row) {
            double t = ((double) row / vals.size());
            for (int col = 0; col < vals.size(); ++ col) {
                int power = vals.size() - col - 1;
                bArr[row][col] = Math.pow(t, power);
            }
        }
        Matrix b = new Matrix(bArr);
        Matrix bInv = b.inverse().transpose();

        double uArr[][] = new double[1][100];
        for (int i = 0; i < vals.size(); ++i) {
            int power = vals.size() - i - 1;
            uArr[0][i] = Math.pow(u, power);
        }
        Matrix uMat = new Matrix(uArr);

        double vArr[][] = new double[vals.size()][1];
        for (int i = 0; i < vals.size(); ++i) {
            vArr[i][0] = vals.get(i);
        }
        Matrix vMat = new Matrix(vArr);

        System.out.println(bInv.getRowDimension());
        System.out.println(bInv.getColumnDimension());
        Matrix res = bInv.times(vMat);
        res = uMat.times(res);
        return res.get(0, 0);
    }

}
