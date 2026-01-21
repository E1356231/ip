import java.util.Scanner;

public class Bob {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hi! I'm Bob");
        System.out.println("What can I help you with?");

        while (true) {
            System.out.println("Ask anything ");
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye!");
                break;
            }
            System.out.println("You asked: " + input);
        }

    }
}
