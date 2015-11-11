import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import java.awt.Point;
import java.util.Set;
import java.util.HashSet;

public class PancakePathSolver {

    public Recipe solve(Color[][] grid) {

        System.out.println("Start");
        Color[][] g = new Color[grid.length][grid[0].length];
        for (int row = 0; row < grid.length; ++row) {
            for (int col = 0; col < grid[0].length; ++col) {
                g[row][col] = grid[row][col];
            }
        }

        List<Color> colors = getColors(g);
        System.out.println("got colors");

        Recipe recipe = new Recipe();
        for (int i = 0; i < colors.size(); ++i) {
            recipe.addPhase(new Phase());
        }
        System.out.println("create phases");


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

    private boolean containsColor(Color color, Color[][] grid) {
        for (int r = 0; r < grid.length; ++r) {
            for (int c = 0; c < grid[r].length; ++c) {
                if (grid[r][c].equals(color)) {
                    return true;
                }
            }
        }
        return false;
    }

    private Point findColor(Color color, Color[][] grid) {
        for (int r = 0; r < grid.length; ++r) {
            for (int c = 0; c < grid[r].length; ++c) {
                if (grid[r][c].equals(color)) {
                    return new Point(c, r);
                }
            }
        }
        return null;
    }

    private List<Color> getColors(Color[][] grid) {
        Set<Color> colors = new HashSet<Color>();
        for (int r = 0; r < grid.length; ++r) {
            for (int c = 0; c < grid[r].length; ++c) {
                colors.add(grid[r][c]);
            }
        }
        colors.remove(Color.WHITE);
        return new ArrayList<Color>(colors);
    }

    private void floodFillSolve(Point point, Color color, Phase phase, Stroke stroke, Color[][] grid) {

        
        if (inBounds(point, grid) && grid[point.y][point.x].equals(color)) {

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
            grid[point.y][point.x] = Color.WHITE;

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

    private boolean inBounds(Point point, Color[][] grid) {
        return point.x >= 0 && point.y >= 0 && 
               point.x < grid[0].length && point.y < grid.length;

    }

    private boolean adjacent(Point a, Point b) {
        int rowDiff = Math.abs(a.y - b.y);
        int colDiff = Math.abs(a.x - b.x);
        int diff = rowDiff + colDiff;
        return diff <= 2;
    }
}
