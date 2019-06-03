package pihead.games.rpg.commandline.models;

import java.util.List;

public class SelectOptionModel<T extends OptionModel> implements Model {

    private String label;
    private List<T> options;

    public SelectOptionModel(String label, List<T> options) {
        this.label = label;
        this.options = options;
    }

    public String getLabel() {
        return label;
    }

    public List<T> getOptions() {
        return options;
    }
}
