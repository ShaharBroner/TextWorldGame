
public abstract class Creature {
    Level.Room currentRoom;

    public Creature(Level.Room currentRoom) {
        this.currentRoom = currentRoom;
    }


    public abstract void move();

    public Level.Room getCurrentRoom() {
        return currentRoom;
    }

    private void setCurrentRoom(Level.Room currentRoom) {
        this.currentRoom = currentRoom;
    }
}
