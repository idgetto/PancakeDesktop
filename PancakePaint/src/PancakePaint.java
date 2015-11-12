public class PancakePaint implements Paintable {

    private PancakeModel _model;
    private PancakePaintFrame _frame;
    private MenuController _menuController;
    private GridController _gridController;

    public PancakePaint() {
        _model = new PancakeModel();
        _menuController = new MenuController(this, _model);
        _gridController = new GridController(this, _model);

        _frame = new PancakePaintFrame(_model);
        _frame.setMenuListener(_menuController);
        _frame.setGridListener(_gridController);
    }

    public void repaint(PancakeModel model) {
        _frame.repaint(model);
    }

}
