import java.awt.Color;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier; 
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent; 
import gnu.io.SerialPortEventListener; 
import java.util.Enumeration;
import java.util.Timer;
import java.util.TimerTask;

public class MenuController implements MenuListener {

    private Paintable _view; private PancakeModel _model;
    private PancakePathSolver _pathSolver;
    private PancakeRecipeStream _recipeStream;

    public MenuController(Paintable view, PancakeModel model) {
        _view = view;
        _model = model;
        _pathSolver = new PancakePathSolver();
        _recipeStream = new PancakeRecipeStream();
    }

    public void onMenuEvent(MenuEvent event) {
        PancakePaintBrush brush = _model.getBrush();
        switch (event) {
            case PENCIL:
                brush.setMode(PancakePaintBrush.BrushMode.PENCIL);
                break;
            case FILL:
                brush.setMode(PancakePaintBrush.BrushMode.FILL);
                break;
            case ERASE:
                brush.setMode(PancakePaintBrush.BrushMode.ERASE);
                break;
            case YELLOW:
                brush.setColor(PancakePallete.YELLOW);
                break;
            case BROWN:
                brush.setColor(PancakePallete.BROWN);
                break;
            case CLEAR:
                clearGrid();
                break;
            case PRINT:
                Recipe recipe = solvePath();
                animateRecipe(recipe);
                sendRecipe(recipe);
                break;
        }
        _view.repaint(_model);
    }

    private void clearGrid() {
        Grid<Color> grid = _model.getGrid();
        for (int row = 0; row < grid.getNumRows(); ++row) {
            for (int col = 0; col < grid.getNumCols(); ++col) {
                grid.set(row, col, PancakePallete.WHITE);
            }
        }
    }

    private Recipe solvePath() {
        return _pathSolver.solve(_model.getGrid());
    }

    private void animateRecipe(Recipe recipe) {
        PancakeModel solveModel = new PancakeModel();
        PancakePaintBrush brush = solveModel.getBrush();
        brush.setColor(PancakePallete.GREEN);
        solveModel.setGrid(new Grid<Color>(_model.getGrid()));

        RecipeAnimator animator = new RecipeAnimator(recipe, solveModel, _view);
        animator.run(new Callback() {
            public void run() {
                _view.repaint(_model);
            }
        });
    }

    private void sendRecipe(Recipe recipe) {
        _recipeStream.sendRecipe(recipe);
    }
}
