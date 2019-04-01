import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Level g = new Level();
        g.addNode("hall", "A magical passage");
        g.addNode("closet", "A door to the unknown");
        g.addNode("dungeon", "Scary");

        g.addUndirectedEdge("hall", "dungeon");
        g.addUndirectedEdge("hall", "closet");

        Player p = new Player("Player 1", "A player");
        p.setCurrentRoom(g.getNode("hall"));

        Item shirt = new Item("shirt");
        Item ball = new Item("ball", "soccer ball");
        Item key = new Item("key");
        g.getNode("hall").addItem(key);
        g.getNode("hall").addItem(ball);
        g.getNode("closet").addItem(shirt);

        String response = "";
        Scanner s = new Scanner(System.in);

        do {
            System.out.println("You are in the " + p.getCurrentRoom().getName());
            System.out.println("What do you want to do?");
            response = s.nextLine();

            String[] words = response.split(" ");

            if (words[0].equals("go")) {
                String roomName = "";
                if (words.length >= 2) {
                    roomName = words[1];
                }
                boolean possible = p.moveToRoom(roomName);
                if (possible == false) {
                    System.out.println("That is not possible, type: *look* to see where you can go");
                } else {
                    System.out.println("You moved to " + roomName);

                }
            } else if (words[0].equals("look")) {
                System.out.println(p.getCurrentRoom().getNeighborsNames());
                System.out.println(p.getCurrentRoom().displayItems());
            } else if (words[0].equals("add")) {
                if (words.length < 3) {
                    System.out.println("Impossible!");
                } else {
                    if (words[1].equals("room")) {
                        String roomName = words[2];
                        g.addNode(roomName);
                        g.addUndirectedEdge(p.getCurrentRoom().getName(), roomName);
                        System.out.println("Added path to " + roomName);
                    }
                }
                g.addDirectedEdge(p.getCurrentRoom().getName(), response.substring(9));
            } else if (words[0].equals("take")) {
                ArrayList<Item> items = p.getCurrentRoom().getItems();
                for (Item i : items) {
                    if (words[1].equals(i.getName())) {
                        p.addItem(p.getCurrentRoom().removeItem(words[1]));
                        System.out.println("You took: " + words[1]);
                        break;
                    }
                }
            } else if (words[0].equals("drop")) {
                ArrayList<Item> items = p.getItems();
                for (Item i : items) {
                    if (words[1].equals(i.getName())) {
                        p.getCurrentRoom().addItem(p.removeItem(words[1]));
                        System.out.println("You dropped: " + words[1]);
                        break;
                    }
                }
            } else if (response.equals("quit")) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Type: *go <roomname>* to go to that room");
                System.out.println("Type: *look* to see all neighbors and items");
                System.out.println("Type: *add room <roomname>* to add that room");
                System.out.println("Type: *take <itemname>* to take an item and remove it from the room");
                System.out.println("Type: *drop <itemname>* to remove an item and add it to the room");
                System.out.println("Type: *quit* to quit the game");
            }
        } while (!response.equals("quit"));
    }
}
