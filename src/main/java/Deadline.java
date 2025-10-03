import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * represents a deadline defined by a user and stores it
 *
 * @author Sivansh
 * @version 1.0
 */
public class Deadline extends Task{

    private TaskTime deadline;

    public Deadline(String userInput) throws IllegalArgumentException{
        super(userInput);
        int dateStartIndex = userInput.indexOf("/") + 4;
        description = userInput.substring(0,dateStartIndex - 5);
        String deadlineAsString = userInput.substring(dateStartIndex);
        try {
            deadline = new TaskTime(deadlineAsString);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
        System.out.println("got it, I've added this task:");
        printTask();
    }

    public Deadline(String mark, String description, String deadline){
        super(description, mark);
        this.deadline = new TaskTime(deadline);
    }

    /**
     * prints the deadline to the user in a specific format with index
     *
     * @param index intended index that is to be printed alongside the deadline
     */

    protected void printTask(int index) {
        System.out.println(index + ".[D] [" + getStatusIcon() + "] " + description + " (by: " + deadline.toString()+ ")");
    }
    /**
     * prints the deadline to the user in a specific format without the index
     */
    protected void printTask() {
        System.out.println("[D] [" + getStatusIcon() + "] " +
                        description + " (by: " + deadline.toString()+ ")");
    }
    /**
     * converts the contents of the entire object into a string in CSV format to
     * be stored locally
     *
     * @return string containing all information about the object in CSV format
     */

    public String toCSV (){
        return "D," + this.getStatusIcon() +","+ this.description + "," + this.deadline.toString();
    }
}
