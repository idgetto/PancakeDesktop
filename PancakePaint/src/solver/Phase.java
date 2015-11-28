package solver;

import models.Stroke;

import java.util.List;
import java.util.ArrayList;

public class Phase {
    private List<Stroke> _strokes;
    private long _endDelay;

    public Phase() {
        _strokes = new ArrayList<Stroke>();
    }

    public void addStroke(Stroke stroke) {
        _strokes.add(stroke);
    }

    public long getEndDelay() {
        return _endDelay;
    }

    public void setEndDelay(long delay) {
        _endDelay = delay;
    }

    public List<Stroke> getStrokes() {
        return _strokes;
    }
}
