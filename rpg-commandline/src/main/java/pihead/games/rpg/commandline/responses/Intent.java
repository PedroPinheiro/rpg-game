package pihead.games.rpg.commandline.responses;

import pihead.games.rpg.commandline.models.Model;

public final class Intent {

    private Action action;
    private Class<?> pageClazz;
    private Model model;

    public Intent(Class<?> pageClazz) {
        this.pageClazz = pageClazz;
    }

    public Intent(Class<?> pageClazz, Model model) {
        this.pageClazz = pageClazz;
        this.model = model;
    }

    public Intent(Action action) {
        this.action = action;
    }

    public Action getAction() {
        return action;
    }

    public Class<?> getPageClazz() {
        return pageClazz;
    }

    public Model getModel() {
        return model;
    }

    public enum Action {
        GO_BACK,
        SAVE_GAME,
        LOAD_GAME
    }
}
