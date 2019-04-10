import java.util.ArrayList;

public class DropCommand implements Command {
    final static private int INDEX_OF_ITEM_NAME = 5;
    private Player p;
    private String item;

    public DropCommand(Player p) {
        this.p = p;
    }

    @Override
    public void init(String userString) {
        item = userString.substring(INDEX_OF_ITEM_NAME);
    }

    @Override
    public boolean execute() {
        ArrayList<Item> items = p.getItems();
        for (Item i : items) {
            if (item.equals(i.getName())) {
                p.getCurrentRoom().addItem(p.removeItem(item));
                System.out.println("You dropped: " + item);
                return true;
            }
        }
        return false;
    }
}
