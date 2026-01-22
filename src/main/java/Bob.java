import java.util.Scanner;

public class Bob {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 1;
        String[] tasks = new String[100];
        System.out.println("Hi! I'm Bob");
        System.out.println("What can I help you with?");

        while (true) {
            System.out.println("Add task: ");
            String input = sc.nextLine();
            tasks[n] = input;

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye!");
                break;
            }
            else if (input.equalsIgnoreCase("tasks")) {
                for (int i = 1; i < n; i++) {
                    System.out.println(i + ". " + tasks[i]);
                }
                continue;
            }
            System.out.println("Task " + n + " added: " + input);
            n++;
        }

    }
}
