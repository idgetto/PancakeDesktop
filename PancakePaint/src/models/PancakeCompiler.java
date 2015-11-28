package models;

import solver.Phase;
import solver.Recipe;
import solver.Stroke;

import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.awt.Point;
import java.text.DecimalFormat;

public class PancakeCompiler {

    private static final double SCALE = 5;
    private DecimalFormat formatter;

    public Queue<String> compile(Recipe recipe) {
        Queue<String> queue = new LinkedList<String>();
        formatter = new DecimalFormat("0.#");

        // start with the grill on
        queue.add("T 300\n");
        queue.add("E 0\n");

        for (Phase phase : recipe.getPhases()) {
            for (Stroke stroke : phase.getStrokes()) {
                List<Point> points = stroke.getPoints();

                // move to the first point and start extruding
                Point first = points.get(0);
                appendMove(queue, first.x, first.y);
                queue.add("E 1\n");

                for (Point point : points) {
                    appendMove(queue, point.x, point.y);
                }

                // stop extruding
                queue.add("E 0\n");
            }

            appendDelay(queue, phase.getEndDelay());
        }

        // turn the grill off when done
        queue.add("E 0\n");
        queue.add("T 0\n");
        queue.add("D\n");

        return queue;
    }

    private void appendDelay(Queue<String> queue, long delay) {
        StringBuffer buf = new StringBuffer();
        buf.append("W ");
        buf.append(delay);
        buf.append("\n");
        queue.add(buf.toString());
    }

    private void appendMove(Queue<String> queue, float x, float y) {
        StringBuffer buf = new StringBuffer();
        buf.append("M ");
        buf.append(formatter.format(x * SCALE));
        buf.append(" ");
        buf.append(formatter.format(y * SCALE));
        buf.append("\n");
        queue.add(buf.toString());
    }

}
