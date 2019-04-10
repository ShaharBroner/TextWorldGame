public class AddRoomCommand implements Command {
    final static private int INDEX_OF_ROOM_NAME = 2;
    private Player p;
    private Level g;
    private String room;

    public AddRoomCommand(Player p, Level g) {
        this.p = p;
        this.g = g;
    }

    @Override
    public void init(String userString) {
        String[] words = userString.split(" ");
        room = words[INDEX_OF_ROOM_NAME];
    }

    @Override
    public boolean execute() {
        g.addRoom(room);
        g.addUndirectedEdge(p.getCurrentRoom().getName(), room);
        System.out.println("Added path to " + room);
        return true;
    }
}
