import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Agus {

    public static final String [] VALID_COMMANDS = { "bye", "list", "mark", "unmark", "todo", "deadline", "event", "delete"};

    public static void echo() {
        // echoes all use input until the user inputs "bye"
        String userInput = "";
        Scanner scanner = new Scanner(System.in);
        while (!userInput.equals("bye")) {
            userInput = scanner.nextLine();
            if (!userInput.equals("bye")) {
                System.out.println(userInput);
            }
        }
    }

    public static int extractNumber(String userInput) {
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

    // helper function to determine whether the user input is a command
    public static boolean isCommand(String userInput){
        for (String command: VALID_COMMANDS){
            if (userInput.equals(command)){
                return true;
            }
        }
        return false;
    }

    public static void parseCommand(String command, String description){
        switch (command){
            case "bye":
                break;
            case "list":
                Task.list();
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
                    Task.unmark(numberToUnmark);
                    try {
                        Task.saveData();
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
                    Task.mark(numberToMark);
                    try {
                        Task.saveData();
                    }catch (IOException e){
                        System.out.println("error saving changes locally");
                    }
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("You've not created that task yet");
                }
                break;
            case "todo":
                new Todo(description);
                try {
                    Task.saveData();
                }catch (IOException e){
                    System.out.println("error saving changes locally");
                }
                break;
            case "deadline":
                new Deadline(description);
                try {
                    Task.saveData();
                }catch (IOException e){
                    System.out.println("error saving changes locally");
                }
                break;
            case "event":
                new Event(description);
                try {
                    Task.saveData();
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
                    Task.delete(numberToDelete);
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("You've not created that task yet");
                }
                break;
            default:
                throw new IllegalCommandException("invalid command input");
        }
    }

    public static void parseInput(String userInput){
        //extract the first word of the userInput to get command

        int commandEndIndex = userInput.indexOf(" ") ;
        String command = (commandEndIndex < 0? userInput: userInput.substring(0, commandEndIndex));
        //debug
        String description = userInput.substring(commandEndIndex + 1);
        try{
            parseCommand(command,description);
        } catch (IllegalCommandException e){
            System.out.println("Sorry, I don't quite understand");
        }
    }

    public static void add() {
        // stores all user inputs
        // has the ability to list them upon the "list" command
        // exits when user inputs "bye"
        String userInput = "";
        Scanner scanner = new Scanner(System.in);
        while (!userInput.equals("bye")) {
            userInput = scanner.nextLine();
            parseInput(userInput);
        }
    }

    public static void parseTaskCSV(String taskCSV) throws InputMismatchException{
        String[] splitData = taskCSV.split(",");
        String type = splitData[0];
        switch (type){
            case "T":
                try{
               new Todo(splitData[1], splitData[2]);}
                catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("Task data corrupted");
                }
               break;
            case "D":
                try{
                    new Deadline(splitData[1], splitData[2], splitData[3]);}
                catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("Task data corrupted");
                }
                break;
            case "E":
                try{
                    new Event(splitData[1], splitData[2], splitData[3], splitData[4]);}
                catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("Task data corrupted");
                }
                break;
            default:
                throw new InputMismatchException();
        }

    }

    protected static void loadData() throws IOException {

        File savedTasks = new File(TASK_DATA_FILE_PATH);

        //check if file already exists, if not, create one
        try {
            if (!savedTasks.exists()) {
                System.out.println("initialising system data files");
                savedTasks.createNewFile();
                return;
            }
        } catch (IOException e){
            System.out.println("an error occurred while creating data file for Agus ");
            throw new IOException();
        }

        //can assert here that the file does exist, time to retrieve file data
        Scanner s;
        try{
        s = new Scanner(savedTasks);
        }catch (IOException e){
            System.out.println("locally stored data not found");
            return;
        }
        try{
            while (s.hasNextLine()){
                String taskCSV = s.nextLine();
                try {
                    parseTaskCSV(taskCSV);
                }catch (InputMismatchException e){
                    System.out.println("invalid task detected");
                }
            }
        }catch (NoSuchElementException e ){
            System.out.println("error reading locally saved data");
        }
    }

    public static void main(String[] args) {
        //main function
        try {
            Agus.loadData();
        }catch (IOException e){
            System.out.println("exiting program");
            return;
        }
        System.out.println("Hello! I'm Agus\n what can I do for you?");
        Agus.add();
        System.out.println( "Bye. Hope to see you again soon!");
    }
}
