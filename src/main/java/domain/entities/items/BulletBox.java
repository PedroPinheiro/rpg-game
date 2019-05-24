package domain.entities.items;

public class BulletBox extends ItemType {

    private int bullets;
    private WeaponType weaponType;

    public int getBullets() {
        return bullets;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    // Builder

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        protected BulletBox instance;

        protected Builder() {
            instance = new BulletBox();
        }

        public Builder name(String name) {
            instance.name = name;
            return this;
        }

        public Builder slots(int slots) {
            instance.slots = slots;
            return this;
        }

        public Builder bullets(int bullets) {
            instance.bullets = bullets;
            return this;
        }

        public Builder weaponType(WeaponType weaponType) {
            instance.weaponType = weaponType;
            return this;
        }

        public BulletBox build() {
            return instance;
        }
    }
}
