package cable;

import models.PancakeCompiler;
import solver.Recipe;

import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

public class SendRecipeTask implements Runnable {
    private static final String BEGIN_RECIPE = "BEGIN RECIPE";
    private static final String READY_MESSAGE = "READY";
    private static final String DONE_MESSAGE = "DONE";
    private static final int SERIAL_BUFFER_SIZE = 64;

    private Recipe _recipe;
    private PancakeCompiler _pancakeCompiler;
    private SerialStream _serialStream;

    public SendRecipeTask(Recipe recipe, SerialStream serialStream) {
        _recipe = recipe;
        _serialStream = serialStream;
        _pancakeCompiler = new PancakeCompiler();
    }

    public void run() {
        Queue<String> commandQueue = _pancakeCompiler.compile(_recipe);

        beginRecipe();
        while (!commandQueue.isEmpty()) {
            String commands = getCommandBuffer(commandQueue);
            waitForReadyMessage();
            _serialStream.send(commands);
            System.out.println("Sending:\n" + commands + "\n");
        }

    }

    // Send a sequence of commands that will
    // fit within the arduinos serial buffer
    private String getCommandBuffer(Queue<String> commands) {
        StringBuffer buf = new StringBuffer();
        while (buf.length() < SERIAL_BUFFER_SIZE) {
            if (commands.isEmpty()) {
                return buf.toString();
            }

            String next = commands.element();
            if (buf.length() + next.length() < SERIAL_BUFFER_SIZE) {
                buf.append(commands.remove());
            } else {
                return buf.toString();
            }
        }

        return buf.toString();
    }

    private void beginRecipe() {
        _serialStream.send(BEGIN_RECIPE);
        System.out.println("Starting print job");
    }

    private void waitForReadyMessage() {
        boolean ready = false;
        while (!ready) {
            String msg = _serialStream.readMessage();

            if (msg != null && msg.equals(READY_MESSAGE)) {
                ready = true;
            }
        }
    }
}
