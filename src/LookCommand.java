import java.util.ArrayList;

public class LookCommand implements Command {
    private Player p;
    private ArrayList<Level.Room> neighbors;

    public LookCommand(Player p) {
        this.p = p;
    }

    @Override
    public void init(String userString) {
        neighbors = new ArrayList<>(p.getCurrentRoom().getNeighbors().values());
    }

    @Override
    public boolean execute() {
        for (Level.Room r : neighbors) {
            System.out.println(r.getName() + ", " + r.getDescription() + ".");
        }
        System.out.println(p.getCurrentRoom().displayItems());
        return true;
    }
}
