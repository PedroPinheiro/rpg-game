package pihead.games.rpg.commandline.presenters;

import pihead.games.rpg.commandline.models.Model;
import pihead.games.rpg.commandline.views.View;

public abstract class Presenter {

    private View view;

    public Presenter(View view) {
        this.view = view;
    }

    public Model present(Model requestModel) {

        Model model = getModel(requestModel);

        return view.show(model);
    }

    protected abstract Model getModel(Model model);

}
