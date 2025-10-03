import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Parses all user inputs passed to it by the chatbot and calls the required
 * methods to execute the parsed commands
 *
 * @author Sivansh
 * @version 1.0
 */
public class Parser {

    public Parser(){
       //nothing to do here
    }

    /**
     * Extracts the numbers in the user input when a particular task number
     * is commanded to be marked or unmarked
     *
     * @param userInput the body of the user input
     * @return the first number it sees in the user input
     * @throws IllegalArgumentException when the string is empty or null
     * @throws InputMismatchException when there isn't a number in the string
     */

    private static int extractNumber(String userInput) throws IllegalArgumentException, InputMismatchException{
        if (userInput == null || userInput.trim().isEmpty()) {
            throw new IllegalArgumentException("Input is empty or null");
        }

        String[] words = userInput.trim().split(" ");

        try {
            return Integer.parseInt(words[0]);
        } catch (IllegalArgumentException e) {
            throw new InputMismatchException();
        }
    }

    /**
     * Reads the users command and executes the relevant methods
     *
     * @param command the command word in the user input
     * @param description the rest of the user input
     * @throws IllegalCommandException if the user's command is invalid
     */

    private static void parseCommand(String command, String description) throws IllegalCommandException{
        switch (command){
            case "bye":
                break;
            case "list":
                Agus.tasks.list();
                break;
            case "unmark":
                int numberToUnmark;
                try {
                    numberToUnmark = extractNumber(description);
                } catch(ArrayIndexOutOfBoundsException e) {
                    System.out.println("I think you forgot to tell me what to unmark");
                    return;
                } catch (InputMismatchException e){
                    System.out.println("I don't think that's a real number");
                    return;
                }
                try {
                    Agus.tasks.unmark(numberToUnmark);
                    try {
                        Agus.tasks.saveData();
                    }catch (IOException e){
                        System.out.println("error saving changes locally");
                    }
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("You've not created that task yet");
                }
                break;
            case "mark":
                int numberToMark;
                try {
                    numberToMark = extractNumber(description);
                } catch( IllegalArgumentException e) {
                    System.out.println("I think you forgot to tell me what to mark");
                    return;
                } catch (InputMismatchException e){
                    System.out.println("I don't think that's a real number");
                    return;
                }
                try {
                    Agus.tasks.mark(numberToMark);
                    try {
                        Agus.tasks.saveData();
                    }catch (IOException e){
                        System.out.println("error saving changes locally");
                    }
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("You've not created that task yet");
                }
                break;
            case "todo":
                Todo newTodo = new Todo(description);
                Agus.tasks.addTask(newTodo);
                try {
                    Agus.tasks.saveData();
                }catch (IOException e){
                    System.out.println("error saving changes locally");
                }
                break;
            case "deadline":
                try {
                    Deadline newDeadline = new Deadline(description);
                    Agus.tasks.addTask(newDeadline);
                }catch (IllegalArgumentException e){
                    System.out.println("unrecognised deadline format!");
                    break;
                }
                try {
                    Agus.tasks.saveData();
                }catch (IOException e){
                    System.out.println("error saving changes locally");
                }
                break;
            case "event":
                try {
                    Event newEvent = new Event(description);
                    Agus.tasks.addTask(newEvent);
                }catch (IllegalArgumentException e){
                    System.out.println("unrecognised date format!");
                    break;
                }
                try {
                    Agus.tasks.saveData();
                }catch (IOException e){
                    System.out.println("error saving changes locally");
                }
                break;
            case "delete":
                int numberToDelete;
                try {
                    numberToDelete = extractNumber(description);
                } catch(ArrayIndexOutOfBoundsException e) {
                    System.out.println("I think you forgot to tell me what to delete");
                    return;
                } catch (InputMismatchException e){
                    System.out.println("I don't think that's a real number");
                    return;
                }
                try {
                    Agus.tasks.delete(numberToDelete);
                    try {
                        Agus.tasks.saveData();
                    }catch (IOException e){
                        System.out.println("error saving changes locally");
                    }
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("You've not created that task yet");
                }
                break;
            case "find":
                List<Task> searchResults = Agus.tasks.searchTasks(description);
                if (searchResults.isEmpty()){
                    System.out.println("No matching tasks found :( ");
                    break;
                }
                System.out.println("Here are some matching tasks I found in your list");
                for(Task task : searchResults){
                    task.printTask();
                }
                break;
            default:
                throw new IllegalCommandException("invalid command input");
        }
    }

    /**
     * Handles the raw user input and converts it into a command and a body
     *
     * @param userInput a string containing the raw user input
     * @throws IllegalCommandException if the user's command is invalid
     */

    protected void parseInput(String userInput) throws IllegalCommandException{

        int commandEndIndex = userInput.indexOf(" ") ;
        String command = (commandEndIndex < 0? userInput: userInput.substring(0, commandEndIndex));
        String body = userInput.substring(commandEndIndex + 1);
        try{
            parseCommand(command,body);
        } catch (IllegalCommandException e){
            System.out.println("Sorry, I don't quite understand");
            throw new IllegalCommandException(" ");
        }
    }

}
