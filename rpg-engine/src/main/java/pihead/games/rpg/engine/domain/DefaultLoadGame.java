package pihead.games.rpg.engine.domain;

import pihead.games.rpg.engine.domain.entities.Game;
import pihead.games.rpg.engine.domain.entities.GameType;
import pihead.games.rpg.engine.domain.entities.characters.PlayerType;
import pihead.games.rpg.engine.gateway.GetGameLoaderGateway;
import pihead.games.rpg.engine.gateway.GetGameTypeGateway;
import pihead.games.rpg.engine.gateway.GetPlayerTypeGateway;
import pihead.games.rpg.engine.gateway.SaveGameGateway;
import pihead.games.rpg.engine.loader.GameLoader;

public class DefaultLoadGame implements LoadGame {

    private GetGameTypeGateway getGameTypeGateway;
    private GetPlayerTypeGateway getPlayerTypeGateway;
    private GetGameLoaderGateway getGameLoaderGateway;
    private SaveGameGateway saveGameGateway;

    public DefaultLoadGame(GetGameTypeGateway getGameTypeGateway,
                           GetPlayerTypeGateway getPlayerTypeGateway,
                           GetGameLoaderGateway getGameLoaderGateway,
                           SaveGameGateway saveGameGateway) {
        this.getGameTypeGateway = getGameTypeGateway;
        this.getPlayerTypeGateway = getPlayerTypeGateway;
        this.getGameLoaderGateway = getGameLoaderGateway;
        this.saveGameGateway = saveGameGateway;
    }

    @Override
    public ResponseModel load(RequestModel requestModel) {

        GameType gameType = getGameType(requestModel);
        PlayerType playerType = getPlayerType(requestModel);
        GameLoader gameLoader = getGameLoader(requestModel);
        String playerName = requestModel.getPlayerName();

        Game game = gameLoader.loadGame(gameType, playerType, playerName);

        Game savedGame = saveGameGateway.save(game);

        return ResponseModel.builder()
                .gameId(savedGame.getId())
                .build();
    }

    private GameType getGameType(RequestModel requestModel) {
        return getGameTypeGateway.getById(requestModel.getGameTypeId())
                .orElseThrow(() -> new RuntimeException("Game Type not found"));
    }

    private PlayerType getPlayerType(RequestModel requestModel) {
        return getPlayerTypeGateway.getById(requestModel.getPlayerTypeId())
                .orElseThrow(() -> new RuntimeException("Player Type not found"));
    }

    private GameLoader getGameLoader(RequestModel requestModel) {
        return getGameLoaderGateway.getByGameTypeId(requestModel.getGameTypeId())
                .orElseThrow(() -> new RuntimeException("Game Loader not found"));
    }
}
