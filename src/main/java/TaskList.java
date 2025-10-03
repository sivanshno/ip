import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores the list of tasks and contains all operations modifying the list
 *
 * @author Sivansh
 * @version 1.0
 */
public class TaskList {
    private Task[] taskHistory;
    private int taskCount = 0;
    private Storage storage;

    public TaskList(Storage mainStorage){
        taskHistory = new Task[100];
        storage = mainStorage;
    }

    public void list(){
        for ( int i = 0; i < taskCount; i += 1){
            Task currentTask = taskHistory[i];
            currentTask.printTask( i+1);
        }
    }

    public void mark (int toMark) throws ArrayIndexOutOfBoundsException{
        if (toMark > taskCount || toMark < 1){
            throw new ArrayIndexOutOfBoundsException();
        }
        taskHistory[toMark - 1].isDone = true;
        list();
    }

    public void unmark(int toUnmark) throws ArrayIndexOutOfBoundsException {
        if (toUnmark > taskCount || toUnmark < 1){
            throw new ArrayIndexOutOfBoundsException();
        }
        taskHistory[toUnmark - 1].isDone = false;
        list();
    }

    public void delete(int toDelete) throws ArrayIndexOutOfBoundsException{
        //checks if the delete index is valid
        if (toDelete > taskCount || toDelete < 1){
            throw new ArrayIndexOutOfBoundsException();
        }

        Task deletedTask = taskHistory[toDelete - 1]; // temporarily store deleted task
        taskCount -= 1; // update the number of tasks

        //rearrange the tasks array by moving every task after delete to the index before
        for ( int i = toDelete - 1; i < taskCount; i += 1){
            taskHistory[i] = taskHistory[i + 1];
        }

        System.out.println("Done!I have deleted the Task:");
        deletedTask.printTask();
        System.out.println("Now you have " + taskCount + "  tasks in your list");
    }

    /**
     * Passes the task data to local storage to be saved
     */
    public void saveData() throws IOException{
        try {
            storage.saveData(taskCount, taskHistory);
        } catch (IOException e) {
            throw new IOException();
        }
    }

    public void addTask (Task newTask){
        taskHistory[taskCount] = newTask;
        taskCount += 1;
        System.out.println("got it, I've added this task:");
        System.out.println("now you have "+ taskCount + " tasks in your list");
    }

    /**
     * Finds all tasks containing a specific phrase
     *
     * @param query the phrase the user wants to search
     * @return list of all tasks containing that phrase in their description
     */

    public List<Task> searchTasks (String query){
        query = query.trim();
        List<Task> searchResults = new ArrayList<>();
        for (int i = 0; i < taskCount; i += 1){
            Task currentTask = taskHistory[i];
            String taskDescription = (currentTask).getDescription();
            if (taskDescription.contains(query)){
                searchResults.add(currentTask);
            }
        }
        return searchResults;
    }

}
