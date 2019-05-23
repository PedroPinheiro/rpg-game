package domain.entities;

import domain.entities.characters.Enemy;

import java.util.List;

public class Room {

    private String name;
    private String description;
    private List<Enemy> enemies;
    private List<RoomSide> sides;
    private List<Room> next;
    private Room previous;

    public Room(String name, String description, List<Enemy> enemies, List<RoomSide> sides, List<Room> next, Room previous) {
        this.name = name;
        this.description = description;
        this.enemies = enemies;
        this.sides = sides;
        this.next = next;
        this.previous = previous;
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
}
