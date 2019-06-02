package pihead.games.rpg.commandline.views.pages;

import java.util.HashMap;
import java.util.Map;

public class Option {

    private int number;
    private String text;
    private Map<String, String> attributes = new HashMap<>();

    private Option() {

    }

    public int getNumber() {
        return number;
    }

    public String getText() {
        return text;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }


}
