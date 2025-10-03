import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Handles all operations regarding reading to and writing from the local storage
 *
 * @author Sivansh
 * @version 1.0
 */
public class Storage {

    private File file;
    private Scanner fileReader;
    private FileWriter fileWriter;
    private String filePath;

    public Storage (String storageFilePath){
        file = new File(storageFilePath);
        filePath = storageFilePath;
    }

    public void loadSavedTasks() throws FileNotFoundException, IOException, NoSuchElementException{
        if (!file.exists()){
            throw new FileNotFoundException();
        }
        try{
            fileReader = new Scanner(file);
        }catch (IOException e){
            throw new IOException();
        }
        try{
            while (fileReader.hasNextLine()) {
                String taskCSV = fileReader.nextLine();
                parseTaskCSV(taskCSV);
            }
        }catch (NoSuchElementException e ){
            throw  new NoSuchElementException();
        }
    }

    /**
     * Reads a task locally saved in csv format and converts it to a task object
     * and inserts it into the task list
     *
     * @param taskCSV task in CSV format
     * @throws InputMismatchException when the task is not stored in CSV format
     */

    private void parseTaskCSV(String taskCSV) throws InputMismatchException{
        String[] splitData = taskCSV.split(",");
        String type = splitData[0];
        switch (type){
            case "T":
                try {
                    Todo newTodo = new Todo(splitData[1], splitData[2]);
                    Agus.tasks.addTask(newTodo);
                }
                catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("Task data corrupted");
                }
                break;
            case "D":
                try {
                    Deadline newDeadline = new Deadline(splitData[1], splitData[2], splitData[3]);
                    Agus.tasks.addTask(newDeadline);
                }
                catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("Task data corrupted");
                }
                break;
            case "E":
                try{
                    Event newEvent = new Event(splitData[1], splitData[2], splitData[3], splitData[4]);
                    Agus.tasks.addTask(newEvent);
                }
                catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("Task data corrupted");
                }
                break;
            default:
                throw new InputMismatchException();
        }

    }

    /**
     * Locally saves all task data in csv format
     *
     * @param taskCount number of tasks in the list
     * @param taskHistory array containing all the tasks
     * @throws IOException when it is unable to write to the file
     */

    public void saveData( int taskCount, Task[] taskHistory) throws IOException {
        fileWriter = new FileWriter( filePath);
        try{
            for (int i = 0; i < taskCount; i++) {
                fileWriter.write(taskHistory[i].toCSV() + System.lineSeparator());
            }
            fileWriter.close();
        }catch (IOException e){
            throw new IOException();
        }
    }
}
