package models;

import views.PancakePallete;

import java.awt.Color;

public class PancakePaintBrush {

    public enum BrushMode {
        LINEAR_STROKE
    }

    private Color _color ;
    private BrushMode _mode;

    public PancakePaintBrush() {
        _color = PancakePallete.YELLOW;
        _mode = BrushMode.LINEAR_STROKE;
    }

    public void setColor(Color color) {
        _color = color;
    }

    public Color getColor() {
        return _color;
    }

    public void setMode(BrushMode mode) {
        _mode = mode;
    }

    public BrushMode getMode() {
        return _mode;
    }

}
