package top;

import models.PancakeModel;

public interface PancakeContext {
    PancakeModel getModel();
    void setModel(PancakeModel model);
    void repaint(PancakeModel model);
}
