package pihead.games.rpg.commandline.views;

import pihead.games.rpg.commandline.models.Model;
import pihead.games.rpg.commandline.responses.Intent;

public interface Page {

    void setModel(Model model);

    Intent show();

}
