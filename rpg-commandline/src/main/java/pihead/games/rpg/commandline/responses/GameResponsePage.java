package pihead.games.rpg.commandline.responses;

import pihead.games.rpg.commandline.models.Model;
import pihead.games.rpg.commandline.presenters.Presenter;

public final class GameResponsePage<T extends Presenter> implements GameResponse {

    private Class<T> presenterClazz;
    private Model model;

    public GameResponsePage(Class<T> presenterClazz, Model model) {
        this.presenterClazz = presenterClazz;
        this.model = model;
    }

    public Class<T> getPresenterClazz() {
        return presenterClazz;
    }

    public Model getModel() {
        return model;
    }
}
