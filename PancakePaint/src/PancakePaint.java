package top;

import controllers.CanvasController;
import controllers.MenuController;
import controllers.MenuBarController;
import models.PancakeModel;
import views.PancakePaintFrame;

public class PancakePaint implements PancakeContext {

    private PancakeModel _model;
    private PancakePaintFrame _frame;
    private MenuBarController _menuBarController;
    private MenuController _menuController;
    private CanvasController _canvasController;

    public PancakePaint() {
        _model = new PancakeModel();
        _menuBarController = new MenuBarController(this);
        _menuController = new MenuController(this);
        _canvasController = new CanvasController(this);

        _frame = new PancakePaintFrame(this);
        _frame.setMenuBarListener(_menuBarController);
        _frame.setMenuListener(_menuController);
        _frame.setCanvasListener(_canvasController);
    }

    @Override
    public PancakeModel getModel() {
        return _model;
    }

    @Override
    public void setModel(PancakeModel model) {
        _model = model;
    }

    @Override
    public void repaint(PancakeModel model) {
        _frame.repaint(model);
    }

}
