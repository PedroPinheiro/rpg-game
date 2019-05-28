package pihead.games.rpg.engine.domain.entities;

import pihead.games.rpg.engine.domain.entities.items.Item;

import java.util.List;

public class RoomSide {

    private Direction side;
    private List<Item> items;
    private Room nextRoom;

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

    public Room getNextRoom() {
        return nextRoom;
    }
}
