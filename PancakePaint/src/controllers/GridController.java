import java.awt.Color;
import java.awt.Point;

public class GridController implements GridListener {

    private Paintable _view;
    private PancakeModel _model;

    public GridController(Paintable view, PancakeModel model) {
        _view = view;
        _model = model;
    }

    public void onGridEvent(GridEvent event) {
        PancakePaintBrush brush = _model.getBrush();
        Color brushColor = brush.getColor();

        Point cell = new Point(event.x, event.y);
        Grid<Color> grid = _model.getGrid();
        boolean touched = (grid.get(cell.y, cell.x) != PancakePallete.WHITE);
        boolean differentColor = (grid.get(cell.y, cell.x) != brushColor);

        if (!touched || differentColor) {
            if (brush.getMode() == PancakePaintBrush.BrushMode.FILL) {
                floodFill(cell, brushColor, grid.get(cell.y, cell.x));
            } else {
                grid.set(cell.y, cell.x, brushColor);
            }
        } else if (touched && !differentColor) {
            grid.set(cell.y, cell.x, PancakePallete.WHITE);
        }

        _view.repaint(_model);
    }

    private void floodFill(Point cell, Color color, Color initialColor) {
        Grid<Color> grid = _model.getGrid();
        if (inBounds(cell) && grid.get(cell.y, cell.x) == initialColor) {
            grid.set(cell.y, cell.x, color);

            floodFill(new Point(cell.x-1, cell.y), color, initialColor);
            floodFill(new Point(cell.x+1, cell.y), color, initialColor);
            floodFill(new Point(cell.x, cell.y-1), color, initialColor);
            floodFill(new Point(cell.x, cell.y+1), color, initialColor);
        }
    }

    private boolean inBounds(Point p) {
        Grid<Color> grid = _model.getGrid();
        return  p.x >= 0 && 
                p.y >= 0 && 
                p.x < grid.getNumCols() && 
                p.y < grid.getNumRows();
    }
}
