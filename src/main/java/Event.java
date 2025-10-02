import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event extends Task {

    private TaskTime from;
    private TaskTime to;

    public Event(String userInput) throws IllegalArgumentException{
        super(userInput);
        int descriptionEndIndex = userInput.indexOf("/");
        description = userInput.substring(0, descriptionEndIndex);
        String startAndEnd = userInput.substring(descriptionEndIndex + 1);
        int fromEndIndex = startAndEnd.indexOf("t") -2;
        String fromAsString = startAndEnd.substring(4, fromEndIndex);
        String toAsString = startAndEnd.substring(fromEndIndex + 5);
        try {
            from = new TaskTime(fromAsString);
            to = new TaskTime(toAsString);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }
        System.out.println("got it, I've added this task:");
        printTask();
    }

    public Event(String mark, String description, String from, String to){
        super(description, mark);
        this.from =  new TaskTime(from);
        this.to = new TaskTime(to);
    }

    protected void printTask(int index) {
        System.out.println(index + ".[E] [" + getStatusIcon() + "] " +
                description + " (from: " + from.toString() + " to: " + to.toString() + ")");
    }

    protected void printTask() {
        System.out.println("[E] [" + getStatusIcon() + "] " +
                description + " (from: " + from.toString() + " to: " + to.toString() + ")");
    }

    public String toCSV (){
        return "E," + this.getStatusIcon() +","+ this.description + "," + this.from.toString() + "," + this.to.toString();
    }
}
