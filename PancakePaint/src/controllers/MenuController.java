import java.awt.Color;

public class MenuController implements MenuListener {

    private Paintable _view;
    private PancakeModel _model;
    private PancakePathSolver _pathSolver;
    private PancakeCompiler _pancakeCompiler;

    public MenuController(Paintable view, PancakeModel model) {
        _view = view;
        _model = model;
        _pathSolver = new PancakePathSolver();
        _pancakeCompiler = new PancakeCompiler();
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
            case SOLVE:
                solvePath();
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

    private void solvePath() {
        Recipe recipe = _pathSolver.solve(_model.getGrid());
        animateRecipe(recipe);
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

        String res = _pancakeCompiler.compile(recipe);
        System.out.println(res);
    }
}
