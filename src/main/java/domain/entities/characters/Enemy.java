package domain.entities.characters;

public class Enemy extends Character {

    private EnemyType type;
    private int distance;

    private Enemy() {

    }

    public EnemyType getType() {
        return type;
    }

    public int getDistance() {
        return distance;
    }

    public boolean canAttack() {
        return distance <= type.getMinAttackDistance();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Enemy enemy;

        private Builder() {
            enemy = new Enemy();
        }

        public Builder type(EnemyType type) {
            enemy.type = type;
            return this;
        }

        public Builder health(int health) {
            enemy.health = health;
            return this;
        }

        public Builder distance(int distance) {
            enemy.distance = distance;
            return this;
        }

        public Enemy build() {
            return enemy;
        }
    }
}
