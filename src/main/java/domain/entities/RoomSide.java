package domain.entities;

import java.util.List;

public class RoomSide {

    private Direction side;
    private List<Item> items;

    public RoomSide(Direction side, List<Item> items) {
        this.side = side;
        this.items = items;
    }

    public enum Direction {
        LEFT, RIGHT, FRONT
    }

    public Direction getSide() {
        return side;
    }

    public List<Item> getItems() {
        return items;
    }
}
