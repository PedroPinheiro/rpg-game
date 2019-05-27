package pihead.games.rpg.engine.templates;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public abstract class TemplateLoader<T> {

    private Map<String, Supplier<T>> fixtures = new HashMap<>();

    public TemplateLoader() {
        loadFixtures(fixtures);
    }

    protected abstract void loadFixtures(Map<String, Supplier<T>> fixtures);

    public T getFixture(String label) {
        return fixtures.get(label).get();
    }
}
