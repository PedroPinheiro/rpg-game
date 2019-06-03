package pihead.games.rpg.residentevil;

import pihead.games.rpg.engine.domain.entities.characters.PlayerType;
import pihead.games.rpg.engine.loader.GameLoader;
import pihead.games.rpg.engine.loader.StageLoader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class ResidentEvilGameLoader extends GameLoader {

    private PoliceDepartmentStage policeDepartmentStage = new PoliceDepartmentStage();

    @Override
    protected Collection<StageLoader> getStageLoaders() {
        return new ArrayList<StageLoader>() {{
            add(policeDepartmentStage);
        }};
    }

    @Override
    protected String getGameLabel() {
        return "Resident Evil";
    }

    @Override
    protected UUID getGameUUID() {
        return Config.RESIDENT_EVIL_UUID;
    }

    @Override
    protected Collection<PlayerType> getPlayerTypes() {
        return new ArrayList<PlayerType>() {{
            add(buildLeon());
            add(buildClaire());
        }};
    }

    private PlayerType buildLeon() {
        return PlayerType.builder()
                .id(1)
                .name("Leon")
                .maxHealth(120)
                .damage(10)
                .build();
    }

    private PlayerType buildClaire() {
        return PlayerType.builder()
                .id(2)
                .name("Claire")
                .maxHealth(100)
                .minAttackDistance(3)
                .damage(9)
                .build();
    }

}
