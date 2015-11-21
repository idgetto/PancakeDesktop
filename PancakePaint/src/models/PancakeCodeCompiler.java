import java.util.List;
import java.awt.Point;

public class PancakeCodeCompiler {

    private static final double SCALE = 5.55;

    private StringBuffer _buf;

    public PancakeCodeCompiler() {
        _buf = new StringBuffer();
    }

    public String compile(Recipe recipe) {
        includeHeaders();
        setupFunction();
        runFunction(recipe);
        loopFunction();
        return _buf.toString();
    }

    private void setupFunction() {
        _buf.append("PancakePrinter pp;\n");
        _buf.append("void setup() {\n");
        _buf.append("pp.init();\n");
        _buf.append("run();\n");
        _buf.append("}\n");
    }

    private void runFunction(Recipe recipe) {
        _buf.append("void run() {\n");
        // start with the grill on
        setTemperature(300);
        extrudeOff();

        for (Phase phase : recipe.getPhases()) {
            for (Stroke stroke : phase.getStrokes()) {
                List<Point> points = stroke.getPoints();

                // move to the first point and start extruding
                Point first = points.get(0);
                move(first.x, first.y);
                extrudeOn();

                for (Point point : points) {
                    move(point.x, point.y);
                }

                // stop extruding
                extrudeOff();
            }

            // delay for shading
            phaseDelay(phase);
        }

        // stop extruding
        extrudeOff();

        // turn off grill
        setTemperature(0);

        // go home
        goHome();

        _buf.append("}\n");
    }

    private void loopFunction() {
        _buf.append("void loop() {}\n");
    }

    private void delay(long delay) {
        _buf.append("delay(");
        _buf.append(delay);
        _buf.append(");\n");
    }

    private void move(float x, float y) {
        _buf.append("pp.moveTo(");
        _buf.append(x * SCALE);
        _buf.append(", ");
        _buf.append(y * SCALE);
        _buf.append(");\n");
    }

    private void setTemperature(float temp) {
        _buf.append("pp.setTemperature(");
        _buf.append(temp);
        _buf.append(");\n");
    }

    private void extrudeOn() {
        _buf.append("pp.extrudeOn();\n");
    }

    private void extrudeOff() {
        _buf.append("pp.extrudeOff();\n");
    }

    private void includeHeaders() {
        _buf.append("#include <Servo.h>\n");
        _buf.append("#include <Adafruit_MotorShield.h>\n");
        _buf.append("#include <Wire.h>\n");
        _buf.append("#include <PancakePrinter.h>\n");
    }

    private void goHome() {
        move(0, 0);
    }

    private void phaseDelay(Phase phase) {
        _buf.append("delay(");
        _buf.append(phase.getEndDelay());
        _buf.append(");\n");
    }

}
