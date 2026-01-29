import java.util.Scanner;

public class Bob {
    public static void main(String[] args) throws BobException{
        Scanner sc = new Scanner(System.in);
        TaskList tasklist = new TaskList();
        System.out.println("Hi! I'm Bob");
        System.out.println("What's on your agenda for today?");

        while (true) {
            try {
                System.out.println("Action:");
                String action = sc.nextLine();

                if (action.equalsIgnoreCase("bye")) {
                    System.out.println("Bye!");
                    break;
                } else if (action.equalsIgnoreCase("add")) {
                    while (true) {
                        try {
                            System.out.print("Task type: ");
                            String input = sc.nextLine();

                            if (input.equalsIgnoreCase("quit")) {
                                break;
                            } else if (input.equalsIgnoreCase("t")) {
                                System.out.print("To-Do task: ");
                                String todo = sc.nextLine();
                                ToDos td = new ToDos(todo);
                                tasklist.add(td);
                                String print = td.toString();
                                System.out.println("To-Do task added: " + print);
                            } else if (input.equalsIgnoreCase("d")) {
                                System.out.print("Deadline task: ");
                                String deadline = sc.nextLine();
                                System.out.print("End Date (e.g. 22-Jan-26): ");
                                String endDate = sc.nextLine();
                                DateUtil.isValidDate(endDate);
                                System.out.print("End Time (e.g. 2359): ");
                                String endTime = sc.nextLine();
                                DateUtil.isValidTime(endTime);
                                Deadlines d = new Deadlines(deadline, endDate, endTime);
                                tasklist.add(d);
                                String print = d.toString();
                                System.out.println("Deadline task added: " + print);
                            } else if (input.equalsIgnoreCase("e")) {
                                System.out.print("Event: ");
                                String event = sc.nextLine();
                                System.out.print("Start Date (e.g. 22-Jan-26): ");
                                String startDate = sc.nextLine();
                                DateUtil.isValidDate(startDate);
                                System.out.print("Start Time (e.g. 0845): ");
                                String startTime = sc.nextLine();
                                DateUtil.isValidTime(startTime);
                                System.out.print("End Date (e.g. 22-Jan-26): ");
                                String endDate = sc.nextLine();
                                DateUtil.isValidDate(endDate);
                                System.out.print("End Time (e.g. 2359): ");
                                String endTime = sc.nextLine();
                                DateUtil.isValidTime(endTime);
                                Events e = new Events(event, startDate, startTime, endDate, endTime);
                                tasklist.add(e);
                                String print = e.toString();
                                System.out.println("Event added: " + print);
                            } else {
                                throw new BobException(Errors.INVALID_COMMAND
                                        + "\n__Valid task types__"
                                        + "\nE: for Events"
                                        + "\nD: for Deadlines"
                                        + "\nT for To-Dos"
                                        + "\nQuit: to stop adding tasks.");
                            }
                        } catch (BobException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                } else if (action.equalsIgnoreCase("tasks")) {
                    System.out.println("Task(s) in your list:");
                    tasklist.listTasks();
                } else if (action.equalsIgnoreCase("mark")) {
                    System.out.print("task: ");
                    int index = sc.nextInt();
                    sc.nextLine();
                    tasklist.getTask(index).markDone();
                    String done = tasklist.getTask(index).toString();
                    System.out.println("Task marked done: " + done);
                } else if (action.equalsIgnoreCase("unmark")) {
                    System.out.print("task: ");
                    int index = sc.nextInt();
                    sc.nextLine();
                    tasklist.getTask(index).unmark();
                    String unmarked = tasklist.getTask(index).toString();
                    System.out.println("Task unmarked: " + unmarked);
                } else if (action.equalsIgnoreCase("delete")) {
                    System.out.print("task: ");
                    int index = sc.nextInt() ;
                    sc.nextLine();
                    tasklist.delete(index);
                } else {
                    throw new BobException(Errors.INVALID_COMMAND
                            + "\n__Valid Actions__"
                            + "\nadd: to add new tasks"
                            + "\ntasks: prints list of tasks"
                            + "\nmark: mark task as completed"
                            + "\nunmark: mark task as incomplete");
                }
            }
            catch (BobException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
