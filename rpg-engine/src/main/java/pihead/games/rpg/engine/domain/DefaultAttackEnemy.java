package pihead.games.rpg.engine.domain;

import pihead.games.rpg.engine.domain.entities.Game;
import pihead.games.rpg.engine.domain.entities.characters.Enemy;
import pihead.games.rpg.engine.domain.entities.items.Weapon;
import pihead.games.rpg.engine.gateway.*;

public class DefaultAttackEnemy implements AttackEnemy {

    private GetGameGateway getGameGateway;
    private GetWeaponGateway getWeaponGateway;
    private GetEnemyGateway getEnemyGateway;
    private UpdateWeaponGateway updateWeaponGateway;
    private UpdateEnemyGateway updateEnemyGateway;

    public DefaultAttackEnemy(GetGameGateway getGameGateway,
                              GetWeaponGateway getWeaponGateway,
                              GetEnemyGateway getEnemyGateway,
                              UpdateWeaponGateway updateWeaponGateway,
                              UpdateEnemyGateway updateEnemyGateway) {
        this.getGameGateway = getGameGateway;
        this.getWeaponGateway = getWeaponGateway;
        this.getEnemyGateway = getEnemyGateway;
        this.updateWeaponGateway = updateWeaponGateway;
        this.updateEnemyGateway = updateEnemyGateway;
    }

    @Override
    public ResponseModel attack(RequestModel requestModel) {

        Weapon weapon = getWeapon(requestModel);
        Enemy enemy = getEnemy(requestModel);

        weapon.shoot();
        enemy.receiveDamage(weapon.getType().getDamage());

        updateWeaponGateway.updateWeapon(weapon);
        updateEnemyGateway.updateEnemy(enemy);

        return ResponseModel.builder()
                .success(true)
                .build();
    }

    private Weapon getWeapon(RequestModel requestModel) {
        return getWeaponGateway.getById(requestModel.getWeaponId())
                .orElseThrow(() -> new RuntimeException("Weapon not found"));
    }

    private Enemy getEnemy(RequestModel requestModel) {
        return getEnemyGateway.getById(requestModel.getEnemyId())
                .orElseThrow(() -> new RuntimeException("Enemy not found"));
    }

}
