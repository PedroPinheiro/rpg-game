package pihead.games.rpg.commandline.pages.playerName;

import pihead.games.rpg.commandline.models.PlayerModel;
import pihead.games.rpg.commandline.models.PlayerTypeModel;

public class PlayerNamePresenter {

    public PlayerNamePresenter() {

    }

    public PlayerModel getModel(PlayerTypeModel playerTypeModel) {

        PlayerModel playerModel = new PlayerModel();
        playerModel.setPlayerTypeId(playerTypeModel.getId());
        playerModel.setPlayerTypeName(playerTypeModel.getName());

        return playerModel;
    }

}
