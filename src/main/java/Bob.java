import java.util.Scanner;

public class Bob {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskList tasklist = new TaskList();
        System.out.println("Hi! I'm Bob");
        System.out.println("What's on your agenda for today?");

        while (true) {
            System.out.println("Action: ");
            String action = sc.nextLine();

            if (action.equalsIgnoreCase("bye")) {
                System.out.println("Bye!");
                break;
            }
            else if (action.equalsIgnoreCase("add")) {
                while (true) {
                    System.out.println("Add a task: ");
                    String input = sc.nextLine();
                    Task t = new Task(input);
                    tasklist.add(t);
                    System.out.println("Added task: " + t.toString());

                    if (input.equalsIgnoreCase("done")) {
                        break;
                    }
                }

            }
            else if (action.equalsIgnoreCase("tasks")) {
                System.out.println("Task(s) in your list: ");
                tasklist.listTasks();
                continue;
            }
            else if (action.equalsIgnoreCase("mark")) {
                System.out.println("Mark task: ");
                int index = sc.nextInt();
                sc.nextLine();
                tasklist.getTask(index).markDone();
                String done = tasklist.getTask(index).toString();
                System.out.println("Task marked done: " + done);

                continue;
            }

            else if (action.equalsIgnoreCase("undo")) {
                System.out.println("Unmark task: ");
                int index = sc.nextInt();
                sc.nextLine();
                tasklist.getTask(index).unmark();
                String unmarked = tasklist.getTask(index).toString();
                System.out.println("Task unmarked: " + unmarked);
                continue;
            }
        }

    }
}
