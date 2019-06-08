package pihead.games.rpg.commandline.models;

public class BackPageModel implements OptionModel {

    @Override
    public Object getId() {
        return -1;
    }

    @Override
    public String getName() {
        return "Get back";
    }
}
