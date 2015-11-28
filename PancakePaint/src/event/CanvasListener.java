package event;

public interface CanvasListener {
    void onTouch(CanvasEvent e);
    void onMove(CanvasEvent e);
    void onRelease(CanvasEvent e);
}
