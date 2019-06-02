package pihead.games.rpg.commandline.models;

import java.util.List;

public class SelectOptionModel implements Model {

    private String label;
    private List<OptionModel> options;

    public SelectOptionModel(String label, List<OptionModel> options) {
        this.label = label;
        this.options = options;
    }

    public String getLabel() {
        return label;
    }

    public List<OptionModel> getOptions() {
        return options;
    }
}
