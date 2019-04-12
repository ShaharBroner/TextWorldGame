import java.util.ArrayList;
import java.util.HashMap;

public class Wumpus extends Creature {
    private Player p;

    public Wumpus(Player p, Level.Room currentRoom) {
        super(currentRoom);
        this.p = p;
    }

    @Override
    public void move() {
        HashMap<String, Level.Room> hmap = currentRoom.getNeighbors();
        ArrayList<Level.Room> rooms = new ArrayList<>(hmap.values());
        Level.Room playerRoom = currentRoom.containsPlayer(rooms, p);
        if (playerRoom != null) {
            if (rooms.size() > 1)
                rooms.remove(playerRoom);
        } else {
            searchSecondNeighbors(rooms);
        }
        int random = (int) (Math.random() * rooms.size());
        setCurrentRoom(rooms.get(random));
    }

    private void searchSecondNeighbors(ArrayList<Level.Room> rooms) {
        for (int i = 0; i < rooms.size(); i++) {
            ArrayList<Level.Room> neighbors = new ArrayList<>(rooms.get(i).getNeighbors().values());
            Level.Room playerRoom = currentRoom.containsPlayer(neighbors, p);
            if (playerRoom != null) {
                if (rooms.size() > 1)
                    rooms.remove(rooms.get(i));
            }
        }
    }
}
