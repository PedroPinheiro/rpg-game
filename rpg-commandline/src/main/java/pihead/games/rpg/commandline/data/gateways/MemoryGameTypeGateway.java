package pihead.games.rpg.commandline.data.gateways;

import pihead.games.rpg.commandline.data.repositories.GameTypeRepository;
import pihead.games.rpg.engine.domain.entities.GameType;
import pihead.games.rpg.engine.domain.entities.characters.PlayerType;
import pihead.games.rpg.engine.gateway.GetGameTypeGateway;
import pihead.games.rpg.engine.gateway.ListGameTypeGateway;
import pihead.games.rpg.engine.loader.GameLoader;
import pihead.games.rpg.residentevil.ResidentEvilGameLoader;

import java.util.*;

public class MemoryGameTypeGateway implements ListGameTypeGateway, GetGameTypeGateway {

    private GameTypeRepository gameTypeRepository;

    public MemoryGameTypeGateway(GameTypeRepository gameTypeRepository) {
        this.gameTypeRepository = gameTypeRepository;
    }

    @Override
    public Collection<GameType> findAll() {
        return gameTypeRepository.findAll();
    }

    @Override
    public Optional<GameType> findById(String gameTypeId) {
        GameType gameType = gameTypeRepository.findById(gameTypeId);
        return Optional.ofNullable(gameType);
    }
}
