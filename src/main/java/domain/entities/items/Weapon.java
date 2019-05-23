package domain.entities.items;

public class Weapon implements Item {

    private WeaponType type;
    private int shots;

    protected Weapon() {

    }

    public int getShots() {
        return shots;
    }

    public WeaponType getType() {
        return type;
    }

    public String getName() {
        return type.getName();
    }

    public int getDamage() {
        return type.getDamage();
    }

    public void shoot() {
        if (canShoot() && type.isFireGun()) {
            this.shots--;
        }
    }

    public boolean canShoot() {
        return this.shots > 0;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        protected Weapon weapon;

        protected Builder() {
            weapon = new Weapon();
        }

        public Builder shots(int shots) {
            weapon.shots = shots;
            return this;
        }

        public Builder type(WeaponType type) {
            weapon.type = type;
            return this;
        }

        public Weapon build() {
            return weapon;
        }
    }
}
