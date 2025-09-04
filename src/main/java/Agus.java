import java.util.Scanner;
public class Agus {

    public static void echo() {
        String userInput = "";
        Scanner scanner = new Scanner(System.in);
        while (!userInput.equals("bye")) {
            userInput = scanner.nextLine();
            if (!userInput.equals("bye")) {
                System.out.println(userInput);
            }
        }
    }
    public static void main(String[] args) {
        System.out.println("Hello! I'm Agus\n what can I do for you?");
        Agus.echo();
        System.out.println( "Bye. Hope to see you again soon!");
    }
}
