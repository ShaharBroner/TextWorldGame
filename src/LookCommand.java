public class LookCommand implements Command {
    private Player p;

    public LookCommand(Player p) {
        this.p = p;
    }

    @Override
    public void init(String userString) {

    }

    @Override
    public boolean execute() {
        return false;
    }
}
