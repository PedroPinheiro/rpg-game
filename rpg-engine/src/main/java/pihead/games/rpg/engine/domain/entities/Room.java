package pihead.games.rpg.engine.domain.entities;

import pihead.games.rpg.engine.domain.entities.characters.Enemy;
import pihead.games.rpg.engine.domain.entities.items.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room {

    private Integer id;
    private String name;
    private String description;
    private List<Enemy> enemies;
    private Map<RoomSide.Direction, RoomSide> sides;
    private List<Room> next;
    private Room previous;

    private Room() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public Map<RoomSide.Direction, RoomSide> getSides() {
        return sides;
    }

    public List<Room> getNext() {
        return next;
    }

    public Room getPrevious() {
        return previous;
    }


    // Builder

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Room instance;
        private Map<RoomSide.Direction, RoomSide.Builder> sides;

        private Builder() {
            instance = new Room();
            instance.enemies = new ArrayList<>();
            sides = new HashMap<>();
            addRoomSide(RoomSide.Direction.LEFT);
            addRoomSide(RoomSide.Direction.RIGHT);
            addRoomSide(RoomSide.Direction.FRONT);
        }

        private void addRoomSide(RoomSide.Direction direction) {
            RoomSide.Builder roomSideBuilder = RoomSide.builder()
                    .direction(direction);
            sides.put(direction, roomSideBuilder);
        }

        public Builder id(int id) {
            instance.id = id;
            return this;
        }

        public Builder name(String name) {
            instance.name = name;
            return this;
        }

        public Builder description(String description) {
            instance.description = description;
            return this;
        }

        public Builder addEnemy(Enemy enemy) {
            instance.enemies.add(enemy);
            return this;
        }

        public Builder addItem(RoomSide.Direction direction, Item item) {
            sides.get(direction).addItem(item);
            return this;
        }

        public Builder addNextRoom(RoomSide.Direction direction, Room room) {
            sides.get(direction).nextRoom(room);
            return this;
        }

        public Room build() {
            if (instance.id == null) {
                throw new IllegalArgumentException("Room Id could not be null.");
            }

            // add sides of the room
            instance.sides = new HashMap<>();
            sides.forEach((direction, roomSideBuilder) ->
                instance.sides.put(direction, roomSideBuilder.build())
            );

            return instance;
        }
    }
}
