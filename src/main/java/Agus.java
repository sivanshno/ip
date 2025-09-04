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

    // helper function to add, lists history of inputs
    public static void listHistory( String[] inputHistory, int inputHistorySize){
        for ( int i = 0; i < inputHistorySize; i += 1){
            System.out.println( i+1 + ". " + inputHistory[i]);
        }
    }

    public static void add() {
        // stores all user inputs
        // has the ability to list them upon the "list" command
        // exits when user inputs "bye"
        String [] inputHistory = new String[100]; // assumes no more than 100 inputs per session
        int inputHistorySize = 0;
        String userInput = "";
        Scanner scanner = new Scanner(System.in);
        while (!userInput.equals("bye")) {
            userInput = scanner.nextLine();
            //dealing with non-command inputs
            if (!( userInput.equals("list") || userInput.equals("bye") || userInput.equals("") ) ){
                inputHistory[inputHistorySize] = userInput;
                inputHistorySize += 1;
            }
            //dealing with command inputs
            if (userInput.equals("list") ){
                listHistory ( inputHistory, inputHistorySize);
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
