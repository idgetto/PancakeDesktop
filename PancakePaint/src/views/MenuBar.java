package views;

import event.MenuBarListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import java.io.File;

public class MenuBar extends JMenuBar {
    private static final JFileChooser fileChooser = new JFileChooser();

    private JMenu _fileMenu;
    private JMenuItem _fileOpen;
    private JMenuItem _fileSave;
    private JMenuItem _fileSaveAs;
    private MenuBarListener _listener;
    private JFrame _frame;

    private File _saveFile;

    public MenuBar(JFrame frame) {
        _frame = frame;
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
                open();
            }
        });

        _fileSave = new JMenuItem("Save");
        _fileSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });

        _fileSaveAs = new JMenuItem("Save As");
        _fileSaveAs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveAs();
            }
        });
    }

    private void addComponents() {
        _fileMenu.add(_fileOpen);
        _fileMenu.add(_fileSave);
        _fileMenu.add(_fileSaveAs);
        add(_fileMenu);
    }

    private void open() {
        File openFile = chooseFile();
        if (openFile != null) {
            _saveFile = openFile;
            _listener.onFileOpen(openFile);
        }
    }

    private void save() {
        if (_saveFile != null) {
            _listener.onFileSave(_saveFile);
        } else {
            saveAs();
        }

    }

    private void saveAs() {
        File saveFile = chooseFile();
        if (saveFile != null) {
            _saveFile = saveFile;
            _listener.onFileSaveAs(_saveFile);
        }
    }

    private File chooseFile() {
        int res = fileChooser.showOpenDialog(_frame);
        if (res == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        } else {
            return null;
        }
    }

}
