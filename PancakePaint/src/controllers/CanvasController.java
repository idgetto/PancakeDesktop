public class CanvasController implements CanvasListener {

    private Paintable _context;
    private PancakeModel _model;

    public CanvasController(Paintable context, PancakeModel model) {
        _context = context;
        _model = model;
    }

    public void onTouch(CanvasEvent e) {
    }

    public void onMove(CanvasEvent e) {
    }

    public void onRelease(CanvasEvent e) {
    }
}
