public class GoCommand implements Command {
    private Player p;
    private String room;

    public GoCommand(Player p) {
        this.p = p;
    }

    @Override
    public void init(String userString) {
        this.room = userString.substring(userString.indexOf(" ") + 1);
    }

    @Override
    public boolean execute() {
        return false;
    }
}
