import event.MenuBarListener;

public class MenuBarController implements MenuBarListener {

    @Override
    public void onFileOpen() {
        System.out.println("Open");
    }

    @Override
    public void onFileSave() {
        System.out.println("Save");
    }

    @Override
    public void onFileSaveAs() {
        System.out.println("Save As");
    }

}
