package pihead.games.rpg.engine.domain;

import pihead.games.rpg.engine.domain.entities.backpack.Backpack;
import pihead.games.rpg.engine.domain.entities.characters.Player;
import pihead.games.rpg.engine.domain.entities.items.HealthItem;
import pihead.games.rpg.engine.domain.entities.items.Weapon;
import pihead.games.rpg.engine.gateway.GetPlayerGateway;

public class DefaultOpenBackpack implements OpenBackpack {

    private GetPlayerGateway getPlayerGateway;

    public DefaultOpenBackpack(GetPlayerGateway getPlayerGateway) {
        this.getPlayerGateway = getPlayerGateway;
    }

    @Override
    public ResponseModel openBackpack(RequestModel requestModel) {

        final Player player = getPlayer(requestModel);
        final Backpack backpack = player.getBackpack();

        ResponseModel.Builder builder = ResponseModel.builder()
                .backpackId(backpack.getId())
                .availableSlots(backpack.getAvailableSlots())
                .maxSlots(backpack.getMaxSlots());

        backpack.getItems().forEach(item -> {
            if (item instanceof HealthItem) {
                HealthItem healthItem = (HealthItem) item;
                builder.addHealthItem(item.getId(), item.getType().getName(), healthItem.getType().getHealthPower());
            } else {
                Weapon weapon = (Weapon) item;
                builder.addWeapon(item.getId(), item.getType().getName(), weapon.getType().getDamage());
            }
        });

        return builder.build();
    }

    private Player getPlayer(RequestModel requestModel) {
        return getPlayerGateway.getById(requestModel.getPlayerId())
                .orElseThrow(() -> new RuntimeException("Player not found"));
    }
}
