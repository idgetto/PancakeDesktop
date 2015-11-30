package models;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by isaac on 11/29/15.
 */
public class CompositeCubicCurve {
    private List<CubicCurve> _curves;
    private CubicCurve _currentCurve;
    private int _points;

    public CompositeCubicCurve() {
        _curves = new ArrayList<>();
        _currentCurve = new CubicCurve();
        _points = 0;
    }

    public void addPoint(Point point) {
        ++_points;

        if (_curves.isEmpty()) {
            _currentCurve = new CubicCurve();
            _curves.add(_currentCurve);

            _currentCurve.setP0(point);
            _currentCurve.setP1(point);
            _currentCurve.setP2(point);
            _currentCurve.setP3(point);
            return;
        }

        if (_points <= 4) {
            switch (_points % 4) {
                case 1:
                    _currentCurve = new CubicCurve();
                    _curves.add(_currentCurve);

                    _currentCurve.setP0(point);
                case 2:
                    _currentCurve.setP1(point);
                case 3:
                    _currentCurve.setP2(point);
                case 0:
                    _currentCurve.setP3(point);
            }
        } else {
            _currentCurve = new CubicCurve();
            _curves.add(_currentCurve);

            CubicCurve last = _curves.get(_curves.size() - 2);
            System.out.println("last: " + last.getP0() + " " + last.getP1() + " " + last.getP2() + " " + last.getP3());
            _currentCurve.setP0(last.getP1());
            _currentCurve.setP1(last.getP2());
            _currentCurve.setP2(last.getP3());
            _currentCurve.setP3(point);
            System.out.println("" + _currentCurve.getP0() + " " + _currentCurve.getP1() + " " + _currentCurve.getP2() + " " + _currentCurve.getP3());
        }
    }

    public List<Point> interpolate() {
        List<Point> points = new ArrayList<>();
        if (!_curves.isEmpty()) {
            CubicCurve first = _curves.get(0);
            points.addAll(first.interpolate(0, 1, 0.01));

            for (int i = 1; i < _curves.size(); ++i) {
                CubicCurve curve = _curves.get(i);
                points.addAll(curve.interpolate(2.0 / 3, 1, 0.01));
            }
        }
        return points;
    }
}
