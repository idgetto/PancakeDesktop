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
                int res = fileChooser.showOpenDialog(_frame);
                if (res == JFileChooser.APPROVE_OPTION) {
                    File openFile = fileChooser.getSelectedFile();
                    _listener.onFileOpen(openFile);
                }
            }
        });

        _fileSave = new JMenuItem("Save");
        _fileSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (_saveFile != null) {
                    _listener.onFileSave(_saveFile);
                } else {
                    int res = fileChooser.showOpenDialog(_frame);
                    if (res == JFileChooser.APPROVE_OPTION) {
                        _saveFile = fileChooser.getSelectedFile();
                        _listener.onFileSaveAs(_saveFile);
                    }
                }
            }
        });

        _fileSaveAs = new JMenuItem("Save As");
        _fileSaveAs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int res = fileChooser.showOpenDialog(_frame);
                if (res == JFileChooser.APPROVE_OPTION) {
                    _saveFile = fileChooser.getSelectedFile();
                    _listener.onFileSaveAs(_saveFile);
                }
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
