import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Level g = new Level();
        g.addNode("hall", "A magical passage");
        g.addNode("closet", "A door to the unknown");
        g.addNode("dungeon", "Scary");

        g.addDirectedEdge("hall", "dungeon");
        g.addUndirectedEdge("hall", "closet");

        Level.Room current = g.getNode("hall");

        String response = "";
        Scanner s = new Scanner(System.in);

        do {
            System.out.println("You are in the " + current.getName());
            System.out.println("What do you want to do?");
            response = s.nextLine();

            String[] words = response.split(" ");

            if (words[0].equals("go")) {
                String roomName = "";
                if (words.length >= 2) {
                    roomName = words[1];
                }
                Level.Room nextRoom = current.getNeighbor(roomName);
                if (nextRoom == null) {
                    System.out.println("That is not possible, type: *look* to see where you can go");
                } else {
                    System.out.println("You moved to " + roomName);
                    current = nextRoom;
                }
            } else if (words[0].equals("look")) {
                System.out.println(current.getNeighborsNames());
            } else if (words[0].equals("add")) {
                if (words.length < 3) {
                    System.out.println("Impossible!");
                } else {
                    if (words[1].equals("room")) {
                        String roomName = words[2];
                        g.addNode(roomName);
                        g.addUndirectedEdge(current.getName(), roomName);
                        System.out.println("Added path to " + roomName);
                    }
                }
                g.addDirectedEdge(current.getName(), response.substring(9));
            } else if (response.equals("quit")) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Type: *go <roomname>* to go to that room");
                System.out.println("Type: *look* to see all neighbors");
                System.out.println("Type: *add room <roomname>* to add that room");
                System.out.println("Type: *quit* to quit the game");
            }
        } while (!response.equals("quit"));
    }
}
