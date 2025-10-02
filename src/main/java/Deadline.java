import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

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

    protected void printTask(int index) {
        System.out.println(index + ".[D] [" + getStatusIcon() + "] " + description + " (by: " + deadline.toString()+ ")");
    }
    protected void printTask() {
        System.out.println("[D] [" + getStatusIcon() + "] " +
                        description + " (by: " + deadline.toString()+ ")");
    }

    public String toCSV (){
        return "D," + this.getStatusIcon() +","+ this.description + "," + this.deadline.toString();
    }
}
