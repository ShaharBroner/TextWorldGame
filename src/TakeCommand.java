import java.util.ArrayList;

public class TakeCommand implements Command {
    final static private int INDEX_OF_ITEM_NAME = 5;
    private Player p;
    private String item;

    public TakeCommand(Player p) {
        this.p = p;
    }

    @Override
    public void init(String userString) {
        item = userString.substring(INDEX_OF_ITEM_NAME);
    }

    @Override
    public boolean execute() {
        ArrayList<Item> items = p.getCurrentRoom().getItems();
        for (Item i : items) {
            if (item.equals(i.getName())) {
                p.addItem(p.getCurrentRoom().removeItem(item));
                System.out.println("You took: " + item);
                return true;
            }
        }
        return false;
    }
}
