public class TakeCommand implements Command {
    private Level g;

    public TakeCommand(Level g) {
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
