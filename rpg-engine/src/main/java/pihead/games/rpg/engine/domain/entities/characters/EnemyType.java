package pihead.games.rpg.engine.domain.entities.characters;

public class EnemyType extends CharacterType {

    private int velocity;

    private EnemyType() {

    }

    public int getVelocity() {
        return velocity;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private EnemyType instance;

        private Builder() {
            this.instance = new EnemyType();
        }

        public Builder name(String name) {
            instance.name = name;
            return this;
        }

        public Builder maxHealth(int maxHealth) {
            instance.maxHealth = maxHealth;
            return this;
        }

        public Builder velocity(int velocity) {
            instance.velocity = velocity;
            return this;
        }

        public Builder damage(int damage) {
            instance.damage = damage;
            return this;
        }

        public Builder minAttackDistance(int minAttackDistance) {
            instance.minAttackDistance = Math.max(minAttackDistance, 0);
            return this;
        }

        public EnemyType build() {
            return instance;
        }
    }
}
