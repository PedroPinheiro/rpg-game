package pihead.games.rpg.commandline.presenters;

import pihead.games.rpg.commandline.models.Model;
import pihead.games.rpg.commandline.views.pages.Page;

public interface Presenter<P extends Page, M extends Model> {

    P getPage();
    M getModel(Model requestModel);

}
