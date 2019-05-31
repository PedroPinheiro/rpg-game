package pihead.games.rpg.engine.domain;

import pihead.games.rpg.engine.domain.entities.GameType;
import pihead.games.rpg.engine.gateway.ListGameTypeGateway;

import java.util.Collection;

public class DefaultListGameTypes implements ListGameTypes {

    private ListGameTypeGateway listGameTypeGateway;

    public DefaultListGameTypes(ListGameTypeGateway listGameTypeGateway){
        this.listGameTypeGateway = listGameTypeGateway;
    }

    @Override
    public ResponseModel list() {

        Collection<GameType> gameTypes = listGameTypeGateway.findAll();

        ResponseModel.Builder builder = ResponseModel.builder();

        for (GameType gameType : gameTypes) {
            builder.addGameType(gameType.getId(), gameType.getName(), gameType.getDescription());
        }

        return builder.build();
    }
}
