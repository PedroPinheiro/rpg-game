package pihead.games.rpg.engine.domain;

import pihead.games.rpg.engine.domain.entities.characters.Enemy;
import pihead.games.rpg.engine.domain.entities.items.Weapon;
import pihead.games.rpg.engine.gateway.*;

public class DefaultAttackEnemy implements AttackEnemy {

    private GetWeaponGateway getWeaponGateway;
    private GetEnemyGateway getEnemyGateway;
    private UpdateWeaponGateway updateWeaponGateway;
    private UpdateEnemyGateway updateEnemyGateway;

    public DefaultAttackEnemy(GetWeaponGateway getWeaponGateway,
                              GetEnemyGateway getEnemyGateway,
                              UpdateWeaponGateway updateWeaponGateway,
                              UpdateEnemyGateway updateEnemyGateway) {
        this.getWeaponGateway = getWeaponGateway;
        this.getEnemyGateway = getEnemyGateway;
        this.updateWeaponGateway = updateWeaponGateway;
        this.updateEnemyGateway = updateEnemyGateway;
    }

    @Override
    public void attack(RequestModel requestModel) {

        Weapon weapon = getWeapon(requestModel);
        Enemy enemy = getEnemy(requestModel);

        weapon.shoot();
        enemy.receiveDamage(weapon.getType().getDamage());

        updateWeaponGateway.updateWeapon(weapon);
        updateEnemyGateway.updateEnemy(enemy);

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
