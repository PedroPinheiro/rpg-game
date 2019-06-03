package pihead.games.rpg.engine.domain;

import pihead.games.rpg.engine.domain.entities.GameType;
import pihead.games.rpg.engine.domain.entities.characters.PlayerType;
import pihead.games.rpg.engine.gateway.GetGameTypeGateway;

public class DefaultListPlayerTypes implements ListPlayerTypes {

    private GetGameTypeGateway getGameTypeGateway;

    public DefaultListPlayerTypes(GetGameTypeGateway getGameTypeGateway) {
        this.getGameTypeGateway = getGameTypeGateway;
    }

    @Override
    public ResponseModel list(RequestModel requestModel) {

        GameType gameType = getGameType(requestModel);

        ResponseModel.Builder builder = ResponseModel.builder();

        for (PlayerType playerType : gameType.getPlayerTypes()) {
            builder.addPlayerType(playerType.getId(), playerType.getName(), playerType.getMaxHealth());
        }

        return builder.build();
    }

    private GameType getGameType(RequestModel requestModel) {
        return getGameTypeGateway.findById(requestModel.getGameTypeId())
                .orElseThrow(() -> new RuntimeException("Game Type not found"));
    }

}
