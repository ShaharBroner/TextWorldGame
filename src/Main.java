import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    final static int CHICKEN_NUMBER = 10;
    final static int WUMPUS_NUMBER = 2;
    final static int POPSTAR_NUMBER = 2;
    private static HashMap<String, Command> commands = new HashMap<>();
    private static Player p = new Player("Player 1", "A player");
    private static Level g = new Level();

    public static void main(String[] args) {
        // TODO: 4/11/2019 let add room command add rooms with more than one word names.
        initCommands();
        initRooms();
        connectRooms();
        p.setCurrentRoom(g.getRoom("hall"));
        createItems();
        ArrayList<Creature> creatures = createCreatures(CHICKEN_NUMBER, WUMPUS_NUMBER, POPSTAR_NUMBER);
        String response;
        Scanner s = new Scanner(System.in);
        do {
            System.out.println("You are in the " + p.getCurrentRoom().getName());
            displayCreaturesInRoom(creatures);
            System.out.println(p.lookForCreatures(creatures));
            System.out.println("What do you want to do?");
            response = s.nextLine();
            Command command = lookUpCommand(response);
            command.execute();
            if (command instanceof EmptyCommand == false) {
                moveCreatures(creatures);
            } else {
                showPossibleCommands();
            }
//            String[] words = response.split(" ");
//
//            if (words[0].equals("go")) {
//                String roomName = "";
//                if (words.length >= 2) {
//                    roomName = words[1];
//                }
//                boolean possible = p.moveToRoom(roomName);
//                if (possible == false) {
//                    System.out.println("That is not possible, type: *look* to see where you can go");
//                } else {
//                    System.out.println("You moved to " + roomName);
//
//                }
//                moveCreatures(creatures);
//            } else if (words[0].equals("look")) {
//                moveCreatures(creatures);
//                System.out.println(p.getCurrentRoom().getNeighborsNames());
//                System.out.println(p.getCurrentRoom().displayItems());
//            } else if (words[0].equals("add")) {
//                moveCreatures(creatures);
//                if (words.length < 3) {
//                    System.out.println("Impossible!");
//                } else {
//                    if (words[1].equals("room")) {
//                        String roomName = words[2];
//                        g.addRoom(roomName);
//                        g.addUndirectedEdge(p.getCurrentRoom().getName(), roomName);
//                        System.out.println("Added path to " + roomName);
//                    }
//                }
//                g.addDirectedEdge(p.getCurrentRoom().getName(), response.substring(9));
//            } else if (words[0].equals("take")) {
//                moveCreatures(creatures);
//                ArrayList<Item> items = p.getCurrentRoom().getItems();
//                for (Item i : items) {
//                    if (words[1].equals(i.getName())) {
//                        p.addItem(p.getCurrentRoom().removeItem(words[1]));
//                        System.out.println("You took: " + words[1]);
//                        break;
//                    }
//                }
//            } else if (words[0].equals("drop")) {
//                moveCreatures(creatures);
//                ArrayList<Item> items = p.getItems();
//                for (Item i : items) {
//                    if (words[1].equals(i.getName())) {
//                        p.getCurrentRoom().addItem(p.removeItem(words[1]));
//                        System.out.println("You dropped: " + words[1]);
//                        break;
//                    }
//                }
//            } else if (response.equals("quit")) {
//                System.out.println("Goodbye!");
        } while (!response.equals("quit"));
    }

    private static void showPossibleCommands() {
        System.out.println("Type: *go <roomname>* to go to that room");
        System.out.println("Type: *look* to see all neighbors and items (and creatures)");
        System.out.println("Type: *addRoom <roomname>* to add that room");
        System.out.println("Type: *take <itemname>* to take an item and remove it from the room");
        System.out.println("Type: *drop <itemname>* to remove an item and add it to the room");
        System.out.println("Type: *quit* to quit the game");
    }

    private static ArrayList<Creature> createCreatures(int numChicken, int numWumpus, int numPopStar) {
        ArrayList<Creature> creatures = new ArrayList<>();
        for (int i = 0; i < numChicken; i++) {
            creatures.add(new Chicken(g.getRoom("portal room")));
        }
        for (int i = 0; i < numWumpus; i++) {
            creatures.add(new Wumpus(p, g.getRoom("Atlantis")));
        }
        for (int i = 0; i < numPopStar; i++) {
            creatures.add(new PopStar(p, g.getRoom("mall")));
        }
        return creatures;
    }

    private static void createItems() {
        Item shirt = new Item("shirt");
        Item ball = new Item("ball", "soccer ball");
        Item key = new Item("key");
        Item kryptonite = new Item("kryptonite");
        Item shinyStone = new Item("shiny stone");
        Item carrot = new Item("carrot");
        Item trident = new Item("trident");
        g.getRoom("Krypton").addItem(kryptonite);
        g.getRoom("space station").addItem(shinyStone);
        g.getRoom("Changaland").addItem(carrot);
        g.getRoom("Atlantis").addItem(trident);
        g.getRoom("hall").addItem(key);
        g.getRoom("hall").addItem(ball);
        g.getRoom("closet").addItem(shirt);
    }

    private static void connectRooms() {
        g.addUndirectedEdge("hall", "dungeon");
        g.addUndirectedEdge("hall", "closet");
        g.addUndirectedEdge("dungeon", "Changaland");
        g.addUndirectedEdge("closet", "Atlantis");
        g.addUndirectedEdge("Atlantis", "arena");
        g.addUndirectedEdge("Changaland", "mall");
        g.addUndirectedEdge("mall", "Gotham");
        g.addUndirectedEdge("Gotham", "space station");
        g.addUndirectedEdge("space station", "Krypton");
        g.addDirectedEdge("Changaland", "portal room");
        g.addDirectedEdge("portal room", "hall");
        g.addDirectedEdge("portal room", "closet");
        g.addDirectedEdge("portal room", "dungeon");
        g.addDirectedEdge("portal room", "Atlantis");
        g.addDirectedEdge("portal room", "arena");
    }

    private static void initRooms() {
        g.addRoom("hall", "A magical passage");
        g.addRoom("closet", "A door to the unknown");
        g.addRoom("dungeon", "Scary");
        g.addRoom("Changaland", "A magical place");
        g.addRoom("Atlantis", "Under water city");
        g.addRoom("portal room", "this is interesting");
        g.addRoom("arena", "where the best of the best fight");
        g.addRoom("mall", "A place for shopping");
        g.addRoom("space station", "fly to space!");
        g.addRoom("Krypton", "A special planet");
        g.addRoom("Gotham", "A dark city");
    }

    private static Command lookUpCommand(String response) {
        String[] words = response.split(" ");
        String commandWord = words[0];
        Command c = commands.get(commandWord);
        if (c == null) {
            return new EmptyCommand();
        }
        c.init(response);
        return c;
    }

    private static void initCommands() {
        commands.put("go", new GoCommand(p));
        commands.put("look", new LookCommand(p));
        commands.put("addRoom", new AddRoomCommand(p, g));
        commands.put("take", new TakeCommand(p));
        commands.put("drop", new DropCommand(p));
        commands.put("quit", new QuitCommand());
    }

    public static void moveCreatures(ArrayList<Creature> creatures) {
        for (Creature c : creatures) {
            c.move();
        }
    }

    public static void displayCreaturesInRoom(ArrayList<Creature> creatures) {
        int chickenCount = 0;
        int wumpusCount = 0;
        int popStarCount = 0;
        for (Creature c : creatures) {
            if (c instanceof Chicken && c.getCurrentRoom().equals(p.getCurrentRoom())) {
                chickenCount++;
            } else if (c instanceof Wumpus && c.getCurrentRoom().equals(p.getCurrentRoom())) {
                wumpusCount++;
            } else if (c instanceof PopStar && c.getCurrentRoom().equals(p.getCurrentRoom())) {
                popStarCount++;
            }
        }
        System.out.println("You have " + chickenCount + " chicken(s), " + wumpusCount + " Wumpuses, and " + popStarCount + " popStars in you room!");
    }

}
