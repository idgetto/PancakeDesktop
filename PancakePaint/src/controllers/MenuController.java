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
import java.awt.Color;

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
        PancakePaintBrush.BrushMode  nextMode;
        Color nextColor;
        switch (event) {
            case LINEAR_STROKE:

                nextMode = PancakePaintBrush.BrushMode.LINEAR_STROKE;
                if (brush.getMode() != nextMode) {
                    _model.finishStroke();
                    brush.setMode(nextMode);
                }
                break;
            case CURVED_STROKE:
                nextMode = PancakePaintBrush.BrushMode.CURVED_STROKE;
                if (brush.getMode() != nextMode) {
                    _model.finishStroke();
                    brush.setMode(nextMode);
                }
                break;
            case YELLOW:
                nextColor = PancakePallete.YELLOW;
                if (brush.getColor() != nextColor) {
                    _model.finishStroke();
                    brush.setColor(nextColor);
                }
                break;
            case BROWN:
                nextColor = PancakePallete.BROWN;
                if (brush.getColor() != nextColor) {
                    _model.finishStroke();
                    brush.setColor(nextColor);
                }
                break;
            case CLEAR:
                _model.finishStroke();
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
