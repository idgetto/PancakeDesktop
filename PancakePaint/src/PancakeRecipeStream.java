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

    public void sendRecipe(Recipe recipe) {
        String recipeString = _pancakeCompiler.compile(recipe);
        final String[] instructions = recipeString.split("\\n");
        
        int delay = 300;
        Timer timer = new Timer(delay, new ActionListener() {
            int index = 0;
            public void actionPerformed(ActionEvent e) {
                if (index >= instructions.length) {
                    ((Timer) e.getSource()).stop();
                } else {
                    _serialStream.send(instructions[index]); 
                    index++;
                }
            }
        });
        timer.start();
    }
}
