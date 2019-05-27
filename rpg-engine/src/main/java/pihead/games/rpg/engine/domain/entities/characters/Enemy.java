package pihead.games.rpg.engine.domain.entities.characters;

public class Enemy extends Character {

    private Integer id;
    private EnemyType type;
    private int distance;

    public int getId() {
        return id;
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

    public void progressDistance() {
        this.distance = Math.max(distance - 1, 0);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Enemy instance;

        private Builder() {
            instance = new Enemy();
        }

        public Builder id(int id) {
            instance.id = id;
            return this;
        }

        public Builder type(EnemyType type) {
            instance.type = type;
            return this;
        }

        public Builder health(int health) {
            instance.health = health;
            return this;
        }

        public Builder distance(int distance) {
            instance.distance = distance;
            return this;
        }

        public Enemy build() {
            return instance;
        }
    }
}
