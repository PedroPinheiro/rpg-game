package pihead.games.rpg.engine.domain;

import pihead.games.rpg.engine.domain.entities.Room;
import pihead.games.rpg.engine.domain.entities.characters.Enemy;
import pihead.games.rpg.engine.gateway.GetRoomGateway;
import pihead.games.rpg.engine.gateway.UpdateEnemyGateway;

public class DefaultCalculateEnemiesDistances implements CalculateEnemiesDistances {

    private GetRoomGateway getRoomGateway;
    private UpdateEnemyGateway updateEnemyGateway;

    public DefaultCalculateEnemiesDistances(GetRoomGateway getRoomGateway,
                                            UpdateEnemyGateway updateEnemyGateway) {
        this.getRoomGateway = getRoomGateway;
        this.updateEnemyGateway = updateEnemyGateway;
    }

    @Override
    public void calculate(RequestModel requestModel) {

        final Room room = getRoom(requestModel);

        room.getEnemies().stream()
                .filter(Enemy::isAlive)
                .forEach(this::calculateEnemyDistance);
    }

    private void calculateEnemyDistance(Enemy enemy) {
        enemy.progressDistance();
        updateEnemyGateway.updateEnemy(enemy);
    }

    private Room getRoom(RequestModel requestModel) {
        return getRoomGateway.getById(requestModel.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));
    }
}
