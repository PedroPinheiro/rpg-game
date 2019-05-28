package pihead.games.rpg.engine.domain;

import pihead.games.rpg.engine.domain.entities.characters.Player;
import pihead.games.rpg.engine.domain.exceptions.PlayerIsNotCarringWeapon;
import pihead.games.rpg.engine.gateway.GetPlayerGateway;

public class DefaultAttackOnRoom implements AttackOnRoom {

    private GetPlayerGateway getPlayerGateway;
    private CalculateEnemiesDistances calculateEnemiesDistances;
    private AttackEnemy attackEnemy;
    private PlayerReceiveDamage playerReceiveDamage;

    public DefaultAttackOnRoom(GetPlayerGateway getPlayerGateway,
                               CalculateEnemiesDistances calculateEnemiesDistances,
                               AttackEnemy attackEnemy,
                               PlayerReceiveDamage playerReceiveDamage) {
        this.getPlayerGateway = getPlayerGateway;
        this.calculateEnemiesDistances = calculateEnemiesDistances;
        this.attackEnemy = attackEnemy;
        this.playerReceiveDamage = playerReceiveDamage;
    }

    @Override
    public ResponseModel attack(RequestModel requestModel) throws PlayerIsNotCarringWeapon {

        final Player player = getPlayer(requestModel);

        attackEnemy(requestModel, player);

        calculateEnemiesDistances.calculate(requestModel.getRoomId());

        playerReceiveDamage(requestModel);

        return null;
    }

    private void attackEnemy(RequestModel requestModel, Player player) throws PlayerIsNotCarringWeapon {

        if (!player.isCarryingWeapon()) {
            throw new PlayerIsNotCarringWeapon();
        }

        attackEnemy.attack(player.getWeapon().getId(),
                requestModel.getChosenEnemyId());
    }

    private void playerReceiveDamage(RequestModel requestModel) {
        playerReceiveDamage.receiveDamage(requestModel.getGameId(),
                requestModel.getPlayerId(),
                requestModel.getRoomId());
    }

    private Player getPlayer(RequestModel requestModel) {
        return getPlayerGateway.getById(requestModel.getPlayerId())
                .orElseThrow(() -> new RuntimeException("Player not found"));
    }
}
