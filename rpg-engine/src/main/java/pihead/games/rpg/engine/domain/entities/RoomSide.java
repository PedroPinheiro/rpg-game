package pihead.games.rpg.engine.domain.entities;

import pihead.games.rpg.engine.domain.entities.items.Item;

import java.util.ArrayList;
import java.util.List;

public class RoomSide {

    private Integer id;
    private Direction direction;
    private List<Item> items;
    private Room nextRoom;

    private RoomSide() {

    }

    public enum Direction {
        LEFT, RIGHT, FRONT
    }

    public int getId() {
        return id;
    }

    public Direction getDirection() {
        return direction;
    }

    public List<Item> getItems() {
        return items;
    }

    public Room getNextRoom() {
        return nextRoom;
    }


    // Builder

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private RoomSide instance;

        protected Builder() {
            instance = new RoomSide();
            instance.items = new ArrayList<>();
        }

        public Builder id(int id) {
            instance.id = id;
            return this;
        }

        public Builder direction(Direction direction) {
            instance.direction = direction;
            return this;
        }

        public Builder addItem(Item item) {
            instance.items.add(item);
            return this;
        }

        public Builder nextRoom(Room nextRoom) {
            instance.nextRoom = nextRoom;
            return this;
        }

        public RoomSide build() {
            return instance;
        }
    }
}
