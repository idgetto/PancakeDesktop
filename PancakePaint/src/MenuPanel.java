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
    private Button _yellowButton;
    private Button _brownButton;
    private Button _solveButton;

    private SolveListener _solveListener;

    PancakePaintBrush _brush;

    public MenuPanel(PancakePaintBrush brush) {
        setupComponents();

        _brush = brush;
        _brush.setColor(Color.YELLOW);
    }

    public void setSolveListener(SolveListener listener) {
        _solveListener = listener;
    }

    public void setupComponents() {
        _pencilButton = new JRadioButton("Pencil");        
        _pencilButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                _brush.setFill(false);
            }
        });

        _fillButton = new JRadioButton("Fill");
        _fillButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                _brush.setFill(true);
            }
        });

        ButtonGroup strokeGroup = new ButtonGroup();
        strokeGroup.add(_pencilButton);
        strokeGroup.add(_fillButton);

        _yellowButton = new Button("Yellow");
        _yellowButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                _brush.setColor(Color.YELLOW);
            }
        });

        _brownButton = new Button("Brown");
        _brownButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                _brush.setColor(Color.BLACK);
            }
        });

        _solveButton = new Button("Solve");
        _solveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                _solveListener.onSolve();
            }
        });

        setLayout(new FlowLayout());
        add(_pencilButton);
        add(_fillButton);
        add(_yellowButton);
        add(_brownButton);
        add(_solveButton);
    }

    public Dimension getPreferredSize() {
        return new Dimension(800, 30);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

}
