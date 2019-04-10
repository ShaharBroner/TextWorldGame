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
        boolean possible = p.moveToRoom(room);
        if (possible == false) {
            System.out.println("That is not possible, type: *look* to see where you can go");
            return false;
        } else {
            System.out.println("You moved to " + room);
            return true;
        }
    }
}
