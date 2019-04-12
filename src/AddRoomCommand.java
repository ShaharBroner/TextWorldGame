public class AddRoomCommand implements Command {
    private Player p;
    private Level g;
    private String room;

    public AddRoomCommand(Player p, Level g) {
        this.p = p;
        this.g = g;
    }

    @Override
    public void init(String userString) {
        room = userString.substring(userString.indexOf(" ") + 1);
    }

    @Override
    public boolean execute() {
        g.addRoom(room);
        g.addUndirectedEdge(p.getCurrentRoom().getName(), room);
        System.out.println("Added path to " + room);
        return true;
    }
}
