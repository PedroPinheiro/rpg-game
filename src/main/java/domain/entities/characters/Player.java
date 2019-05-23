package domain.entities.characters;

import domain.entities.backpack.Backpack;

public class Player extends Character {

    private String name;
    private int maxHealth;
    private Backpack backpack;

    private Player() {

    }

    public String getName() {
        return name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void heal(int value) {
        this.health = Math.min(health + value, maxHealth);
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

        public Builder maxHealth(int maxHealth) {
            player.maxHealth = maxHealth;
            return this;
        }

        public Player build() {

            return player;
        }
    }
}
