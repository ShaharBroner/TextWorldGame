import java.util.ArrayList;

public class Player {
    String name, description;
    ArrayList<Item> items;
    Level.Room currentRoom;

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

    public boolean moveToRoom(String name){
        
    }
}
