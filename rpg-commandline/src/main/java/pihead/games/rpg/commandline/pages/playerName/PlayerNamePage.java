package pihead.games.rpg.commandline.pages.playerName;

import pihead.games.rpg.commandline.console.Console;
import pihead.games.rpg.commandline.models.*;
import pihead.games.rpg.commandline.pages.selectPlayer.SelectPlayerPage;
import pihead.games.rpg.commandline.responses.Intent;
import pihead.games.rpg.commandline.views.Page;

// TODO: incomplete class
public class PlayerNamePage implements Page {

    private PlayerNamePresenter playerNamePresenter;
    private PlayerModel model;

    public PlayerNamePage(PlayerNamePresenter playerNamePresenter) {
        this.playerNamePresenter = playerNamePresenter;
    }

    @Override
    public void setModel(Model model) {

        if (!(model instanceof PlayerTypeModel)) {
            throw new RuntimeException();
        }

        this.model = playerNamePresenter.getModel((PlayerTypeModel) model);
    }

    @Override
    public Intent show() {

        Console.print("Your name: ");
        String name = Console.readLine();

        this.model.setName(name);

        Console.println("\n\nYour name: " + name);
        Console.readLine();

        return new Intent(Intent.Action.EXIT_GAME);
    }

    private Intent buildIntent(OptionModel option) {

        if (option instanceof BackPageModel)
            return new Intent(Intent.Action.GO_BACK);
        else
            return new Intent(SelectPlayerPage.class, option);
    }

}
