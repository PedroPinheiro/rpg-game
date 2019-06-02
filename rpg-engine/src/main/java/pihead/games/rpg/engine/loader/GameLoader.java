package pihead.games.rpg.engine.loader;

import pihead.games.rpg.engine.domain.entities.Game;
import pihead.games.rpg.engine.domain.entities.GameType;
import pihead.games.rpg.engine.domain.entities.characters.Player;
import pihead.games.rpg.engine.domain.entities.characters.PlayerType;

import java.util.Collection;
import java.util.UUID;

public abstract class GameLoader {

    public Game loadGame(GameType gameType, PlayerType playerType, String playerName) {

        Game.Builder builder = Game.builder()
                .player(getPlayer(playerType, playerName))
                .type(gameType);

        for (StageLoader stageLoader : getStageLoaders()) {
            builder.addStage(stageLoader.getStage());
        }

        return builder.build();
    }

    private Player getPlayer(PlayerType playerType, String playerName) {
        return Player.builder()
                .type(playerType)
                .name(playerName)
                .build();
    }

    public GameType getGameType() {

        GameType.Builder builder = GameType.builder()
                .id(getGameUUID().toString())
                .name(getGameLabel());

        for (PlayerType playerType : getPlayerTypes()) {
            builder.addPlayerType(playerType);
        }

        return builder.build();
    }

    protected abstract Collection<StageLoader> getStageLoaders();

    protected abstract String getGameLabel();

    protected abstract UUID getGameUUID();

    protected abstract Collection<PlayerType> getPlayerTypes();

}
