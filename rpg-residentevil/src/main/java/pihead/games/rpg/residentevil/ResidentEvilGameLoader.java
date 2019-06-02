package pihead.games.rpg.residentevil;

import pihead.games.rpg.engine.domain.entities.characters.PlayerType;
import pihead.games.rpg.engine.loader.GameLoader;
import pihead.games.rpg.engine.loader.StageLoader;
import pihead.games.rpg.residentevil.stages.first.PoliceDepartmentStage;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

public class ResidentEvilGameLoader extends GameLoader {

    private static final String TITLE_PATH = "resident-evil.txt";

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
    protected Collection<PlayerType> getPlayerTypes() {
        return new ArrayList<PlayerType>() {{
            add(buildLeon());
            add(buildClaire());
        }};
    }

    @Override
    public InputStream getTitle() {
        return this.getClass().getClassLoader().getResourceAsStream(TITLE_PATH);
    }

    private PlayerType buildLeon() {
        return PlayerType.builder()
                .name("Leon")
                .maxHealth(120)
                .damage(10)
                .build();
    }

    private PlayerType buildClaire() {
        return PlayerType.builder()
                .name("Claire")
                .maxHealth(100)
                .minAttackDistance(3)
                .damage(9)
                .build();
    }

}
