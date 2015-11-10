import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import java.awt.BorderLayout;

class PancakePaintRunner {
    private static final int GRILL_WIDTH = 800;
    private static final int GRILL_HEIGHT = 430;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame f = new JFrame("Pancake Paint");
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(GRILL_WIDTH, GRILL_HEIGHT);

        f.add(new MenuPanel(), BorderLayout.PAGE_START);
        f.add(new GridPanel(16, 32), BorderLayout.PAGE_END);
        f.pack();
        f.setVisible(true);
    }
}
