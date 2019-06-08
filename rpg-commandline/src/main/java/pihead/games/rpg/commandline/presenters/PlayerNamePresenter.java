package pihead.games.rpg.commandline.presenters;

import pihead.games.rpg.commandline.models.Model;
import pihead.games.rpg.commandline.models.PlayerTypeModel;
import pihead.games.rpg.commandline.views.pages.PlayerNamePage;

public class PlayerNamePresenter {

    private PlayerNamePage playerNamePage;

    public PlayerNamePresenter(PlayerNamePage playerNamePage) {
        this.playerNamePage = playerNamePage;
    }

    public PlayerNamePage getPage() {
        return playerNamePage;
    }

    public PlayerTypeModel getModel(Model requestModel) {

        if (!(requestModel instanceof PlayerTypeModel)) {
            throw new RuntimeException();
        }

        return (PlayerTypeModel) requestModel;
    }


}
