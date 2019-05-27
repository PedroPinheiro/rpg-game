package pihead.games.rpg.engine.domain.entities.characters;

import pihead.games.rpg.engine.domain.entities.backpack.Backpack;

public class Player extends Character {

    private Integer id;
    private String name;
    private Backpack backpack;
    private PlayerType type;

    private Player() {

    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Backpack getBackpack() {
        return backpack;
    }

    public PlayerType getType() {
        return type;
    }

    public void heal(int value) {
        this.health = Math.min(health + value, type.getMaxHealth());
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Player instance;

        private Builder() {
            instance = new Player();
        }

        public Builder id(int id) {
            instance.id = id;
            return this;
        }

        public Builder name(String name) {
            instance.name = name;
            return this;
        }

        public Builder health(int health) {
            instance.health = health;
            return this;
        }

        public Builder type(PlayerType type) {
            instance.type = type;
            return this;
        }

        public Player build() {
            if (instance.id == null) {
                throw new IllegalArgumentException("Player Id could not be null.");
            }
            if (instance.type == null) {
                throw new IllegalArgumentException("Player type could not be null.");
            }
            if (instance.health == null) {
                instance.health = instance.type.getMaxHealth();
            } else {
                instance.health = Math.min(instance.health, instance.type.getMaxHealth());
            }
            return instance;
        }
    }
}
