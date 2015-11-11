import java.awt.Color;

public class PancakeSolverTest {

    public static void main(String[] args) {
       Color[][] grid = {{Color.WHITE, Color.WHITE,  Color.WHITE, Color.WHITE,  Color.WHITE},
                         {Color.WHITE, Color.YELLOW, Color.YELLOW,Color.YELLOW, Color.WHITE},
                         {Color.WHITE, Color.YELLOW, Color.RED, Color.YELLOW, Color.WHITE},
                         {Color.WHITE, Color.YELLOW, Color.YELLOW,Color.YELLOW, Color.WHITE},
                         {Color.WHITE, Color.YELLOW,  Color.WHITE, Color.WHITE,  Color.WHITE}};

       PancakePathSolver solver = new PancakePathSolver(); 
       solver.solve(grid);
    }

}
