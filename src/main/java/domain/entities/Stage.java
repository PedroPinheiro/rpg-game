package domain.entities;

public class Stage {

    private Room firstRoom;
    private Stage next;

    public Stage(Room firstRoom, Stage next) {
        this.firstRoom = firstRoom;
        this.next = next;
    }

    public Room getFirstRoom() {
        return firstRoom;
    }

    public Stage getNext() {
        return next;
    }
}
