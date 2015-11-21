import java.util.List;
import java.awt.Point;

public class PancakeCompiler {

    public String compile(Recipe recipe) {
        StringBuffer buf = new StringBuffer();

        // start with the grill on
        buf.append("TEMP 300\n");
        buf.append("EXTRUDE 0\n");

        for (Phase phase : recipe.getPhases()) {
            for (Stroke stroke : phase.getStrokes()) {
                List<Point> points = stroke.getPoints();

                // move to the first point and start extruding
                Point first = points.get(0);
                appendMove(buf, first.x, first.y);
                buf.append("EXTRUDE 1\n");

                for (Point point : points) {
                    appendMove(buf, point.x, point.y);
                }

                // stop extruding
                buf.append("EXTRUDE 0\n");
            }

            appendDelay(buf, phase.getEndDelay());
        }

        // turn the grill off when done
        buf.append("EXTRUDE 0\n");
        buf.append("TEMP 0\n");
        buf.append("DONE\n");

        return buf.toString();
    }

    private void appendDelay(StringBuffer buf, long delay) {
            buf.append("DELAY ");
            buf.append(delay);
            buf.append("\n");
    }

    private void appendMove(StringBuffer buf, float x, float y) {
                buf.append("MOVE ");
                buf.append(x); // will need to multiply by coeff
                buf.append(" ");
                buf.append(y);
                buf.append("\n");
    }

}
