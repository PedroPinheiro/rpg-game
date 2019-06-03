package pihead.games.rpg.commandline.views;

import pihead.games.rpg.commandline.models.Model;

public interface View<RequestModel extends Model, ResponseModel extends Model> {

    ResponseModel show(RequestModel model);
}
