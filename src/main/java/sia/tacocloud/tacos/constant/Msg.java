package sia.tacocloud.tacos.constant;

public enum Msg {

    USER_NOT_FOUND_PATTERN("User '%s' not found.");

    private String pattern;

    Msg(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
}
