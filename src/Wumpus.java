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
            rooms.remove(playerRoom);
        }
        searchSecondNeighbors(rooms);
        int random = (int) (Math.random() * rooms.size());
        setCurrentRoom(rooms.get(random));
    }

    private void searchSecondNeighbors(ArrayList<Level.Room> rooms) {
        for (Level.Room r : rooms) {
            ArrayList<Level.Room> neighbors = new ArrayList<>(r.getNeighbors().values());
            Level.Room playerRoom = currentRoom.containsPlayer(neighbors, p);
            if (playerRoom != null) {
                rooms.remove(r);
            }
        }
    }
}
