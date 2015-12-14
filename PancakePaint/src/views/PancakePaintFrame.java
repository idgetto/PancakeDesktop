package views;

import top.PancakeContext;
import event.MenuBarListener;
import event.MenuListener;
import event.CanvasListener;
import models.PancakeModel;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.Color;
import javax.swing.Timer;
import javax.swing.event.MouseInputListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PancakePaintFrame extends JFrame {

    // The grill size is 23"X12" or 584mmX304mm
    private static final int GRILL_WIDTH = 1461;
    private static final int GRILL_HEIGHT = 651;
    private static final int MENU_WIDTH = GRILL_WIDTH;
    private static final int MENU_HEIGHT = 30;
    private static final int FRAME_WIDTH = GRILL_WIDTH;
    private static final int FRAME_HEIGHT = GRILL_HEIGHT + MENU_HEIGHT;

    private MenuBar _menuBar;
    private MenuPanel _menuPanel;
    private CanvasPanel _canvasPanel;

    public PancakePaintFrame(PancakeContext context) {
        super("Pancake Paint");
        setupWindow();
        addComponents(context.getModel());
        setVisible(true);
    }

    public void repaint(PancakeModel model) {
        _menuPanel.repaint(model);
        _canvasPanel.repaint(model);
    }

    public void setMenuBarListener(MenuBarListener listener) {
        _menuBar.setMenuBarListener(listener);
    }

    public void setMenuListener(MenuListener listener) {
        _menuPanel.setMenuListener(listener);
    }

    public void setCanvasListener(MouseInputListener listener) {
        _canvasPanel.setCanvasListener(listener);
    }

    private void setupWindow() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    private void addComponents(PancakeModel model) {
        _menuBar = new MenuBar(this);
        _menuPanel = new MenuPanel(MENU_WIDTH, MENU_HEIGHT);
        _canvasPanel = new CanvasPanel(model, GRILL_WIDTH, GRILL_HEIGHT);

        setJMenuBar(_menuBar);
        add(_menuPanel, BorderLayout.PAGE_START);
        add(_canvasPanel, BorderLayout.PAGE_END);
        pack();
    }


}
