package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.IOException;

import top.PancakeContext;
import models.PancakeModel;
import event.MenuBarListener;

public class MenuBarController implements MenuBarListener {

    private PancakeContext _context;

    public MenuBarController(PancakeContext context) {
        _context = context;
    }

    @Override
    public void onFileOpen(File file) {
        try {
            FileInputStream in = new FileInputStream(file);
            ObjectInput s = new ObjectInputStream(in);
            PancakeModel model = (PancakeModel) s.readObject();
            _context.setModel(model);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFileSave(File file) {
        try {
            FileOutputStream out = new FileOutputStream(file);
            ObjectOutput s = new ObjectOutputStream(out);
            s.writeObject(_context.getModel());
            s.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFileSaveAs(File file) {
        try {
            FileOutputStream out = new FileOutputStream(file);
            ObjectOutput s = new ObjectOutputStream(out);
            s.writeObject(_context.getModel());
            s.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
