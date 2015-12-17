package models;

import solver.Phase;
import solver.Recipe;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.awt.Point;
import java.text.DecimalFormat;

public class PancakeCompiler {

    // center the drawing within the grill
    private static final double SCALE = 0.8 * (1.0/3.0);
    private static final double MAX_X = 1461;
    private static final double MAX_Y = 651;
    private static final double X_OFFSET = 0.1 * (1.0/3.0) * MAX_X;
    private static final double Y_OFFSET = 0.1 * (1.0/3.0) * MAX_Y;

    private DecimalFormat formatter;

    public Queue<String> compile(Recipe recipe) {
        Queue<String> queue = new LinkedList<String>();
        formatter = new DecimalFormat("0.#");

        // start with the grill on
        appendTemperature(queue, 300);
        appendExtrude(queue, false);

        for (Phase phase : recipe.getPhases()) {
            for (Stroke stroke : phase.getStrokes()) {
                List<Point2D.Double> points = stroke.getPoints();

                // move to the first point and start extruding
                Point2D.Double first = points.get(0);
                appendMove(queue, first.getX(), first.getY());
                queue.add("E 1\n");
                appendExtrude(queue, true);

                for (Point2D.Double point : points) {
                    appendMove(queue, point.x, point.y);
                }

                // stop extruding
                appendExtrude(queue, false);
            }

            appendGoHome(queue);
            appendDelay(queue, phase.getEndDelay());
        }

        // turn the grill off when done
        appendExtrude(queue, false);
        appendTemperature(queue, 0);
        appendDone(queue);

        List<String> q = new ArrayList<>(queue);
        for (String s : q) {
            System.out.print(s);
        }
        return queue;
    }

    private void appendDelay(Queue<String> queue, long delay) {
        StringBuffer buf = new StringBuffer();
        buf.append("W ");
        buf.append(delay);
        buf.append("\n");
        queue.add(buf.toString());
    }

    private void appendMove(Queue<String> queue, double x, double y) {
        x = javaXtoGrillX(x);
        y = javaYtoGrillY(y);

        StringBuffer buf = new StringBuffer();
        buf.append("M ");
        buf.append(formatter.format(X_OFFSET + (x * SCALE)));
        buf.append(" ");
        buf.append(formatter.format(Y_OFFSET + (y * SCALE)));
        buf.append("\n");
        queue.add(buf.toString());
    }

    private void appendExtrude(Queue<String> queue, boolean extrude) {
        StringBuffer buf = new StringBuffer();
        buf.append("E");
        buf.append(" ");
        if (extrude) {
            buf.append("1");
        } else {
            buf.append("0");
        }
        buf.append("\n");
        queue.add(buf.toString());
    }

    private void appendTemperature(Queue<String> queue, double temp) {
        StringBuffer buf = new StringBuffer();
        buf.append("T");
        buf.append(" ");
        buf.append(temp);
        buf.append("\n");
        queue.add(buf.toString());
    }

    private void appendGoHome(Queue<String> queue) {
        queue.add("H\n");
    }

    private void appendDone(Queue<String> queue) {
        queue.add("D\n");
    }

    private double javaXtoGrillX(double x) {
        return x;
    }

    private double javaYtoGrillY(double y) {
        return remap(y, 0, MAX_Y, MAX_Y, 0);
    }

    private double remap(double val,
                         double fromLow, double fromHigh,
                         double toLow, double toHigh) {
        return (val - fromLow) * (toHigh - toLow) / (fromHigh - fromLow) + toLow;
    }

}
