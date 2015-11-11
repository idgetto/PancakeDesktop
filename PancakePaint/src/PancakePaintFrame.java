import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.Color;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PancakePaintFrame extends JFrame implements SolveListener {

    // The grill size is 23"X12" or 584mmX304mm
    private static final int GRILL_WIDTH = 600;
    private static final int GRILL_HEIGHT = 300;
    private static final int MENU_WIDTH = GRILL_WIDTH;
    private static final int MENU_HEIGHT = 30;
    private static final int FRAME_WIDTH = GRILL_WIDTH;
    private static final int FRAME_HEIGHT = GRILL_HEIGHT + MENU_HEIGHT;

    private static final int ROWS = 30;
    private static final int COLS = 60;

    private MenuPanel _menuPanel;
    private GridPanel _gridPanel;
    private PancakeGridAdapter _pancakeGridAdapter;
    private PancakePaintBrush _pancakePaintBrush;
    private PancakePathSolver _pathSolver;
    private PrinterCompiler _printerCompiler;

    public PancakePaintFrame() {
        super("Pancake Paint");
        _pathSolver = new PancakePathSolver();
        _printerCompiler = new PrinterCompiler();

        setupWindow();
        addComponents();

        setVisible(true);
    }

    private void setupWindow() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    private void addComponents() {
        _pancakePaintBrush = new PancakePaintBrush();
        _menuPanel = new MenuPanel(_pancakePaintBrush, MENU_WIDTH, MENU_HEIGHT);
        _menuPanel.setSolveListener(this);

        _pancakeGridAdapter = new PancakeGridAdapter(_gridPanel, _pancakePaintBrush, ROWS, COLS);
        _gridPanel = new GridPanel(_pancakeGridAdapter, GRILL_WIDTH, GRILL_HEIGHT);

        add(_menuPanel, BorderLayout.PAGE_START);
        add(_gridPanel, BorderLayout.PAGE_END);
        pack();
    }

    public void onSolve() {
        Color[][] grid = _pancakeGridAdapter.getColorGrid();
        Recipe recipe = _pathSolver.solve(grid);

        SolverGridAdapter solveAdapter = new SolverGridAdapter(recipe, 
                                                               _gridPanel,
                                                               grid,
                                                               ROWS,
                                                               COLS);
        _gridPanel.setGridAdapter(solveAdapter);
        _gridPanel.repaint();

        final GridPanel gp = _gridPanel;
        final GridAdapter ga = _pancakeGridAdapter;
        solveAdapter.run(new Callback() {
            public void run() {
                gp.setGridAdapter(ga);
                gp.repaint();
            }
        });

        String res = _printerCompiler.compile(recipe);
        System.out.println(res);
    }

}
