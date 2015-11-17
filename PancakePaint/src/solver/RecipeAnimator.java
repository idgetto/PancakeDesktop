import java.util.List;
import java.awt.Point;
import java.awt.Color;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RecipeAnimator {

    private Recipe _recipe;
    private PancakeModel _model;
    private Paintable _view;

    public RecipeAnimator(Recipe recipe, PancakeModel model, Paintable view) {
        _recipe = recipe;
        _model = model;
        _view = view;
    }

    public void run(final Callback callback) {
        final Grid<Color> grid = _model.getGrid();
        final List<Phase> phases = _recipe.getPhases();

        final Timer t = new Timer(50, new ActionListener() {
            int phaseIndex = 0;
            int strokeIndex = 0;
            int pointIndex = 0;

            public void actionPerformed(ActionEvent e) {
                if (phases.size() > phaseIndex) {
                    Phase phase = phases.get(phaseIndex);

                    List<Stroke> strokes = phase.getStrokes();
                    if (strokes.size() > strokeIndex) {
                        Stroke stroke = strokes.get(strokeIndex);

                        List<Point> points = stroke.getPoints();
                        if (points.size() > pointIndex) {
                            Point point = points.get(pointIndex);
                            grid.set(point.y, point.x, PancakePallete.GREEN);
                            _view.repaint(_model);

                            pointIndex++;
                        } else {
                            pointIndex = 0;
                            strokeIndex++;
                        }
                    } else {
                        pointIndex = 0;
                        strokeIndex = 0;
                        phaseIndex++;
                    }
                } else {
                    ((Timer) e.getSource()).stop();
                    callback.run();
                }
            }

        });
        t.start();
    }
}
