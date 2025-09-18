public class Todo extends Task{
    public Todo(String description){
        super(description);
        printTask();
        System.out.println("now you have "+ Task.taskCount + " tasks in your list");
    }

    public Todo(String mark, String description){
        super(description, mark );
    }

    protected void printTask(int index){
        System.out.println( index + ".[T] [" + getStatusIcon() + "] " + description);
    }
    protected void printTask(){
        System.out.println( "[T] [" + getStatusIcon() + "] " + description);
    }

    public String toCSV (){
        return "T," + this.getStatusIcon() +","+ this.description;
    }
}
