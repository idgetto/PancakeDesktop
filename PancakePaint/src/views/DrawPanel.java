package views;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

class DrawPanel extends JPanel {

    private int _x;
    private int _y;
    private ArrayList<Point2D.Double> sequence;

    public DrawPanel() {
        sequence = new ArrayList<Point2D.Double>();

        setBorder(BorderFactory.createLineBorder(Color.black));

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                sequence = new ArrayList<Point2D.Double>();
                sequence.add(new Point2D.Double(e.getX(), e.getY()));
                repaint();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                sequence.add(new Point2D.Double(e.getX(), e.getY()));
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
            Point2D.Double prev = sequence.get(0);
            for (int i = 1; i < sequence.size(); ++i) {
                Point2D.Double curr = sequence.get(i);
                Line2D.Double line = new Line2D.Double(prev.getX(), prev.getY(), curr.getX(), curr.getY());
                Graphics2D g2 = (Graphics2D) g;
                g2.draw(line);

                prev = curr;
            }
        }
    }
}
