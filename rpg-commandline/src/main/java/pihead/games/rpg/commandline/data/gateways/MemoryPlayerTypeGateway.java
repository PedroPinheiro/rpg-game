package pihead.games.rpg.commandline.data.gateways;

import pihead.games.rpg.commandline.data.repositories.PlayerTypeRepository;
import pihead.games.rpg.engine.domain.entities.characters.PlayerType;
import pihead.games.rpg.engine.gateway.GetPlayerTypeGateway;

import java.util.Optional;

public class MemoryPlayerTypeGateway implements GetPlayerTypeGateway {

    private PlayerTypeRepository playerTypeRepository;

    public MemoryPlayerTypeGateway(PlayerTypeRepository playerTypeRepository) {
        this.playerTypeRepository = playerTypeRepository;
    }

    @Override
    public Optional<PlayerType> getById(int playerTypeId) {

        PlayerType playerType = playerTypeRepository.findById(playerTypeId);
        return Optional.ofNullable(playerType);

    }
}
