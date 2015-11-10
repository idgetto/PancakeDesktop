import java.awt.geom.Rectangle2D;
import java.awt.Point;
import java.awt.Graphics;

public interface GridAdapter {
    void cellTouched(Point cell); 
    void paintCell(Point cell, Graphics g, Rectangle2D.Double rect);
    int getRows();
    int getCols();
}
