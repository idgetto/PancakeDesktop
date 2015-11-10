import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;

public class MenuPanel extends JPanel {

    private  Rectangle2D.Double _pencilButton;
    private  Rectangle2D.Double _fillButton;
    private  Rectangle2D.Double _yellowButton;
    private  Rectangle2D.Double _brownButton;

    PancakePaintBrush _brush;

    public MenuPanel(PancakePaintBrush brush) {
        _brush = brush;
        _brush.setColor(Color.YELLOW);

        _pencilButton = new Rectangle2D.Double(0, 
                                               0, 
                                               getButtonWidth(), 
                                               getButtonHeight());

        _fillButton = new Rectangle2D.Double(getButtonWidth(), 
                                             0, 
                                             getButtonWidth(), 
                                             getButtonHeight());

        _yellowButton = new Rectangle2D.Double(2 * getButtonWidth(), 
                                               0,
                                               getButtonWidth(), 
                                               getButtonHeight());

        _brownButton = new Rectangle2D.Double(3 * getButtonWidth(), 
                                              0,
                                              getButtonWidth(), 
                                              getButtonHeight());

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {

                Point2D.Double point = new Point2D.Double(e.getX(), e.getY());
                System.out.println("x: " + point.x + ", y: "  + point.y);
                if (_pencilButton.contains(point)) {
                    System.out.println("Pencil");
                    _brush.setFill(false);
                } else if (_fillButton.contains(point)) {
                    System.out.println("Fill");
                    _brush.setFill(true);
                } else if (_yellowButton.contains(point)) {
                    System.out.println("Yellow");
                    _brush.setColor(Color.YELLOW);
                } else if (_brownButton.contains(point)) {
                    System.out.println("Black");
                    _brush.setColor(Color.BLACK);
                }
                
            }
        });
    }

    public Dimension getPreferredSize() {
        return new Dimension(800, 30);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, getWidth(), getHeight());

        drawButtons(g);
    }

    private void drawButtons(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.draw(_pencilButton);
        g2.draw(_yellowButton);
        g2.draw(_brownButton);
        g2.draw(_fillButton);
    }

    private int getButtonWidth() {
        return 30;
    }

    private int getButtonHeight() {
        return 30;
    }

}
