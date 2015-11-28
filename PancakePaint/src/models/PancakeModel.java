package models;

import java.util.List;
import java.util.ArrayList;

public class PancakeModel {

    private List<Stroke> _strokes;
    private PancakePaintBrush _brush;

    public PancakeModel() {
        _strokes = new ArrayList<Stroke>();
        _brush = new PancakePaintBrush();
    }

    public List<Stroke> getStrokes() {
        return _strokes;
    }

    public void setStrokes(List<Stroke> strokes) {
        _strokes = strokes;
    }

    public PancakePaintBrush getBrush() {
        return _brush;
    }
}
