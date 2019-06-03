package pihead.games.rpg.residentevil.healthItems;

import pihead.games.rpg.engine.domain.entities.characters.Enemy;
import pihead.games.rpg.engine.domain.entities.characters.EnemyType;
import pihead.games.rpg.engine.domain.entities.items.HealthItem;
import pihead.games.rpg.engine.domain.entities.items.HealthItemType;

import java.util.HashMap;
import java.util.Map;

import static pihead.games.rpg.residentevil.healthItems.ResidentEvilHealthItem.*;

public class ResidentEvilHealthItemFactory {

    private static Map<ResidentEvilHealthItem, HealthItemType> healthItems = new HashMap<>();

    public static HealthItem buildGreenPlant() {
        return HealthItem.builder()
                .type(healthItems.get(GREEN_PLANT))
                .build();
    }

    public static HealthItem buildBluePlant() {
        return HealthItem.builder()
                .type(healthItems.get(BLUE_PLANT))
                .build();
    }

    public static HealthItem buildRepairKit() {
        return HealthItem.builder()
                .type(healthItems.get(REPAIR_KIT))
                .build();
    }

    static {

        HealthItemType greenPlant = HealthItemType.builder()
                .healthPower(5)
                .name("Green Plant")
                .slots(1)
                .build();
        healthItems.put(GREEN_PLANT, greenPlant);

        HealthItemType bluePlant = HealthItemType.builder()
                .healthPower(10)
                .name("Blue Plant")
                .slots(1)
                .build();
        healthItems.put(BLUE_PLANT, bluePlant);

        HealthItemType repairKit = HealthItemType.builder()
                .healthPower(50)
                .name("Repair Kit")
                .slots(2)
                .build();
        healthItems.put(REPAIR_KIT, repairKit);


    }

}
