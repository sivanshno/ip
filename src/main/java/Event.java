import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents an event defined by a user and stores its start and end time
 *
 * @author Sivansh
 * @version 1.0
 */
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

    /**
     * prints the event to the user in a specific format with index
     *
     * @param index intended index that is to be printed alongside the deadline
     */
    protected void printTask(int index) {
        System.out.println(index + ".[E] [" + getStatusIcon() + "] " +
                description + " (from: " + from.toString() + " to: " + to.toString() + ")");
    }

    /**
     * prints the event to the user in a specific format without the index
     */
    protected void printTask() {
        System.out.println("[E] [" + getStatusIcon() + "] " +
                description + " (from: " + from.toString() + " to: " + to.toString() + ")");
    }

    /**
     * converts the contents of the entire object into a string in CSV format to
     * be stored locally
     *
     * @return string containing all information about the object in CSV format
     */
    public String toCSV (){
        return "E," + this.getStatusIcon() +","+ this.description + "," + this.from.toString() + "," + this.to.toString();
    }
}
