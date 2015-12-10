package solver;

import top.PancakeContext;

import java.util.List;
import java.awt.Point;
import java.awt.Color;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RecipeAnimator {

    private Recipe _recipe;
    private PancakeContext _context;

    public RecipeAnimator(Recipe recipe, PancakeContext context) {
        _recipe = recipe;
        _context = context;
    }

    public void run(final Callback callback) {
        final Timer t = new Timer(50, new ActionListener() {
            int phaseIndex = 0;
            int strokeIndex = 0;
            int pointIndex = 0;

            public void actionPerformed(ActionEvent e) {
                ((Timer) e.getSource()).stop();
                callback.run();
            }

        });
        t.start();
    }
}
