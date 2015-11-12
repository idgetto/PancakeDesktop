import java.awt.Color; 

public class PancakeModel {

    private static final int ROWS = 30;
    private static final int COLS = 60;

    private Grid<Color> _grid;
    private PancakePaintBrush _brush;

    public PancakeModel() {
        _grid = new Grid<Color>(ROWS, COLS, PancakePallete.WHITE);
        _brush = new PancakePaintBrush();
    }

    public Grid<Color> getGrid() {
        return _grid;
    }

    public void setGrid(Grid<Color> grid) {
        _grid = grid;
    }

    public PancakePaintBrush getBrush() {
        return _brush;
    }
}
