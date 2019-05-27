package pihead.games.rpg.engine.domain.entities.items;

public class Weapon implements Item {

    private int id;
    private WeaponType type;
    private int shots;

    protected Weapon() {

    }

    public int getId() {
        return id;
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

    public void reload(int bullets) {
        if (type.isFireGun()) {
            this.shots = Math.min(shots + bullets, type.getShots());
        }
    }

    // Builder

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        protected Weapon instance;

        protected Builder() {
            instance = new Weapon();
        }

        public Builder id(int id) {
            instance.id = id;
            return this;
        }

        public Builder type(WeaponType type) {
            instance.type = type;
            return this;
        }

        public Weapon build() {
            if (instance.type == null) {
                throw new IllegalArgumentException("Weapon Type could not be null.");
            }
            instance.shots = instance.type.getShots();
            return instance;
        }
    }
}
