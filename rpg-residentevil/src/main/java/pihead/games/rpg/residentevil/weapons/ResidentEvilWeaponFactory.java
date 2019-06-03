package pihead.games.rpg.residentevil.weapons;

import pihead.games.rpg.engine.domain.entities.items.Weapon;
import pihead.games.rpg.engine.domain.entities.items.WeaponType;

import java.util.HashMap;
import java.util.Map;

import static pihead.games.rpg.residentevil.weapons.ResidentEvilWeapon.*;

public class ResidentEvilWeaponFactory {

    private static Map<ResidentEvilWeapon, WeaponType> weapons = new HashMap<>();

    public static Weapon buildHandgun() {
        return Weapon.builder()
                .type(weapons.get(HANDGUN))
                .build();
    }

    public static Weapon buildAssaultShotgun() {
        return Weapon.builder()
                .type(weapons.get(ASSAULT_SHOTGUN))
                .build();
    }

    public static Weapon buildColtPython() {
        return Weapon.builder()
                .type(weapons.get(COLT_PYTHON))
                .build();
    }

    public static Weapon buildMagnum() {
        return Weapon.builder()
                .type(weapons.get(MAGNUM))
                .build();
    }


    static {

        WeaponType handgun = WeaponType.builder()
                .name("Handgun")
                .slots(1)
                .damage(2)
                .shots(10)
                .range(10)
                .build();
        weapons.put(HANDGUN, handgun);

        WeaponType assaultShotgun = WeaponType.builder()
                .name("Assault Shotgun")
                .slots(3)
                .damage(10)
                .shots(10)
                .range(5)
                .build();
        weapons.put(ASSAULT_SHOTGUN, assaultShotgun);

        WeaponType coltPython = WeaponType.builder()
                .name("Colt Python")
                .slots(1)
                .damage(5)
                .shots(10)
                .range(2)
                .build();
        weapons.put(COLT_PYTHON, coltPython);

        WeaponType magnum = WeaponType.builder()
                .name("Magnum")
                .slots(2)
                .damage(20)
                .shots(5)
                .range(5)
                .build();
        weapons.put(MAGNUM, magnum);

    }

}
