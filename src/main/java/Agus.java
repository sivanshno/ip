import java.util.Scanner;
public class Agus {

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
        return Integer.parseInt(words[1]);
    }
    // helper function to determine whether the user input is a command
    public static boolean isCommand(String userInput){
        if ( userInput.equals("list") || userInput.equals("bye") || userInput.equals("") || userInput.startsWith("mark") || userInput.startsWith("unmark") ){
            return true;
        }
        return false;
    }

    public static void add() {
        // stores all user inputs
        // has the ability to list them upon the "list" command
        // exits when user inputs "bye"
        String userInput = "";
        Scanner scanner = new Scanner(System.in);
        while (!userInput.equals("bye")) {
            userInput = scanner.nextLine();
            //dealing with non-command inputs
            if (!isCommand(userInput) ){
                new Task(userInput);
                System.out.println("added: " + userInput);
            }
            //dealing with command inputs
            if (userInput.equals("list") ){
                Task.list();
            }
            if (userInput.startsWith("unmark")){
                int numberToUnmark = extractNumber(userInput);
                Task.unmark(numberToUnmark);
            }
            if (userInput.startsWith("mark")){
                int numberToMark = extractNumber(userInput);
                Task.mark(numberToMark);
            }
        }
    }

    public static void main(String[] args) {
        //main function
        System.out.println("Hello! I'm Agus\n what can I do for you?");
        Agus.add();
        System.out.println( "Bye. Hope to see you again soon!");
    }
}
