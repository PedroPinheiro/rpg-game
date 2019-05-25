package pihead.games.rpg.engine.domain.entities.characters;

public class PlayerType extends CharacterType {


    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        protected PlayerType instance;

        protected Builder() {
            instance = new PlayerType();
        }

        public Builder name(String name) {
            instance.name = name;
            return this;
        }

        public Builder maxHealth(int maxHealth) {
            instance.maxHealth = maxHealth;
            return this;
        }

        public Builder damage(int damage) {
            instance.damage = damage;
            return this;
        }

        public Builder minAttackDistance(int minAttackDistance) {
            instance.minAttackDistance = minAttackDistance;
            return this;
        }

        public PlayerType build() {
            return instance;
        }
    }
}
