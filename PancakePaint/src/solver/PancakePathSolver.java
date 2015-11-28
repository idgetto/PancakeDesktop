package solver;

import models.Stroke;
import views.PancakePallete;

import java.util.ArrayList;
import java.util.List;

public class PancakePathSolver {

    public Recipe solve(List<Stroke> strokes) {
        List<Stroke> yellowStrokes = getYellowStrokes(strokes);
        List<Stroke> brownStrokes = getBrownStrokes(strokes);

        Recipe recipe = new Recipe();
        Phase yellowPhase = new Phase();
        for (Stroke stroke : yellowStrokes) {
            yellowPhase.addStroke(stroke);
        }
        recipe.addPhase(yellowPhase);

        Phase brownPhase = new Phase();
        for (Stroke stroke : brownStrokes) {
            brownPhase.addStroke(stroke);
        }
        recipe.addPhase(brownPhase);

        return recipe;
    }

    private List<Stroke> getYellowStrokes(List<Stroke> strokes) {
        List<Stroke> yellowStrokes = new ArrayList<>();
        for (Stroke stroke : strokes) {
            if (stroke.getColor() == PancakePallete.YELLOW) {
                yellowStrokes.add(stroke);
            }
        }
        return yellowStrokes;
    }

    private List<Stroke> getBrownStrokes(List<Stroke> strokes) {
        List<Stroke> brownStrokes = new ArrayList<>();
        for (Stroke stroke : strokes) {
            if (stroke.getColor() == PancakePallete.BROWN) {
                brownStrokes.add(stroke);
            }
        }
        return brownStrokes;
    }

}
