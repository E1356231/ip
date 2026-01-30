package bob.ui;

import bob.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the interactions with user for the Bob chatbot.
 * Responsible for reading user inputs and printing outputs.
 */

public class Ui {
    private final Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void greet() {
        System.out.println("Hi! I'm Bob");
        System.out.println("What's on your agenda for today?");
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks!");
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }

    public String read() {
        System.out.println("Enter a command:");
        return sc.nextLine();
    }

    public void showFoundTasks(ArrayList<Task> tasks) {
        System.out.println("Matching tasks in your list:");
        for (Task t : tasks) {
            System.out.println(t);
        }
    }

    public void showAddedTask(Task task) {
        System.out.println("Task added: " + task);
    }

    public void printRemovedTask(Task task) {
        System.out.println(task + " has been removed.");
    }

    public void printBye() {
        System.out.println("Bye!");
    }


}
