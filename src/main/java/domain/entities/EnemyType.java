package domain.entities;

public class EnemyType {

    private String name;
    private int velocity;
    private int damage;

    private EnemyType() {

    }

    public String getName() {
        return name;
    }

    public int getVelocity() {
        return velocity;
    }

    public int getDamage() {
        return damage;
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

        public Builder velocity(int velocity) {
            instance.velocity = velocity;
            return this;
        }

        public Builder damage(int damage) {
            instance.damage = damage;
            return this;
        }

        public EnemyType build() {
            return instance;
        }
    }
}
