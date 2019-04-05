
public abstract class Creature {
    protected Level.Room currentRoom;

    public Creature(Level.Room currentRoom) {
        this.currentRoom = currentRoom;
    }


    public abstract void move();

    public Level.Room getCurrentRoom() {
        return currentRoom;
    }

    protected void setCurrentRoom(Level.Room currentRoom) {
        this.currentRoom = currentRoom;
    }
}
