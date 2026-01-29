public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobException {
        tasks.unmark(index);
        ui.showMessage("Task unmarked: " + tasks.getTask(index));
        storage.saveTasks(tasks.listTasks());
    }
}
