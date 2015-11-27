import java.util.ArrayList;

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
                clearCanvas();
                break;
            case PRINT:
                Recipe recipe = solvePath();
                animateRecipe(recipe);
                sendRecipe(recipe);
                break;
        }
        _view.repaint(_model);
    }

    private void clearCanvas() {
        _model.setStrokes(new ArrayList<Stroke>());
    }

    private Recipe solvePath() {
        return _pathSolver.solve(_model.getStrokes());
    }

    private void animateRecipe(Recipe recipe) {
    }

    private void sendRecipe(Recipe recipe) {
        _recipeStream.sendRecipe(recipe);
    }
}
