public class Event extends Task {

    private String from;
    private String to;

    public Event(String userInput) {
        super(userInput);
        int descriptionEndIndex = userInput.indexOf("/");
        description = userInput.substring(0, descriptionEndIndex);
        String startAndEnd = userInput.substring(descriptionEndIndex + 8);
        int fromEndIndex = startAndEnd.indexOf("/") -1;
        from = startAndEnd.substring(0, fromEndIndex);
        to = startAndEnd.substring(fromEndIndex + 6);
        printTask();
        System.out.println("now you have "+ Task.taskCount + " tasks in your list");
    }

    protected void printTask(int index) {
        System.out.println(index + ".[E] [" + getStatusIcon() + "] " +
                description + " (from: " + from + " to: " + to + ")");
    }

    protected void printTask() {
        System.out.println("[E] [" + getStatusIcon() + "] " +
                description + " (from: " + from + " to: " + to + ")");
    }
}
