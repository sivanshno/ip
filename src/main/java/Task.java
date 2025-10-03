/**
 * Abstract class to serve as a template for the other kinds of tasks
 *
 * @author Sivansh
 * @version 1.0
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, String mark) {
        this.description = description;
        if ( mark.equals(" ") ){
            this.isDone = false;
        }else{
            this.isDone = true;
        }
    }

    /**
     * Retrives the status of the task( whether it is marked as done or not)
     *
     * @return string either containing "X" to signify done or " " to signify not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Retrieves the task description
     *
     * @return String containing the task description
     */

    public String getDescription(){
        return this.description;
    }

    protected void printTask(int index){
        System.out.println( index +1 + ".[" + getStatusIcon() + "] " + description);
    }

    protected void printTask(){
        System.out.println("[T] [" + getStatusIcon() + "] " + description);
    }

    /**
     * converts the contents of the entire object into a string in CSV format to
     * be stored locally
     *
     * @return string containing all information about the object in CSV format
     */
    public String toCSV (){
        return "task," + this.getStatusIcon() +"," +  this.description;
    }
}
