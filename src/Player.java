import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private String name, description;
    private ArrayList<Item> items;
    private Level.Room currentRoom;

    public Player(String name, String description) {
        this.name = name;
        this.description = description;
        items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public Item removeItem(String name) {
        for (Item i : items) {
            if (i.getName().equals(name)) {
                items.remove(i);
                return i;
            }
        }
        return null;
    }

    public boolean destroyItem(String name) {
        for (Item i : items) {
            if (i.getName().equals(name)) {
                return items.remove(i);
            }
        }
        return false;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void displayInventory() {
        for (Item i : items) {
            System.out.print(i.getName() + ", ");
        }
    }

    public Level.Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Level.Room newRoom) {
        currentRoom = newRoom;
    }

    public boolean moveToRoom(String name) {
        Level.Room nextRoom = currentRoom.getNeighbor(name);
        if (nextRoom == null) {
            return false;
        }
        setCurrentRoom(nextRoom);
        return true;
    }

    public String lookForCreatures(ArrayList<Creature> creatures) {
        HashMap<String, Level.Room> hmap = currentRoom.getNeighbors();
        ArrayList<Level.Room> neighbors = new ArrayList<>(hmap.values());
        int wumpusCount = 0;
        int popStarCount = 0;
        for (Creature c : creatures) {
            if (currentRoom.containsNonRandomCreature(neighbors, c)) {
                if (c instanceof Wumpus)
                    wumpusCount++;
                if (c instanceof PopStar)
                    popStarCount++;
            }
        }
        return wumpusCount + " wumpuses and " + popStarCount + " popStars nearby!";
    }
}
