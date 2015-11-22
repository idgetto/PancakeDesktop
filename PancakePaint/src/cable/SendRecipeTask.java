public class SendRecipeTask implements Runnable {
    private static final String BEGIN_RECIPE = "BEGIN RECIPE";
    private static final String READY_MESSAGE = "READY";
    private static final String DONE_MESSAGE = "DONE";

    private Recipe _recipe;
    private PancakeCompiler _pancakeCompiler;
    private SerialStream _serialStream;

    public SendRecipeTask(Recipe recipe, SerialStream serialStream) {
        _recipe = recipe;
        _serialStream = serialStream;
        _pancakeCompiler = new PancakeCompiler();
    }

    public void run() {
        String recipeString = _pancakeCompiler.compile(_recipe);
        System.out.println(recipeString);
        String[] instructions = recipeString.split("\\n");

        beginRecipe();
        waitForReady();
        for (String command : instructions) {
            System.out.println("Sending " + command);
            _serialStream.send(command);
            if (!command.equals(DONE_MESSAGE)) {
                waitForCommandCompleted(command);
                System.out.println("Completed " + command);
            }
        }
    }

    private void beginRecipe() {
        _serialStream.send(BEGIN_RECIPE);
    }

    private void waitForReady() {
        System.out.println("Waiting for printer ready");
        boolean ready = false;
        while (!ready) {
            String msg = _serialStream.readMessage();

            if (msg != null && msg.equals(READY_MESSAGE)) {
                System.out.println("Starting print job");
                ready = true;
            }
        }
    }

    private void waitForCommandCompleted(String command) {
        boolean completedCommand = false;
        String completedMessage = "Completed: \"" + command + "\"";
        while (!completedCommand) {
            String msg = _serialStream.readMessage();

            if (msg != null && msg.equals(completedMessage)) {
                completedCommand = true;
            }
        }
    }
}
