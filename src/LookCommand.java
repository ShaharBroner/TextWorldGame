public class LookCommand implements Command {
    private Player p;
    private Level.Room room;

    public LookCommand(Player p) {
        this.p = p;
    }

    @Override
    public void init(String userString) {
        room = p.getCurrentRoom();
    }

    @Override
    public boolean execute() {
        System.out.println(p.getCurrentRoom().getNeighborsNames());
        System.out.println(p.getCurrentRoom().displayItems());
        return true;
    }
}
