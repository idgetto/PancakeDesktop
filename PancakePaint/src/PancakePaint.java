import controllers.CanvasController;
import controllers.MenuController;
import models.PancakeModel;
import views.Paintable;
import views.PancakePaintFrame;

public class PancakePaint implements Paintable {

    private PancakeModel _model;
    private PancakePaintFrame _frame;
    private MenuController _menuController;
    private CanvasController _canvasController;

    public PancakePaint() {
        _model = new PancakeModel();
        _menuController = new MenuController(this, _model);
        _canvasController = new CanvasController(this, _model);

        _frame = new PancakePaintFrame(_model);
        _frame.setMenuListener(_menuController);
        _frame.setCanvasListener(_canvasController);
    }

    public void repaint(PancakeModel model) {
        _frame.repaint(model);
    }

}
