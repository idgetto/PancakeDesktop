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
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class MenuPanel extends JPanel {

    private JRadioButton _pencilButton;
    private JRadioButton _fillButton;
    private JRadioButton _eraseButton;
    private Button _yellowButton;
    private Button _brownButton;
    private Button _solveButton;

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
        
        ButtonGroup strokeGroup = new ButtonGroup();
        strokeGroup.add(_pencilButton);
        strokeGroup.add(_fillButton);
        strokeGroup.add(_eraseButton);

        add(_pencilButton);
        add(_fillButton);
        add(_eraseButton);
        add(_yellowButton);
        add(_brownButton);
        add(_solveButton);
    }

    private void setupButtons() {
        _pencilButton = new JRadioButton("Pencil");        
        _pencilButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                _menuListener.onMenuEvent(MenuEvent.PENCIL);
            }
        });

        _fillButton = new JRadioButton("Fill");
        _fillButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                _menuListener.onMenuEvent(MenuEvent.FILL);
            }
        });

        _eraseButton = new JRadioButton("Erase");
        _eraseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                _menuListener.onMenuEvent(MenuEvent.ERASE);
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

        _solveButton = new Button("Solve");
        _solveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                _menuListener.onMenuEvent(MenuEvent.SOLVE);
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
