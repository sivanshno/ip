import java.util.Scanner;
public class Agus {

    public static final String [] VALID_COMMANDS = { "bye", "list", "mark", "unmark", "todo", "deadline", "event"};

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

    public static int extractNumber(String userInput){
        String [] words = userInput.split(" ");
        return Integer.parseInt(words[0]);
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
                int numberToUnmark = extractNumber(description);
                Task.unmark(numberToUnmark);
                break;
            case "mark":
                int numberToMark = extractNumber(description);
                System.out.println("marking: " + numberToMark);
                Task.mark(numberToMark);
                break;
            case "todo":
                new Todo(description);
                System.out.println("doing this");
                break;
            case "deadline":
                new Deadline(description);
                break;
            case "event":
                new Event(description);
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

    public static void main(String[] args) {
        //main function
        System.out.println("Hello! I'm Agus\n what can I do for you?");
        Agus.add();
        System.out.println( "Bye. Hope to see you again soon!");
    }
}
