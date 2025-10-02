public class Event extends Task {

    private String from;
    private String to;

    public Event(String userInput) {
        super(userInput);
        int descriptionEndIndex = userInput.indexOf("/");
        description = userInput.substring(0, descriptionEndIndex);
        String startAndEnd = userInput.substring(descriptionEndIndex + 1);
        int fromEndIndex = startAndEnd.indexOf("/") -2;
        from = startAndEnd.substring(0, fromEndIndex);
        to = startAndEnd.substring(fromEndIndex + 4);
        printTask();
    }

    public Event(String mark, String description, String from, String to){
        super(description, mark);
        this.from = from;
        this.to = to;
    }

    protected void printTask(int index) {
        System.out.println(index + ".[E] [" + getStatusIcon() + "] " +
                description + " (from: " + from + " to: " + to + ")");
    }

    protected void printTask() {
        System.out.println("[E] [" + getStatusIcon() + "] " +
                description + " (from: " + from + " to: " + to + ")");
    }

    public String toCSV (){
        return "E," + this.getStatusIcon() +","+ this.description + "," + this.from + "," + this.to;
    }
}
