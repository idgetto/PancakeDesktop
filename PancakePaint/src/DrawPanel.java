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

class DrawPanel extends JPanel {

    private int _x;
    private int _y;
    private ArrayList<Point> sequence;

    public DrawPanel() {
        sequence = new ArrayList<Point>();

        setBorder(BorderFactory.createLineBorder(Color.black));

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                sequence = new ArrayList<Point>();
                sequence.add(new Point(e.getX(), e.getY()));
                repaint();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                sequence.add(new Point(e.getX(), e.getY()));
                repaint();
            }
        });
    }

    private void paintPixel(int x, int y) {
        _x = x;
        _y = y;
        repaint(x, y, x+1, y+1);
    }

    public Dimension getPreferredSize() {
        return new Dimension(800, 400);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);

        if (!sequence.isEmpty()) {
            Point prev = sequence.get(0);
            for (int i = 1; i < sequence.size(); ++i) {
                Point curr = sequence.get(i);
                g.drawLine(prev.x, prev.y, curr.x, curr.y);

                prev = curr;
            }
        }
    }
}
