package pihead.games.rpg.commandline.pages;

import pihead.games.rpg.commandline.console.Console;
import pihead.games.rpg.commandline.console.TextColor;
import pihead.games.rpg.commandline.models.PlayerTypeModel;
import pihead.games.rpg.commandline.responses.GameResponse;
import pihead.games.rpg.commandline.responses.GameResponseShutdown;

// TODO: incomplete class
public class PlayerNamePage extends Page<PlayerTypeModel> {

    @Override
    protected GameResponse doShow(PlayerTypeModel model) {

        clearScreen();

        Console.print(TextColor.RED, model.getName());

        readLine();
//        selectCharacter()

        return new GameResponseShutdown();
    }

}
