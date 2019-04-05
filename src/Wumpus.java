import java.util.ArrayList;
import java.util.HashMap;

public class Wumpus extends Creature {
    Player p;
    Level.Room currentRoom;

    public Wumpus(Player p, Level.Room currentRoom) {
        super(currentRoom);
        this.p = p;
    }

    @Override
    public void move() {
        HashMap<String, Level.Room> hmap = currentRoom.getNeighbors();
        ArrayList<Level.Room> rooms = new ArrayList<>(hmap.values());
        Level.Room playerRoom = currentRoom.containsPlayer(rooms, p);
        if (!playerRoom.equals(null)) {
            if (hmap.size() > 1) {
                hmap.remove(playerRoom);
            }
        }
        int random = (int) (Math.random() * rooms.size());
        setCurrentRoom(rooms.get(random));
    }
}
