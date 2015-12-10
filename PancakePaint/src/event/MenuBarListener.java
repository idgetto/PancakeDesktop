package event;

import java.io.File;

public interface MenuBarListener {
    void onFileOpen(File file);
    void onFileSave(File file);
    void onFileSaveAs(File file);
}
