package controllers;

import top.PancakeContext;
import event.CanvasEvent;
import event.CanvasListener;
import models.CurvedStroke;
import models.LinearStroke;
import models.PancakeModel;
import models.Stroke;

import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.List;

public class CanvasController implements MouseInputListener {

    private PancakeContext _context;

    public CanvasController(PancakeContext context) {
        _context = context;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        PancakeModel model = _context.getModel();
        _context.repaint(model);
        setMouseLocation(mouseEvent);
    }

    private boolean isRightClick(int modifiers) {
        return (modifiers & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK;
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        setMouseLocation(mouseEvent);
        PancakeModel model = _context.getModel();
        _context.repaint(model);
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        PancakeModel model = _context.getModel();

        int modifiers = mouseEvent.getModifiers();
        if (isRightClick(modifiers)) {
            System.out.println("Right Click: " + mouseEvent.getPoint());
            model.finishStroke();
        } else {
            Point2D.Double point = new Point2D.Double(mouseEvent.getX(), mouseEvent.getY());
            System.out.println("Clicked: " + mouseEvent.getPoint());
            if (model.nearStartPoint(point)) {
                model.closeStroke();
            } else {
                model.addPoint(point);
            }

        }

        setMouseLocation(mouseEvent);
        _context.repaint(model);
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        setMouseLocation(mouseEvent);
        PancakeModel model = _context.getModel();
        _context.repaint(model);
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        setMouseLocation(mouseEvent);
        PancakeModel model = _context.getModel();
        _context.repaint(model);
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        setMouseLocation(mouseEvent);
        PancakeModel model = _context.getModel();
        _context.repaint(model);
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        setMouseLocation(mouseEvent);
        PancakeModel model = _context.getModel();
        _context.repaint(model);
    }

    private void setMouseLocation(Point2D.Double point) {
        PancakeModel model = _context.getModel();
        model.setMouseLocation(point);
    }

    private void setMouseLocation(MouseEvent mouseEvent) {
        Point2D.Double point = new Point2D.Double(mouseEvent.getX(), mouseEvent.getY());
        setMouseLocation(point);
    }

}
