package pihead.games.rpg.engine.domain;

import pihead.games.rpg.engine.domain.entities.backpack.Backpack;
import pihead.games.rpg.engine.domain.entities.backpack.exception.BackpackIsFullException;
import pihead.games.rpg.engine.domain.entities.backpack.exception.ItemWillNotFitInBackpackException;
import pihead.games.rpg.engine.domain.entities.characters.Player;
import pihead.games.rpg.engine.domain.entities.items.Item;
import pihead.games.rpg.engine.gateway.GetItemGateway;
import pihead.games.rpg.engine.gateway.GetPlayerGateway;
import pihead.games.rpg.engine.gateway.UpdateBackpackGateway;

public class DefaultPutItemOnBackpack implements PutItemOnBackpack {

    private GetPlayerGateway getPlayerGateway;
    private GetItemGateway getItemGateway;
    private UpdateBackpackGateway updateBackpackGateway;

    public DefaultPutItemOnBackpack(GetPlayerGateway getPlayerGateway,
                                    GetItemGateway getItemGateway,
                                    UpdateBackpackGateway updateBackpackGateway) {
        this.getPlayerGateway = getPlayerGateway;
        this.getItemGateway = getItemGateway;
        this.updateBackpackGateway = updateBackpackGateway;
    }

    @Override
    public void putItem(RequestModel requestModel) throws BackpackIsFullException, ItemWillNotFitInBackpackException {

        final Player player = getPlayer(requestModel);
        final Backpack backpack = player.getBackpack();
        final Item item = getItem(requestModel);

        backpack.addItem(item);

        updateBackpackGateway.updateBackpack(backpack);
    }

    private Player getPlayer(RequestModel requestModel) {
        return getPlayerGateway.getById(requestModel.getPlayerId())
                .orElseThrow(() -> new RuntimeException("Player not found"));
    }

    private Item getItem(RequestModel requestModel) {
        return getItemGateway.getById(requestModel.getItemId())
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }
}
