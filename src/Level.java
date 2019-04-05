import java.util.ArrayList;
import java.util.HashMap;

public class Level {
    private HashMap<String, Room> nodes;

    public Level() {
        nodes = new HashMap<>();
    }

    public void addRoom(String name) {
        Room n = new Room(name);
        nodes.put(name, n);
    }

    public void addRoom(String name, String description) {
        Room n = new Room(name, description);
        nodes.put(name, n);
    }

    public void addDirectedEdge(String name1, String name2) {
        Room n1 = getRoom(name1);
        Room n2 = getRoom(name2);
        n1.addNeighbor(n2);
    }

    public void addUndirectedEdge(String name1, String name2) {
        addDirectedEdge(name1, name2);
        addDirectedEdge(name2, name1);
    }

    public Room getRoom(String name) {
        return nodes.get(name);
    }

    public class Room {
        private String name;
        private HashMap<String, Room> neighbors;
        private String description;
        private ArrayList<Item> items;

        public Room(String name) {
            this.name = name;
            neighbors = new HashMap<>();
            description = "";
            items = new ArrayList<>();
        }

        public Room(String name, String description) {
            this.name = name;
            neighbors = new HashMap<>();
            this.description = description;
            items = new ArrayList<>();
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public String getName() {
            return name;
        }

        public void addNeighbor(Room n) {
            neighbors.put(n.getName(), n);
        }

        public String getNeighborsNames() {
            String names = "";
            for (Room n : neighbors.values()) {
                names = names + n.getName() + ", ";
            }
            return names;
        }

        public Room getNeighbor(String name) {
            return neighbors.get(name);
        }

        public HashMap<String, Room> getNeighbors() {
            return neighbors;
        }

        public ArrayList<Item> getItems() {
            return items;
        }

        public String displayItems() {
            String output = "";
            for (Item i : items) {
                output += i.getName() + ", ";
            }
            return output;
        }

        public void addItem(String name) {
            Item i = new Item(name);
            items.add(i);
        }

        public void addItem(String name, String description) {
            Item i = new Item(name, description);
            items.add(i);
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

        public Room containsPlayer(Room r, Player p) {
            if (p.getCurrentRoom().equals(r)) {
                return r;
            }
            return null;
        }

        public Room containsPlayer(ArrayList<Room> roomList, Player p) {
            for (Room current : roomList) {
                if (p.getCurrentRoom().equals(current)) {
                    return current;
                }
            }
            return null;
        }

        public boolean containsNonRandomCreature(Room r, Creature c) {
            if (c.getCurrentRoom().equals(r)) {
                return true;
            }
            return false;
        }

        public boolean containsNonRandomCreature(ArrayList<Room> roomList, Creature c) {
            for (Room current : roomList) {
                if (c.getCurrentRoom().equals(current)) {
                    return true;
                }
            }
            return false;
        }
    }
}
