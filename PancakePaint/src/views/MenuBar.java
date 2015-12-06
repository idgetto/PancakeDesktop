package views;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {

    private JMenu _fileMenu;
    private JMenuItem _fileOpen;
    private JMenuItem _fileSave;
    private JMenuItem _fileSaveAs;

    public MenuBar() {
        setupComponents();
        addComponents();
    }

    private void setupComponents() {
        _fileMenu = new JMenu("File");
        _fileOpen = new JMenuItem("Open");
        _fileSave = new JMenuItem("Save");
        _fileSaveAs = new JMenuItem("Save As");
    }

    private void addComponents() {
        _fileMenu.add(_fileOpen);
        _fileMenu.add(_fileSave);
        _fileMenu.add(_fileSaveAs);
        add(_fileMenu);
    }

}
