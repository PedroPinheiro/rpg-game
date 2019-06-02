package pihead.games.rpg.commandline.models;

public interface OptionModel<T> extends Model {

    T getId();
    String getName();

}
