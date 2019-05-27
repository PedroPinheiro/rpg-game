package pihead.games.rpg.engine.templates;

import pihead.games.rpg.engine.domain.entities.Game;

import java.util.Map;
import java.util.function.Supplier;

public class GameTemplateLoader extends TemplateLoader<Game> {

    @Override
    protected void loadFixtures(Map<String, Supplier<Game>> fixtures) {

    }
}