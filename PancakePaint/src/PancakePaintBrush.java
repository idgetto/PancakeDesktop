import java.awt.Color;

public class PancakePaintBrush {

    private Color _color ;
    private boolean _fill;

    public PancakePaintBrush() {
        _color = Color.WHITE;
        _fill = false;
    }

    public void setColor(Color color) {
        _color = color;
    }

    public void setFill(boolean fill) {
        _fill = fill;
    }

    public Color getColor() {
        return _color;
    }

    public boolean isFill() {
        return _fill;
    }
}
