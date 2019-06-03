package pihead.games.rpg.commandline.presenters;

import pihead.games.rpg.commandline.models.Model;
import pihead.games.rpg.commandline.models.PlayerTypeModel;
import pihead.games.rpg.commandline.pages.PlayerNamePage;

public class PlayerNamePresenter implements Presenter<PlayerNamePage, PlayerTypeModel> {

    private PlayerNamePage playerNamePage;

    public PlayerNamePresenter(PlayerNamePage playerNamePage) {
        this.playerNamePage = playerNamePage;
    }

    public PlayerNamePage getPage() {
        return playerNamePage;
    }

    @Override
    public PlayerTypeModel getModel(Model requestModel) {

        if (!(requestModel instanceof PlayerTypeModel)) {
            throw new RuntimeException();
        }

        return (PlayerTypeModel) requestModel;
    }


}
