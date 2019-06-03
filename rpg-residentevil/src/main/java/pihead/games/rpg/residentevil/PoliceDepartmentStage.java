package pihead.games.rpg.residentevil;

import pihead.games.rpg.engine.domain.entities.Room;
import pihead.games.rpg.engine.domain.entities.RoomSide;
import pihead.games.rpg.engine.domain.entities.Stage;
import pihead.games.rpg.engine.loader.StageLoader;

import static pihead.games.rpg.residentevil.enemies.ResidentEvilEnemyFactory.*;
import static pihead.games.rpg.residentevil.healthItems.ResidentEvilHealthItemFactory.*;
import static pihead.games.rpg.residentevil.weapons.ResidentEvilWeaponFactory.*;

public class PoliceDepartmentStage implements StageLoader {

    public Stage getStage() {
        return Stage.builder()
                .name("First Stage")
                .addFirstRoom(getHallRoom1())
                .build();
    }

    private Room getHallRoom1() {
        return Room.builder()
                .addItem(RoomSide.Direction.FRONT, buildHandgun())
                .addNextRoom(RoomSide.Direction.FRONT, getHallRoom2())
                .build();
    }

    private Room getHallRoom2() {
        return Room.builder()
                .addEnemy(buildZombieLevel1(5))
                .addItem(RoomSide.Direction.LEFT, buildGreenPlant())
                .addNextRoom(RoomSide.Direction.RIGHT, getOfficeRoom1())
                .addNextRoom(RoomSide.Direction.FRONT, getHallRoom3())
                .build();
    }

    private Room getOfficeRoom1() {
        return Room.builder()
                .addEnemy(buildZombieLevel1(3))
                .addEnemy(buildZombieLevel1(3))
                .addItem(RoomSide.Direction.LEFT, buildGreenPlant())
                .addItem(RoomSide.Direction.RIGHT, buildAssaultShotgun())
                .build();
    }

    private Room getHallRoom3() {
        return Room.builder()
                .addItem(RoomSide.Direction.LEFT, buildBluePlant())
                .addNextRoom(RoomSide.Direction.FRONT, getHallRoom4())
                .build();
    }

    private Room getHallRoom4() {
        return Room.builder()
                .addItem(RoomSide.Direction.RIGHT, buildGreenPlant())
                .addNextRoom(RoomSide.Direction.FRONT, getHallRoom5())
                .addNextRoom(RoomSide.Direction.RIGHT, getOfficeRoom2())
                .build();
    }

    private Room getOfficeRoom2() {
        return Room.builder()
                .addEnemy(buildZombieLevel1(5))
                .addEnemy(buildZombieLevel2(7))
                .addEnemy(buildZombieLevel2(8))
                .addEnemy(buildZombieLevel3(10))
                .addItem(RoomSide.Direction.RIGHT, buildColtPython())
                .build();
    }

    private Room getHallRoom5() {
        return Room.builder()
                .addEnemy(buildZombieLevel3(7))
                .addEnemy(buildZombieLevel3(7))
                .addItem(RoomSide.Direction.RIGHT, buildMagnum())
                .addNextRoom(RoomSide.Direction.RIGHT, getHallRoom6())
                .build();
    }

    private Room getHallRoom6() {
        return Room.builder()
                .addEnemy(buildZombieLevel2(5))
                .addEnemy(buildZombieLevel2(6))
                .addEnemy(buildZombieLevel3(8))
                .addItem(RoomSide.Direction.LEFT, buildMagnum())
                .addItem(RoomSide.Direction.LEFT, buildBluePlant())
                .addItem(RoomSide.Direction.LEFT, buildRepairKit())
                .addItem(RoomSide.Direction.RIGHT, buildMagnum())
                .addNextRoom(RoomSide.Direction.RIGHT, getHallRoom7())
                .build();
    }

    private Room getHallRoom7() {
        return Room.builder()
                .addEnemy(buildNemesis(10))
                .build();
    }

}
