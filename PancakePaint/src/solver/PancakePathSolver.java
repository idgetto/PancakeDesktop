import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import java.awt.Point;
import java.util.Set;
import java.util.HashSet;

public class PancakePathSolver {

    public Recipe solve(Grid<Color> grid) {
        Grid<Color> g = new Grid<Color>(grid);
        List<Color> colors = getColors(g);

        Recipe recipe = new Recipe();
        for (int i = 0; i < colors.size(); ++i) {
            recipe.addPhase(new Phase());
        }

        for (int i = 0; i < colors.size(); ++i) {
            Color color = colors.get(i);
            Phase phase = recipe.getPhase(i);

            // until all paths of this color are found
            while (containsColor(color, g)) {
                Point point = findColor(color, g);

                // find this stroke
                Stroke stroke = new Stroke();
                phase.addStroke(stroke);
                floodFillSolve(point, color, phase, stroke, g);
            }
        }

        System.out.println(recipe);
        return recipe;
    }

    private boolean containsColor(Color color, Grid<Color> grid) {
        for (int r = 0; r < grid.getNumRows(); ++r) {
            for (int c = 0; c < grid.getNumCols(); ++c) {
                if (grid.get(r, c).equals(color)) {
                    return true;
                }
            }
        }
        return false;
    }

    private Point findColor(Color color, Grid<Color> grid) {
        for (int r = 0; r < grid.getNumRows(); ++r) {
            for (int c = 0; c < grid.getNumCols(); ++c) {
                if (grid.get(r, c).equals(color)) {
                    return new Point(c, r);
                }
            }
        }
        return null;
    }

    private List<Color> getColors(Grid<Color> grid) {
        Set<Color> colors = new HashSet<Color>();
        for (int r = 0; r < grid.getNumRows(); ++r) {
            for (int c = 0; c < grid.getNumCols(); ++c) {
                colors.add(grid.get(r, c));
            }
        }
        colors.remove(PancakePallete.WHITE);
        return new ArrayList<Color>(colors);
    }

    private void floodFillSolve(Point point, 
                                Color color, 
                                Phase phase, 
                                Stroke stroke, 
                                Grid<Color> grid) {

        
        if (inBounds(point, grid) && grid.get(point.y, point.x).equals(color)) {

            Point lastPoint = stroke.getLastPoint();
            boolean adjac = (lastPoint == null || adjacent(lastPoint, point));
            if (adjac) {
                stroke.addPoint(point); 
            } else {
                stroke = new Stroke();
                stroke.addPoint(point); 
                phase.addStroke(stroke);
            }

            // mark this point as visited
            grid.set(point.y, point.x, Color.WHITE);

            Point up = new Point(point.x - 1, point.y);
            Point down = new Point(point.x + 1, point.y);
            Point left = new Point(point.x, point.y - 1);
            Point right = new Point(point.x, point.y + 1);

            List<Point> points = getAdjacentPoints(point);
            for (Point p : points) {
                floodFillSolve(p, color, phase, stroke, grid);
            }
            
        }
    }

    private List<Point> getAdjacentPoints(Point point) {
        List<Point> points = new ArrayList<Point>(8);

        points.add(new Point(point.x, point.y - 1));
        points.add(new Point(point.x - 1, point.y));
        points.add(new Point(point.x + 1, point.y));
        points.add(new Point(point.x, point.y + 1 ));

        points.add(new Point(point.x + 1, point.y - 1));
        points.add(new Point(point.x - 1, point.y + 1));
        points.add(new Point(point.x + 1, point.y + 1));
        points.add(new Point(point.x - 1, point.y - 1));

        return points;
    }

    private boolean inBounds(Point point, Grid<Color> grid) {
        return point.x >= 0 && point.y >= 0 && 
               point.x < grid.getNumCols() && point.y < grid.getNumRows();

    }

    private boolean adjacent(Point a, Point b) {
        int rowDiff = Math.abs(a.y - b.y);
        int colDiff = Math.abs(a.x - b.x);
        int diff = rowDiff + colDiff;
        return diff <= 2;
    }
}
