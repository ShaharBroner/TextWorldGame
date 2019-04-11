public class QuitCommand implements Command {
    @Override
    public void init(String userString) {

    }

    @Override
    public boolean execute() {
        System.out.println("Goodbye!");
        return true;
    }
}
