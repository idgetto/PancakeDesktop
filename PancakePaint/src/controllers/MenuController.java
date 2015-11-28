package controllers;

import cable.PancakeRecipeStream;
import event.MenuEvent;
import event.MenuListener;
import models.PancakeModel;
import models.PancakePaintBrush;
import solver.PancakePathSolver;
import solver.Recipe;
import models.Stroke;
import views.Paintable;
import views.PancakePallete;

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
            case LINEAR_STROKE:
                brush.setMode(PancakePaintBrush.BrushMode.LINEAR_STROKE);
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
                System.out.println("Recipe: " + recipe);
                //animateRecipe(recipe);
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
