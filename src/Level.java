import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Level {
    private HashMap<String, Room> nodes;

    public Level() {
        nodes = new HashMap<>();
    }

    public void addNode(String name) {
        Room n = new Room(name);
        nodes.put(name, n);
    }

    public void addNode(String name, String description) {
        Room n = new Room(name, description);
        nodes.put(name, n);
    }

    public void addDirectedEdge(String name1, String name2) {
        Room n1 = getNode(name1);
        Room n2 = getNode(name2);
        n1.addNeighbor(n2);
    }

    public void addUndirectedEdge(String name1, String name2) {
        addDirectedEdge(name1, name2);
        addDirectedEdge(name2, name1);
    }

    public Room getNode(String name) {
        return nodes.get(name);
    }

    public class Room {
        private String name;
        private HashMap<String, Room> neighbors;
        private String description;

        public Room(String name) {
            this.name = name;
            neighbors = new HashMap<>();
            description = "";
        }

        public Room(String name, String description) {
            this.name = name;
            neighbors = new HashMap<>();
            this.description = description;
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
    }
}
