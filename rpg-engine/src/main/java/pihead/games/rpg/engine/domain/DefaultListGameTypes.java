package pihead.games.rpg.engine.domain;

import pihead.games.rpg.engine.domain.entities.GameType;
import pihead.games.rpg.engine.gateway.GetGameTypeGateway;

import java.util.List;

public class DefaultListGameTypes implements ListGameTypes {

    private GetGameTypeGateway getGameTypeGateway;

    public DefaultListGameTypes(GetGameTypeGateway getGameTypeGateway){
        this.getGameTypeGateway = getGameTypeGateway;
    }

    @Override
    public ResponseModel list() {

        List<GameType> gameTypes = getGameTypeGateway.findAll();

        ResponseModel.Builder builder = ResponseModel.builder();

        for (GameType gameType : gameTypes) {
            builder.addGameType(gameType.getId(), gameType.getName(), gameType.getDescription());
        }

        return builder.build();
    }
}
