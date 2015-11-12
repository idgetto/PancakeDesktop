import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.Color;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PancakePaintFrame extends JFrame {

    // The grill size is 23"X12" or 584mmX304mm
    private static final int GRILL_WIDTH = 1200;
    private static final int GRILL_HEIGHT = 600;
    private static final int MENU_WIDTH = GRILL_WIDTH;
    private static final int MENU_HEIGHT = 30;
    private static final int FRAME_WIDTH = GRILL_WIDTH;
    private static final int FRAME_HEIGHT = GRILL_HEIGHT + MENU_HEIGHT;

    private MenuPanel _menuPanel;
    private GridPanel _gridPanel;

    public PancakePaintFrame(PancakeModel model) {
        super("Pancake Paint");
        setupWindow();
        addComponents(model);
        setVisible(true);
    }

    public void repaint(PancakeModel model) {
        _menuPanel.repaint(model);
        _gridPanel.repaint(model);
    }

    public void setMenuListener(MenuListener listener) {
        _menuPanel.setMenuListener(listener);
    }

    public void setGridListener(GridListener listener) {
        _gridPanel.setGridListener(listener);
    }

    private void setupWindow() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    private void addComponents(PancakeModel model) {
        _menuPanel = new MenuPanel(MENU_WIDTH, MENU_HEIGHT);
        _gridPanel = new GridPanel(model, GRILL_WIDTH, GRILL_HEIGHT);

        add(_menuPanel, BorderLayout.PAGE_START);
        add(_gridPanel, BorderLayout.PAGE_END);
        pack();
    }


}