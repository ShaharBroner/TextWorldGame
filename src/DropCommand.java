public class DropCommand implements Command {
    private Level g;

    public DropCommand(Level g) {
        this.g = g;
    }

    @Override
    public void init(String userString) {

    }

    @Override
    public boolean execute() {
        return false;
    }
}
