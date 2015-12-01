package models;

import Jama.Matrix;

import java.awt.Point;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by isaac on 11/29/15.
 */
public class CubicCurve {
    private List<Point> _points;

    public CubicCurve() {
        _points = new ArrayList<>();
    }

    public void addPoint(Point point) {
        _points.add(point);
    }

    public List<Point> getPoints() {
        return _points;
    }

    public List<Point> interpolate() {
        if (_points.size() < 4) {
            return new ArrayList<>();
        }

        List<Point> points = new ArrayList<>();
        double step = 0.01;
        for (double u = 0; u <= 1; u += step) {
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
        List<Integer> xs = new ArrayList<>();
        for (Point point : _points) {
            xs.add(point.x);
        }
        return _calculate(u, xs);
    }

    private double calculateY(double u) {
        List<Integer> ys = new ArrayList<>();
        for (Point point : _points) {
            ys.add(point.y);
        }
        return _calculate(u, ys);
    }

    private double _calculate(double u, List<Integer> vals) {
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
