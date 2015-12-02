package controllers;

import event.CanvasEvent;
import event.CanvasListener;
import models.CurvedStroke;
import models.LinearStroke;
import models.PancakeModel;
import models.Stroke;
import views.Paintable;

import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.List;

public class CanvasController implements MouseInputListener {

    private Paintable _context;
    private PancakeModel _model;

    public CanvasController(Paintable context, PancakeModel model) {
        _context = context;
        _model = model;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        _context.repaint(_model);
        setMouseLocation(mouseEvent);
    }

    private boolean isRightClick(int modifiers) {
        return (modifiers & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK;
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        setMouseLocation(mouseEvent);
        _context.repaint(_model);
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        int modifiers = mouseEvent.getModifiers();
        if (isRightClick(modifiers)) {
            System.out.println("Right Click: " + mouseEvent.getPoint());
            _model.finishStroke();
        } else {
            Point2D.Double point = new Point2D.Double(mouseEvent.getX(), mouseEvent.getY());
            _model.addPoint(point);
            System.out.println("Clicked: " + mouseEvent.getPoint());

        }

        setMouseLocation(mouseEvent);
        _context.repaint(_model);
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        setMouseLocation(mouseEvent);
        _context.repaint(_model);
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        setMouseLocation(mouseEvent);
        _context.repaint(_model);
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        setMouseLocation(mouseEvent);
        _context.repaint(_model);
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        setMouseLocation(mouseEvent);
        _context.repaint(_model);
    }

    private void setMouseLocation(Point2D.Double point) {
        _model.setMouseLocation(point);
    }

    private void setMouseLocation(MouseEvent mouseEvent) {
        Point2D.Double point = new Point2D.Double(mouseEvent.getX(), mouseEvent.getY());
        setMouseLocation(point);
    }

}
