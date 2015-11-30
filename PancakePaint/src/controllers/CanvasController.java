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
import java.util.List;

public class CanvasController implements MouseInputListener {

    private Paintable _context;
    private PancakeModel _model;
    private Stroke _currentStroke;

    public CanvasController(Paintable context, PancakeModel model) {
        _context = context;
        _model = model;
    }

    public void setupStroke() {
        switch (_model.getBrush().getMode()) {
            case LINEAR_STROKE:
                System.out.println("linear mode");
                _currentStroke = new LinearStroke();
                break;
            case CURVED_STROKE:
                System.out.println("curved mode");
                _currentStroke = new CurvedStroke();
                break;
        }

        _currentStroke.setColor(_model.getBrush().getColor());
        List<Stroke> strokes = _model.getStrokes();
        strokes.add(_currentStroke);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        int modifiers = mouseEvent.getModifiers();
        if (isRightClick(modifiers)) {
            System.out.println("Right Click: " + mouseEvent.getPoint());
            _currentStroke = null;
        } else {
            System.out.println("Clicked: " + mouseEvent.getPoint());

            if (_currentStroke == null) {
                setupStroke();
            }

            // set the stroke color to the brush color
            if (_currentStroke.getPoints().isEmpty()) {
                _currentStroke.setColor(_model.getBrush().getColor());
            }

            _currentStroke.addPoint(mouseEvent.getPoint());

        }

        setMouseLocation(mouseEvent);
        _context.repaint(_model);
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

    private void setMouseLocation(Point point) {
        _model.setMouseLocation(point);
    }

    private void setMouseLocation(MouseEvent mouseEvent) {
        setMouseLocation(mouseEvent.getPoint());
    }

}
