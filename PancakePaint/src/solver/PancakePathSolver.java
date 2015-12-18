package solver;

import models.Stroke;
import views.PancakePallete;

import java.util.ArrayList;
import java.util.List;

public class PancakePathSolver {

    public Recipe solve(List<Stroke> strokes) {
        System.out.println("got " + strokes.size() + " strokes");
        Stroke st = strokes.get(0);
        System.out.println(st.getColor());
        List<Stroke> yellowStrokes = getYellowStrokes(strokes);
        System.out.println("got " + yellowStrokes.size() + " yellow strokes");
        List<Stroke> brownStrokes = getBrownStrokes(strokes);
        System.out.println("got " + brownStrokes.size() + " brown strokes");

        Recipe recipe = new Recipe();

        Phase brownPhase = new Phase();
        brownPhase.setEndDelay(30 * 1000);
        for (Stroke stroke : brownStrokes) {
            brownPhase.addStroke(stroke);
        }
        recipe.addPhase(brownPhase);

        Phase yellowPhase = new Phase();
        for (Stroke stroke : yellowStrokes) {
            yellowPhase.addStroke(stroke);
        }
        recipe.addPhase(yellowPhase);


        return recipe;
    }

    private List<Stroke> getYellowStrokes(List<Stroke> strokes) {
        List<Stroke> yellowStrokes = new ArrayList<>();
        for (Stroke stroke : strokes) {
            if (stroke.getColor().equals(PancakePallete.YELLOW)) {
                yellowStrokes.add(stroke);
            }
        }
        return yellowStrokes;
    }

    private List<Stroke> getBrownStrokes(List<Stroke> strokes) {
        List<Stroke> brownStrokes = new ArrayList<>();
        for (Stroke stroke : strokes) {
            if (stroke.getColor().equals(PancakePallete.BROWN)) {
                brownStrokes.add(stroke);
            }
        }
        return brownStrokes;
    }

}
