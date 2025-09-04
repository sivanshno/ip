public class Task {
    protected String description;
    protected boolean isDone;
    private static Task [] taskHistory = new Task[100];
    private static int taskCount = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        taskHistory[taskCount] = this;
        taskCount += 1;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription(){
        return this.description;
    }

    public static void list(){
        for ( int i = 0; i < taskCount; i += 1){
           Task currentTask = taskHistory[i];
            System.out.println( i+1 + ".[" + currentTask.getStatusIcon() + "] " + currentTask.description);
        }
    }

    public static void mark(int toMark){
        taskHistory[toMark - 1].isDone = true;
        list();
    }
    public static void unmark(int toUnmark) {
        taskHistory[toUnmark - 1].isDone = false;
        list();
    }

}
