package pihead.games.rpg.engine.domain;

import pihead.games.rpg.engine.domain.entities.characters.Enemy;
import pihead.games.rpg.engine.domain.entities.items.Weapon;
import pihead.games.rpg.engine.domain.entities.characters.Player;

public class DefaultAttackEnemy implements AttackEnemy {

    @Override
    public ResponseModel attack(RequestModel requestModel) {

        Player player = requestModel.getPlayer();
        Enemy enemy = requestModel.getEnemy();
        Weapon weapon = requestModel.getWeapon();

        weapon.shoot();
        if (enemy.canAttack()) {
            player.receiveDamage(enemy.getType().getDamage());
        }
        enemy.receiveDamage(weapon.getType().getDamage());

        return mountResponse(player, weapon, enemy);
    }

    private ResponseModel mountResponse(Player player, Weapon weapon, Enemy enemy) {

        return ResponseModel.builder()
                .player(player)
                .enemy(enemy)
                .weapon(weapon)
                .build();
    }
}
