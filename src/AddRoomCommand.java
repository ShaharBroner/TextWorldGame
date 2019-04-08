public class AddRoomCommand implements Command {
    private Player p;

    public AddRoomCommand(Player p) {
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
