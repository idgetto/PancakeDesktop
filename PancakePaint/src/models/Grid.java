import java.util.List;
import java.util.ArrayList;

public class Grid<T> {

    private int _rows, _cols;
    private List<List<T>> _grid;

    public Grid(int rows, int cols, T initial) {
        _rows = rows;
        _cols = cols;
        _grid = new ArrayList<List<T>>(rows);
        for (int row = 0; row < _rows; ++row) {
            _grid.add(new ArrayList<T>(_cols));
            for (int col = 0; col < _cols; ++col) {
                _grid.get(row).add(initial);
            }
        }
    }

    public Grid(Grid<T> other) {
        _rows = other.getNumRows();
        _cols = other.getNumCols();
        _grid = new ArrayList<List<T>>(_rows);
        for (int row = 0; row < _rows; ++row) {
            _grid.add(new ArrayList<T>(_cols));
            for (int col = 0; col < _cols; ++col) {
                T val = other.get(row, col);
                _grid.get(row).add(val);
            }
        }
    }

    public T get(int row, int col) {
        return _grid.get(row).get(col);
    }

    public void set(int row, int col, T val) {
        _grid.get(row).set(col, val);
    }

    public int getNumRows() {
        return _rows;
    }

    public int getNumCols() {
        return _cols;
    }

}
