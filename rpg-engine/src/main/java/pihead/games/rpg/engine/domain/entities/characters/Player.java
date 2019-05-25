package pihead.games.rpg.engine.domain.entities.characters;

import pihead.games.rpg.engine.domain.entities.backpack.Backpack;

public class Player extends Character {

    private String name;
    private Backpack backpack;
    private PlayerType type;

    private Player() {

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

        private Player player;

        private Builder() {
            player = new Player();
        }

        public Builder name(String name) {
            player.name = name;
            return this;
        }

        public Builder health(int health) {
            player.health = health;
            return this;
        }

        public Builder type(PlayerType type) {
            player.type = type;
            return this;
        }

        public Player build() {

            return player;
        }
    }
}
