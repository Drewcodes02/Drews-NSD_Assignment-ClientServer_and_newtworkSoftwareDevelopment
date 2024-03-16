package Assigment.utils;

public enum ClientCommands {

    OPEN_COMMAND("o"),
    PUBLISH_COMMAND("p"),
    SUBSCRIBE_COMMAND("s"),
    UNSUBSCRIBE_COMMAND("u"),
    GET_COMMAND("g"),
    LOGOUT_COMMAND("l");

    private final String text;

    ClientCommands(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}
