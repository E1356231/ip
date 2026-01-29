public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobException {
        tasks.markDone(index);
        ui.showMessage("Task marked done: " + tasks.getTask(index));
        storage.saveTasks(tasks.listTasks());
    }
}
