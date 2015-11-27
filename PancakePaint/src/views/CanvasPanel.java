import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;

public class CanvasPanel extends JPanel {

    private PancakeModel _model;
    private int _width;
    private int _height;
    private CanvasListener _listener;

    public CanvasPanel(PancakeModel model, int width, int height) {
        _model = model;
        _width = width;
        _height = height;
    }

    public void setCanvasListener(CanvasListener listener) {
        _listener = listener;
    }

    public Dimension getPreferredSize() {
        return new Dimension(_width, _height);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(PancakePallete.WHITE);
        g.fillRect(0, 0, _width, _height);
    }

    public void repaint(PancakeModel model) {
        _model = model;
        repaint();
    }

}
