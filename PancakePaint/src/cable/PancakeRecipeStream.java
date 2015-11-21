import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PancakeRecipeStream {

    private SerialStream _serialStream;
    private PancakeCompiler _pancakeCompiler;

    public PancakeRecipeStream() {
        _serialStream = new SerialStream();
        _pancakeCompiler = new PancakeCompiler();
    }

    // Send the recipe as it executes on the printer
    //
    // This takes a long time (the length of the print job)
    // so run in a seperate thread.
    public synchronized void sendRecipe(Recipe recipe) {
        SendRecipeTask task = new SendRecipeTask(recipe, _serialStream);
        Thread t = new Thread(task);
        t.start();
    }

}
