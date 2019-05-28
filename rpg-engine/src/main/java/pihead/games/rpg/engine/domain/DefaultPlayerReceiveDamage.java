package pihead.games.rpg.engine.domain;

import pihead.games.rpg.engine.domain.entities.Room;
import pihead.games.rpg.engine.domain.entities.characters.Enemy;
import pihead.games.rpg.engine.domain.entities.characters.Player;
import pihead.games.rpg.engine.gateway.GetPlayerGateway;
import pihead.games.rpg.engine.gateway.GetRoomGateway;
import pihead.games.rpg.engine.gateway.UpdatePlayerGateway;

public class DefaultPlayerReceiveDamage implements PlayerReceiveDamage {

    private GetRoomGateway getRoomGateway;
    private GetPlayerGateway getPlayerGateway;
    private UpdatePlayerGateway updatePlayerGateway;

    public DefaultPlayerReceiveDamage(GetRoomGateway getRoomGateway,
                                      GetPlayerGateway getPlayerGateway,
                                      UpdatePlayerGateway updatePlayerGateway) {
        this.getRoomGateway = getRoomGateway;
        this.getPlayerGateway = getPlayerGateway;
        this.updatePlayerGateway = updatePlayerGateway;
    }

    @Override
    public void receiveDamage(RequestModel requestModel) {

        final Room room = getRoom(requestModel);
        final Player player = getPlayer(requestModel);

        room.getEnemies().stream()
                .filter(Enemy::canAttack)
                .forEach(enemy -> processEnemyDamage(enemy, player));

        updatePlayerGateway.updatePlayer(player);

    }

    private void processEnemyDamage(Enemy enemy, Player player) {
        player.receiveDamage(enemy.getType().getDamage());
    }

    private Room getRoom(RequestModel requestModel) {
        return getRoomGateway.getById(requestModel.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));
    }

    private Player getPlayer(RequestModel requestModel) {
        return getPlayerGateway.getById(requestModel.getPlayerId())
                .orElseThrow(() -> new RuntimeException("Player not found"));
    }

}
