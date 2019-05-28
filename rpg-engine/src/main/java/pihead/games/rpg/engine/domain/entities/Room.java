package pihead.games.rpg.engine.domain.entities;

import pihead.games.rpg.engine.domain.entities.characters.Enemy;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private Integer id;
    private String name;
    private String description;
    private List<Enemy> enemies;
    private List<RoomSide> sides;
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

    public List<RoomSide> getSides() {
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

        protected Room instance;

        protected Builder() {
            instance = new Room();
            instance.enemies = new ArrayList<>();
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

        public Room build() {
            if (instance.id == null) {
                throw new IllegalArgumentException("Room Id could not be null.");
            }
            return instance;
        }
    }
}
