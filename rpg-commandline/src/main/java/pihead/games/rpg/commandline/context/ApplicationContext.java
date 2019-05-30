package pihead.games.rpg.commandline.context;

import java.util.Map;

public class ApplicationContext {

    private static Map<Class<?>, Object> beans;

    public static void init(Object init) {

    }

    public static <T> T get(Class<T> clazz) {
        return (T) beans.get(clazz);
    }


}
