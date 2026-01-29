public class AddToDoCommand extends Command {
    private final String description;

    public AddToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobException {
        ToDos todo = new ToDos(description);
        tasks.add(todo);
        ui.showMessage("To-Do task added: " + todo);
        storage.saveTasks(tasks.listTasks());
    }
}
