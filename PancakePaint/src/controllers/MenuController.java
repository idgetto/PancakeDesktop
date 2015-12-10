package controllers;

import top.PancakeContext;
import cable.PancakeRecipeStream;
import event.MenuEvent;
import event.MenuListener;
import models.PancakeModel;
import models.PancakePaintBrush;
import solver.PancakePathSolver;
import solver.Recipe;
import models.Stroke;
import views.PancakePallete;

import java.awt.Color;
import java.util.ArrayList;
import java.io.File;

public class MenuController implements MenuListener {

    private PancakeContext _context;
    private PancakePathSolver _pathSolver;
    private PancakeRecipeStream _recipeStream;

    public MenuController(PancakeContext context) {
        _context = context;
        _pathSolver = new PancakePathSolver();
        _recipeStream = new PancakeRecipeStream();
    }

    public void onMenuEvent(MenuEvent event) {
        PancakeModel model = _context.getModel();
        PancakePaintBrush brush = model.getBrush();
        PancakePaintBrush.BrushMode  nextMode;
        Color nextColor;
        switch (event) {
            case LINEAR_STROKE:

                nextMode = PancakePaintBrush.BrushMode.LINEAR_STROKE;
                if (brush.getMode() != nextMode) {
                    model.finishStroke();
                    brush.setMode(nextMode);
                }
                break;
            case CURVED_STROKE:
                nextMode = PancakePaintBrush.BrushMode.CURVED_STROKE;
                if (brush.getMode() != nextMode) {
                    model.finishStroke();
                    brush.setMode(nextMode);
                }
                break;
            case YELLOW:
                nextColor = PancakePallete.YELLOW;
                if (brush.getColor() != nextColor) {
                    model.finishStroke();
                    brush.setColor(nextColor);
                }
                break;
            case BROWN:
                nextColor = PancakePallete.BROWN;
                if (brush.getColor() != nextColor) {
                    model.finishStroke();
                    brush.setColor(nextColor);
                }
                break;
            case CLEAR:
                model.finishStroke();
                clearCanvas();
                break;
            case PRINT:
                Recipe recipe = solvePath();
                System.out.println("Recipe: " + recipe);
                //animateRecipe(recipe);
                sendRecipe(recipe);
                break;
        }
        _context.repaint(model);
    }

    private void clearCanvas() {
        PancakeModel model = _context.getModel();
        model.setStrokes(new ArrayList<Stroke>());
    }

    private Recipe solvePath() {
        PancakeModel model = _context.getModel();
        return _pathSolver.solve(model.getStrokes());
    }

    private void animateRecipe(Recipe recipe) {
    }

    private void sendRecipe(Recipe recipe) {
        _recipeStream.sendRecipe(recipe);
    }
}
