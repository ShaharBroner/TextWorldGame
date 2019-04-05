import java.util.ArrayList;
import java.util.HashMap;

public class Chicken extends Creature {
    public Chicken(Level.Room currentRoom) {
        super(currentRoom);
    }

    @Override
    public void move() {
        HashMap<String, Level.Room> hmap = currentRoom.getNeighbors();
        ArrayList<Level.Room> rooms = new ArrayList<>(hmap.values());
        int random = (int) (Math.random() * rooms.size());
        setCurrentRoom(rooms.get(random));
    }
}
