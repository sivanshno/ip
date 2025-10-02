import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

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

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription(){
        return this.description;
    }

    protected void printTask(int index){
        System.out.println( index +1 + ".[" + getStatusIcon() + "] " + description);
    }

    protected void printTask(){
        System.out.println("[T] [" + getStatusIcon() + "] " + description);
    }

    public String toCSV (){
        return "task," + this.getStatusIcon() +"," +  this.description;
    }
}
