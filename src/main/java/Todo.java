/**
 * Represents to-do tasks defined by the user
 *
 * @author Sivansh
 * @version 1.0
 */
public class Todo extends Task{
    public Todo(String description){
        super(description);
        System.out.println("got it, I've added this task:");
        printTask();
    }

    public Todo(String mark, String description){
        super(description, mark );
    }

    /**
     * prints the event to the user in a specific format with index
     *
     * @param index intended index that is to be printed alongside the deadline
     */
    protected void printTask(int index){
        System.out.println( index + ".[T] [" + getStatusIcon() + "] " + description);
    }

    /**
     * prints the event to the user in a specific format without the index
     */
    protected void printTask(){
        System.out.println( "[T] [" + getStatusIcon() + "] " + description);
    }

    /**
     * converts the contents of the entire object into a string in CSV format to
     * be stored locally
     *
     * @return string containing all information about the object in CSV format
     */
    public String toCSV (){
        return "T," + this.getStatusIcon() +","+ this.description;
    }
}
