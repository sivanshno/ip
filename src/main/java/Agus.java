import java.io.IOException;
import java.util.*;

/**
 * Represents the chatbot and all of its functions
 *
 * @author Sivansh
 * @version 1.0
 */
public class Agus {

    private Storage storage;
    private Ui ui;
    public static TaskList tasks;

    public Agus(){
        ui = new Ui();
        storage = new Storage ("data/savedTasks.txt");
        tasks = new TaskList(storage);
    }

    /**
     * Method that runs the entire chatbot and handles its startup and shut down
     */
    public void run (){
        try{
            storage.loadSavedTasks();
        }catch (IOException e){
            ui.storageNotFoundError();
            return;
        } catch (NoSuchElementException e) {
            ui.storageRetrievalError();
           return;
        }
        ui.greet();
        ui.run();
        ui.bye();
    }

    public static void main(String[] args) {
        //main function
        new Agus().run();
    }
}
