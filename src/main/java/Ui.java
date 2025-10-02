import java.util.Scanner;

public class Ui {
    private Parser parser ;

    public Ui(){
        parser = new Parser();
    }

    public void greet(){
        System.out.println("Hello! I'm Agus\n what can I do for you?");
    }

    public void bye(){
        System.out.println( "Bye. Hope to see you again soon!");
    }

    public void storageNotFoundError(){
        System.out.println("Unable to store data internally \n exiting program");
    }

    public void storageRetrievalError(){
        System.out.println("Local storage unreadable or corrupted \n exiting program");
    }

    public void run(){
        String userInput = "";
        Scanner scanner = new Scanner(System.in);
        while (!userInput.equals("bye")) {
            userInput = scanner.nextLine();
            try {
                parser.parseInput(userInput);
            }catch( IllegalCommandException e){
                System.out.println("Sorry, I don't quite understand");
            }
        }
    }


}
