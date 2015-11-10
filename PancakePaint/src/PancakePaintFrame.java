import javax.swing.JFrame;
import java.awt.BorderLayout;

public class PancakePaintFrame extends JFrame {

    private static final int GRILL_WIDTH = 800;
    private static final int GRILL_HEIGHT = 430;

    public PancakePaintFrame() {
        super("Pancake Paint");

        setupWindow();
        addComponents();

        setVisible(true);
    }

    private void setupWindow() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(GRILL_WIDTH, GRILL_HEIGHT);
    }

    private void addComponents() {
        add(new MenuPanel(), BorderLayout.PAGE_START);
        add(new GridPanel(16, 32), BorderLayout.PAGE_END);
        pack();
    }

}
