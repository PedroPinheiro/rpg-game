package pihead.games.rpg.residentevil.stages.first;

import pihead.games.rpg.engine.domain.entities.Room;
import pihead.games.rpg.engine.domain.entities.Stage;
import pihead.games.rpg.engine.loader.StageLoader;

public class PoliceDepartmentStage implements StageLoader {

    public Stage getStage() {
        return Stage.builder()
                .name("First Stage")
                .addFirstRoom(getRoom())
                .build();
    }

    private Room getRoom() {
        return Room.builder().build();
    }

}
