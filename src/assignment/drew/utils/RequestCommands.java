package Assigment.utils;

public enum RequestCommands {

    OPEN_COMMAND("OpenRequest"),
    PUBLISH_COMMAND("PublishRequest"),
    SUBSCRIBE_COMMAND("SubscribeRequest"),
    UNSUBSCRIBE_COMMAND("UnsubscribeRequest"),
    GET_COMMAND("GetRequest"),
    SEARCH_COMMAND("SearchRequest"),
    LOGOUT_COMMAND("LogoutRequest");

    private final String text;

    RequestCommands(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}
