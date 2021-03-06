package views;

import event.MenuEvent;
import event.MenuListener;
import models.PancakeModel;

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
import java.awt.FlowLayout;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuPanel extends JPanel {

    private Button _linearStrokeButton;
    private Button _curvedStrokeButton;
    private Button _yellowButton;
    private Button _brownButton;
    private Button _clearButton;
    private Button _printButton;

    private MenuListener _menuListener;
    private int _width, _height;

    public MenuPanel(int width, int height) {
        _width = width;
        _height = height;
        setupComponents();

    }

    public void setMenuListener(MenuListener listener) {
        _menuListener = listener;
    }

    public void setupComponents() {
        setupButtons();

        setLayout(new FlowLayout());

        add(_linearStrokeButton);
        add(_curvedStrokeButton);
        add(_yellowButton);
        add(_brownButton);
        add(_clearButton);
        add(_printButton);
    }

    private void setupButtons() {
        _linearStrokeButton = new Button("Linear Stroke");
        _linearStrokeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                _menuListener.onMenuEvent(MenuEvent.LINEAR_STROKE);
            }
        });

        _curvedStrokeButton = new Button("Curved Stroke");
        _curvedStrokeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                _menuListener.onMenuEvent(MenuEvent.CURVED_STROKE);
            }
        });

        _yellowButton = new Button("Yellow");
        _yellowButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                _menuListener.onMenuEvent(MenuEvent.YELLOW);
            }
        });

        _brownButton = new Button("Brown");
        _brownButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                _menuListener.onMenuEvent(MenuEvent.BROWN);
            }
        });

        _clearButton = new Button("Clear");
        _clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                _menuListener.onMenuEvent(MenuEvent.CLEAR);
            }
        });

        _printButton = new Button("Print");
        _printButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                _menuListener.onMenuEvent(MenuEvent.PRINT);
            }
        });
    }

    public Dimension getPreferredSize() {
        return new Dimension(_width, _height);
    }

    public void repaint(PancakeModel model) {
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

}
