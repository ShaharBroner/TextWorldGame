import java.util.ArrayList;
import java.util.HashMap;

public class PopStar extends Creature {
    private Player p;

    public PopStar(Player p, Level.Room currentRoom) {
        super(currentRoom);
        this.p = p;
    }

    @Override
    public void move() {
        HashMap<String, Level.Room> hmap = currentRoom.getNeighbors();
        ArrayList<Level.Room> rooms = new ArrayList<>(hmap.values());
        Level.Room playerRoom = currentRoom.containsPlayer(rooms, p);
        if (playerRoom != null) {
            setCurrentRoom(playerRoom);
        }
        int random = (int) (Math.random() * rooms.size());
        setCurrentRoom(rooms.get(random));
    }
}
