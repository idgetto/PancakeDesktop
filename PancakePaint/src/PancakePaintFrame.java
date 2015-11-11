import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.Color;

public class PancakePaintFrame extends JFrame implements SolveListener {

    private static final int GRILL_WIDTH = 800;
    private static final int GRILL_HEIGHT = 430;

    private MenuPanel _menuPanel;
    private GridPanel _gridPanel;
    private PancakeGridAdapter _pancakeGridAdapter;
    private PancakePaintBrush _pancakePaintBrush;
    private PancakePathSolver _pathSolver;

    public PancakePaintFrame() {
        super("Pancake Paint");
        _pathSolver = new PancakePathSolver();

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
        _pancakePaintBrush = new PancakePaintBrush();
        _menuPanel = new MenuPanel(_pancakePaintBrush);
        _menuPanel.setSolveListener(this);

        _pancakeGridAdapter = new PancakeGridAdapter(_pancakePaintBrush);
        _gridPanel = new GridPanel(_pancakeGridAdapter);

        add(_menuPanel, BorderLayout.PAGE_START);
        add(_gridPanel, BorderLayout.PAGE_END);
        pack();
    }

    public void onSolve() {
        System.out.println("Solve!");
        Color[][] grid = _pancakeGridAdapter.getColorGrid();
        Recipe recipe = _pathSolver.solve(grid);
    }

}
