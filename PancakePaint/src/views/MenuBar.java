package views;

import event.MenuBarListener;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuBar extends JMenuBar {

    private JMenu _fileMenu;
    private JMenuItem _fileOpen;
    private JMenuItem _fileSave;
    private JMenuItem _fileSaveAs;
    private MenuBarListener _listener;

    public MenuBar() {
        setupComponents();
        addComponents();
    }

    public void setMenuBarListener(MenuBarListener listener) {
        _listener = listener;
    }

    private void setupComponents() {
        _fileMenu = new JMenu("File");

        _fileOpen = new JMenuItem("Open");
        _fileOpen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                _listener.onFileOpen();
            }
        });

        _fileSave = new JMenuItem("Save");
        _fileSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                _listener.onFileSave();
            }
        });

        _fileSaveAs = new JMenuItem("Save As");
        _fileSaveAs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                _listener.onFileSaveAs();
            }
        });
    }

    private void addComponents() {
        _fileMenu.add(_fileOpen);
        _fileMenu.add(_fileSave);
        _fileMenu.add(_fileSaveAs);
        add(_fileMenu);
    }

}
