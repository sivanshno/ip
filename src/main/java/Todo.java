public class Todo extends Task{
    public Todo(String description){
        super(description);
        printTask();
        System.out.println("now you have "+ Task.taskCount + " tasks in your list");
    }

    protected void printTask(int index){
        System.out.println( index + ".[T] [" + getStatusIcon() + "] " + description);
    }
    protected void printTask(){
        System.out.println( "[T] [" + getStatusIcon() + "] " + description);
    }
}
