public class Deadline extends Task{

    private String deadline;

    public Deadline(String userInput){
        super(userInput);
        int dateStartIndex = userInput.indexOf("/") + 4;
        description = userInput.substring(0,dateStartIndex - 5);
        deadline = userInput.substring(dateStartIndex);
        printTask();
        System.out.println("now you have "+ Task.taskCount + " tasks in your list");
    }

    public Deadline(String mark, String description, String deadline){
        super(description, mark);
        this.deadline = deadline;
    }

    protected void printTask(int index) {
        System.out.println(index + ".[D] [" + getStatusIcon() + "] " + description + " (by: " + deadline + ")");
    }
    protected void printTask() {
        System.out.println("[D] [" + getStatusIcon() + "] " +
                        description + " (by: " + deadline + ")");
    }

    public String toCSV (){
        return "D," + this.getStatusIcon() +","+ this.description + "," + this.deadline;
    }
}
