import java.io.FileWriter;
import java.io.IOException;

public class Task {
    protected String description;
    protected boolean isDone;
    private static Task [] taskHistory = new Task[100];
    protected static int taskCount = 0;

    public Task(String description) {
        System.out.println("got it, I've added this task:");
        this.description = description;
        this.isDone = false;
        taskHistory[taskCount] = this;
        taskCount += 1;
    }

    public Task(String description, String mark) {
        this.description = description;
        if ( mark.equals(" ") ){
            this.isDone = false;
        }else{
            this.isDone = true;
        }
        taskHistory[taskCount] = this;
        taskCount += 1;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription(){
        return this.description;
    }

    protected void printTask(int index){
        System.out.println( index +1 + ".[" + getStatusIcon() + "] " + description);
    }

    public static void list(){
        for ( int i = 0; i < taskCount; i += 1){
           Task currentTask = taskHistory[i];
           currentTask.printTask( i+1);
        }
    }

    public static void mark (int toMark) throws ArrayIndexOutOfBoundsException{
        if (toMark > taskCount){
            throw new ArrayIndexOutOfBoundsException();
        }
        taskHistory[toMark - 1].isDone = true;
        list();
    }

    public static void unmark(int toUnmark) throws ArrayIndexOutOfBoundsException {
        if (toUnmark > taskCount){
            throw new ArrayIndexOutOfBoundsException();
        }
        taskHistory[toUnmark - 1].isDone = false;
        list();
    }

    public String toCSV (){
        return "task," + this.getStatusIcon() +"," +  this.description;
    }



    public static void saveData() throws IOException {
        FileWriter fw = new FileWriter( Agus.TASK_DATA_FILE_PATH);
        try{
            for (int i = 0; i < taskCount; i++) {
                fw.write(taskHistory[i].toCSV() + System.lineSeparator());
            }
            fw.close();
        }catch (IOException e){
            throw new IOException();
        }
    }
}
