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

    private  Button _pencilButton;
    private  Button _fillButton;
    private  Button _yellowButton;
    private  Button _brownButton;

    PancakePaintBrush _brush;

    public MenuPanel(PancakePaintBrush brush) {
        _brush = brush;
        _brush.setColor(Color.YELLOW);

        _pencilButton = new Button("Pencil");        
        _pencilButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                _brush.setFill(false);
            }
        });

        _fillButton = new Button("Fill");
        _fillButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                _brush.setFill(true);
            }
        });

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

        setLayout(new FlowLayout());
        add(_pencilButton);
        add(_fillButton);
        add(_yellowButton);
        add(_brownButton);
    }

    public Dimension getPreferredSize() {
        return new Dimension(800, 30);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

}
