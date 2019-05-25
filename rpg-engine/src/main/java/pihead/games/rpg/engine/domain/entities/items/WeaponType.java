package pihead.games.rpg.engine.domain.entities.items;

public class WeaponType extends ItemType {

    private int damage;
    private int range;
    private int shots;

    public int getDamage() {
        return damage;
    }

    public int getRange() {
        return range;
    }

    public int getShots() {
        return shots;
    }


    public boolean isFireGun() {
        return shots > 0;
    }


    // Builder

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        protected WeaponType instance;

        protected Builder() {
            instance = new WeaponType();
        }

        public Builder name(String name) {
            instance.name = name;
            return this;
        }

        public Builder slots(int slots) {
            instance.slots = slots;
            return this;
        }

        public Builder damage(int damage) {
            instance.damage = damage;
            return this;
        }

        public Builder range(int range) {
            instance.range = range;
            return this;
        }

        public Builder shots(int shots) {
            instance.shots = shots;
            return this;
        }

        public WeaponType build() {
            return instance;
        }
    }
}
